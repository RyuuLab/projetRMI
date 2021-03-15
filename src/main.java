import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Produit;
import serveurs.DAO.*;
import serveurs.banque.interfaces.BanqueInterface;
import serveurs.magasin.interfaces.MagasinInterface;


public class main {

	public static void main(String[] args) throws SQLException {
		Produit a = new Produit(1, 1, "te", "test", 300.0, 15);
		Produit b = new Produit(1, 1, "te", "test", 300.0, 15);
		Produit c = a;
		HashMap<Produit, Integer> test = new HashMap<Produit, Integer>();
		test.put(a, 2);
		System.out.println(test.containsKey(a));
		System.out.println(test.containsKey(b));
		System.out.println(test.containsKey(c));
		List aList = new ArrayList();
		aList.add(a);
		aList.add(b);
		System.out.println("The element C is available in ArrayList? " + aList.contains(a));
		System.out.println("The element Z is available in ArrayList? " + aList.contains(b));


	}

}
