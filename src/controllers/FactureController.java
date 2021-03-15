package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ClientMagasin;
import model.Magasin;
import model.Panier;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class FactureController {
    private ArrayList<Panier> panierClient = new ArrayList<Panier>();
    private ArrayList<Produit> produits = new ArrayList<Produit>();
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    private ClientMagasin client;
    private Magasin lastMagasin;


    FactureController(ClientMagasin client, ArrayList<Panier> panierClient, Magasin lastMagasin) {
        this.panierClient = panierClient;
        this.client = client;
        this.lastMagasin = lastMagasin;
    }

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mtCommande;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;

    public void initialize() throws RemoteException, SQLException {
        nom.setText(client.getNom());
        prenom.setText(client.getPrenom());
        produitClient();
        mtCommande.setText(Double.toString(this.serveurMagasin.calculPanier(this.produits)));
    }

    @FXML
    void returnPanier() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Panier.fxml"));
        PanierController panier = new PanierController(this.client, this.panierClient, this.lastMagasin);
        AnchorPane root = null;
        fxmlLoader.setController(panier);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnValider.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Panier");
        appStage.show();
    }

    @FXML
    void toBanque() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Banque.fxml"));
        BanqueController banque = new BanqueController(this.client, this.panierClient, this.lastMagasin);
        AnchorPane root = null;
        fxmlLoader.setController(banque);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnValider.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Banque");
        appStage.show();
    }

    private void produitClient() {
        this.panierClient.forEach(panier -> {
            this.produits.add(new Produit(
                    panier.getIdProduit(),
                    panier.getIdMagasin(),
                    panier.getImageProduit(),
                    panier.getNom(),
                    panier.getPrix(),
                    panier.getQteClient()));
        });
    }
}
