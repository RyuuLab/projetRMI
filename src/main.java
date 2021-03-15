import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controllers.ConnectToServeur;
import model.Produit;
import serveurs.DAO.*;
import serveurs.banque.interfaces.BanqueInterface;
import serveurs.magasin.interfaces.MagasinInterface;


public class main {

	public static void main(String[] args) throws SQLException, RemoteException, InterruptedException {
		ConnectToServeur.banque().updateSolde(1, 300.);
	}




}
