import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        // connect to server
        System.out.printf("attempting to connect to 127.0.0.1 on port %d\n", Server.PORT);
        Socket socket = new Socket("127.0.0.1", Server.PORT); // ip address 127.0.0.1. is reserved for localhost

        System.out.println("connected to server");

        Console cons = System.console();
        String line = cons.readLine("Please enter a message: ");

        //what ever server does, client does reverse - read and send back 

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(line);
        bw.newLine(); // can bw.append() also
        bw.flush();

        InputStream is = socket.getInputStream();// start talking
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        line = br.readLine();
        System.out.printf("SERVER RESPONSE: %s\n", line);

        os.close();
        is.close();

        socket.close();
        System.out.println("Socket closed");
    }

}
