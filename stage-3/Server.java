import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    // if 2 clients are being handled and 3 th connects it goes into queue and wait until thread free up
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("server running with thread pool...");
        int clientId = 0;

        while (true){
            Socket clientSocket=  serverSocket.accept();
            clientId++;
            System.out.println("client" + clientId + "connected " + "active threads" + Thread.activeCount());

            threadPool.submit(new ClientHandler(clientSocket,clientId));
        }
    }
}
