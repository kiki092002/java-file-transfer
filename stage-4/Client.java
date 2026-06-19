import java.io.*;
import java.net.*;
public class Client {

    public static void main(String[] args) throws IOException{
         //File file = new File("myfile.txt");
         File bigFile = new File("bigfile.bin");
         Socket socket  =new Socket("localhost",9090);

         DataOutputStream out = new DataOutputStream(socket.getOutputStream());

         String fileName = bigFile.getName();
         byte[] fileNameBytes = fileName.getBytes();
         out.writeInt(fileNameBytes.length);
         out.write(fileNameBytes);

         out.writeLong(bigFile.length());

         FileInputStream fileIn = new FileInputStream(bigFile);
         byte[] buffer = new byte[4096];
         int bytesRead; 
         while ((bytesRead = fileIn.read(buffer))!=-1){
             out.write(buffer,0,bytesRead);
         }
         System.out.println("file sent" + fileName);
         fileIn.close();
         socket.close();
         
    }
    
}
