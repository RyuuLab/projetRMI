package serveurs.magasin.interfaces;

import model.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MagasinInterface extends Remote {
    public ClientMagasin toConnectMagasin(String email, String mdp) throws RemoteException, SQLException;
    public List<Magasin> getMagasins() throws RemoteException, SQLException;
    public List<Produit> getProduits() throws RemoteException, SQLException;
    public List<Produit> getProduitsByMagasin(int idMagasin) throws RemoteException, SQLException;
    public Produit getProduit(int idProduit) throws RemoteException, SQLException;
    public boolean removeQuantityProduit(int idProduit, int quantite) throws RemoteException, SQLException;
    public boolean setQuantityProduit(int idProduit, int quantite) throws RemoteException, SQLException;
    public double calculPanier(ArrayList<Produit> produits) throws RemoteException;
}
