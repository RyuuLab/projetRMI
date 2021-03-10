package serveurs.magasin.controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import model.ClientMagasin;
import model.Magasin;
import model.Produit;
import serveurs.DAO.ClientMagasinDAO;
import serveurs.DAO.MagasinDAO;
import serveurs.DAO.ProduitDAO;
import serveurs.magasin.interfaces.MagasinInterface;

public class MagasinController extends UnicastRemoteObject implements MagasinInterface {

	public MagasinController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientMagasin toConnectMagasin(String email, String mdp) throws RemoteException, SQLException {
		return ClientMagasinDAO.toConnectMagasin(email, mdp);
	}

	@Override
	public List<Magasin> getMagasins() throws RemoteException, SQLException {
		return MagasinDAO.getMagasins();
	}

	@Override
	public List<Produit> getProduits() throws RemoteException, SQLException {
		return ProduitDAO.getProduits();
	}

	@Override
	public List<Produit> getProduitsByMagasin(int idMagasin) throws RemoteException, SQLException {
		return ProduitDAO.getProduitsByMagasin(idMagasin);
	}

	@Override
	public Produit getProduit(int idProduit) throws RemoteException, SQLException {
		return ProduitDAO.getProduit(idProduit);
	}

	@Override
	public boolean removeQuantityProduit(int idProduit, int quantite) throws RemoteException, SQLException {
		return ProduitDAO.removeQuantityProduit(idProduit, quantite);
	}
}
