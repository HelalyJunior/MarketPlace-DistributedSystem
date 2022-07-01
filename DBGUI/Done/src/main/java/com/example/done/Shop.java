package com.example.done;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Shop implements Initializable
{

    private Stage stage;

    private Scene scene;

    private Parent root;

    public Product_Card chosenCard = new Product_Card();

    @FXML
    private TextField searchText;

    @FXML
    private Label ProductName_lbl;

    @FXML
    private Label ProductPrice_lbl;

    @FXML
    private ImageView Product_img;

    @FXML
    private VBox chosenItemCard;
    @FXML
    private GridPane grid_pane;

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private TextField quantity;

    public static List<Product_Card> cards = new ArrayList<>();

    private MyListener myListener;

    public String username;

    private void setChosenItemCard(Product_Card p1)
    {
        quantity.setText("0");
        chosenCard=p1;
        p1.setImgSrc("DBGUI/Done/src/main/resources/com/example/done/shopping-basket.png");
        ProductName_lbl.setText(p1.getName());
        ProductPrice_lbl.setText(""+p1.getPrice());
//        chosenItemCard.setStyle("-fx-background-color: #" + p1.getColor() + ";\n" +
//                "    -fx-background-radius: 30;");
        //Product_img.setImage(new Image(getClass().getResourceAsStream(p1.getImgSrc())));


    }

    public static void getData(String s )
    {
        cards.clear();
        String[] sb = s.split(",");
        List<Product_Card> cc = new ArrayList<>();
        Product_Card card;
        for (int i = 0 ; i <  sb.length; i++)
        {
            String[] st = sb[i].split("_");
            card = new Product_Card();
            card.setId(Integer.valueOf(st[0]));
            card.setName(st[1]);
            card.setPrice(Integer.valueOf(st[2]));
            card.setStock(Integer.valueOf(st[3]));
            card.setImgSrc("DBGUI/Done/src/main/resources/com/example/done/shopping-basket.png");
            card.setColor("6A7324");
            cc.add(card);
        }
        cards.addAll(cc);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChosenItemCard(cards.get(0));
        if (cards.size() > 0)
        {

            myListener = new MyListener() {
                @Override
                public void onClick(Product_Card product_card) {

                    setChosenItemCard(product_card);
                }
            };
        }
        int row = 1;
        int col = 0;
        try {


            for (int i = 0; i < cards.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Product_Controller product_controller = fxmlLoader.getController();
                product_controller.setData(cards.get(i),myListener);
                if (col == 3)
                {
                    col = 0;
                    row++;
                }

                grid_pane.add(anchorPane,col++,row);
                grid_pane.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_pane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_pane.setMaxWidth(Region.USE_PREF_SIZE);
                grid_pane.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_pane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_pane.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));



            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }




    }

    public void back(ActionEvent event) throws IOException
    {
        cards.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-Auth.fxml"));
        root = loader.load();
        ClientAuth clientController = loader.getController();
        clientController.username=username;
        clientController.welcome_lbl.setText("Welcome "+ clientController.username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cart_pressed(ActionEvent event) throws IOException {
        HelloApplication.client.output.println("getCart_"+username);

        String s = HelloApplication.client.input.readLine();
        if( s.length()>0)
        {
            CartView.FetchingData(s);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CartView.fxml"));
            root = loader.load();
            CartView clientController = loader.getController();
            clientController.username=username;
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            AlertBox.display("Error" , "Your Cart is Empty!");
        }
    }

    public void add_pressed(ActionEvent event)
    {
        if(Integer.valueOf(quantity.getText())==0)
        {
            AlertBox.display("Error","Please Enter a valid quantity ");
            return;
        }
        HelloApplication.client.output.println("add_"+username+"_"+chosenCard.getId()+"_"+quantity.getText());
    }

    public void decr_pressed(ActionEvent event)
    {
        if(Integer.valueOf(quantity.getText())>0)
        {
            quantity.setText("" + Integer.valueOf(Integer.valueOf(quantity.getText())-1));
        }
        else
        {
            AlertBox.display("Error","Invalid Quantity !");

        }
    }

    public void incr_pressed(ActionEvent event)
    {
        if(Integer.valueOf(quantity.getText())<chosenCard.getStock())
        {
            quantity.setText("" + Integer.valueOf(Integer.valueOf(quantity.getText())+1));
        }
        else
        {
            AlertBox.display("Error","Quantity exceeded Stock ! ");

        }
    }

    public void search_pressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
        HelloApplication.client.output.println("search_"+searchText.getText());
        String s = HelloApplication.client.input.readLine();
        if(s.equals("NO SUCH ITEM"))
        {
            AlertBox.display("Error","Invalid search");
            return;
        }
        getData(s);
        root = loader.load();
        Shop clientController = loader.getController();
        clientController.username=username;
        searchText.setText("");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
