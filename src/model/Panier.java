package model;

import java.util.HashMap;

public class Panier {
	int idPanier;
	int idClient;
	HashMap<Integer,Integer> tabProd = new HashMap<Integer,Integer>();
	
	public Panier(int idPanier, int idClient,  HashMap<Integer,Integer> tabProd) {
		this.idPanier = idPanier;
		this.idClient = idClient;
		this.tabProd = tabProd;
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

	public HashMap<Integer, Integer> getTabProd() {
		return tabProd;
	}

	public void setTabProd(HashMap<Integer, Integer> tabProd) {
		this.tabProd = tabProd;
	}

	
	
	
}
