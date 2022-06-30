package com.example.done;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class Product_Controller {
    @FXML
    private ImageView img;

    @FXML
    private Label name_lbl;

    @FXML
    private Label price_lbl;


    private Product_Card product_card;

    private MyListener myListener;

    public  void setData(Product_Card product_card, MyListener myListener)
    {

        this.product_card = product_card;
        this.myListener = myListener;
        name_lbl.setText(product_card.getName());
        price_lbl.setText("" + product_card.getPrice());
        //System.out.println(product_card.getImgSrc());
        //Image image = new Image(getClass().getResourceAsStream(product_card.getImgSrc()));
        //img.setImage(image);
        //System.out.println("Item Added");

    }
    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.onClick(product_card);
    }
}
