import java.util.Scanner;

public class Nim {
    private static int saisieEnt(int min) {
        // { min > 0 } => { résultat = un entier positif supérieur ou égal à min saisi par l'utilisateur }
        int nb = min;
        Scanner l = new Scanner(System.in);

        do {
            if (nb < min){
                System.out.println("Erreur : La valeur n'est pas supérieur ou égale à " + min);
            }
            System.out.print("Saisissez un entier supérieur ou égale à " + min + " : ");
            nb = l.nextInt(); l.nextLine();
        } while (nb < min);

        return nb;
    }
    private static int unePrise(int reste, int max) {
        // { reste > 0, max > 0 }
        // => { résultat = entier > 0 et inférieur ou égal à la plus petite valeur parmi reste et max
        final int min = 1;
        int nb = 1;
        Scanner l = new Scanner(System.in);

        do {
            if (nb < min | nb > max | nb > reste) {
                System.out.println("*** Saisie invalide recommencez...");
            }
            System.out.print("Entrer un entier (min 1, max 3) : ");
            nb = l.nextInt(); l.nextLine();
        } while (nb < min | nb > max | nb > reste);

        return nb;
    }


    public static void main(String[] args) {
        final int MINJOUEURS = 2, MINALLUMETTES = 6, MAXALLUMETTES = 3, MAXPRISES = 3;
        int nbJoueurs, nbAllumettes, numJoueur = 1, nbCoups = 0, prises, reste;
        Scanner lecteur = new Scanner(System.in);

        System.out.println("-----------------------------------------------------");
        System.out.println("Nombres de joueurs ? (min 2) : ");
        nbJoueurs = saisieEnt(MINJOUEURS);
        System.out.println("-----------------------------------------------------");
        System.out.println("Combien d'allumettes ? (min 6) : ");
        nbAllumettes = saisieEnt(MINALLUMETTES);
        reste = nbAllumettes;
        System.out.println("-----------------------------------------------------");
        System.out.println("\n*****************************************************");
        System.out.println("* Nombres de joueurs : " + nbJoueurs);
        System.out.println("* Nombres d'allumettes : " + nbAllumettes);
        System.out.println("*****************************************************");

        while (reste > 0){
            System.out.println("Joueur n° " + numJoueur + ", combien prennez-vous d'allumettes ?");
            reste -= unePrise(reste, MAXALLUMETTES);
            System.out.println("-> Il reste " + reste + " allumettes");
            if (numJoueur == nbJoueurs){
                numJoueur = 1;
            }else{
                numJoueur += 1;
            }
            nbCoups += 1;
        }
        System.out.println("*****************************************************");
        System.out.println("Victoire du joueur n° " + numJoueur + " après " + nbCoups + " coups !");
        System.out.println("*****************************************************");
    }
}
