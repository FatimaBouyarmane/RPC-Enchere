import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServeurEnchere {
    public static void main(String[] args) {
        try {
            //Registry registry = LocateRegistry.createRegistry("localhost",1043);

            EnchereImpl objetEnchere = new EnchereImpl();

            // Enregistrement de l'objet distant
            Naming.rebind("Enchere", objetEnchere);

            System.out.println("Serveur d'enchères prêt.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
