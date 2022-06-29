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
    public String username;
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

    private List<Product_Card> cards = new ArrayList<>();

    private List<Product_Card> getData()
    {
        List<Product_Card> cc = new ArrayList<>();
        Product_Card card;
        for (int i = 0 ; i < 20 ; i++)
        {
            card = new Product_Card();
            card.setName("Tea");
            card.setPrice(20);
            card.setImgSrc("DBGUI/Done/src/DB/eggs_110803370_1000.jpg   ");
            card.setColor("6A7324");
            cc.add(card);

        }
        return cc;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cards.addAll(getData());
        int row = 1;
        int col = 0;
        try {


            for (int i = 0; i < cards.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Product.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                Product_Controller product_controller = fxmlLoader.getController();
                product_controller.setData(cards.get(i));
                if (col == 3)
                {
                    col = 0;
                    row++;
                }

                grid_pane.add(anchorPane,col++,row);
                grid_pane.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_pane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_pane.setMaxWidth(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane,new Insets(10));



            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }




    }

    public void back(ActionEvent event) throws IOException {
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
}
