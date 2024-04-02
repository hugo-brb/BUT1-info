public class EBadExponentation extends Exception{
    // constructeur par défaut
    public EBadExponentation() { super(); }

    // constructeur avec paramètre de type string
    // s : est un message d'erreur associé à l'exception
    public EBadExponentation(String s) {
        super(s);
    }
}