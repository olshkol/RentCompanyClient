package cars.menu;

import cars.io.InputOutput;

public class SubMenuItem implements Item {
    private String name;
    private Item[] menuItems;
    private InputOutput inputOutput;

    public SubMenuItem(String name, Item[] menuItems, InputOutput inputOutput) {
        this.name = name;
        this.menuItems = menuItems;
        this.inputOutput = inputOutput;
    }

    @Override
    public String displayedName() {
        return name;
    }

    @Override
    public void perform() {
        Menu menu = new Menu(inputOutput, menuItems);
        menu.runMenu();
    }
}
