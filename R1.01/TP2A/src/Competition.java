import java.util.Scanner;

public class Competition {
    public static void main(String[] args) {
        float moy = 0.0F, temp, min = 20.0F, max = 0.0F;
        int nbJuges;
        Scanner lecteur = new Scanner(System.in);

        System.out.print("Nombre de juges ? ");
        nbJuges = lecteur.nextInt(); lecteur.nextLine();

        for (int i = 1 ; i <= nbJuges ; i ++) {
            do {
                System.out.print("Temps estimé par le juge N°" + i + " (<= 20.0) ? ");
                temp = lecteur.nextFloat(); lecteur.nextLine();
            } while(temp < 0 | temp > 20);
            if (temp < min){
                min = temp;
            }else if (temp > max){
                max = temp;
            }
            moy += temp;
        }
        moy /= nbJuges;

        System.out.println("\nLe temps minimum estimé est de : " + min +
                "\n Le temps maximum estimé est de : " + max +
                "\n La moyenne des temps estimé est de : " + moy);

    }
}
