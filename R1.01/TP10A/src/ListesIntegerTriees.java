import java.util.ArrayList;
import java.util.Arrays;

public class ListesIntegerTriees {
    public static void main(String[] args) {
        ArrayList<Integer> vIntTest = new ArrayList<>(Arrays.asList(31, 1, 46, 35, 5, 32, 14, 49, 19, 28, 2, 30, 32, 12, 20));
        ListeTrieeD<Integer> listeD = new ListeTrieeD<>();
        for (Integer nb : vIntTest) {
            listeD.insereTrie(nb);
        }
        System.out.println("* Liste triée décroissante initialisée avec les éléments du vecteur vIntTest :");
        listeD.afficheGD();
        ArrayList<Integer> vIntRandom1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            vIntRandom1.add((int) (Math.random() * 40));
        }
        ArrayList<Integer> vIntRandom2 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            vIntRandom2.add((int) (Math.random() * 40));
        }
        System.out.println("Affichage des vecteurs vIntRandom1 et vIntRandom2 :");
        for (Integer nb : vIntRandom1) {
            System.out.print(nb + " | ");
        }
        System.out.println();
        for (Integer nb : vIntRandom2) {
            System.out.print(nb + " | ");
        }
        ListeTrieeC<Integer> listeIntC1 = new ListeTrieeC<>();
        ListeTrieeC<Integer> listeIntC2 = new ListeTrieeC<>();
        for (Integer nb : vIntRandom1) {
            listeIntC1.insereTrie(nb);
        }
        for (Integer nb : vIntRandom2) {
            listeIntC2.insereTrie(nb);
        }
        System.out.println("\n* Liste triée croissante 1 initialisée avec les éléments du vecteur vIntRandom1 :");
        listeIntC1.afficheGD();
        System.out.println("* Liste triée croissante 2 initialisée avec les éléments du vecteur vIntRandom2 :");
        listeIntC2.afficheGD();

        ListeTrieeC<Integer> listeIntC1_sdb = null;
        try {
            listeIntC1_sdb = Utilitaire.lCsansDoublons(listeIntC1);
        } catch (ExceptionMauvaisIndice e) {
            throw new RuntimeException(e);
        }
        System.out.println("* Liste triée croissante 1 sans doublons :");
        listeIntC1_sdb.afficheGD();
        ListeTrieeC<Integer> listeIntC2_sdb = null;
        try {
            listeIntC2_sdb = Utilitaire.lCsansDoublons(listeIntC2);
        } catch (ExceptionMauvaisIndice e) {
            throw new RuntimeException(e);
        }
        System.out.println("* Liste triée croissante 2 sans doublons :");
        listeIntC2_sdb.afficheGD();
        System.out.println();
        ListeTrieeC<Integer> listeUnionC = Utilitaire.union(listeIntC1_sdb, listeIntC2_sdb);
        System.out.println("* Liste triée croissante union des deux listes triées croissantes sans doublons :");
        listeUnionC.afficheGD();
        ListeTrieeC<Integer> listeInterC = Utilitaire.intersect(listeIntC1_sdb, listeIntC2_sdb);
        System.out.println("* Liste triée croissante intersection des deux listes triées croissantes sans doublons :");
        listeInterC.afficheGD();
    }
}
