package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import serveurs.magasin.controllers.ConnectToBanque;
import serveurs.magasin.interfaces.MagasinInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanqueController {
    private ArrayList<Panier> panierClient = new ArrayList<Panier>();
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    private ArrayList<Produit> produits = new ArrayList<Produit>();
    private ClientMagasin client;
    private Magasin lastMagasin;
    private Banque banque;


    @FXML
    private TextField nCarte;
    @FXML
    private PasswordField cvv;
    @FXML
    private Label error;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;

    BanqueController(ClientMagasin client, ArrayList<Panier> panierClient, Magasin lastMagasin) {
        this.panierClient = panierClient;
        this.client = client;
        this.lastMagasin = lastMagasin;
    }

    public void initialize() throws RemoteException, SQLException {
        produitClient();
    }

    @FXML
    void returnFacture() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Facture.fxml"));
        FactureController factureController = new FactureController(this.client, this.panierClient, this.lastMagasin);
        AnchorPane root = null;
        fxmlLoader.setController(factureController);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnValider.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Facture");
        appStage.show();
    }


    @FXML
    void sendBanque() throws IOException, SQLException {
        if(nCarte.getText() != "" && cvv.getText() !="") {
            long numCarte = Long.parseLong(nCarte.getText());
            int numCvv = Integer.parseInt(cvv.getText());
            ClientBanque clientBanque = ConnectToServeur.magasin().sendCoordBanque(numCarte, numCvv);
            if(clientBanque == null) {
                error.setText("Coordonnees bancaires incorrectes.");
            } else {
                error.setText("");
                this.banque = ConnectToBanque.banque().getBanque(clientBanque.getIdBanque());
                double totalAchat = ConnectToServeur.magasin().calculPanier(produits);
                PopupBanque.display(clientBanque, totalAchat, banque);
                boolean achatTerminer = ConnectToServeur.magasin().getAchatTermine();
                System.out.println(achatTerminer);
                if (achatTerminer) {
                    panierClient.forEach(panier -> {
                        try {
                            serveurMagasin.removeQuantityProduit(panier.getIdProduit(), panier.getQteClient());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                toAchatTerminer(totalAchat);
            }
            }
        } else {
            error.setText("Les champs sont obligatoires");
        }
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

    private void toAchatTerminer(double totalAchat) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/AchatTerminer.fxml"));
        AchatTerminerController achatTerminerController = new AchatTerminerController(this.client, totalAchat, this.lastMagasin);
        AnchorPane root = null;
        fxmlLoader.setController(achatTerminerController);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnAnnuler.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Achat termine");
        appStage.show();
    }
}
