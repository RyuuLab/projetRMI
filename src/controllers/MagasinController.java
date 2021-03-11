package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.ClientMagasin;
import model.Magasin;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;

public class MagasinController {
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;
    private Magasin magasin;
    private Stage stage;

    public MagasinController(ClientMagasin client,Magasin magasin, HashMap<Produit, Integer> panier){
        this.panier = panier;
        this.client = client;
        this.magasin = magasin;
    }

    @FXML
    private Button btnPanier;
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6;
    @FXML
    private Label label1, label2, label3, label4, label5, label6;
    @FXML
    private Label labelPrix1, labelPrix2, labelPrix3, labelPrix4, labelPrix5, labelPrix6;
    @FXML
    private Label labelQte1, labelQte2, labelQte3, labelQte4, labelQte5, labelQte6;
    @FXML
    private Label qtePanier;
    @FXML
    private Button plus1, plus2, plus3, plus4, plus5, plus6;
    @FXML
    private Button moins1, moins2, moins3, moins4, moins5, moins6;
    @FXML
    private Button addPanier1, addPanier2, addPanier3, addPanier4, addPanier5, addPanier6;
    @FXML
    private TextField qte1, qte2, qte3, qte4, qte5, qte6;
    @FXML
    private ComboBox magasinChoix;

    @FXML
    void PlusUn() throws IOException{
    }

    @FXML
    void Select() throws IOException, SQLException {
        this.magasin = (Magasin) magasinChoix.getSelectionModel().getSelectedItem();
        Tools.initMagasin(this.magasin.getIdMagasin(), this.stage, this.serveurMagasin);
    }

    public void initialize() throws IOException, SQLException {
        ObservableList<Magasin> magasins = FXCollections.observableArrayList();
        magasins.addAll(serveurMagasin.getMagasins());
        StringConverter<model.Magasin> converter = new StringConverter<model.Magasin>() {
            @Override
            public String toString(model.Magasin object) {
                return object.getNomMagasin();
            }
            @Override
            public model.Magasin fromString(String string) {
                return null;
            }
        };
        magasinChoix.setConverter(converter);
        magasinChoix.setValue(this.magasin);
        magasinChoix.setItems(magasins);
    }


    @FXML
    void toPanier() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Panier.fxml"));
        PanierController panier = new PanierController(this.client, this.panier);
        AnchorPane root = null;
        fxmlLoader.setController(panier);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnPanier.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Panier");
        appStage.show();
    }

    public void controlerPassing(Stage stage){
        this.stage = stage;
    }


}
