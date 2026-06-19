import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int clientId;

    public ClientHandler(Socket socket, int id){
        this.clientSocket = socket;
        this.clientId = id;
    }

    public void run() { 
        System.out.println("Client" + clientId+ "handler starter");
        try{
            InputStream in = clientSocket.getInputStream();
            FileOutputStream fileOut = new FileOutputStream("received_from_client" + clientId+".txt");
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = in.read(buffer))!=-1){
                fileOut.write(buffer,0,bytesRead);
            }
            System.out.println("file received from client"+ clientId);
            fileOut.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("client" + clientId + "error:"+ e.getMessage());
        }
    }
    
}
