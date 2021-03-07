package model;

public class ClientMagasin {
	
	int idClientMagasin;
	String nom;
	String prenom;
	String email;
	String mdp;
	
	public ClientMagasin (int idClientMagasin, String nom, String prenom, String email, String mdp) {
		this.idClientMagasin = idClientMagasin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}

	public int getIdClientMagasin() {
		return idClientMagasin;
	}

	public void setIdClientMagasin(int idClientMagasin) {
		this.idClientMagasin = idClientMagasin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
