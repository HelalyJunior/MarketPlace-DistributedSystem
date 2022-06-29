package SocketHandling;

import com.example.done.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;


public class ClientHandler implements Runnable {
    private Socket client;
    private  BufferedReader input;
    private  PrintWriter output;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }


    public  void login (String[] request) {
        String password = Api.getPassword("client",request[1]);
        if ( password.equals(request[2])){
            this.output.println("true");
        }
        else{
            this.output.println("false");
        }
    }
    public  void register (String[] request)  {

        try {
            //String[] request=line.split("_");
            boolean flag = Api.Register(request[1],request[2]);
            if ( flag){
                this.output.println("true");
            }

            else{
                this.output.println("false");
            }

//                    //System.out.println(request[1]);
//                    if ("hello server".equals(request[1])) {
//                        output.println("hello client");
//                    } else if ("exit".equals(line)) {
//                        this.stop();
//                        System.out.println("after stop");
//
//                    } else {
//                        output.println("unrecognised greeting");
//                    }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void viewInfo () {
    }
    public static void purchase () {
    }
    public static void search () {
    }
    public static void deposit () {
    }
    public static void cart (String[] request){
        int id=Integer.parseInt(request[2]);
        Api.Remove(request[1],id);
    }
    public static void returnInfo(String[] request){
        List<Map<String, String>> maps = Api.returnAccountInfo(request[1]);
        maps.stream().flatMap(m->m.entrySet().stream()).forEach(e-> System.out.println(e.getKey()+":"+e.getValue()));

    }
    public void buyItem(String[] request){
        int id=Integer.parseInt(request[2]);
        int q=Integer.parseInt(request[3]);
        boolean flag= Api.buy(request[1],id,q);
        if (flag){
            this.output.println("true");
        }
        else {
            this.output.println("false");
        }
    }
    @Override
    public void run() {

        try {
            while (true) {
//                while(!input.ready()){}
                String line = input.readLine();
                System.out.println(line);
                String[] request = line.split("_");
                System.out.println(request[1]);
                if ("register".equalsIgnoreCase(request[0])) {
                    System.out.println("beforereg");
                    register(request);
                }
                else if ("login".equalsIgnoreCase(request[0])){
                    login(request);
                }
                else if ("remove".equalsIgnoreCase(request[0])){
                    cart(request);
                }
                else if ("returnInfo".equalsIgnoreCase(request[0])){
                    returnInfo(request);
                }
                else if ("buy".equalsIgnoreCase(request[0])){
                    buyItem(request);
                }

//                if ("hello server".equals(line)) {
//                    output.println("hello client");
//                } else if ("exit".equals(line)) {
//                    this.stop();
//                    System.out.println("after stop");
//
//                } else {
//                    output.println("unrecognised greeting");
//                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop() throws IOException {
        input.close();
        output.close();
    }
}

