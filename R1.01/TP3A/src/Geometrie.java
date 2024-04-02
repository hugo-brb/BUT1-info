import java.util.Scanner;

public class Geometrie {
    public static void main(String[] args) {

        Rectangle rect;
        float c1, c2;

        System.out.println("------------------------");
        System.out.println("Autour du RECTANGLE...");
        System.out.println("------------------------");
        System.out.println("Longueur d'un des côtés d'un rectangle ? ");
        c1 = Utilitaire.saisieFloatPos();
        System.out.println("\nLongueur d'un de ses côtés adjacents ?");
        c2 = Utilitaire.saisieFloatPos();
        rect = new Rectangle(c1, c2);
        System.out.println(rect.toString());
        System.out.println(rect.cercleInscrit(c1, c2));
        System.out.println(rect.cercleCirconscrit(c1, c2));


        Cercle circle;
        float r;

        System.out.println("------------------------");
        System.out.println("Autour du CERCLE...");
        System.out.println("------------------------");
        System.out.println("Longueur du rayon du cercle ? ");
        r = Utilitaire.saisieFloatPos();
        circle = new Cercle(r);
        System.out.println(circle.toString());

        Triangle tr;

        System.out.println("------------------------");
        System.out.println("Autour du Triangle...");
        System.out.println("------------------------");
        tr = Utilitaire.saisirTriangle();
        System.out.println(tr.toString());

    }
}
