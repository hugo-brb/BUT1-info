public class EDivideByZero extends Exception {

    // il faut deux constructeurs
    // constructeur par défaut
    public EDivideByZero() { super(); }

    // constructeur avec paramètre de type string
    // s : est un message d'erreur associé à l'exception
    public EDivideByZero(String s) {
        super(s);
    }

}