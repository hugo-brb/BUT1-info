import java.util.Scanner;

public class ControleSaisie {
    public static void main(String[] args) {
        System.out.println("TEST IME : ");
        Scanner lecteur = new Scanner(System.in);
        int val1 = Utilitaire.getInt_IME(lecteur);
        System.out.println(val1);
        int min=2, max=5, val2=Utilitaire.getIntMinMax_IME(lecteur, min, max);
        System.out.println(val2);

        System.out.println("TEST NFE : ");
        int val3 = Utilitaire.getInt_NFE(lecteur);
        System.out.println(val3);
        float val4 = Utilitaire.getFloat_NFE(lecteur);
        System.out.println(val4);
    }
}
