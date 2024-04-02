import java.util.Scanner;
public class Permutation {
    public static void main(String[] args) {
        int a, b, c, d, e, sauv;
        Scanner lecteur = new Scanner(System.in);

        System.out.println("Saissez un premier entier :");
        a = lecteur.nextInt(); lecteur.nextLine();
        System.out.println("Saissez un deuxième entier :");
        b = lecteur.nextInt(); lecteur.nextLine();
        System.out.println("Saissez un troisième entier :");
        c = lecteur.nextInt(); lecteur.nextLine();
        System.out.println("Saissez un quatrième entier :");
        d = lecteur.nextInt(); lecteur.nextLine();
        System.out.println("Saissez un cinquième entier :");
        e = lecteur.nextInt(); lecteur.nextLine();

        sauv = a; a = b; b = c; c = d; d = e; e = sauv;

        System.out.println(a + " " + b + " " + c + " " + d + " " + e);

    }
}
