package cars.menu;

import java.time.LocalDate;
import java.util.HashMap;

import static application.RentCompanyClientApp.inputOutput;
import static application.RentCompanyClientApp.tcpClient;
import static cars.routing.RoutingPath.*;

public class Utils {
    public static final int MIN_BIRTH_YEAR_DRIVER = 1940;
    public static final int MAX_BIRTH_YEAR_DRIVER = LocalDate.now().getYear();
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final Item[] CLERK_ITEMS = {
            new RentCompanyItem(tcpClient, inputOutput, ADD_DRIVER),
            new RentCompanyItem(tcpClient, inputOutput, GET_MODEL_CARS),
            new RentCompanyItem(tcpClient, inputOutput, RENT_CAR),
            new RentCompanyItem(tcpClient, inputOutput, RETURN_CAR),
            new ExitItem()
    };

    private static final Item[] DRIVER_ITEMS = {
            new RentCompanyItem(tcpClient, inputOutput, GET_CAR_DRIVERS),
            new RentCompanyItem(tcpClient, inputOutput, GET_CAR),
            new RentCompanyItem(tcpClient, inputOutput, GET_DRIVER),
            new ExitItem()
    };

    private static final Item[] MANAGER_ITEMS = {
            new RentCompanyItem(tcpClient, inputOutput, ADD_CAR),
            new RentCompanyItem(tcpClient, inputOutput, ADD_MODEL),
            new RentCompanyItem(tcpClient, inputOutput, REMOVE_CAR),
            new RentCompanyItem(tcpClient, inputOutput, REMOVE_MODEL),
            new ExitItem()
    };

    private static final Item[] STATIST_ITEMS = {
            new RentCompanyItem(tcpClient, inputOutput, GET_MOST_ACTIVE_DRIVERS),
            new RentCompanyItem(tcpClient, inputOutput, GET_MOST_POPULAR_MODELS),
            new RentCompanyItem(tcpClient, inputOutput, GET_MOST_PROFITABLE_MODELS),
            new ExitItem()
    };

    private static final Item[] TECHNICIAN_ITEMS = {
            new RentCompanyItem(tcpClient, inputOutput, GET_RECORDS),
            new ExitItem()
    };

    public static final Item[] SUB_MENUS = {
            new SubMenuItem("Clerk", CLERK_ITEMS, inputOutput),
            new SubMenuItem("Driver", DRIVER_ITEMS, inputOutput),
            new SubMenuItem("Manager", MANAGER_ITEMS, inputOutput),
            new SubMenuItem("Statist", STATIST_ITEMS, inputOutput),
            new SubMenuItem("Technician", TECHNICIAN_ITEMS, inputOutput),
            new ExitItem()
    };

    public static final HashMap<String, String> MESSAGES = new HashMap<>();
    static {
        MESSAGES.put("licenseId", "Enter driver license id: ");
        MESSAGES.put("name", "Enter driver name: ");
        MESSAGES.put("birthYear", "Enter birth year");
        MESSAGES.put("phone", "Enter telephone number: ");
        MESSAGES.put("regNumber", "Enter register car number: ");
        MESSAGES.put("rentDays", "Enter count days rent: ");
        MESSAGES.put("rentDate", "Enter rent date: ");
        MESSAGES.put("damages", "Enter damages, %: ");
        MESSAGES.put("tankPercent", "Enter tank, %: ");
        MESSAGES.put("returnDate", "Enter return date, %: ");
        MESSAGES.put("color", "Enter color: ");
        MESSAGES.put("modelName", "Enter model name: ");
        MESSAGES.put("gasTank", "Enter gas tank: ");
        MESSAGES.put("company", "Enter company: ");
        MESSAGES.put("country", "Enter country: ");
        MESSAGES.put("priceDay", "Enter price per rent day no delay, %: ");
        MESSAGES.put("fromDate", "Enter start date: ");
        MESSAGES.put("toDate", "Enter end date: ");
        MESSAGES.put("ageFrom", "Enter min age driver: ");
        MESSAGES.put("ageTo", "Enter max age driver: ");
    }
}