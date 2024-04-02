import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VecteursDeString {
    private static int nbChEgales(ArrayList<String> v1, ArrayList<String> v2) {
        // { v1 non vide, sans doublons, de même taille que v2 } =>
        // { résultat = nombre d'éléments de v1 dont la valeur est égale
        // à celle de l'élément de même indice dans v2 }
        int nb = 0;
        for (int i=0; i<v1.size(); i++){
            if (v1.get(i).equals(v2.get(i))){
                nb++;
            }
        }
        return nb;
    }

    public static String equiv(String uneChaine, ArrayList<String> v1, ArrayList<String> v2) {
        // { v1 non vide, sans doublons, de même taille que v2 } =>
        // { résultat = * chaîne du vecteur v2, ayant le même indice que
        // uneChaine dans v1 si uneChaine est un élément de v1,
        // * chaîne vide sinon }
        int i = 0;
        while (i<v1.size() && !v1.get(i).equals(uneChaine)){
            i++;
        }
        if (i< v1.size()){
            return v2.get(i);
        }
        return "";
    }

    public static void main(String[] args) {
        ArrayList<String> arc_ciel_fr = new ArrayList<>(Arrays.asList("rouge", "orange", "jaune", "vert", "bleu", "indigo", "violet"));
        ArrayList<String> arc_ciel_en = new ArrayList<>(Arrays.asList("red", "orange", "yellow", "green", "blue", "indigo", "purple"));
        System.out.println("Couleur de l'arc en ciel en français : " + arc_ciel_fr);
        System.out.println("Couleur de l'arc en ciel en anglais : " + arc_ciel_en);
        System.out.println("--------------------");
        System.out.println("Nombre de couleurs de même nom : " + nbChEgales(arc_ciel_fr, arc_ciel_en));
        char c = 'n';
        do {
            System.out.println("--------------------");
            Scanner lecteur = new Scanner(System.in);
            String val;
            System.out.print("Entrez le nom en français d'une des 7 couleurs de l'arc en ciel : ");
            val = lecteur.nextLine();
            if (!arc_ciel_fr.contains(val)){
                System.out.println("La couleur " + val + " ne fait pas partie des 7 couleurs de l'arc en ciel en français");
            }else{
                System.out.println("--> l'équivalent de " + val + " parmi les noms des 7 couleurs de l'arc en ciel en anglais est : "
                        + equiv(val, arc_ciel_fr, arc_ciel_en));
            }
            System.out.print("*** Recommencer (o pour continuer / n pour arrêter) ? ");
            c = lecteur.next().charAt(0); lecteur.nextLine();
        }while (c == 'o');
    }
}
