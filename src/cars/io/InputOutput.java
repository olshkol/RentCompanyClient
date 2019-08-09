package cars.io;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class InputOutput {
    private static final Map<Type, Method> PARSERS = new HashMap<>();
    static {
        try {
            PARSERS.put(Integer.class, Integer.class.getMethod("parseInt", String.class));
            PARSERS.put(int.class, Integer.class.getMethod("parseInt", String.class));
            PARSERS.put(Long.class, Long.class.getMethod("parseLong", String.class));
            PARSERS.put(long.class, Long.class.getMethod("parseLong", String.class));
            PARSERS.put(Short.class, Short.class.getMethod("parseShort", String.class));
            PARSERS.put(short.class, Short.class.getMethod("parseShort", String.class));
            PARSERS.put(Double.class, Double.class.getMethod("parseDouble", String.class));
            PARSERS.put(double.class, Double.class.getMethod("parseDouble", String.class));
            PARSERS.put(Float.class, Float.class.getMethod("parseFloat", String.class));
            PARSERS.put(float.class, Float.class.getMethod("parseFloat", String.class));
            PARSERS.put(Boolean.class, Boolean.class.getMethod("parseBoolean", String.class));
            PARSERS.put(boolean.class, Boolean.class.getMethod("parseBoolean", String.class));
            PARSERS.put(Character.class, InputOutput.class.getDeclaredMethod("parseCharacter", String.class));
            PARSERS.put(char.class, InputOutput.class.getDeclaredMethod("parseCharacter", String.class));
            PARSERS.put(String.class, InputOutput.class.getDeclaredMethod("parseString", String.class));
            PARSERS.put(LocalDate.class, InputOutput.class.getDeclaredMethod("parseDate", String.class));
        } catch (NoSuchMethodException ignored) { }
    }

    private  static char parseCharacter(String line){
        return line.charAt(0);
    }

    private  static String parseString(String line){
        return line;
    }

    private  static LocalDate parseDate(String line){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(line, formatter);
    }

    public abstract String inputString(String prompt);

    public abstract void output(Object object);

    public<T> T input(String prompt, Class clazz) {
        return inputObject(prompt, "it's not a number", s -> {
            try {
                return (T) InputOutput.PARSERS.get(clazz).invoke(null, s);
            } catch (NumberFormatException | InvocationTargetException | IllegalAccessException e) {
                return null;
            }
        });
    }

    public<T extends Comparable> T input(String prompt, T min, T max, Class clazz) {
        return inputObject(prompt, "it's not a number in range " + min + "-" + max, s -> {
            try {
                T num = (T) InputOutput.PARSERS.get(clazz).invoke(null, s);
                return (num.compareTo(min) >= 0 && num.compareTo(max) <= 0) ? num : null;
            } catch (NumberFormatException | InvocationTargetException | IllegalAccessException e) {
                return null;
            }
        });
    }


    public Integer inputInteger(String prompt) {
        return inputObject(prompt, "it's not a number", s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public Integer inputInteger(String prompt, Integer min, Integer max) {
        return inputObject(prompt, String.format("it's not a number in range %d-%d", min, max), s ->
        {
            try {
                int num = Integer.parseInt(s);
                return (num >= min && num <= max) ? num : null;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public Long inputLong(String prompt) {
        return inputObject(prompt, "it's not a number", s -> {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public Double inputDouble(String prompt) {
        return inputObject(prompt, "it's not a number", s -> {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public Double inputDouble(String prompt, Double min, Double max) {
        return inputObject(prompt, String.format("it's not a double in range %f-%f", min, max), s -> {
            try {
                double num = Double.parseDouble(s);
                return (num >= min && num <= max) ? num : null;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    public String inputString(String prompt, List<String> options) {
        return inputObject(prompt, "not in options", s -> options.contains(s) ? s : null);
    }

    public LocalDate inputDate(String prompt, String format) {
        return inputObject(prompt, "date is not valid", s -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
                return null;
            }
        });
    }

    public <R> R inputObject(String prompt, String errorPrompt, Function<String, R> mapper) {
        while (true) {
            String text = inputString(prompt);
            if (text == null)
                return null;
            R res = mapper.apply(text);
            if (res != null)
                return res;
            outputLine(errorPrompt);
        }
    }

    public void outputLine(Object object) {
        output(object);
    }
}