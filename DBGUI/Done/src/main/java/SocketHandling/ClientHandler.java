package SocketHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    public ClientHandler(Socket client) throws IOException {
        this.client=client;
        input=new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        output=new PrintWriter(client.getOutputStream(),true);
    }
    @Override
    public void run() {
        try {
            while(true){
                String request= input.readLine();

                output.println("im ur commander says no "+request);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
