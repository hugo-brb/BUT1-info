public class EPileVide extends Exception{
    // il faut deux constructeurs
    // constructeur par défaut
    public EPileVide() {
        super();
    }

    // constructeur avec paramètre de type string
    // s : est un message d'erreur associé à l'exception
    public EPileVide(String s) {
        super(s);
    }
}