package SocketHandling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ArrayList<ClientHandler> clients;

    private static ExecutorService pool= Executors.newFixedThreadPool(10);
  public static void main(String[] args) {
      clients=new ArrayList<>();
      try {
          ServerSocket listener = new ServerSocket(2022);
          System.out.println("waiting for clients");
          while(true) {


              Socket client = listener.accept();
              System.out.println("connected to " + client.getInetAddress().getHostAddress());
//              RequestHandler requestHandler=new RequestHandler("register",client);
//              pool.execute(requestHandler);
//              pool.execute((Runnable) requestHandler.getTask(client));

          }
//          PrintWriter output =new PrintWriter(client.getOutputStream(),true);
//          BufferedReader input =new BufferedReader(new InputStreamReader(client.getInputStream()));
          /* getinputstream: to get bytes from socket
          * inputstream reader to take bystes and decode them into char stream
          * wrap them with bufferreader to provide efficent reads*/
//          while(true){
//              String request= input.readLine();
//              output.println("im ur commander says no "+request);
//          }

      }
      catch (Exception e){
          e.printStackTrace();
      }


  }
}
