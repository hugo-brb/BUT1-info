public class Rectangle {
    // un rectangle est défini par la longueur de ses côtés
    // quelle que soit sa position dans le plan

    // Attributs
    private float cote1; // longueur d'un côté
    private float cote2; // longueur des côtés adjacents à cote1

    // Constructeur
    public Rectangle(float cote1, float cote2) {
        this.cote1 = cote1;
        this.cote2 = cote2;
    }

    // Méthodes
    // 1 - getters 
    //1.1 - valeur de cote1
    public float getCote1() {
        return this.cote1;
     }
    //1.2 - valeur de cote2
    public float getCote2() {
        return this.cote2;
     }

    //2 - périmètre
    public float getPerimetre() {
        // { } => { résultat = périmètre de ce Rectangle }
        return ((2*getCote1()) + (2*getCote2()));
    }


    // 3 - surface
    public float getSurface() {
        // { } => { résultat = surface de ce Rectangle }
        return (getCote1()*getCote2());
     }

     public String cercleInscrit(float cote1, float cote2) {
        /// { } => { résultat = le Cercle inscrit dans ce Rectangle }
         float r;
         System.out.println("\nCercle inscrit dans ce rectangle");
         if (cote1 < cote2){
             r = cote2/2;
         }else{
             r = cote1/2;
         }
         float perim = (float) (2*Math.PI*r);
         float surface = (float) (Math.PI*r*r);
         return "Rayon : " + r + " | Périmètre : " + perim + " | Surface : " + surface;
     }

    public String cercleCirconscrit(float cote1, float cote2) {
        // { } => { résultat = le Cercle circonscrit à ceRectangle }
        float r = (float)(Math.sqrt(Math.pow(cote1, 2) + Math.pow(cote2, 2))/2);
        System.out.println("\nCercle circonscrit dans ce rectangle");
        float perim = (float) (2*Math.PI*r);
        float surface = (float) (Math.PI*r*r);
        return "Rayon : " + r + " | Périmètre : " + perim + " | Surface : " + surface;
    }

    // Pour l'affichage des cotés, du périmètre et de la surface de ce Rectangle
    @Override
    public String toString() {
        return "Cotés : ("+ cote1 + ", " + cote2 + ") | Périmètre : " + getPerimetre() + " | Surface : " + getSurface();
    }
}