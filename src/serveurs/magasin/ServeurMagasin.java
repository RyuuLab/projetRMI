package serveurs.magasin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import serveurs.magasin.controllers.MagasinController;

public class ServeurMagasin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            int port = 9008;
            MagasinController magasinController = new MagasinController();
            LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://localhost:"+port+"/magasin", magasinController);
            System.out.println (" Serveur Pret");
        }
        catch (RemoteException e) { System.out.println (e.getMessage()); }
        catch (MalformedURLException e) { e.getMessage();}
	}

}
