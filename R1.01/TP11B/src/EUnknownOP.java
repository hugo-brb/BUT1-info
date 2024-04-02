public class EUnknownOP extends Exception{
    // constructeur par défaut
    public EUnknownOP() { super(); }

    // constructeur avec paramètre de type string
    // s : est un message d'erreur associé à l'exception
    public EUnknownOP(String s) {
        super(s);
    }
}