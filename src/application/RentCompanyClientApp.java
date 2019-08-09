package application;

import cars.io.ConsoleInputOutput;
import cars.io.InputOutput;
import cars.menu.Menu;

import static cars.menu.Utils.SUB_MENUS;

public class RentCompanyClientApp {
    public static InputOutput inputOutput = new ConsoleInputOutput();
    public static TCPClient tcpClient = new TCPClient();

    public static void main(String[] args) {
        Menu menu = new Menu(inputOutput, SUB_MENUS);
        menu.runMenu();
    }
}