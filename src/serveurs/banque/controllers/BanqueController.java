package serveurs.banque.controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import model.Banque;
import model.ClientBanque;
import serveurs.DAO.BanqueDAO;
import serveurs.DAO.ClientBanqueDAO;
import serveurs.banque.interfaces.BanqueInterface;

public class BanqueController extends UnicastRemoteObject implements BanqueInterface {

	public BanqueController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Banque getBanque(int idBanque) throws RemoteException, SQLException {
		return BanqueDAO.getBanque(idBanque);
	}

	@Override
	public ClientBanque toConnectBanque(long cb, int cvv) throws RemoteException, SQLException {
		return ClientBanqueDAO.toConnectBanque(cb, cvv);
	}

	@Override
	public Boolean toVerifBanque(long cb, int cvv, String mdp) throws RemoteException, SQLException {
		return ClientBanqueDAO.toVerifBanque(cb, cvv, mdp);
	}

	@Override
	public Boolean toVerifSolde(int idClient, double solde) throws RemoteException, SQLException {
		return ClientBanqueDAO.toVerifSolde(idClient, solde);
	}

	@Override
	public Boolean updateSolde(int idClient, double solde) throws RemoteException, SQLException {
		return ClientBanqueDAO.updateSolde(idClient, solde);
	}
}
