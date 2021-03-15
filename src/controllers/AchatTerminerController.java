package controllers;


import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import serveurs.magasin.interfaces.MagasinInterface;
//

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class AchatTerminerController {
    private double achatTotal;
    private ClientMagasin client;
    private Magasin lastMagasin;
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    public static ArrayList<Panier> panierClient = new ArrayList<Panier>();

    @FXML
    private Label totalAchat;

    AchatTerminerController(ClientMagasin client, double achatTotal, Magasin lastMagasin) {
        this.client = client;
        this.achatTotal = achatTotal;
        this.lastMagasin = lastMagasin;


    }

    public void initialize() throws RemoteException, SQLException {
        totalAchat.setText(Double.toString(this.achatTotal));
    }


    @FXML
    void toMagasin() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Magasin.fxml"));
        MagasinController magasinController;
        AnchorPane root = null;
        magasinController = new MagasinController(this.client, this.lastMagasin, this.panierClient);
        fxmlLoader.setController(magasinController);
        try{
            root = fxmlLoader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) totalAchat.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle(this.lastMagasin.getNomMagasin());
        magasinController.controlerPassing(appStage);
        Tools.initMagasin(this.lastMagasin.getIdMagasin(), appStage, this.serveurMagasin, this.panierClient);
        appStage.show();
    }

}
