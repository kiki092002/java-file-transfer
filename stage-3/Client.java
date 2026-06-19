import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket("localhost",9090);
        System.out.println("connected to server!");

        OutputStream out = socket.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream("myfile.txt");
        byte[] buffer = new byte[1024];
        //byte[] buffer = new byte[1];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer))!=-1){
            //out.write(buffer);
            out.write(buffer,0,bytesRead);
        }
        System.out.println("file sent");
        fileInputStream.close();
        out.close();
        socket.close();
    }
}
