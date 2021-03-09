import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import serveurs.DAO.*;
import serveurs.banque.interfaces.BanqueInterface;
import serveurs.magasin.interfaces.MagasinInterface;


public class main {

	public static void main(String[] args) throws SQLException {
		try{
			BanqueInterface banqueInterface = (BanqueInterface) Naming.lookup("rmi://localhost:9005/banque");
			System.out.println(banqueInterface.getBanque(1));
		}
		catch (Exception e)
		{
			System.out.println ("banque exception: " + e);
		}
	}

}
