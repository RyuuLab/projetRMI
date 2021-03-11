package controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.ClientMagasin;
import model.Magasin;
import model.Produit;
import serveurs.magasin.interfaces.MagasinInterface;

public class ConnexionController {
	private MagasinInterface serveurMagasin = ConnectToServeur.magasin();
	private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
	private ClientMagasin client;
	private Magasin magasin;

	@FXML
	private Label erreur;
	@FXML
	private ComboBox magasinChoix;
	@FXML
	private TextField identifiant;
	@FXML
	private PasswordField mdp;
	@FXML
	private Button connexion;


	public void initialize() throws RemoteException, SQLException {
		ObservableList<Magasin> magasins = FXCollections.observableArrayList();
		magasins.addAll(serveurMagasin.getMagasins());
		StringConverter<Magasin> converter = new StringConverter<Magasin>() {
			@Override
			public String toString(Magasin object) {
				return object.getNomMagasin();
			}

			@Override
			public Magasin fromString(String string) {
				return null;
			}
		};
		magasinChoix.setConverter(converter);
		magasinChoix.setItems(magasins);
	}

	@FXML
	void Select(ActionEvent event) {
		String s = magasinChoix.getSelectionModel().getSelectedItem().toString();
	}

	@FXML
	void UserConnexion() throws IOException{
		try {
			checkLogin();
		}
		catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void checkLogin() throws IOException, SQLException {
		this.magasin = (Magasin) magasinChoix.getSelectionModel().getSelectedItem();
		this.client = serveurMagasin.toConnectMagasin(identifiant.getText().toString(),mdp.getText().toString());
		if(!magasinChoix.getSelectionModel().isEmpty()){
			if(this.client != null) {
				erreur.setText("Succès");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Magasin.fxml"));
				MagasinController magasinController;
				AnchorPane root = null;
				magasinController = new MagasinController(this.client, this.magasin, this.panier);
				fxmlLoader.setController(magasinController);
				try{
					root = fxmlLoader.load();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				Stage appStage = (Stage) connexion.getScene().getWindow();
				appStage.setScene(scene);
				appStage.setTitle(this.magasin.getNomMagasin());
				magasinController.controlerPassing(appStage);
				Tools.initMagasin(this.magasin.getIdMagasin(), appStage, this.serveurMagasin);
				appStage.show();
			} else {
				erreur.setText("Identifiant ou mdp incorrect");
			}
			if (identifiant.getText().isEmpty() || mdp.getText().isEmpty()) {
				erreur.setText("Veuillez remplir tous les champs");
			}
		} else {
			erreur.setText("Choisissez un magasin");
		}

	}
}
