package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.ClientMagasin;
import model.Produit;

import java.util.HashMap;

public class PanierController {
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;


    PanierController(ClientMagasin client, HashMap<Produit, Integer> panier) {
        this.panier = panier;
        this.client = client;
    }

    @FXML
    private TableView tableViewPanier;


}
