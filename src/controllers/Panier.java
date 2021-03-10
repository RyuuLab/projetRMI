package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.ClientMagasin;
import model.Produit;

import java.util.HashMap;

public class Panier {
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;

    @FXML
    private TableView tableViewPanier;


}
