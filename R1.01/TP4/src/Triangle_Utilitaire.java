import java.util.Scanner;

public class Triangle_Utilitaire {
    public static Point saisirPoint() {
        // { } => {résultat = un Point dont les coordonnées sont saisies par l'utilisateur}
        Scanner lecteur = new Scanner(System.in);
        int x, y;
        System.out.print("* Abscisse ? ");
        x = lecteur.nextInt(); lecteur.nextLine();
        System.out.print("* Ordonnée ? ");
        y = lecteur.nextInt(); lecteur.nextLine();
        return new Point(x, y);
    }

    public static Point saisirPointNonConfondu(Point lePoint) {
        // { } => {résultat = un Point non confondu avec lePoint,
        // dont les coordonnées sont saisies par l'utilisateur}
        Scanner lecteur = new Scanner(System.in);
        Point point;
        do {
            point = saisirPoint();
            if (point.getX() == lePoint.getX() & point.getY() == lePoint.getY()){
                System.out.println("--> Point confondu avec (" + lePoint.getX() + ", " + lePoint.getY() + "), recommencez..." );
            }
        } while (point.getX() == lePoint.getX() && point.getY() == lePoint.getY());
        return point;
    }

    public static Point saisirPointNonAligneP1P2(Point p1, Point p2) {
        // { } => { résultat = un Point de coordonnées saisies par l'utilisateur,
        // différent de p1 et de p2 et non aligné avec p1 et p2 }
        Scanner lecteur = new Scanner(System.in);
        Point point;

        do {
            point = saisirPoint();
            if ((point.getX() == p1.getX() && point.getY() == p1.getY()) || (point.getX() == p2.getX() && point.getY() == p2.getY())){
                System.out.println("--> Point égale à (" + p1.getX() + ", " + p1.getY() +
                        ") ou (" + p2.getX() + ", " + p2.getY() + ")..." );
            }
            if ((p1.getX() == p2.getX() && p1.getX() == point.getX())
                    || (p1.getY() == p2.getY() && p1.getY() == point.getY())
                    || (p2.getX() != point.getX() && (p2.getX()-p1.getX() != 0) && (point.getX()- p1.getX() != 0) && ((p2.getY()-p1.getY())/(p2.getX()-p1.getX())) == ((point.getY()-p1.getY())/(point.getX()- p1.getX())))){
                System.out.println("--> Point est aligné avec (" + p1.getX() + ", " + p1.getY() +
                ") et (" + p2.getX() + ", " + p2.getY() + ")...");
            }
        } while (((point.getX() == p1.getX() && point.getY() == p1.getY()) || (point.getX() == p2.getX() && point.getY() == p2.getY()))
                || ((p1.getX() == p2.getX() && p1.getX() == point.getX())
                || (p1.getY() == p2.getY() && p1.getY() == point.getY())
                || (p2.getX() != point.getX() && (p2.getX()-p1.getX() != 0) && (point.getX()- p1.getX() != 0) && ((p2.getY()-p1.getY())/(p2.getX()- p1.getX())) == ((point.getY()-p1.getY())/(point.getX()- p1.getX())))));
        return point;
    }

    public static TriangleCompose symetriqueH(TriangleCompose tComp) {
        // { } =>
        // { résultat = nouveau TriangleCompose dont les sommets sont obtenus par
        // symétrie des sommets de tComp par rapport à l'axe horizontal }
        int a = tComp.getSomA().getY();
        int b = tComp.getSomB().getY();
        int c = tComp.getSomC().getY();
        return new TriangleCompose(new Point(tComp.getSomA().getX(),-a), new Point(tComp.getSomB().getX(), -b), new Point(tComp.getSomC().getX(), -c));
    }

    public static TriangleCompose symetriqueV(TriangleCompose tComp) {
        // { } =>
        // { résultat = nouveau TriangleCompose dont les sommets sont obtenus par
        // symétrie des sommets de tComp par rapport à l'axe vertical }
        int a = tComp.getSomA().getX();
        int b = tComp.getSomB().getX();
        int c = tComp.getSomC().getX();
        return new TriangleCompose(new Point(-a, tComp.getSomA().getY()), new Point(-b, tComp.getSomB().getY()), new Point(-c, tComp.getSomC().getY()));
    }
}
