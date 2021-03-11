package controllers;

import serveurs.banque.interfaces.BanqueInterface;
import serveurs.magasin.interfaces.MagasinInterface;

import java.rmi.Naming;

public class ConnectToServeur {
    public static MagasinInterface magasinInterface;
    public static BanqueInterface banqueInterface;

    static MagasinInterface magasin() {
        try{
            magasinInterface = (MagasinInterface) Naming.lookup("rmi://localhost:9008/magasin");
        }
        catch (Exception e)
        {
            System.out.println ("magasin exception: " + e);
        }
        return magasinInterface;
    }

    static BanqueInterface banque() {
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
