import java.util.Scanner;

public class Calculette {
    public static final String[] opBinaire = new String[]{"divise", "exposant", "moins", "multiplie", "plus"};
    public static float calcul(float f1, float f2, String op)
            throws EDivideByZero, EBadExponentation, EUnknownOP {
        //{op représente une opérateur binaire} =>
        // {résultat = réel résultat de l'opération f1 op f2 si elle est valide
        // - l'exception EDivideByZero est levée si f2 est nul
        // et op est l'opérateur de division
        // - l'exception EBadExponentation est levée si f1 est négatif, f2 est inférieur à 1
        // et op est l'opérateur d'exponentiation
        // - l'exception EUnknownOP est levée si op n'est pas un opérateur connu}
        if (op.equals("divise")) {
            if (f2 == 0) {
                throw new EDivideByZero("Division par zéro");
            } else {
                return f1 / f2;
            }
        } else if (op.equals("exposant")) {
            if (f1 < 0 && f2 < 1) {
                throw new EBadExponentation("Exponentiation impossible");
            } else {
                return (float) Math.pow(f1, f2);
            }
        } else if (op.equals("moins")) {
            return f1 - f2;
        } else if (op.equals("multiplie")) {
            return f1 * f2;
        } else if (op.equals("plus")) {
            return f1 + f2;
        } else {
            throw new EUnknownOP("L'opérateur est inconnu");
        }
    }

    public static boolean estOpBinaire(String op) {
        // {} => {résultat = vrai si op est un opérateur binaire}
        for (int i = 0; i < opBinaire.length; i++) {
            if (op.equals(opBinaire[i])) {
                return true;
            }
        } return false;
    }

    public static boolean isFloat(String s) {
        //{} => {résultat = vrai si s représente un float}
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static float resExp(String exp, PileFloat pCalc)
            throws EPilePleine, EPileVide, EDivideByZero, EBadExponentation, EUnknownOP {
        //{exp représente une séquence de calcul RPN (ex : 3 ENTER 2 plus 5 multiplie}
        // => {résultat = valeur résultant du calcul s'il est possible, sinon
        // l'exception levée est traitée et un message approprié est affiché}
        String[] expSplit = exp.split(" ");
        for (int i = 0; i < expSplit.length; i++) {
            if (isFloat(expSplit[i])) {
                pCalc.empile(Float.parseFloat(expSplit[i]));
            } else if (expSplit[i].equals("ENTER")) {
                pCalc.empile(pCalc.getValPile(pCalc.getSommet()));
            } else if (estOpBinaire(expSplit[i])) {
                float f1 = pCalc.depile();
                float f2 = pCalc.depile();
                pCalc.empile(calcul(f2, f1, expSplit[i]));
                System.out.println(pCalc.getValPile(pCalc.getSommet()));
            }
        } return pCalc.depile();
    }

    public static void main(String[] args) {
        boolean continu = false;
        do {
            PileFloat pCalc = new PileFloat(10);
            Scanner lecteur = new Scanner(System.in);
            System.out.print("Entrez une expression : ");
            String exp = lecteur.nextLine();
            try {
                System.out.println(resExp(exp, pCalc));
            } catch (EPilePleine e) {
                System.out.println("Pile pleine");
            } catch (EPileVide e) {
                System.out.println("Pile vide");
            } catch (EDivideByZero e) {
                System.out.println("Division par zéro");
            } catch (EBadExponentation e) {
                System.out.println("Exponentiation impossible");
            } catch (EUnknownOP e) {
                System.out.println("L'opérateur est inconnu");
            }
            System.out.print("Voulez-vous faire une autre opération ? [true/false] : ");
            continu = lecteur.nextBoolean(); lecteur.nextLine();
        }while(continu);

    }

}
