import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server running , waiting for clients...");
        int clientId = 0 ;

        while (true){
            Socket clientSocket = serverSocket.accept();
            clientId++;
            System.out.println("Client"+clientId+"connected from " + clientSocket.getInetAddress());
            ClientHandler clientHandler = new ClientHandler(clientSocket,clientId);
            // every client = new thread created
            Thread thread = new Thread(clientHandler); // this will cause machine cash because of too many threads
            thread.start();
        }
    }
}
