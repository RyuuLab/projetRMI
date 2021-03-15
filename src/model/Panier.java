package model;

import controllers.ConnectToServeur;
import controllers.MagasinController;
import controllers.PanierController;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import serveurs.magasin.interfaces.MagasinInterface;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Observer;

public class Panier extends Produit implements Serializable {
	private int qteClient;
	private int qteMax;
	private ChoiceBox choiceBox;
	private MagasinInterface serveurMagasin = ConnectToServeur.magasin();


	public Panier(int idProduit, int idMagasin, String imageProduit, String nom, double prix, int quantite, int qteClient, int qteMax) {
		super(idProduit, idMagasin, imageProduit, nom, prix, quantite);
		this.qteClient = qteClient;
		this.qteMax = qteMax;
		this.choiceBox = new ChoiceBox();
		initChoiceBox();
		choiceBox.setOnAction(actionEvent -> {
			this.qteClient = Integer.parseInt(choiceBox.getValue().toString());
			this.quantite = this.qteMax - this.qteClient;
			PanierController.updateObservableList();
		});
	}

	public int getQteClient() {
		return qteClient;
	}

	public void setQteClient(int qteClient) {
		this.qteClient = qteClient;
	}

	public ChoiceBox getChoiceBox() {
		return choiceBox;
	}

	public void setChoiceBox(ChoiceBox choiceBox) {
		this.choiceBox = choiceBox;
	}


	public void initChoiceBox() {
		for (int i = 1; i <= this.quantite+this.qteClient; i++) {
			choiceBox.getItems().add(i);
		}
	}


	public void setDefaultValueChoiceBox() {
		choiceBox.setValue(this.qteClient);
	}


	@Override
	public String toString() {
		return "Panier{" +
				"qteClient=" + qteClient +
				", idProduit=" + idProduit +
				", idMagasin=" + idMagasin +
				", imageProduit='" + imageProduit + '\'' +
				", nom='" + nom + '\'' +
				", prix=" + prix +
				", quantite=" + quantite +
				'}';
	}
}
