package serveurs.banque.controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveurs.banque.interfaces.BanqueInterface;

public class BanqueController extends UnicastRemoteObject implements BanqueInterface {

	public BanqueController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
