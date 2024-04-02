import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Calendrier {
    public static void main(String[] args) {
        int uneAnne, nbJours;
        Scanner lecteur = new Scanner(System.in);

        System.out.print("Saisissez une année (entier positif) : ");
        uneAnne = lecteur.nextInt(); lecteur.nextLine();

        if ((uneAnne % 100) == 0) {
            System.out.println("Cette année marque la fin du " + (uneAnne/100) + "ème siècle");
            if (((uneAnne / 100)% 4) == 0){
                System.out.print("Cette année est bissextile et comporte 366 jours");
            }else{
                System.out.print("Cette année n'est pas bissextile et comporte 365 jours");
            }
        }else {
            System.out.println("Cette année n'est pas une fin de siècle");
            if ((uneAnne % 4) == 0){
                System.out.print("Cette année est bissextile et comporte 366 jours");
            }else{
                System.out.print("Cette année n'est pas bissextile et comporte 365 jours");
            }
        }






    }
}
