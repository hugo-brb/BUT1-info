import java.util.ArrayList;
import java.util.Scanner;

public class Monde {
    public static void main(String[] args) {
        ArrayList<Pays> leMonde = InitMonde.creerMonde();

        System.out.println("PAYS LE PLUS GRAND");
        System.out.println("*ITERATIVE*");
        Pays r = Utilitaire.plusGrandPaysIter(leMonde);
        System.out.println(r.getNom()+" | "+r.getContinent()+" | "+r.getSuperficie());
        System.out.println("*RECURSIF*");
        r = Utilitaire.plusGrandPaysDPR(leMonde);
        System.out.println(r.getNom()+" | "+r.getContinent()+" | "+r.getSuperficie());

        System.out.println("-------------------------------------------------------------");
        System.out.println("PAYS LE MOINS PEUPLE");
        System.out.println("*ITERATIVE*");
        System.out.println(leMonde.get(Utilitaire.indMinPopIter(leMonde)).getNom()+" | "+leMonde.get(Utilitaire.indMinPopIter(leMonde)).getContinent()+
                " | "+leMonde.get(Utilitaire.indMinPopIter(leMonde)).getPopulation() + " | Indice : " +Utilitaire.indMinPopIter(leMonde));
        System.out.println("*RECURSIF*");
        System.out.println(leMonde.get(Utilitaire.indMinPopDPR(leMonde)).getNom()+" | "+leMonde.get(Utilitaire.indMinPopDPR(leMonde)).getContinent()+
                " | "+leMonde.get(Utilitaire.indMinPopDPR(leMonde)).getPopulation() + " | Indice : " +Utilitaire.indMinPopDPR(leMonde));

        System.out.println("-------------------------------------------------------------");
        System.out.println("TRIE DU VECTEUR LE MONDE");
        ArrayList<Pays> leMondeTrie = InitMonde.creerMonde();
        Utilitaire.triBulleContNom(leMondeTrie);
        System.out.println("Le vecteur est bien trié ? : "+Utilitaire.verifTriContNom(leMondeTrie));

        System.out.println("-------------------------------------------------------------");
        System.out.println("RECHERCHE");
        Scanner lecteur = new Scanner(System.in);
        System.out.print("** Saisir le nom d'un continent : ");
        String cont = lecteur.nextLine();
        cont = cont.toLowerCase();
        cont = cont.substring(0, 1).toUpperCase() + cont.substring(1);
        System.out.print("** Saisir le nom d'un pays : ");
        String pays = lecteur.nextLine();
        pays = pays.toLowerCase();
        pays = pays.substring(0, 1).toUpperCase() + pays.substring(1);
        System.out.println("*ITERATIVE*");
        Pays result = leMondeTrie.get(Utilitaire.indDichoIter(leMondeTrie, cont, pays));
        System.out.println(result.getNom()+" | "+result.getContinent()+" | Supérficie : "+result.getSuperficie()+" | Population : "+result.getPopulation()+" | Indice : "+Utilitaire.indDichoIter(leMondeTrie, cont, pays));
        System.out.println("*RECURSIVE*");
        result = leMondeTrie.get(Utilitaire.indDichoRec(leMondeTrie, cont, pays));
        System.out.println(result.getNom()+" | "+result.getContinent()+" | Supérficie : "+result.getSuperficie()+" | Population : "+result.getPopulation()+" | Indice : "+Utilitaire.indDichoRec(leMondeTrie, cont, pays));

        System.out.println("-------------------------------------------------------------");
        System.out.println("NOMBRE DE PAYS DE MOINS DE 100 000 HABITANTS SITUÉS EN "+cont.toUpperCase());
        System.out.println("* Nombre de pays trouvé par parcours itératif : "+Utilitaire.nbPaysDeContInfNbHabIter(leMondeTrie, cont, 100000));
        System.out.println("* Nombre de pays trouvé par parcours récursif : "+Utilitaire.nbPaysDeContInfNbHabRec(leMondeTrie, cont, 100000));

        System.out.println("-------------------------------------------------------------");
        System.out.println("TRI DU VECTEUR PAR SUPERFICIE CROISSANTE, SELON LA MÉTHODE DU TRI PAR FUSION");
        System.out.println("* Affichage du nom et de la superficie des éléments du vecteur après tri fusion :");
        Utilitaire.triFusion(leMonde, 0, leMonde.size()-1);
        int retour=1;
        for(Pays p:leMonde){
            if (retour%5 == 0){
                System.out.print("\n\n");
            }
            System.out.print("("+p.getNom()+" "+p.getSuperficie()+" km2) / ");
            retour++;
        }
    }
}
