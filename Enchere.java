import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Enchere extends Remote {
    void encherir(String nomClient, double prix) throws RemoteException;
    double getPrixActuel() throws RemoteException;
    boolean estVendu() throws RemoteException;
    String getDernierEncherisseur() throws RemoteException;
}
