import java.util.Scanner;
public class Conversion {
    public static void main(String[] args){
        float dollars, euros;
        float taux = 0.89f;
        int choix;
        Scanner lecteur = new Scanner(System.in);

        System.out.println("Convertion de dollard à euros ? (tapez 1)");
        System.out.println("Convertion d'euros à dollard ? (tapez 2)");
        choix = lecteur.nextInt();
        lecteur.nextLine();
        if (choix == 1) {
            System.out.print("Saissez un montant en dollards à convertir en euros : ");
            dollars = lecteur.nextFloat(); lecteur.nextLine();
            //euros = dollars * taux;
            //System.out.println("En euros cela fait : " + euros + "€");
            System.out.println("En euros cela fait : " + (dollars * taux) + "€");
        }else if (choix == 2) {
            System.out.print("Saissez un montant en euros à convertir en dollards : ");
            euros = lecteur.nextFloat(); lecteur.nextLine();
            System.out.println("En dollards cela fait : " + (euros/0.89) + "$");
        }

    }
}
