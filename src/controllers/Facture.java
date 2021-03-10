package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import model.ClientMagasin;
import model.Produit;

import java.util.HashMap;

public class Facture {
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;

    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label mtCommande;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;


}
