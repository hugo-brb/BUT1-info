import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Calculs {
    private static int saisieEntPosOuNul() {
        // { } => { résultat = un entier positif ou nul saisi par l'utilisateur }
        int nb = 0;
        Scanner lecteur = new Scanner(System.in);

        do {
            if (nb < 0){
                System.out.println("--> Saisie invalide recommencez...");
            }
            System.out.print("Entrer un entier positif ou nul : ");
            nb = lecteur.nextInt(); lecteur.nextLine();
        } while (nb < 0);

        return nb;
    }
    private static int saisieEntMinMax(int min, int max) {
        // { 0 <= min <= max } => { résultat = entier compris entre min et max saisi par l 'utilisateur }
        int nb = min;
        Scanner lecteur = new Scanner(System.in);

        do {
            if (nb < min | nb > max){
                System.out.println("--> Saisie invalide recommencez...");
            }
            System.out.print("Entrez un entier compris entre " + min + " et " + max + " : ");
            nb = lecteur.nextInt(); lecteur.nextLine();
        } while (nb < min | nb > max);

        return nb;
    }
    private static int factorielle(int n) {
        // { n >= 0 } => { résultat = factorielle de n (n!) }
        int nb = 1;

        for (int i = n; i > 0; i--) {
            nb *= i;
        }

        return nb;
    }
    private static int combinaison(int n, int p) {
        // { 0 <= p <= n } =>
        // { résultat = nombre de sous-ensembles de p éléments que l'on peut obtenir à partir d 'un ensemble de n éléments
        // formule : n!/(p!*(n-p)!) }

        return factorielle(n)/(factorielle(p)*(factorielle(n-p)));
    }
    private static int arrangement(int n, int p) {
        // { 0 <= p <= n } =>
        // { résultat = nombre de n-uplets ordonnés de p éléments que l'on peut obtenir à partir d'un ensemble de n éléments
        // formule : n!/(n-p)! }

        return factorielle(n) / (factorielle(n - p));
    }

    public static void main(String[] args) {
        int n = saisieEntPosOuNul(), p = saisieEntMinMax(0,n);

        System.out.print("------------------------");
        System.out.print("Factorielle de " + n + " = " + factorielle(n));
        System.out.print("------------------------");
        System.out.print("Nombre de sous-ensembles de " + p + " éléments parmi " + n + " éléments : " + combinaison(p, n));
        System.out.print("------------------------");
        System.out.print("Nombre de n-uplets ordonnés de" + p + "éléments parmi" + n + " éléments : ");

    }

}
