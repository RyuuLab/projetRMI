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


public class PanierController {
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    public static ArrayList<Panier> panierClient = new ArrayList<Panier>();
    public static ObservableList<Panier> observableList = FXCollections.observableArrayList();
    private ClientMagasin client;
    private Magasin lastMagasin;

    @FXML
    private TableView<Panier> tableViewPanier;
    @FXML
    private TableColumn<Panier, String> nom;
    @FXML
    private TableColumn<Panier, Integer> qte;
    @FXML
    private TableColumn<Panier, Double> prix;
    @FXML
    private TableColumn<Panier, Void> edit;
    @FXML
    private Label sousTotal;


    PanierController(ClientMagasin client, ArrayList<Panier> panierClient, Magasin lastMagasin) {
        this.panierClient = panierClient;
        this.client = client;
        this.lastMagasin = lastMagasin;
        observableList.addListener((ListChangeListener<Panier>) change -> {
            while (change.next()) {
                updatePanier();
            }
        });
    }

    public static void updateObservableList() {
        observableList.clear();
        observableList.addAll(panierClient);
    }

    public void initialize() throws RemoteException, SQLException {
        final ObservableList<Panier> data = FXCollections.observableArrayList(panierClient);
        nom.setCellValueFactory(new PropertyValueFactory<Panier, String>("nom"));
        qte.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("choiceBox"));
        prix.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
        tableViewPanier.setItems(data);
        addButtonToTable();
        setDefaultValueChoiceBox();
        updatePanier();
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
        Stage appStage = (Stage) tableViewPanier.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle(this.lastMagasin.getNomMagasin());
        magasinController.controlerPassing(appStage);
        Tools.initMagasin(this.lastMagasin.getIdMagasin(), appStage, this.serveurMagasin, this.panierClient);
        appStage.show();
    }


    @FXML
    void toFacture() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Facture.fxml"));
        FactureController facture = new FactureController(this.client, this.panierClient, this.lastMagasin);
        AnchorPane root = null;
        fxmlLoader.setController(facture);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) sousTotal.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Facture");
        appStage.show();
    }




    private void addButtonToTable() {
        Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>> cellFactory = new Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>>() {
            @Override
            public TableCell<Panier, Void> call(final TableColumn<Panier, Void> param) {
                final TableCell<Panier, Void> cell = new TableCell<Panier, Void>() {

                    private final Button btn = new Button("supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Panier produit = getTableView().getItems().get(getIndex());
                            getTableView().getItems().remove(produit);
                            Panier p = panierClient.stream()
                                    .filter(panier -> produit.getIdProduit() == (panier.getIdProduit()))
                                    .findAny()
                                    .orElse(null);
                            panierClient.remove(p);
                            updatePanier();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        edit.setCellFactory(cellFactory);
    }

    private void setDefaultValueChoiceBox() {
        panierClient.forEach((panier) -> {
            panier.setDefaultValueChoiceBox();
        });
    }

    private void updatePanier() {
        double prixTotal = this.panierClient.stream().mapToDouble(panier -> panier.getPrix()*panier.getQteClient()).sum();
        int quantity = this.panierClient.stream().mapToInt(Panier::getQteClient).sum();
        sousTotal.setText("Sous-total ("+quantity+" articles): "+prixTotal+" euro(s)");
    }

    
}
