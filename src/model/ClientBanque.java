package model;

import java.io.Serializable;

public class ClientBanque implements Serializable {
	int idBanque;
	int idClientBanque;
	long cb;
	int cvv;
	String mdp;
	
	public ClientBanque(int idBanque, int idClientBanque, long cb, int cvv, String mdp) {
		this.idBanque = idBanque;
		this.idClientBanque = idClientBanque;
		this.cb = cb;
		this.cvv = cvv;
		this.mdp = mdp;
	}

	public ClientBanque(int idBanque, int idClientBanque, long cb, int cvv) {
		this.idBanque = idBanque;
		this.idClientBanque = idClientBanque;
		this.cb = cb;
		this.cvv = cvv;
	}

	public int getIdBanque() {
		return idBanque;
	}

	public void setIdBanque(int idBanque) {
		this.idBanque = idBanque;
	}

	public int getIdClientBanque() {
		return idClientBanque;
	}

	public void setIdClientBanque(int idClientBanque) {
		this.idClientBanque = idClientBanque;
	}

	public long getCb() {
		return cb;
	}

	public void setCb(int cb) {
		this.cb = cb;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "ClientBanque{" +
				"idBanque=" + idBanque +
				", idClientBanque=" + idClientBanque +
				", cb=" + cb +
				", cvv=" + cvv +
				", mdp='" + mdp + '\'' +
				'}';
	}
}
