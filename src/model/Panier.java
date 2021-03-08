package model;

import java.util.HashMap;

public class Panier {
	int idPanier;
	int idClient;
	Produit produit;
	int qteProduit;



	public Panier(int idPanier, int idClient, Produit produit , int qteProduit) {
		this.idPanier = idPanier;
		this.idClient = idClient;
		this.produit = produit;
		this.qteProduit = qteProduit;
		//this.idProduit = idProduit;
		//this.qteProduit = qteProduit;
	}

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQteProduit() {
		return qteProduit;
	}

	public void setQteProduit(int qteProduit) {
		this.qteProduit = qteProduit;
	}


	
	
	
}
