package cars.menu;

import application.TCPClient;
import cars.Package;
import cars.Request;
import cars.Response;
import cars.io.InputOutput;

import java.util.ArrayList;

import static cars.menu.Utils.MESSAGES;

public class RentCompanyItem implements Item {
    private InputOutput inputOutput;
    private TCPClient tcpClient;
    private String nameItem;

    RentCompanyItem(TCPClient tcpClient, InputOutput inputOutput, String nameItem) {
        this.tcpClient = tcpClient;
        this.inputOutput = inputOutput;
        this.nameItem=nameItem;
    }

    @Override
    public String displayedName() {
        return nameItem;
    }

    @Override
    public void perform() {
        Response response = tcpClient.exchangeData(new Request(nameItem));

        Package receivedPackage = response.getPackage();

        for (int i = 0; i < receivedPackage.paramsClassesSize(); i++) {
            receivedPackage.getCompositeParams().add(new ArrayList<>());
            for (int j = 0; j < receivedPackage.getCompositeParamsClasses().get(i).size(); j++) {
                Object data = inputOutput.input(
                        MESSAGES.get(receivedPackage.getCompositeParamsNames().get(i).get(j)),
                        receivedPackage.getCompositeParamsClasses().get(i).get(j));
                if (data == null) return;
                receivedPackage.getCompositeParams().get(i).add(data);
            }
        }
        Request request = new Request(nameItem, receivedPackage);
        response = tcpClient.exchangeData(request);
        inputOutput.outputLine(response);
    }
}
