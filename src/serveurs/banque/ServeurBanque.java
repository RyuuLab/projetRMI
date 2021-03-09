package serveurs.banque;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import serveurs.banque.controllers.BanqueController;

public class ServeurBanque {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            int port = 9005;
            BanqueController obj = new BanqueController();
            LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://localhost:"+port+"/banque", obj);
            System.out.println (" Serveur Pret");
        }
        catch (RemoteException e) { System.out.println (e.getMessage()); }
        catch (MalformedURLException e) { e.getMessage();}
	}
}


