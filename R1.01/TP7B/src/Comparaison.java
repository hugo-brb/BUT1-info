import java.util.ArrayList;
import java.util.Scanner;

public class Comparaison {
    public static void main(String[] args) {
        ArrayList<Polar> mesPolars = CreationBib.lesPolars();
        System.out.println("* NOMBRES DE COMPARAISON TRI SELECTION");
        System.out.println(Utilitaire.triSelectNbComp(mesPolars));
        mesPolars = CreationBib.lesPolars();
        System.out.println("* NOMBRES DE COMPARAISON TRI INSERTION");
        System.out.println(Utilitaire.triInsertionNbComp(mesPolars));

        String auteurTest; int anneTest;
        Scanner lecteur = new Scanner(System.in);
        System.out.println("* RECHERCHER UN LIVRE");
        boolean fin;
        do {
            System.out.println("------------------------------------------------------");
            System.out.print("Entrer le nom d'un auteur : ");
            auteurTest = lecteur.nextLine(); auteurTest = auteurTest.toUpperCase();
            System.out.print("Entrer une année (entier) : ");
            anneTest = lecteur.nextInt(); lecteur.nextLine();
            System.out.println("------------------------------------------------------");
            System.out.println("--> RECHERCHE SEQUENTIELLE");
            PaireResultatCompteur<Integer> result = Utilitaire.rechSeqT_O(mesPolars, auteurTest, anneTest);
            if (result.getRes()!=-1){
                System.out.println("* Premier roman de "+auteurTest+" écrit en "+anneTest+" : "+
                        mesPolars.get(result.getRes()).getTitre()+" (trouvé à l'indice "+result.getRes()+")");
                System.out.println("* Nombres de comparaisons effectuées : "+result.getCompteur());
            }else {
                System.out.println("* Aucun roman de "+auteurTest+" pour "+anneTest+" ...");
                System.out.println("* Nombres de comparaisons effectuées : "+result.getCompteur());
            }
            System.out.println("------------------------------------------------------");
            System.out.println("--> RECHERCHE DICHOTOMIQUE");
            result = Utilitaire.rechDicho_O(mesPolars, auteurTest, anneTest);
            if (result.getRes()!=-1){
                System.out.println("* Premier roman de "+auteurTest+" écrit en "+anneTest+" : "+
                        mesPolars.get(result.getRes()).getTitre()+" (trouvé à l'indice "+result.getRes()+")");
                System.out.println("* Nombres de comparaisons effectuées : "+result.getCompteur());
            }else {
                System.out.println("* Aucun roman de "+auteurTest+" pour "+anneTest+" ...");
                System.out.println("* Nombres de comparaisons effectuées : "+result.getCompteur());
            }
            System.out.print("Voulez-vous faire une nouvelle recherche ? ");
            String fin_temp = lecteur.nextLine();
            fin_temp = fin_temp.toLowerCase();
            fin = fin_temp.equals("t") || fin_temp.equals("true") || fin_temp.equals("oui") || fin_temp.equals("o") || fin_temp.equals("yes") || fin_temp.equals("y");
        }while (fin);
    }
}
