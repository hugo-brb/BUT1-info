public class PileFloat {
        private int sommet; // indice de la dernière valeur empilée
        private final float[] pile; // tableau pour représenter une pile de float

        // constructeur
        public PileFloat(int capacite) {
            // {capacite = nombre de niveaux de la pile à construire}
            // => {cette pile est dimensionnée, sommet = -1 (aucune valeur empilée)}
            this.pile = new float[capacite];
            this.sommet = -1;
        }

        // getters
        int getSommet() {
            return sommet;
        }

        float getValPile(int pos) {
            // {pos compris entre 0 et nombre de niveaux de la pile} =>
            // {résultat = valeur de pile à l'indice pos}
            return pile[pos];
        }

        //----- méthodes – À COMPLÉTER ------
        boolean estVide() {
            // {} => {résultat = vrai si sommet = -1}
            if (this.sommet == -1) {
                return true;
            } else {
                return false;
            }
        }

        boolean estPleine() {
            //{} => {résultat = vrai si sommet = capacité-1}
            if (this.sommet == this.pile.length - 1) {
                return true;
            } else {
                return false;
            }
        }

        void empile(float val) throws EPilePleine {
            //{} => {si la pile n'est pas pleine, val a été insérée dans la pile et
            // sommet est mis à jour, sinon, l'exception EPilePleine est levée
            // avec un message approprié et la pile est réinitialisée}
            if (this.estPleine()) {
                throw new EPilePleine("La pile est pleine");
            } else {
                this.sommet++;
                this.pile[this.sommet] = val;
            }
        }

        float depile() throws EPileVide {
            //{} => {si la pile n'est pas vide, la dernière valeur empilée a été retirée
            // de la pile et sommet est mis à jour, sinon, l'exception EPileVide est levée
            // avec un message approprié}
            if (this.estVide()) {
                throw new EPileVide("La pile est vide");
            } else {
                float val = this.pile[this.sommet];
                this.sommet--;
                return val;
            }
        }
}
