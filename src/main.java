import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import serveurs.DAO.*;


public class main {

	public static void main(String[] args) throws SQLException {
//		System.out.println(MagasinDAO.getMagasins());
//		System.out.println(PanierDAO.addPanier(1,1,1));
//		System.out.println(ProduitDAO.getQuantityProduit(1));
//		System.out.println(ProduitDAO.removeQuantityProduit(1, 30));
		System.out.println(ClientMagasinDAO.toConnectMagasin("jager.eren@original.fr", "azerty"));
	}

}
