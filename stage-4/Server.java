import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final AtomicInteger activeClient = new AtomicInteger(0); // thread-safe counter
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    // if 2 clients are being handled and 3 th connects it goes into queue and wait until thread free up
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("server running with thread pool size 2...");
        int clientId = 0;

        while (true){
            Socket clientSocket=  serverSocket.accept();
            clientId++;
            activeClient.incrementAndGet();
            System.out.println("client" + clientId + "connected " + "active clients:" + activeClient.get());
            final int id = clientId;
            threadPool.submit(()->{
                try{
                    new ClientHandler(clientSocket,id).run();
                } finally{
                    activeClient.decrementAndGet();
                    System.out.println("client" + id + "done " + "active clients:" + activeClient.get());
                }
            });
            
        }
    }
}
