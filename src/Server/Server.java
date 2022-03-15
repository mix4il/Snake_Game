package Server;



import Client.Request;
import Client.Response;
import core.Position;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server {


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectInputStream objInputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("«Служба сервера запущена ...»");
            socket = serverSocket.accept();
            while(true){
                if(objInputStream == null){
                    objInputStream = new ObjectInputStream(socket.getInputStream());
                }
                Request request = (Request) objInputStream.readObject();
                System.out.println(request);
                Position newApple = generatePosition(request.getWeight(), request.getHeight());
                String labelRecord = getRecord(request.getScore());
                Response response = new Response(newApple.getX(), newApple.getY(), labelRecord);
                if(objOutputStream == null){
                    objOutputStream = new ObjectOutputStream(socket.getOutputStream());
                }
                objOutputStream.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static Position generatePosition(int weight, int height){
        int appleX;
        int appleY;

        appleX = new Random().nextInt((weight - 50) / 25) * 25 + 25;
        appleY = new Random().nextInt((height - 100)/ 25) * 25 + 50;

        return new Position(appleX, appleY);
    }

    private static String getRecord(int score) throws IOException {
        File file = new File("/Users/mihailbaskakov/IdeaProjects/Snake_Game/src/Server/record.txt");
        FileReader fr = new FileReader(file);
        FileWriter fw =  null;
        Scanner sc  = new Scanner(fr);

        int record = 0;
        if(sc.hasNext()){
            record = sc.nextInt();
            System.out.println(score);
        }
        if(score > record){
            fw = new FileWriter(file);
            fw.write(Integer.toString(score));
            fw.flush();
            fw.close();
            return "Поздравляю! Вы поставили новый рекорд сервера!";
        }
        fr.close();
        sc.close();
        return "Рекорд сервера:" + record;
    }
}
