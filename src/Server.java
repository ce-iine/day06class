import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// to run this, open 2 terminals and run the 2 files on the different terminals and see them communitcate 

public class Server {

    public static final int PORT = 3000;

    public static void main(String[] args) throws Exception {
        //create server and bind to port 3000
        System.out.printf("listening on port: %s\n", PORT);
        ServerSocket server = new ServerSocket(PORT);

        //wait for client connection to arrive, we are now blocked
        while (true){
            System.out.println("Waiting for client connection");
            Socket client = server.accept(); 

            InputStream is = client.getInputStream();// start talking 
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr); 
            // why need create filereader/InputStreamReader then use bufferedreader to read instead of just using bufferedreader to read
            // filereader(implements reader interface), InputStreamReader implements reader interface 
            // bufferedreader needs a reader, can start readLine(). it handles reader(any reader), not file directly 
            // input stream come from client come from socket which is a connection to the network  

            String line = br.readLine();
            System.out.printf("CLIENT REQUEST: %s\n",line);

            line = line.toUpperCase();

            OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            // add a \n (CR) carraige return to flush the line 
            bw.write(line + "\n");
            
            bw.flush();
            is.close();
            os.close();

            System.out.printf("Got a client connection: %d\n", client.getLocalPort());
        }

        // server.close();

    }

}
