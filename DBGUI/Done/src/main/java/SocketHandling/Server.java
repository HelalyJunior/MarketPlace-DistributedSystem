package SocketHandling;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private ServerSocket serverSocket;
    private PrintWriter output;
    private BufferedReader input;
    private static ArrayList<ClientHandler> clients;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    public static int number_connected = 0;

    @FXML
    private Label number_connectedd;


    public void start2(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            number_connected++;
            number_connectedd.setText(""+number_connected);
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
        server.start2(2022);
    }
    
}




