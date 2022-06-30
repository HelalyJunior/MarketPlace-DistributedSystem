package SocketHandling;

import com.example.done.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;



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

    public void loginAdmin(String[] request)
    {
        String password = Api.getPassword("admin",request[1]);
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
            boolean flag = Api.Register(request);
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

    public static void add (String[] request)
    {
        Api.AddToCart(request[1],Integer.valueOf(request[2]),Integer.valueOf(request[3]));
    }

    public void getCart(String[] request)
    {
        StringBuffer sb = new StringBuffer();
        List<Map<String, String>> maps = Api.getCart(request[1]);
        if(maps==null)
        {
            this.output.println("false");
        }
        else
        {
            for(int i=0;i<maps.size();i++)
            {
                StringBuffer s = new StringBuffer();
                s.append(maps.get(i).get("ProductName")+"_");
                s.append(maps.get(i).get("Price")+"_");
                if(i==maps.size()-1)
                {
                    s.append(maps.get(i).get("amount"));
                }
                else
                {
                    s.append(maps.get(i).get("amount")+",");
                }
                sb.append(s);
            }
        }
        this.output.println(sb);
    }

    public void report()
    {
        StringBuffer sb = new StringBuffer();
        List<Map<String, String>> maps = Api.getAllItems();
        for(int i=0;i<maps.size();i++)
        {
            StringBuffer s = new StringBuffer();
            s.append(maps.get(i).get("Id")+"_");
            s.append(maps.get(i).get("ProductName")+"_");
            s.append(maps.get(i).get("Price")+"_");
            s.append(maps.get(i).get("Stock")+"_");
            if(i==maps.size()-1)
            {
                s.append(maps.get(i).get("Sold"));
            }
            else
            {
                s.append(maps.get(i).get("Sold")+",");
            }
            sb.append(s);
        }
        System.out.println(sb);
        this.output.println(sb);
    }

    public void purchase(String[] request)
    {
        boolean flag = Api.buy(request[1],Integer.valueOf(request[2]));
        if(flag)
        {
            this.output.println("true");
        }
        else
        {
            this.output.println("false");
        }
    }

    public  void deposit (String[] request)
    {
        boolean b = Api.addBalance(request[1],Integer.valueOf(request[2]));
        if(b){
            this.output.println("true");
        }
        else
        {
            this.output.println("false");
        }
    }
    public static void cart (String[] request){
        int id=Integer.parseInt(request[2]);
        Api.Remove(request[1],id);
    }
    public  void returnInfo(String[] request){
        List<Map<String, String>> maps = Api.returnAccountInfo(request[1]);
        StringBuffer s = new StringBuffer();
        maps.stream().flatMap(m->m.entrySet().stream()).forEach(e-> s.append(e.getValue()+"_"));
        this.output.println(s);
    }
    public void search(String[] request){
        ArrayList<String> items = Api.Search(request[1]);
        StringBuffer s = new StringBuffer();
        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                s.append(items.get(i) + "_");
            }
        }
        // in case of no item found !!
        else {
            s.append("NO SUCH ITEM");
        }
        this.output.println(s);
    }
//    public void buyItem(String[] request){
//        int id=Integer.parseInt(request[2]);
//        int q=Integer.parseInt(request[3]);
//        boolean flag= Api.buy(request[1],id,q);
//        if (flag){
//            this.output.println("true");
//        }
//        else {
//            this.output.println("false");
//        }
//    }
    @Override
    public void run() {

        try {
            while (true) {
//                while(!input.ready()){}
                String line = input.readLine();
                String[] request = line.split("_");
                if ("register".equalsIgnoreCase(request[0])) {
                    register(request);
                }

                if ("report".equalsIgnoreCase(request[0])) {
                    report();
                }

                else if ("loginAdmin".equalsIgnoreCase(request[0])){
                    loginAdmin(request);
                }

                else if ("login".equalsIgnoreCase(request[0])){
                    login(request);
                }
                else if ("remove".equalsIgnoreCase(request[0])){
                    cart(request);
                }

                else if("getCart".equalsIgnoreCase(request[0]))
                {
                    getCart(request);
                }

                else if ("deposit".equalsIgnoreCase(request[0])){
                    deposit(request);
                }

                else if ("shop".equalsIgnoreCase(request[0])){
                    report();
                }

                else if ("add".equalsIgnoreCase(request[0])){
                    add(request);
                }

                else if ("returnInfo".equalsIgnoreCase(request[0])){
                    returnInfo(request);
                }
                else if ("buy".equalsIgnoreCase(request[0])){
                    purchase(request);
                }
                else if ("search".equalsIgnoreCase(request[0])){
                    search(request);
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

