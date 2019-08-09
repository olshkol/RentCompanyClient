package cars.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputOutput extends InputOutput {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String inputString(String prompt) {
        System.out.println(prompt);
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void output(Object object) {
        System.out.println(object);
    }
}