package SocketHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private PrintWriter output;
    private BufferedReader input;
    private static ArrayList<ClientHandler> clients;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            pool.execute(clientHandler);
        }


    }

    public void stop() throws IOException {
        input.close();
        output.close();

        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(2022);
    }
}




