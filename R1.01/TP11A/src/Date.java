public class Date implements Comparable<Date> {
    // 3 attributs de type int : jour, mois, annee
    private int jour;
    private int mois;
    private int annee;

    // constructeur
    public Date(int jour, int mois, int annee) {
        // { } => { jour, mois et annee sont initialisés avec les valeurs passées en paramètre }
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }
    // getters
    public int getJour() {
        return this.jour;
    }
    public int getMois() {
        return this.mois;
    }
    public int getAnnee() {
        return this.annee;
    }

    @Override
    public int compareTo(Date o) {
        // Ordre choronologique
        // Comparaison sur les années
        if (this.annee < o.annee) {
            return -1;
        } else if (this.annee > o.annee) {
            return 1;
        } else {
            // Comparaison sur les mois
            if (this.mois < o.mois) {
                return -1;
            } else if (this.mois > o.mois) {
                return 1;
            } else {
                // Comparaison sur les jours
                if (this.jour < o.jour) {
                    return -1;
                } else if (this.jour > o.jour) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    public String toString() {
        // affichage sous la forme jour/mois/annee
        return this.jour + "/" + this.mois + "/" + this.annee;
    }

}
