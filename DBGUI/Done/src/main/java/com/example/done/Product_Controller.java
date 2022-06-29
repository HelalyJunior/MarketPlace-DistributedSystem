package com.example.done;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Product_Controller {
    @FXML
    private ImageView img;

    @FXML
    private Label name_lbl;

    @FXML
    private Label price_lbl;

    private Product_Card product_card;

    public  void setData(Product_Card product_card)
    {
        this.product_card = product_card;
        name_lbl.setText(product_card.getName());
        price_lbl.setText("" + product_card.getPrice());
        //System.out.println(product_card.getImgSrc());
        //Image image = new Image(getClass().getResourceAsStream(product_card.getImgSrc()));

        //img.setImage(image);
        System.out.println("Item Added");

    }


}
