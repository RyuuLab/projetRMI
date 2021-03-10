package controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import model.ClientMagasin;
import model.Produit;

public class Connexion {
	public void LogIn() {
		
	}

	private HashMap<Produit, Integer> panier = new HashMap<Produit, Integer>();
	private ClientMagasin client;

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

	public void initialize(){
		ObservableList<String> list = FXCollections.observableArrayList("Magasin1", "Magasin2");
		magasinChoix.setItems(list);
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
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkLogin() throws IOException {
		Main m = new  Main();
		if(!magasinChoix.getSelectionModel().isEmpty()){
			String s = magasinChoix.getSelectionModel().getSelectedItem().toString();
			if(identifiant.getText().toString().equals("abc") && mdp.getText().toString().equals("123")) {
				erreur.setText("Succès");
				//m.changeScene("../vues/"+s+".fxml");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(s+".fxml"));
				Magasin1 magasin1;
				Magasin2 magasin2;
				if(s.equals("Magasin1")){
					magasin1 = new Magasin1(this.panier,this.client);
					fxmlLoader.setController(magasin1);
				}
				else{
					magasin2 = new Magasin2(this.panier,this.client);
					fxmlLoader.setController(magasin2);
				}
				try {
					root = fxmlLoader.load();
				} catch (IOException e) {

					e.printStackTrace();
				}

				stage.setScene(new Scene(root));
				stage.setTitle(s);


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
