import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("server is waiting for a connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!"+ clientSocket.getInetAddress());

        InputStream inputStream = clientSocket.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

        byte[] buffer = new byte[1024];
        int bytesRead; 
        while ((bytesRead = inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        System.out.println("file received");
        fileOutputStream.close();
        clientSocket.close();
        serverSocket.close();


        
    }
}