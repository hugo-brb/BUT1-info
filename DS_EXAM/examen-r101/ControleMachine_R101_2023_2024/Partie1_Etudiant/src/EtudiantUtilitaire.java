import java.util.Scanner;

public class EtudiantUtilitaire {
    /**
     * À ÉCRIRE - Exercice 1
     * @return cf. postcondition
     */
    public static float saisirNote() {
        // { } =>
        // { résultat = une valeur de type float dans l'intervalle [0,0 .. 20,0]
        //                 saisie par l'utlllisateur }
        Scanner lecteur = new Scanner(System.in);
        float result;
        do {
            System.out.print("Saisir un float compris entre 0,0 et 20,0 : ");
            result = lecteur.nextFloat(); lecteur.nextLine();
            if(result < 0 || result > 20){
                System.out.println("ERREUR DE SAISIE : NOMBRE NON COMPRIS ENTRE 0 ET 20");
            }
        }while (result < 0 || result > 20);
        return result;
    }

}
