public class Point {
    // attributs
    private int x; // abscisse
    private int y; // ordonnée
    // constructeurs
    public Point() {
        x = 0;
        y = 0;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // getters et setters des attributs
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Déplacement des coordonnées
     * en abscisse et en ordonnée
     * @param dx déplacement en abscisse
     * @param dy déplacement en ordonnée
     */
    public void deplace(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    /**
     * Affichage avec printXX() en mode mathématique
     * @return (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}