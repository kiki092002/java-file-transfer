import java.io.*;
import java.net.*;
import java.util.UUID;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private int clientId;

    public ClientHandler(Socket socket, int id){
        this.clientSocket = socket;
        this.clientId = id;
    }
    @Override
    public void run(){
        try{
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());

            int fileNameLength = in.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            in.readFully(fileNameBytes);
            String fileName = new String(fileNameBytes);

            long fileSize = in.readLong();

            System.out.println("client:"+clientId+" sending: " +fileName +"("+fileSize+" bytes)");
            
            String uniqueName = UUID.randomUUID().toString() + "_" + fileName;
            FileOutputStream fileOut = new FileOutputStream(uniqueName);
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalReceived = 0;

            while((bytesRead = in.read(buffer))!=-1){
                fileOut.write(buffer,0,bytesRead);
                totalReceived += bytesRead;
                int progress = (int)((totalReceived*100)/fileSize);
                System.out.print("\rClient:" + clientId + "-" + fileName + " progress: " + progress + "% " + totalReceived + "/" + fileSize + " bytes");

            }
            System.out.println("\nfile received from client"+ clientId);
            fileOut.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("error:"+e.getMessage());
        }
    }
} 
    

