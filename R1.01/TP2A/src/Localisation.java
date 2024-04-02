import java.util.Scanner;

public class Localisation {
    public static void main(String[] args) {
        int x, y;
        boolean continu;
        Scanner lecteur = new Scanner(System.in);

        do {
            System.out.println("Nous allons déterminer votre position géographique par rapport à un point de référence ... ");
            System.out.print("Abscisse de votre position ? ");
            x = lecteur.nextInt();
            lecteur.nextLine();
            System.out.print("Ordonnée de votre position ? ");
            y = lecteur.nextInt();
            lecteur.nextLine();

            if (x == 0 & y == 0){
                System.out.println("Position : Centre");
            }else {
                if (x == 0) {
                    if (y > 0) {
                        System.out.println("Position : Nord");
                    } else {
                        System.out.println("Position : Sud");
                    }
                }

                if (y == 0) {
                    if (x > 0) {
                        System.out.println("Position : Est");
                    } else {
                        System.out.println("Position : Ouest");
                    }
                }
                if (y > 0 & x != 0) {
                    if (x > 0) {
                        System.out.println("Position : Nord-Est");
                    }else {
                        System.out.println("Position : Nord-Ouest");
                    }

                }else if (y < 0 & x != 0) {
                    if (x > 0) {
                        System.out.println("Position : Sud-Est");
                    }else {
                        System.out.println("Position : Sud-Ouest");
                    }
                }
            }


            System.out.print("Voulez vous saissir de nouvelles coordonées ? (true/false) ");
            continu = lecteur.nextBoolean(); lecteur.nextLine();
        } while (continu);

    }
}
