package cars.menu;

import cars.io.InputOutput;

public class Menu {
    private InputOutput inputOutput;
    private Item[] items;

    public Menu(InputOutput inputOutput, Item[] items) {
        this.inputOutput = inputOutput;
        this.items = items;
    }

    public void runMenu(){
        while (true){
            for (int i = 0; i < items.length; i++) {
                inputOutput.outputLine(String.format("%d. %s", i+1, items[i].displayedName()));
            }
            Integer answer = inputOutput.inputInteger("Your choose: ", 1, items.length);
            if (answer == null)
                continue;
            Item item = items[answer - 1];
            try {
                item.perform();
            } catch (Exception e) {
                inputOutput.outputLine(e.getMessage());
            }
            if (item.isExit())
                return;
        }
    }
}