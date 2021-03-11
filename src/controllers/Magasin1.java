package controllers;

import java.io.IOException;
import java.util.HashMap;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.ClientMagasin;
import model.Produit;

public class Magasin1 {
    private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
    private ClientMagasin client;
    @FXML
    private AnchorPane root1;

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
            ajouterUn();
    }

    @FXML
    void Select() throws IOException {
        String page = magasinChoix.getSelectionModel().getSelectedItem().toString();
        if (page.equals("Magasin2")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/" + page + ".fxml"));
            Magasin2 magasin2;
            AnchorPane root = null;
            magasin2 = new Magasin2(this.panier, this.client);
            fxmlLoader.setController(magasin2);
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            Stage appStage = (Stage) btnPanier.getScene().getWindow();
            appStage.setScene(scene);
            appStage.setTitle(page);
            appStage.show();
        }
    }


    public Magasin1(HashMap<Produit, Integer> panier, ClientMagasin client){
        this.panier = panier;
        this.client = client;
    }


    public void initialize(){
        ObservableList<String> list = FXCollections.observableArrayList("Magasin1", "Magasin2");
        magasinChoix.setItems(list);
        magasinChoix.setValue("Magasin1");
    }

    private void ajouterUn() throws IOException {
        //int num1 = Integer.parseInt(qte1.getText());
        //String num2 = Integer.toString(num1);
        String num2 ="Success";
        qte1.setText(num2);
    }

    @FXML
    void toPanier() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Panier.fxml"));
        Panier panier = new Panier(this.panier, this.client);
        AnchorPane root = null;
        fxmlLoader.setController(panier);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = (Stage) btnPanier.getScene().getWindow();
        appStage.setScene(scene);
        appStage.setTitle("Panier");
        appStage.show();
    }
}
