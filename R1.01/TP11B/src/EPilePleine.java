public class EPilePleine extends Exception{
    // il faut deux constructeurs
    // constructeur par défaut
    public EPilePleine() {
        super();
    }

    // constructeur avec paramètre de type string
    // s : est un message d'erreur associé à l'exception
    public EPilePleine(String s) {
        super(s);
    }
}