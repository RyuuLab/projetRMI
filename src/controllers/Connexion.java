package controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.*;

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
		String s = magasinChoix.getSelectionModel().getSelectedItem().toString();
		Main m = new  Main();
		if(identifiant.getText().toString().equals("abc") && mdp.getText().toString().equals("123")) {
			erreur.setText("Succès");
			//m.changeScene("../vues/Magasin1.fxml");
			m.changeScene("../vues/"+s+".fxml");
		}
		else if (identifiant.getText().isEmpty() && mdp.getText().isEmpty()) {
			erreur.setText("Champs manquants");
		}
		else {
			erreur.setText("Identifiant ou mdp incorrect");
		}
	}
	
}
