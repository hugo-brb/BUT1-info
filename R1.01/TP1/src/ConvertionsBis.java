import java.util.Scanner;

public class ConvertionsBis {
    public static void main(String[] args){
        final float EURO_TO_USD = 1.07F, EURO_TO_CHF = 0.96F, EURO_TO_GBP = 0.86F;
        float euros;
        int temp;
        boolean continu;
        Scanner lecteur = new Scanner(System.in);

        do {
            System.out.print("Saisissez un valeur en Euros à convertir : ");
            euros = lecteur.nextFloat();
            lecteur.nextLine();
            System.out.print("Voulez-vous convertir cette somme en dollar américain [1], en franc suisse [2] ou en livre sterling [3] : ");
            temp = lecteur.nextInt();
            lecteur.nextLine();

            if (temp == 1) {
                System.out.print("En dollar américan cela fait : " + (euros * EURO_TO_USD));
            } else if (temp == 2) {
                System.out.print("En franc suisse cela fait : " + (euros * EURO_TO_CHF));
            } else {
                System.out.print("En livre sterling cela fait : " + (euros * EURO_TO_GBP));
            }
            System.out.print("Continuez à convertir ? True/False ");
            continu = lecteur.nextBoolean(); lecteur.nextLine();
        }while (continu);
    }
}
