import java.util.Scanner;

public class Moyenne {
    public static void main(String[] args){
        Scanner lecteur = new Scanner(System.in);
        int nb_notes;
        float moyenne = 0.0F;

        System.out.print("Combien de notes voulez-vous saisir ? ");
        nb_notes = lecteur.nextInt(); lecteur.nextLine();

        for (int i = 1 ; i <= nb_notes; i++) {
            System.out.print("Saisissez la note " + i + " : ");
            moyenne += lecteur.nextFloat();
            lecteur.nextLine();
        }
        moyenne /= nb_notes;

        System.out.print("\nLa moyenne des notes est de : ");
        System.out.format("%.2f", moyenne);
    }
}
