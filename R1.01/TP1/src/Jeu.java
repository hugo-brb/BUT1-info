import java.util.Scanner;

public class Jeu {
    public static void main(String[] args) {
        String nom;
        int naissance, pointure, calcul, resultat;
        Scanner lecteur = new Scanner(System.in);

        System.out.println("Bonjour !");
        System.out.println(" ");
        System.out.print("Donner votre nom : ");
        nom = lecteur.nextLine();
        System.out.print("Donner votre année de naissance : ");
        naissance = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("Donner votre pointure : ");
        pointure = lecteur.nextInt(); lecteur.nextLine();

        calcul = pointure*5;
        System.out.println("(1) On multiplie votre pointure par 5 : "+ calcul);
        calcul += 50;
        System.out.println("(2) 0n ajoute 50 : "+ calcul);
        calcul *= 20;
        System.out.println("(3) On multiplie par 20 : "+ calcul);
        calcul += 1023;
        System.out.println("(4) 0n ajoute 1023 : "+ calcul);
        calcul -= naissance;
        System.out.println("(5) On soustrait votre année de naissance :\n" +
                "----------------\n" +
                "RESULTAT = "+ calcul +"\n" +
                "----------------");

        System.out.print("Les 2 premiers chiffres sont : "+ (calcul/100) + "\n" +
                "> C'est votre pointure !!!\n" +
                "Les 2 derniers chiffres sont : "+ (calcul%100) + "\n" +
                "> C'est l'âge que vous atteignez cette année !!!\n" +
                "AU REVOIR " + nom + " !");
    }
}
