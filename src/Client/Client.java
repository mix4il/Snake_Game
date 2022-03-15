package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket clientSocket;
    ObjectOutputStream objOutputStream = null;
    ObjectInputStream objInputStream = null;
    public Client() throws IOException {
        clientSocket = new Socket("127.0.0.1", 8000);
    }

    public void sendRequest(int weight, int height, int score) throws IOException {
        //ObjectOutputStream objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        if(objInputStream == null){
            objOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        Request request = new Request(weight, height, score);
        System.out.println(request);
        objOutputStream.writeObject(request);
    }

    public Response getResponse() throws IOException, ClassNotFoundException {
        //ObjectInputStream objInputStream = new ObjectInputStream(clientSocket.getInputStream());
        if(objInputStream == null){
            objInputStream = new ObjectInputStream(clientSocket.getInputStream());
        }
        Response response = (Response) objInputStream.readObject();
        System.out.println(response.getAppleX() + " ответ X");
        System.out.println(response.getAppleY() + " ответ Y");
        return response;
    }

}
