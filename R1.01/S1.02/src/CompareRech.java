import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;

public class CompareRech {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);
        /*ArrayList<Integer> vals = new ArrayList<>(Arrays.asList(-45,-45,-10,9,10,20,30,75,90));
        System.out.print("Liste de valeurs : ");
        for(int x : vals){
            System.out.print(x + " / ");
        }
        System.out.println("\nNombres de valeurs : " + vals.size());

        System.out.print("** Saisir un 1er nombre entier : ");
        int nb1 = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("** Saisir un 2ième nombre entier : ");
        int nb2 = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("** Saisir un 3ième nombre entier : ");
        int nb3 = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("** Saisir un 4ième nombre entier : ");
        int nb4 = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("** Saisir un 5ième nombre entier : ");
        int nb5 = lecteur.nextInt(); lecteur.nextLine();

        PaireResCompteur<Integer> resultSeq1 = Utilitaire.rechSeqIt_O(vals, nb1);
        PaireResCompteur<Integer> resultDicho1 = Utilitaire.rechDichoIt_O(vals, nb1);
        PaireResCompteur<Integer> resultSeq2 = Utilitaire.rechSeqIt_O(vals, nb2);
        PaireResCompteur<Integer> resultDicho2 = Utilitaire.rechDichoIt_O(vals, nb2);
        PaireResCompteur<Integer> resultSeq3 = Utilitaire.rechSeqIt_O(vals, nb3);
        PaireResCompteur<Integer> resultDicho3 = Utilitaire.rechDichoIt_O(vals, nb3);
        PaireResCompteur<Integer> resultSeq4 = Utilitaire.rechSeqIt_O(vals, nb4);
        PaireResCompteur<Integer> resultDicho4 = Utilitaire.rechDichoIt_O(vals, nb4);
        PaireResCompteur<Integer> resultSeq5 = Utilitaire.rechSeqIt_O(vals, nb5);
        PaireResCompteur<Integer> resultDicho5 = Utilitaire.rechDichoIt_O(vals, nb5);

        System.out.println("                | RECHERCHE SEQUENTIELLE ITERATIVE | RECHERCHE DICHOTOMIQUE ITERATIVE\n"+
                " Entier cherché |     résultat   |   comparaisons  |     résultat   |   comparaisons \n"+
                "       "+nb1+"        |        "+resultSeq1.getRes()+"       |        "+resultSeq1.getCompteur()
                +"        |        "+resultDicho1.getRes()+"        |       "+resultDicho1.getCompteur() +"   \n"+
                "       "+nb1+"        |       "+resultSeq2.getRes()+"       |        "+resultSeq2.getCompteur()
                +"        |       "+resultDicho2.getRes()+"        |       "+resultDicho2.getCompteur() +"   \n"+
                "       "+nb1+"        |        "+resultSeq3.getRes()+"       |        "+resultSeq3.getCompteur()
                +"        |        "+resultDicho3.getRes()+"        |       "+resultDicho3.getCompteur() +"   \n"+
                "       "+nb1+"        |        "+resultSeq4.getRes()+"       |        "+resultSeq4.getCompteur()
                +"        |        "+resultDicho4.getRes()+"        |       "+resultDicho4.getCompteur() +"   \n"+
                "       "+nb1+"        |       "+resultSeq5.getRes()+"       |        "+resultSeq5.getCompteur()
                +"        |       "+resultDicho5.getRes()+"        |       "+resultDicho5.getCompteur() +"   \n");
        */

        ArrayList<Integer> v160 = Utilitaire.genVectSansDoublons(160);
        Utilitaire.triBulle_O(v160);
        int retour=1;
        for (int x:v160) {
            System.out.print(x + " / ");
            if (retour%30==0) {
                System.out.print("\n");
            }
            retour++;
        }
        int unInt;
        do {
            System.out.println("\nSaisir un entier inferieur ou égale à "+v160.get(v160.size()-1)+" : ");
            unInt = lecteur.nextInt(); lecteur.nextLine();
            if (unInt > v160.get(v160.size()-1)){
                System.out.print("Recommençez");
            }
        }while (unInt > v160.get(v160.size()-1));
        System.out.println("RECHERCHE DE "+unInt+" DANS V160 :");
        PaireResCompteur<Integer> rech = Utilitaire.rechDichoIt_O(v160, unInt);
        if (rech.getRes() == -1){
            System.out.println("La valeur n'existe pas dans v160... ");
        }else {
            System.out.println("Valeur trouvé à l'indice : "+rech.getRes());
        }
        System.out.println("Nombre de comparaisons effectué : "+rech.getCompteur());




















    }
}
