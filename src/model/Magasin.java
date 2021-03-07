package model;

public class Magasin {
	int idMagasin;
	String nomMagasin;
	String adresse;
	String ville;
	
	public Magasin(int idMagasin, String nomMagasin, String adresse, String ville) {
		this.idMagasin = idMagasin;
		this.nomMagasin = nomMagasin;
		this.adresse = adresse;
		this.ville = ville;
	}

	public int getIdMagasin() {
		return idMagasin;
	}

	public void setIdMagasin(int idMagasin) {
		this.idMagasin = idMagasin;
	}

	public String getNomMagasin() {
		return nomMagasin;
	}

	public void setNomMagasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}