/**
 * Classe générique pour outiller une fonction
 * @param <R> : R est le type du résultat de la fonction outillée
 */
public class PaireResultatCompteur<R> {
    private R res; // résultat de la fonction outillée
    private int compteur; // compteur du code observé
    // (ici nombre de comparaisons effectuées)
    // constructeur
    public PaireResultatCompteur(R res, int compteur) {
        this.res = res;
        this.compteur = compteur;
    }
    // getters
    public R getRes() {
        return res;
    }
    public int getCompteur() {
        return compteur;
    }
}
