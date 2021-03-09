package Controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Connexion {
	public void LogIn() {
		
	}
	
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
	@FXML
	private Hyperlink dejainscrit;
	
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
		if(identifiant.getText().toString().equals("abc") && mdp.getText().toString().equals("123")) {
			erreur.setText("Succès");
			m.changeScene("../vues/Magasin1.fxml");
		}
		else if (identifiant.getText().isEmpty() && mdp.getText().isEmpty()) {
			erreur.setText("Champs manquants");
		}
		else {
			erreur.setText("Identifiant ou mdp incorrect");
		}
	}
	
}
