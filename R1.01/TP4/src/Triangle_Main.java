import java.util.Scanner;

public class Triangle_Main {
    public static void main(String[] args) {
        Point ptA = new Point(1,1), ptB = new Point(3,5), ptC = new Point(5,1);
        TriangleCompose tComp = new TriangleCompose(ptA, ptB, ptC);
        TriangleAgrege tAgr = new TriangleAgrege(ptA, ptB, ptC);
        Scanner lecteur = new Scanner(System.in);
        int dx, dy;

        System.out.println("-------------------------------------------------------------------------------------\n" +
                "Coordonnées initiales des points ptA, ptB et ptC : ptA(1, 1) ptB(3, 5) ptC(5, 1)\n" +
                "-------------------------------------------------------------------------------------\n" +
                "Triangle construit avec les points ptA, ptB et ptC en composition");
        System.out.println(tComp + "\n------------\n" +
                "Triangle construit avec les points ptA, ptB et ptC en agrégation");
        System.out.println(tAgr);
        System.out.print("-----------------------------------------\n" +
                "DÉPLACEMENT DES TRIANGLES\n" +
                "-----------------------------------------\n" +
                "Déplacement horizontal des sommets ? ");
        dx = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("Déplacement vertical des sommets ? ");
        dy = lecteur.nextInt(); lecteur.nextLine();

        System.out.println("======================================================================\n" +
                "Après déplacement du triangle construit avec les points ptA, ptB et ptC en composition\n" +
                "======================================================================\n" +
                "Coordonnées des points ptA, ptB et ptC : ptA(" + tComp.getSomA().getX() + ", " + tComp.getSomA().getY() +
                ") ptB(" + tComp.getSomB().getX() + ", " + tComp.getSomB().getY() +
                ") ptC(" + tComp.getSomC().getX() + ", " + tComp.getSomC().getY() + ")\n" +
                "Caractéristiques du triangle :\n");
        tComp.deplacer(dx, dy);
        System.out.println(tComp);
        tAgr.deplacer(dx, dy);

        System.out.println("=====================================================================\n" +
                "Après déplacement du triangle construit avec les points ptA, ptB et ptC en agrégation\n" +
                "=====================================================================\n" +
                "Coordonnées des points ptA, ptB et ptC : ptA(" + tAgr.getSomA().getX() + ", " + tAgr.getSomA().getY() +
                ") ptB(" + tAgr.getSomB().getX() + ", " + tAgr.getSomB().getY() +
                ") ptC(" + tAgr.getSomC().getX() + ", " + tAgr.getSomC().getY() + ")\n" +
                "Caractéristiques du triangle :\n" + tAgr);

    }
}
