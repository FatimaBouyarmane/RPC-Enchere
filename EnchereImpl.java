import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EnchereImpl extends UnicastRemoteObject implements Enchere {
    private double prixActuel;
    private String dernierEncherisseur;
    private boolean vendu;
    private static final int TEMPS_ATTENTE = 10; // 10 secondes

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public EnchereImpl() throws RemoteException {
        prixActuel = 0.0;
        dernierEncherisseur = "Aucun";
        vendu = false;
    }

    public synchronized void encherir(String nomClient, double prix) throws RemoteException {
        if (!vendu && prix > prixActuel) {
            prixActuel = prix;
            dernierEncherisseur = nomClient;
            System.out.println(nomClient + " a enchérit avec un prix de " + prix);

            // Planifier la vente du produit après TEMPS_ATTENTE secondes
            scheduler.schedule(() -> {
                if (!vendu) {
                    vendu = true;
                    System.out.println("Le produit est vendu à " + dernierEncherisseur + " pour " + prixActuel);
                }
            }, TEMPS_ATTENTE, TimeUnit.SECONDS);
        } else {
            System.out.println("L'enchère actuelle est plus élevée ou le produit est déjà vendu.");
        }
    }

    public synchronized double getPrixActuel() throws RemoteException {
        return prixActuel;
    }

    public synchronized boolean estVendu() throws RemoteException {
        return vendu;
    }

    public synchronized String getDernierEncherisseur() throws RemoteException {
        return dernierEncherisseur;
    }
}
