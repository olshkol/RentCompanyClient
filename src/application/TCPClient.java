package application;

import cars.Request;
import cars.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient {
    public Response exchangeData(Request request){
        Response response = null;
        try (
                Socket socket = new Socket(ConfigClient.HOST, ConfigClient.PORT);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            oos.writeObject(request);
            response = (Response) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}