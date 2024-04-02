import java.util.Scanner;

public class Triangle_Main2 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------------------------\n" +
                        "Saisie des trois sommets ptA, ptB, ptC d'un triangle\n" +
                        "-------------------------------------------------------------------------------------\n" +
                "(1) Coordonnées du sommet ptA");
        Point ptA = Triangle_Utilitaire.saisirPoint();
        System.out.println("(2) Coordonnées du sommet ptB");
        Point ptB = Triangle_Utilitaire.saisirPointNonConfondu(ptA);
        System.out.println("(3) Coordonnées du sommet ptC");
        Point ptC = Triangle_Utilitaire.saisirPointNonAligneP1P2(ptA, ptB);
        TriangleCompose tComp = new TriangleCompose(ptA, ptB, ptC);
        TriangleCompose tCompH, tCompV;
        Scanner lecteur = new Scanner(System.in);
        int dx, dy;

        System.out.println("-------------------------------------------------------------------------------------\n" +
                "Coordonnées initiales des points ptA, ptB et ptC : ptA(" + ptA.getX() + ", " + ptA.getY()
                + ") ptB(" + ptB.getX() + ", " + ptB.getY()
                + ") ptC(" + ptC.getX() + ", " + ptC.getY() + ")\n" +
                "-------------------------------------------------------------------------------------\n" +
                "Triangle construit avec les points ptA, ptB et ptC en composition");
        System.out.println(tComp);
        System.out.println("Triangle symétrique par rapport à l'axe horizontal");
        tCompH = Triangle_Utilitaire.symetriqueH(tComp);
        System.out.println(tCompH);
        System.out.println("Triangle symétrique par rapport à l'axe vertical");
        tCompV = Triangle_Utilitaire.symetriqueV(tComp);
        System.out.println(tCompV);
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
    }
}
