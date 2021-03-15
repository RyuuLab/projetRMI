package serveurs.magasin.controllers;

import serveurs.banque.interfaces.BanqueInterface;

import java.rmi.Naming;

public class ConnectToBanque {
    public static BanqueInterface banqueInterface;
    public static BanqueInterface banque() {
        try{
            banqueInterface = (BanqueInterface) Naming.lookup("rmi://localhost:9005/banque");
        }
        catch (Exception e)
        {
            System.out.println ("banque exception: " + e);
        }
        return banqueInterface;
    }
}
