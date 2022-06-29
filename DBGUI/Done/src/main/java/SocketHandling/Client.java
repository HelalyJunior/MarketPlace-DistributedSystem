package SocketHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    public  static Socket socket;
    public  static BufferedReader input;
    public  PrintWriter output;
    public Client() throws IOException {
        socket=new Socket("localhost",2022);
        input =new BufferedReader(new InputStreamReader(socket.getInputStream()));
         output=new PrintWriter(socket.getOutputStream(),true);
    }
    public static void main(String args[]) throws IOException {
         socket=new Socket("localhost",2022);
         input =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader terminal=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output=new PrintWriter(socket.getOutputStream(),true);
        while (true) {
            String line = terminal.readLine();
            if(line.equals("exit")) {
            output.println("exit");
            break;
            }

            output.println(line);
            String serverResponse = input.readLine();
            System.out.println(serverResponse);
        }
        socket.close();

    }
}
