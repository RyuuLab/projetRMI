package controllers;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.util.StringConverter;
import model.ClientMagasin;
import model.Magasin;
import model.Panier;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;

public class MagasinController {
    private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
    private ArrayList<Panier> panierClient = new ArrayList<Panier>();
    private List magasinsId = new ArrayList();
    private ClientMagasin client;
    private Magasin magasin;
    private Stage stage;
    private ObservableList<Produit> produits;
    private ObservableList<Button> buttonsPlus;
    private ObservableList<Button> buttonsMoins;
    private ObservableList<Button> buttonsAddPanier;
    private ObservableList<TextField> qte;
    private ObservableList<Label> labelQte;
    private int qtePanier = 0;

    public MagasinController(ClientMagasin client,Magasin magasin, ArrayList<Panier> panierClient){
        this.client = client;
        this.magasin = magasin;
        this.panierClient = panierClient;
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
    private Button plus1, plus2, plus3, plus4, plus5, plus6;
    @FXML
    private Button moins1, moins2, moins3, moins4, moins5, moins6;
    @FXML
    private Button addPanier1, addPanier2, addPanier3, addPanier4, addPanier5, addPanier6;
    @FXML
    private TextField qte1, qte2, qte3, qte4, qte5, qte6;
    @FXML
    private ComboBox magasinChoix;

    public void initialize() throws IOException, SQLException {
        buttonsPlus = FXCollections.observableArrayList(plus1, plus2, plus3, plus4, plus5, plus6);
        buttonsMoins = FXCollections.observableArrayList(moins1, moins2, moins3, moins4, moins5, moins6);
        buttonsAddPanier = FXCollections.observableArrayList(addPanier1, addPanier2, addPanier3, addPanier4, addPanier5, addPanier6);
        qte = FXCollections.observableArrayList(qte1, qte2, qte3, qte4, qte5, qte6);
        labelQte = FXCollections.observableArrayList(labelQte1, labelQte2, labelQte3, labelQte4, labelQte5, labelQte6);
        produits = FXCollections.observableArrayList(serveurMagasin.getProduitsByMagasin(this.magasin.getIdMagasin()));
        magasinsId.add(this.magasin.getIdMagasin());
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
        quantityPanier();
    }

    @FXML
    void Select() throws IOException, SQLException {
        this.magasin = (Magasin) magasinChoix.getSelectionModel().getSelectedItem();
        produits = FXCollections.observableArrayList(serveurMagasin.getProduitsByMagasin(this.magasin.getIdMagasin()));
        Tools.initMagasin(this.magasin.getIdMagasin(), this.stage, this.serveurMagasin, this.panierClient);
        resteLabelQte();
        Stage appStage = (Stage) btnPanier.getScene().getWindow();
        appStage.setTitle(this.magasin.getNomMagasin());
    }

    @FXML
    public void IncrementButtonPressed(ActionEvent event) {
        Button button = (Button) event.getSource();
        int id = buttonsPlus.indexOf(button);
        incrementDecrement(0, id);
    }

    @FXML
    public void DecrementButtonPressed(ActionEvent event) {
        Button button = (Button) event.getSource();
        int id = buttonsMoins.indexOf(button);
        incrementDecrement(1, id);
    }


    @FXML
    public void addPanier(ActionEvent event) throws RemoteException, SQLException {
        Button button = (Button) event.getSource();
        int id = buttonsAddPanier.indexOf(button);
        TextField textFieldQte = this.qte.get(id);
        int qte = Integer.parseInt(textFieldQte.getText());
        incrementPanier(id);
        int idProduit = produits.get(id).getIdProduit();
        int maxQte = getMaxQteProduit(idProduit).get();
        verifBtn(id, maxQte, 0);
        initPanier(idProduit, qte);
        quantityPanier();
    }



    @FXML
    void toPanier() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Panier.fxml"));
        PanierController panier = new PanierController(this.client, this.panierClient, this.magasin);
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

    private void incrementDecrement(int incrementDecrement, int id) {
        TextField labelQte = this.qte.get(id);
        int idProduit = produits.get(id).getIdProduit();
        int maxQte = getMaxQteProduit(idProduit).get();
        int oldValue = Integer.parseInt(labelQte.getText());
        if (incrementDecrement == 0 && oldValue+1 <= maxQte) {
            labelQte.setText(String.valueOf(oldValue+1));
            verifBtn(id, maxQte, oldValue+1);
        } else if (incrementDecrement == 1 && oldValue-1 >= 0){
            labelQte.setText(String.valueOf(oldValue-1));
            verifBtn(id, maxQte, oldValue-1);
        }
    }

    private void incrementPanier(int id) {
        TextField textFieldQte = this.qte.get(id);
        Produit produit = produits.get(id);
        int qtePanier = this.qtePanier;
        int qte = Integer.parseInt(textFieldQte.getText());
        int maxQte = produit.getQuantite();
        Panier p = panierClient.stream()
                .filter(panier -> produit.getIdProduit() == (panier.getIdProduit()))
                .findAny()
                .orElse(null);
        if(p != null) {
            maxQte = p.getQuantite();
        }
        if(qte <= maxQte && qte > 0) {
            this.labelQte.get(id).setText(String.valueOf(maxQte-qte));
            this.qtePanier = qtePanier+qte;
            textFieldQte.setText("0");
        }
    }
    private void verifBtn(int id, int maxQte, int qte) {
        Button btn = this.buttonsAddPanier.get(id);
        Button btnPlus = this.buttonsPlus.get(id);
        Button btnMoins = this.buttonsMoins.get(id);
        if(qte == 0){
            btn.setDisable(true);
        } else {
            btn.setDisable(false);
        }
        if(qte == maxQte){
            btnPlus.setDisable(true);
        } else {
            btnPlus.setDisable(false);
        }
        if(qte == 0){
            btnMoins.setDisable(true);
        } else {
            btnMoins.setDisable(false);
        }
    }

    private void initPanier(int id, int qte) {
        produits.forEach((produit) -> {
            if(produit.getIdProduit() == id) {
                Panier p = panierClient.stream()
                        .filter(panier -> id == panier.getIdProduit())
                        .findAny()
                        .orElse(null);
                if(p == null) {
                    this.panierClient.add(new Panier(
                            produit.getIdProduit(),
                            produit.getIdMagasin(),
                            produit.getImageProduit(),
                            produit.getNom(),
                            produit.getPrix(),
                            produit.getQuantite()-qte,
                            qte,
                            produit.getQuantite()));
                } else {
                    addPanierCLient(produit, qte, p);
                }
            }
        });
    }
    private void addPanierCLient(Produit produit, int qte, Panier p) {
        int qteClient = panierClient.get(panierClient.indexOf(p)).getQteClient();
        panierClient.get(panierClient.indexOf(p)).setQteClient(qte+qteClient);
        panierClient.get(panierClient.indexOf(p)).setQuantite(produit.getQuantite() - (qte+qteClient));
    }

    private int quantityPanier() {
        int quantity = this.panierClient.stream().mapToInt(Panier::getQteClient).sum();
        this.btnPanier.setText("Panier : " + String.valueOf(quantity));
        return quantity;
    }

    private AtomicInteger getMaxQteProduit(int id) {
        AtomicInteger qte = new AtomicInteger();
        produits.forEach((produit) -> {
            if(produit.getIdProduit() == id) {
                Panier p = panierClient.stream()
                        .filter(panier -> id == panier.getIdProduit())
                        .findAny()
                        .orElse(null);
                if(p == null) {
                    qte.set(produit.getQuantite());
                } else {
                    qte.set(p.getQuantite());
                }
            }
        });
        return qte;
    }

    private void resteLabelQte() {
        qte.forEach((qte) -> {
            qte.setText("0");
        });
    }
}
