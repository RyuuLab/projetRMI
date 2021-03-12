package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ClientMagasin;
import model.Magasin;
import model.Produit;
import model.Panier;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
//
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

import javax.xml.crypto.Data;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;


public class PanierController {
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;

    @FXML
    private TableView<HashMap<Produit, Integer>> tableViewPanier;

    private final ObservableList<HashMap<Produit, Integer>> tvObservableList = FXCollections.observableArrayList();

//    @FXML
//    private TableColumn<Produit, String> nom;
//    @FXML
//    private TableColumn<Produit, Integer> qte;
//    @FXML
//    private TableColumn<Produit, Double> prix;
//    @FXML
//    private TableColumn<Produit, Button> edit;


    PanierController(ClientMagasin client, HashMap<Produit, Integer> panier) {
        this.panier = panier;
        this.client = client;
    }

    public void initialize() throws RemoteException, SQLException {
        //createPanier();
        //loadData();
        start();
    }

    public void start() {
        createPanier();
        tableViewPanier.setItems(tvObservableList);

        TableColumn<Produit, String> nom = new TableColumn<>("Nom");
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Produit, Integer> qte = new TableColumn<>("Qte");
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));

        TableColumn<Produit, Double> prix = new TableColumn<>("Prix");
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableViewPanier.getColumns().addAll(nom,qte,prix);
        addButtonToTable();
    }

    public void createPanier(){
        this.panier.put(new Produit(5,1,"test","nameTest",10,46),2);
        this.panier.put(new Produit(6,1,"test2","nameTest2",11,46),4);
        this.panier.put(new Produit(7,1,"test3","nameTest3",12,46),6);
    }

    private void addButtonToTable() {
        TableColumn<Data, Void> colBtn = new TableColumn("Edit");
        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                final TableCell<Data, Void> cell = new TableCell<Data, Void>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Data data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
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
        colBtn.setCellFactory(cellFactory);
        tableViewPanier.getColumns().add(colBtn);
    }





//
//    public void createPanier(){
//        this.panier.put(new Produit(5,1,"test","nameTest",10,46),2);
//        this.panier.put(new Produit(6,1,"test2","nameTest2",11,46),4);
//        this.panier.put(new Produit(7,1,"test3","nameTest3",12,46),6);
//    }
//
//    private void initTableview(){
//        initColonne();
//    }
//
//    public void initColonne(){
//        //int idProduit;
//        //	int idMagasin;
//        //	String imageProduit;
//        //	String nom;
//        //	double prix;
//        //	int quantite;
//        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
//        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
//
//        editableCols();
//    }
//
//    private void editableCols() {
//        nom.setCellFactory(TextFieldTableCell.forTableColumn());
//        nom.setOnEditCommit(e->{
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
//        });
//
//        qte.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        qte.setOnEditCommit(e->{
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setQuantite(e.getNewValue());
//        });
//
//        prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//        prix.setOnEditCommit(e->{
//            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrix(e.getNewValue());
//        });
//
//        tableViewPanier.setEditable(true);
//    }
//
//    private void loadData(){
//        ObservableList<HashMap<Produit, Integer>> data_table = FXCollections.observableArrayList();
//        data_table.addAll(this.panier);
//        tableViewPanier.setItems(data_table);
//    }











}
