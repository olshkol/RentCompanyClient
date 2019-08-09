package cars.menu;

import application.TCPClient;
import cars.dto.Request;
import cars.dto.Response;
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
        Response response = tcpClient.exchangeData(new Request(nameItem, null));

        ArrayList<Class> paramsClasses = response.getParamsClasses();
        ArrayList<ArrayList<Class>> compositeParamsClasses = response.getCompositeParamsClasses();
        ArrayList<ArrayList<String>> compositeParamsNames = response.getCompositeParamsNames();
        ArrayList<ArrayList<Object>> compositeParams = new ArrayList<>();

        for (int i = 0; i < paramsClasses.size(); i++) {
            compositeParams.add(new ArrayList<>());
            for (int j = 0; j < compositeParamsClasses.get(i).size(); j++) {
                Object data = inputOutput.input(MESSAGES.get(compositeParamsNames.get(i).get(j)), compositeParamsClasses.get(i).get(j));
                if (data == null) return;
                compositeParams.get(i).add(data);
            }
        }
        response.setCompositeParamsClasses(null);
        response.setParamsClasses(null);
        Request request = new Request(nameItem, paramsClasses, compositeParamsClasses, compositeParams);
        request.setBody(new Boolean(true)); //some
        response = tcpClient.exchangeData(request);
        inputOutput.outputLine(response);
    }
}
