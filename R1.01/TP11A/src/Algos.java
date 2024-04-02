import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Algos {
    public static int saisieEntMinMax(Scanner lecteur, int min, int max) {
        // { } => { résultat = un entier de l'intervalle [min..max] saisi par l'utilisateur }
        int resultat = 0;
        do {
            try {
                System.out.print("Veuillez saisir un entier entre " + min + " et " + max + " :");
                resultat = lecteur.nextInt(); lecteur.nextLine();
                if (resultat < min || resultat > max) {
                    System.out.println("Mauvaise saisie...");
                }
            } catch (InputMismatchException e) {
                System.out.print("Veuillez saisir un entier entre " + min + " et " + max + " :");
                lecteur.next(); lecteur.nextLine();
            }
        } while (resultat < min || resultat > max);
        return resultat;
    }

    public static int saisieEntSup(Scanner lecteur, int val) {
        // { } => { résultat = un entier supérieur à val, saisi par l'utilisateur }
        // L'exception InputMismatchException qui sera déclenchée si l'utilisateur
        // saisit autre chose qu'un entier est gérée
        int resultat = 0;
        do {
            try {
                System.out.print("Veuillez saisir un entier supérieur à " + val + " :");
                resultat = lecteur.nextInt(); lecteur.nextLine();
                if (resultat <= val) {
                    System.out.println("Mauvaise saisie...");
                }
            } catch (InputMismatchException e) {
                System.out.print("Veuillez saisir un entier supérieur à " + val + " :");
                lecteur.next(); lecteur.nextLine();
            }
        } while (resultat <= val);
        return resultat;
    }

    public static String saisieChaineDeV(Scanner lecteur, ArrayList<String> vString) {
        // { vString non vide }
        // => { résultat = un élément de vString, saisi par l'utilisateur }
        String resultat = "";
        do {
            try {
                System.out.print("Veuillez saisir une valeur parmi : " + vString);
                resultat = lecteur.next(); lecteur.nextLine();
                if (!vString.contains(resultat)) {
                    System.out.println("Mauvaise saisie...");
                }
            } catch (InputMismatchException e) {
                System.out.print("Veuillez saisir une valeur parmi : " + vString);
                lecteur.next(); lecteur.nextLine();
            }
        } while (!vString.contains(resultat));
        return resultat;
    }

    public static int maximum(ArrayList<Integer> vInt) {
        // { vInt non vide } => { résultat = plus grand entier dans vInt }
        int resultat = vInt.get(0);
        for (int i = 1; i < vInt.size(); i++) {
            if (vInt.get(i) > resultat) {
                resultat = vInt.get(i);
            }
        }
        return resultat;
    }

    public static float moyenne(ArrayList<Integer> vInt) throws ExceptionMoyImpossible {
        // { } =>
        // { * si vInt est vide, l'exception ExceptionMoyImpossible est levée
        // avec un message expliquant le problème
        // * sinon, la moyenne des éléments de vInt est retournée }
        if (vInt.isEmpty()) {
            throw new ExceptionMoyImpossible("La moyenne ne peut pas être calculée sur une liste vide");
        }
        float resultat = 0;
        for (Integer integer : vInt) {
            resultat += integer;
        }
        return resultat / vInt.size();
    }

    public static String minOrdreLG(ArrayList<String> vString) {
        // { vString non vide } =>
        // { résultat = plus petite chaîne de vString selon l'ordre lexicographique }
        String resultat = vString.get(0);
        for (int i = 1; i < vString.size(); i++) {
            if (vString.get(i).compareTo(resultat) < 0) {
                resultat = vString.get(i);
            }
        }
        return resultat;
    }

    public static int somme(ArrayList<Integer> vInt) {
        // { vInt non vide } => { résultat = somme des éléments de vInt }
        if (vInt.isEmpty()){
            return 0;
        }else {
            return somme_wk(vInt, 0);
        }
    }

    private static int somme_wk(ArrayList<Integer> vInt, int dep) {
        if(dep == vInt.size()){
            return dep;
        }else{
            return vInt.get(dep) + somme_wk(vInt, dep+1);
        }
    }

    public static int nbEntDeVal(ListeTrieeC<Integer> lIntC, int unEnt) {
        // { } => { résultat = nombre d'entiers égaux à unEnt dans lIntC }
        if (lIntC.estVide()){
            return 0;
        }else {
            Cellule<Integer> cel = lIntC.getTete();
            return nbEntDeVal_wk(cel, unEnt);
        }
    }

    private static int nbEntDeVal_wk(Cellule<Integer> cellCour, int unEnt) {
        if (cellCour.getCelluleSuivante() == null){
            if (cellCour.getInfo() == unEnt){
                return 1;
            }else {
                return 0;
            }
        }else {
            if (cellCour.getInfo() == unEnt){
                return 1 + nbEntDeVal_wk(cellCour.getCelluleSuivante(), unEnt);
            }else {
                return nbEntDeVal_wk(cellCour.getCelluleSuivante(), unEnt);
            }
        }
    }

    public static int nbJetonsSup3(ArrayList<Jeton> vJetons) {
        // { vJetons non vide } =>
        // { résultat = nombre d'éléments de vJetons dont la valeur est supérieure à 3}
        int resultat = 0;
        for (Jeton jeton : vJetons) {
            if (jeton.getValeur() > 3) {
                resultat++;
            }
        }
        return resultat;
    }

    public static boolean existValSeq_it(ArrayList<Integer> vInt, int val) {
        // { } => { résultat = vrai si val est un élément de vInt}
        for (Integer integer : vInt) {
            if (integer == val) {
                return true;
            }
        }
        return false;
    }

    public static boolean existValSeq_rec(ArrayList<Integer> vInt, int val) {
        // { } => { résultat = vrai si val est un élément de vInt }
        return existValSeq_rec_wk(vInt, val, 0);
    }
    private static boolean existValSeq_rec_wk(ArrayList<Integer> vInt, int val, int indice) {
        // { 0 <= indice < vInt.size() } =>
        // { résultat = vrai si val est un élément de vInt[indice..vInt.size()-1] }
        if (indice == vInt.size()) {
            return false;
        } else {
            if (vInt.get(indice) == val) {
                return true;
            } else {
                return existValSeq_rec_wk(vInt, val, indice + 1);
            }
        }
    }

    public static int rechIndDicho(ArrayList<Integer> vInt, int val) {
        // { vInt trié } =>
        // { résultat = indice de la 1ère occurrence de val dans vInt si trouvé, -1 sinon}
        int inf = 0;
        int sup = vInt.size() - 1;
        int milieu;
        while (inf <= sup) {
            milieu = (inf + sup) / 2;
            if (vInt.get(milieu) == val) {
                return milieu;
            } else {
                if (vInt.get(milieu) < val) {
                    inf = milieu + 1;
                } else {
                    sup = milieu - 1;
                }
            }
        }
        return -1;
    }

    public static int rechPos(ListeTrieeC<String> lString, String uneChaine) {
        // { } =>
        // { résultat = position de la 1ère occurrence de uneChaine dans lString,
        // 0 si non trouvée}
        if (lString.estVide()) {
            return 0;
        } else {
            Cellule<String> cel = lString.getTete();
            int pos = 0;
            while (cel != null) {
                if (cel.getInfo().equals(uneChaine)) {
                    return pos;
                } else {
                    cel = cel.getCelluleSuivante();
                    pos++;
                }
            }
            return 0;
        }
    }

    public static void triBulle(ArrayList<Integer> vInt) {
        // { } => { vInt a été trié par la méthode du tri à bulles amélioré }
        int taille = vInt.size();
        boolean permut;
        do {
            permut = false;
            for (int i = 0; i < taille - 1; i++) {
                if (vInt.get(i) > vInt.get(i + 1)) {
                    int temp = vInt.get(i);
                    vInt.set(i, vInt.get(i + 1));
                    vInt.set(i + 1, temp);
                    permut = true;
                }
            }
            taille--;
        } while (permut);
    }

    public static ArrayList<Jeton> vJetonTrie(ArrayList<Jeton> vJetons) {
        // { vJetons non vide } =>
        // { résultat = vecteur de Jeton trié dont les éléments sont ceux de vJetons }
        // Méthode utilisée pour le tri : tri par insertion
        ArrayList<Jeton> resultat = new ArrayList<>();
        for (Jeton jeton : vJetons) {
            int i = 0;
            while (i < resultat.size() && resultat.get(i).getValeur() < jeton.getValeur()) {
                i++;
            }
            resultat.add(i, jeton);
        }
        return resultat;
    }

    public static boolean veriftri(ArrayList<Jeton> vJeton) {
        // { vJeton non vide }=> {
        // { résultat = vrai si vJeton trié selon l'ordre naturel de la classe Jeton, faux sinon }
        if (vJeton.size() == 0 || vJeton.size() == 1){
            return true;
        }else {
            boolean estTrie = true; int i=1;
            while (estTrie && i < vJeton.size()){
                if (vJeton.get(i).compareTo(vJeton.get(i-1)) < 0){
                    estTrie = false;
                }
                i++;
            }
            return estTrie;
        }
    }
}
