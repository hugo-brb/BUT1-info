public class TriangleAgrege {
        //Attributs
        private Point somA;
        private Point somB;
        private Point somC;

        //Constructeur
        public TriangleAgrege(Point somA, Point somB, Point somC){
            this.somA = somA;
            this.somB = somB;
            this.somC = somC;
        }

        //Méthodes
        public Point getSomA(){
            return somA;
        }

        public Point getSomB() {
            return somB;
        }

        public Point getSomC() {
            return somC;
        }

        public double getCoteAB(){
            // { } => { résultat = longueur du côté d'extrémités somA et somB }
            return Math.sqrt(Math.pow((somA.getX()-somB.getX()), 2) + Math.pow((somA.getY()- somB.getY()), 2));
        }

        public double getCoteAC() {
            // { } => { résultat = longueur du côté d'extrémités somA et somC }
            return  Math.sqrt(Math.pow((somA.getX()-somC.getX()), 2) + Math.pow((somA.getY()- somC.getY()), 2));
        }

        public double getCoteBC() {
            // { } => { résultat = longueur du côté d'extrémités somB et somC }
            return  Math.sqrt(Math.pow((somB.getX()-somC.getX()), 2) + Math.pow((somB.getY()- somC.getY()), 2));
        }

        public double perimetre() {
            // { } => { résultat = périmètre de ce Triangle }
            return getCoteAB()+getCoteAC()+getCoteBC();
        }

        public double surface() {
            // { } => { résultat = surface de ce Triangle }
            double p = perimetre()/2;
            return Math.sqrt(p*(p-getCoteAB()) * (p-getCoteAC()) * (p-getCoteBC()));
        }

        public void deplacer(int dx, int dy) {
            // { } => { les sommets de ce Triangle sont déplacés
            // horizontalement de dx et verticalement de dy }
            somA.deplace(dx, dy);
            somB.deplace(dx, dy);
            somC.deplace(dx, dy);
        }

        @Override
        public String toString() {
            return "* Sommets : " + somA + " " + somB + " " + somC + " \n" +
                    "* Périmètre : " + Math.round(100 * perimetre()) / 100.0 + "\n" +
                    "* Surface : " + Math.round(100 * surface()) / 100.0;
        }
}
