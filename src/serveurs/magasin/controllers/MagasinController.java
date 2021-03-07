package serveurs.magasin.controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveurs.magasin.interfaces.MagasinInterface;

public class MagasinController extends UnicastRemoteObject implements MagasinInterface {

	public MagasinController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
