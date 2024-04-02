import org.w3c.dom.TypeInfo;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Utilitaire {
    public static int getInt_IME(Scanner lecteur) {
        // { } => {résultat = un entier saisi par l'utilisateur }
        // L'EXCEPTION InputMismatchException EST GÉRÉE
        int val;
        try {
            System.out.print("Saisir un entier : ");
            val = lecteur.nextInt();
            lecteur.nextLine();
            return val;
        } catch (InputMismatchException e) {
            lecteur.nextLine();
            System.out.println("Erreur mauvais type, resassir");
            return getInt_IME(lecteur);
        }
    }

    public static int getIntMinMax_IME(Scanner lecteur, int min, int max) {
        // { min <= max } =>
        // { résultat = un entier compris entre min et max, saisi par l'utilisateur }
        // L'EXCEPTION inputMismatchException EST GÉRÉE
        int val;
        try {
            do {
                System.out.print("Saisir un entier compris entre " + min + " et " + max + " : ");
                val = lecteur.nextInt();
                lecteur.nextLine();
                if (val < min || val > max) {
                    System.out.println("Erreur : nombre trop petit ou trop grand");
                }
            } while (val < min || val > max);
            return val;
        } catch (InputMismatchException e) {
            lecteur.nextLine();
            System.out.println("Erreur");
            return getIntMinMax_IME(lecteur, min, max);
        }
    }

    public static int getInt_NFE(Scanner lecteur) {
        // { } => {résultat = un entier saisi par l'utilisateur }
        // L'EXCEPTION NumberFormatException EST GÉRÉE
        String s;
        try {
            System.out.print("Saisir un entier : ");
            s = lecteur.nextLine();
            return parseInt(s);
        } catch (NumberFormatException nfe) {
            System.out.println("Erreur");
            return getInt_NFE(lecteur);
        }
    }

    public static float getFloat_NFE(Scanner lecteur) {
        // { } => { résultat = un réel saisi par l'utilisateur }
        // L'EXCEPTION NumberFormatException EST GÉRÉE
        String s;
        try {
            System.out.print("Saisir un float : ");
            s = lecteur.nextLine();
            return parseFloat(s);
        } catch (NumberFormatException nfe) {
            System.out.println("Erreur");
            return getFloat_NFE(lecteur);
        }
    }

    public static int sumIter(ListeChainee<Integer> listInt) {
        // { listInt non vide } =>
        // { résultat = somme des entiers portés par les cellules de listInt }
        Cellule<Integer> cel = listInt.getTete();
        int sum=0;
        while (cel != null){
            sum += cel.getInfo();
            cel = cel.getCelluleSuivante();
        }
        return sum;
    }

    public static int sumRec(ListeChainee<Integer> listInt) {
        // { listInt non vide } =>
        // { résultat = somme des entiers portés par les cellules de listInt }
        Cellule<Integer> cel = listInt.getTete();
        return sumRec_wk(cel);
    }

    private static int sumRec_wk(Cellule<Integer> cellCour) {
        // { } =>
        // { résultat = somme des entiers portés par les cellules d'une sous-liste
        // de tête cellCour }
        if (cellCour == null){
            return 0;
        }else {
            return cellCour.getInfo() + sumRec_wk(cellCour.getCelluleSuivante());
        }
    }

    public static boolean existIntRec(ListeChainee<Integer> listInt, int unInt) {
        // { } =>
        // { résultat = vrai si au moins une cellule de listInt porte l'info unInt }
        if (listInt.estVide()){
            return false;
        }else {
            Cellule<Integer> cel = listInt.getTete();
            return existIntRec_wk(cel, unInt);
        }
    }
    private static boolean existIntRec_wk(Cellule<Integer> cellCour, int unInt) {
        // { } => { résultat = vrai si au moins une cellule d'une sous-liste de tête
        // cellCour porte l'info unInt }
        if (cellCour == null){
            return false;
        }else {
            return (cellCour.getInfo() == unInt || existIntRec_wk(cellCour.getCelluleSuivante(), unInt));
        }
    }

    public static int posFirstSup(ListeChainee<Integer> listInt, int unInt) {
        // { } => {résultat = rang de la première cellule de listInt portant
        // un entier supérieur à unInt, 0 si non trouvée }
        Cellule<Integer> cel = listInt.getTete();
        int pos = 1;
        while ((cel != null) && (cel.getInfo() <= unInt)){
            pos++;
            cel = cel.getCelluleSuivante();
        }
        if (pos == listInt.getLongueur()+1){
            return 0;
        }else {
            return pos;
        }
    }

    public static int bigger(ListeChainee<Integer> listInt) {
        // { listInt non vide } =>
        // { résultat = plus grand entier porté par une cellule de listInt }
        Cellule<Integer> cel = listInt.getTete();
        int max = 0;
        while (cel!=null){
            if (max < cel.getInfo()){
                max = cel.getInfo();
            }
            cel = cel.getCelluleSuivante();
        }
        return max;
    }

    public static ListeChainee<Integer> subList(ListeChainee<Integer> listInt, int position) {
        // { * listInt non vide,
        // * position compris entre 1 et le nombre de cellules de listInt } =>
        // { résultat = une liste d'entiers contenant les cellules de listInt
        // à partir de position }
        ListeChainee<Integer> result = new ListeChainee<>();
        Cellule<Integer> cel = listInt.getTete();
        for (int i = position; i<= listInt.getLongueur(); i++){
            result.insereQueue(cel.getInfo());
            cel = cel.getCelluleSuivante();
        }
        return result;
    }

    public static int nbMult2(ListeChainee<Integer> listeInt) {
        // { } =>
        // { résultat = nombre d'entiers pairs portés par les cellules de listeInt }
        if (listeInt.estVide()){
            return 0;
        }else {
            Cellule<Integer> cel = listeInt.getTete();
            return nbMult2_wk(cel);
        }
    }
    private static int nbMult2_wk(Cellule<Integer> cellCour) {
        // { } =>
        // { résultat = nombre d'entiers pairs portés par les cellules d'une sous-liste
        // de tête cellCour }
        if (cellCour == null){
            return 0;
        }else {
            if (cellCour.getInfo()%2 == 0){
                return 1+nbMult2_wk(cellCour.getCelluleSuivante());
            }else {
                return nbMult2_wk(cellCour.getCelluleSuivante());
            }
        }


    }
}