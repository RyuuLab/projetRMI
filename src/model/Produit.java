package model;

public class Produit {
	int idProduit;
	int idMagasin;
	String imageProduit;
	String nom;
	double prix;
	int quantite;

	public Produit(int idProduit, int idMagasin, String imageProduit, String nom, double prix, int quantite) {
		this.idProduit = idProduit;
		this.idMagasin = idMagasin;
		this.imageProduit = imageProduit;
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public int getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getImageProduit() {
		return imageProduit;
	}

	public void setImageProduit(String imageProduit) {
		this.imageProduit = imageProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public static boolean removeQteProduit(int qteInit, int qteToRemove) {
		if(qteInit < qteToRemove) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "Produit{" +
				"idProduit=" + idProduit +
				", idMagasin=" + idMagasin +
				", imageProduit='" + imageProduit + '\'' +
				", nom='" + nom + '\'' +
				", prix=" + prix +
				", quantite=" + quantite +
				'}';
	}

}
