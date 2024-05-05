import java.rmi.Naming;

public class ClientEnchere {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java ClientEnchere <adresse_ip_serveur> <nom_client> <montant_enchere>");
            return;
        }

        String adresseServeur = args[0];
        String nomClient = args[1];
        double montantEnchere = Double.parseDouble(args[2]);

        try {
            // Obtention de l'URL distant pour l'objet "Enchere" sur le serveur spécifié
            String urlObjetEnchere = "//" + adresseServeur + "/Enchere";

            // Recherche de l'objet distant dans le registre RMI
            Enchere objetEnchere = (Enchere) Naming.lookup(urlObjetEnchere);

            // Appel de la méthode distante pour enchérir
            objetEnchere.encherir(nomClient, montantEnchere);
            
            System.out.println("Enchère effectuée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
