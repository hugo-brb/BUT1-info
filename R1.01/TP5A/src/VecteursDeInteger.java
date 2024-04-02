import java.util.ArrayList;
import java.util.Scanner;

public class VecteursDeInteger {
    public static int saisieEntPos() {
        // { } => { résultat = un entier >0, saisi par l'utilisateur }
        Scanner lecteur = new Scanner(System.in);
        int entier;
        do {
            System.out.print("Entrez un entier strictement positif : ");
            entier = lecteur.nextInt(); lecteur.nextLine();
            if (entier <= 0){
                System.out.println("** Error : Saisir un entier > 0");
            }
        }while (entier<=0);
        return entier;
    }

    public static ArrayList<Integer> lesDiviseurs(int unEnt){
        ArrayList<Integer> diviseurs = new ArrayList<>();
        for (int i=1; i<=unEnt; i++){
            if (unEnt%i == 0) diviseurs.add(i);
        }
        return diviseurs;
    }
    private static int sommeDe(ArrayList<Integer> unVectEntPos) {
        // { unVectEntPos non vide, tous ses éléments sont > 0 } =>
        // { résultat = somme des éléments de unVectEntPos }
        int som = 0, i=0;
        while (i != unVectEntPos.size()){
            som += unVectEntPos.get(i);
            i++;
        }
        return som;
    }

    private static boolean estPremier(int unEnt){
        // { unEnt >0 } => { résultat = vrai si unEnt est PREMIER }
        ArrayList<Integer> div= new ArrayList<>(lesDiviseurs(unEnt));
        return (div.size() == 2);
    }
    private static boolean estParfait(int unEnt){
        // {unEnt >0 } => { résultat = vrai si unEnt est PARFAIT }
        ArrayList<Integer> div= new ArrayList<>(lesDiviseurs(unEnt));
        return (unEnt == (sommeDe(div)/2));
    }
    private static boolean estSublime(int unEnt) {
        //{ unEnt >0 } => { résultat = vrai si unEnt est SUBLIME }
        ArrayList<Integer> div= new ArrayList<>(lesDiviseurs(unEnt));
        return (estParfait(div.size()) && estParfait(sommeDe(div)));
    }
    private static String qualiteDe(int unEnt) {
        // { unEnt >0 } =>
        // { résultat = "PREMIER", "PARFAIT", "SUBLIME" ou "QUELCONQUE"
        // selon les propriétés de unEnt }
        if (estPremier(unEnt)){
            return "PREMIER";
        } else if (estParfait(unEnt)) {
            return "PARFAIT";
        } else if (estSublime(unEnt)) {
            return "SUBLIME";
        }else {
            return "QUELCONQUE";
        }
    }


    public static void main(String[] args) {
        int unEnt = saisieEntPos();
        ArrayList<Integer> lesDiv = lesDiviseurs(unEnt);
        System.out.println("* Diviseur de " + unEnt + " : " + lesDiv);
        System.out.println("* Nombre de diviseurs : " + lesDiv.size());
        System.out.println("* Somme des diviseurs : " + sommeDe(lesDiv));
        System.out.println("* Qualité : " + qualiteDe(unEnt));
        for (int i=1; i<lesDiv.size()-1 ; i++){
            System.out.println(" - " + lesDiv.get(i) + " est " + qualiteDe(lesDiv.get(i)));
        }
    }
}
