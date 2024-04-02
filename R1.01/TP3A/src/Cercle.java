public class Cercle {
    // Constante PI (rapport constant du périmètre d'un cercle à son diamètre)
    final double PI = 3.14;
    // Attribut
    float rayon;  // longueur du rayon

    // Constructeur
    public Cercle(float rayon) {
        this.rayon = rayon;
    }

    // Méthodes
    // 1 - getter
    public float getRayon() {
        return this.rayon;
    }

    //2 - périmètre
    public float getPerimetre() {
        // { } => { Résultat = périmètre de ce Cercle }
        return (float) (2*PI*getRayon());
    }

    // 3 - surface
    public float getSurface() {
        // { } => { Résultat = surface de ce Cercle }
        return (float) (Math.pow(getRayon(), 2)*PI);
    }

    // Pour l'affichage du rayon, du périmètre et de la surface de ce Cercle
    @Override
    public String toString() {
        return "Rayon : "+ rayon + " | Périmètre : " + getPerimetre() + " | Surface : " + getSurface();
    }
}
