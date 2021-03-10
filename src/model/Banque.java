package model;

public class Banque {
	int idBanque;
	String nom;
	
	public Banque(int idBanque, String nom) {
		this.idBanque = idBanque;
		this.nom = nom;
	}

	public int getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(int idBanque) {
		this.idBanque = idBanque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Banque{" +
				"idBanque=" + idBanque +
				", nom='" + nom + '\'' +
				'}';
	}
}
