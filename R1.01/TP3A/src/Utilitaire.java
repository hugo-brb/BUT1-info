import java.util.Scanner;

public class Utilitaire {
    public static float saisieFloatPos() {
        // { } => { résultat = un réel (float) strictement positif }
        float n;
        Scanner lecteur = new Scanner(System.in);
        do {
            System.out.print("* Entrer un réel strictement positif : ");
            n = lecteur.nextFloat(); lecteur.nextLine();
            if (n<=0) {
                System.out.println("--> Saisie invalide, recommencez...");
            }
        } while (n <= 0);
        return n;
    }

    public static float saisieFloatPosMinMax(float min, float max) {
        // { 0 < min < max } =>
        // { résultat = un réel saisi par l'utilisateur, appartenant à ]min, max[ }
        float n;
        Scanner lecteur = new Scanner(System.in);
        do {
            System.out.print("Saisissez un réel strictement inférieur à " + min + " et strictement supérieur à " + max + " : ");
            n = lecteur.nextFloat(); lecteur.nextLine();
            if (n <= min | n >= max) {
                System.out.println("--> Saisie invalide, recommencez...");
            }
        } while (n <= min | n >= max);
        return n;
    }

    public static float troisiemeCoteTriangle(float cote1, float cote2) {
        // { cote1 et cote2 > 0 } =>
        // { le résultat est un float strictement positif, tel qu'un triangle dont
        // les côtés seraient cote1, cote2 et le float saisi puisse être construit }
        // INDICATION : INÉGALITÉ TRIANGULAIRE
        return saisieFloatPosMinMax(Math.abs(cote1-cote2), (cote1+cote2));
    }

    public static Triangle saisirTriangle() {
        // { } => { résultat = un nouvel objet de type triangle
        // dont les côtés sont saisis par l'utilisateur }
        System.out.println("Longeur d'un côté d'un triangle ?");
        float a = saisieFloatPos();
        System.out.println("\nLongeur d'un autre coté du triangle ?");
        float b = saisieFloatPos();
        System.out.println("\nLongueur du troisième coté de ce triangle ?");
        float c = troisiemeCoteTriangle(a, b);
        return new Triangle(a, b, c);
    }
}
