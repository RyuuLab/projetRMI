package serveurs.DAO;

import model.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {
    private static MysqlConnect db = MysqlConnect.getDbCon();


    public static List<Produit> getProduits() throws SQLException {
        String sql = "select * from Produit";
        List<Produit> produits = new ArrayList<Produit>();
        PreparedStatement ps = db.conn.prepareStatement(sql);
        try (ResultSet res = ps.executeQuery();) {
            while (res.next()) {
                Produit produit = new Produit(
                        res.getInt("idProduit"),
                        res.getInt("idMagasin"),
                        res.getString("imageProduit"),
                        res.getString("nom"),
                        res.getDouble("prix"),
                        res.getInt("quantite"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public static List<Produit> getProduitsByMagasin(int idMagasin) throws SQLException {
        String sql = "select * from Produit where idMagasin = ?";
        List<Produit> produits = new ArrayList<Produit>();
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, idMagasin);
        try (ResultSet res = ps.executeQuery();) {
            while (res.next()) {
                Produit produit = new Produit(
                        res.getInt("idProduit"),
                        res.getInt("idMagasin"),
                        res.getString("imageProduit"),
                        res.getString("nom"),
                        res.getDouble("prix"),
                        res.getInt("quantite"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public static Produit getProduit(int idProduit) throws SQLException {
        String sql = "select * from Produit where idProduit = ?";
        Produit produit = null;
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, idProduit);
        try (ResultSet res = ps.executeQuery();) {
            while (res.next()) {
                produit = new Produit(
                        res.getInt("idProduit"),
                        res.getInt("idMagasin"),
                        res.getString("imageProduit"),
                        res.getString("nom"),
                        res.getDouble("prix"),
                        res.getInt("quantite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produit;
    }

    public static int getQuantityProduit(int idProduit) throws SQLException {
        int qte = 0;
        String sql = "select quantite from Produit where idProduit = ?";
        PreparedStatement ps = db.conn.prepareStatement(sql);
        ps.setInt(1, idProduit);
        try (ResultSet res = ps.executeQuery();) {
            while (res.next()) {
                qte = res.getInt("quantite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qte;
    }


    public static boolean removeQuantityProduit(int idProduit, int quantite) throws SQLException {
        String sql = "UPDATE Produit SET quantite = ? where idProduit = ?;";
        int qteProduit = getQuantityProduit(idProduit);
        PreparedStatement ps = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, qteProduit-quantite);
        ps.setInt(2, idProduit);
        try {
            if (Produit.removeQteProduit(qteProduit, quantite)) {
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
