package serveurs.banque.interfaces;

import model.Banque;
import model.ClientBanque;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface BanqueInterface extends Remote{
    public Banque getBanque(int idBanque) throws RemoteException, SQLException;
    public ClientBanque toConnectBanque(long cb, int cvv) throws RemoteException, SQLException;
    public Boolean toVerifBanque(long cb, int cvv, String mdp) throws RemoteException, SQLException;
    public Boolean toVerifSolde(int idClient, double totalAchat) throws RemoteException, SQLException;
    public Boolean updateSolde(int idClient, double totalAchat) throws RemoteException, SQLException;

}
