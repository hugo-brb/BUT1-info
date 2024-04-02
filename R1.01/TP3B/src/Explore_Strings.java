import java.util.Arrays;
import java.util.OptionalInt;

public class Explore_Strings {
    private static int nbOccCar(String uneChaine, char unCar) {
        // { } => { résultat = nombre de fois où le caractère unCar
        // apparaît dans la chaîne uneChaine }
        int n = 0;
        for (int i = 0; i < uneChaine.length(); i++){
            if (uneChaine.charAt(i) == unCar) {
                n ++;
            }
        }
        return n;
    }

    private static int nbLettresMajSansAccent(String uneChaine) {
        // { } => { résultat = nombre de lettres majuscules
        // non accentuées dans uneChaine }
        String maj = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int n = 0;
        for (int i = 0; i < uneChaine.length(); i++){
            for (int j = 0; j < maj.length() ; j++){
                if (uneChaine.charAt(i) == maj.charAt(j)) {
                    n ++;
                }
            }
        }
        return n;
    }

    private static int nbMots(String uneChaine) {
        // { uneChaine ne contient que :
        // * des lettres de l'alphabet (accentuées ou non)
        // * des espaces
        // * un point final }
        // => { résultat = nombre de mots dans uneChaine }
        // NOTE : un mot est constitué de lettres de l'alphabet
        String[] mots = uneChaine.split("\\s+"); // Création d'une liste ou chaque élément contient un mot.
        return mots.length; //Retourne le nombre d'éléments de la liste mots.
    }

    private static int nbCarMax(String uneChaine) {
        // { uneChaine ne contient que :
        // * des lettres de l'alphabet (accentuées ou non)
        // * des espaces
        // * un point final }
        // => { résultat = plus grand nombre de caractères
        // d'un mot de uneChaine }
        // NOTE : un mot est constitué de lettres de l'alphabet
        String[] mots = uneChaine.split("\\s+");
        int[] nb = new int[mots.length];
        for (int i = 0; i < mots.length; i++) {
            int temp = 0;
            for (int x = 0; x < mots[i].length(); x++){
                if (mots[i].charAt(x) != '.'){
                    temp++;
                }
            }
            nb[i] = temp;
        }
        OptionalInt max = Arrays.stream(nb).max();
        return max.getAsInt();
    }



    public static void main(String[] args) {
        String lipogramme = "Un rond pas tout à fait clos, fini par un trait horizontal";
        String lesVoyellesSansAccent = "aeiouy";
        String ch1 = "Il y a huit mots dans cette phrase.";
        String ch2 = "CE TP GAGNERAIT À ÊTRE TERMINÉ.";

        System.out.println("Analyse de la chaîne : 'Un rond pas tout à fait clos, fini par un trait horizontal'");
        for (int i = 0; i < lesVoyellesSansAccent.length(); i++) {
            System.out.println("* Nombre de '" + lesVoyellesSansAccent.charAt(i) + "' : " + nbOccCar(lipogramme, lesVoyellesSansAccent.charAt(i)));
        }

        System.out.println("\n----------------");
        System.out.println("NOMBRE DE MOTS");
        System.out.println("----------------");
        System.out.println("Nombre de mots dans la chaîne " + ch1 + " : " + nbMots(ch1));
        System.out.println("Nombre de mots dans la chaîne " + ch2 + " : " + nbMots(ch2));
        System.out.println("--------------------------------");
        System.out.println("NOMBRE DE MAJUSCULES NON ACCENTUÉES");
        System.out.println("--------------------------------");
        System.out.println("Nombre de majuscules non accentuées dans la chaîne " + ch1 + " : " + nbLettresMajSansAccent(ch1));
        System.out.println("Nombre de majuscules non accentuées dans la chaîne " + ch2 + " : " + nbLettresMajSansAccent(ch2));
        System.out.println("--------------------------------");
        System.out.println("PLUS GRAND NOMBRE DE CARACTÈRES D'UN MOT");
        System.out.println("--------------------------------");
        System.out.println("Nombre maximal de caractères d'un mot de la chaîne " + ch1 + " : " + nbCarMax(ch1));
        System.out.println("Nombre maximal de caractères d'un mot de la chaîne " + ch2 + " : " + nbCarMax(ch2));
    }
}
