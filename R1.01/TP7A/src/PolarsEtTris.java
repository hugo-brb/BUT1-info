import java.util.ArrayList;
import java.util.Scanner;

public class PolarsEtTris {
    public static void main(String[] args) {
        ArrayList<Polar> lesPolars = CreationBib.lesPolars();
        if (Utilitaire.verifTri(lesPolars)){
            System.out.println("Les polars sont trié par titre");
        }else {
            System.out.println("Les polars ne sont pas trié par titre");
        }
        for(Polar unPolar : lesPolars){
            System.out.println(unPolar.getTitre());
        }
        // Intermède : vérification du code de compareTo et de toString
        Polar pol1 = new Polar(2000, "AUTEUR1", "Une oeuvre");
        Polar pol2 = new Polar(2000, "AUTEUR2", "Mon oeuvre");
        Polar pol3 = new Polar(1998, "AUTEUR1", "Oeuvre sans nom");
        System.out.print("pol1 : "); System.out.println(pol1);
        System.out.print("pol2 : "); System.out.println(pol2);
        System.out.print("pol3 : "); System.out.println(pol3);
        System.out.println("* pol1 comparé à pol2 : " + pol1.compareTo(pol2));
        System.out.println("* pol1 comparé à pol1 : " + pol1.compareTo(pol1));
        System.out.println("* pol1 comparé à pol3 : " + pol1.compareTo(pol3));

        System.out.println("*\n\n TRI PAR SELECTION selon (auteur, annee) *\n");
        Utilitaire.triSelect(lesPolars);
        for (Polar pol : lesPolars){
            System.out.println(pol);
        }

        lesPolars = CreationBib.lesPolars();
        System.out.println("\n\n* TRI A BULLES AMELIORE selon (auteur, annee) *\n");
        Utilitaire.triBulle(lesPolars);
        for (Polar pol : lesPolars){
            System.out.println(pol);
        }

        lesPolars = CreationBib.lesPolars();
        System.out.println("\n\n* TRI PAR INSERTION selon (auteur, annee) *\n");
        Utilitaire.triInsertion(lesPolars);
        for (Polar pol : lesPolars){
            System.out.println(pol);
        }

        System.out.println("\n\n* NOMBRES DE LIVRES PAR AUTEURS *\n");
        Utilitaire.nbPolarAuteur(lesPolars);

        System.out.println("\n\n* AUTEURS / ANNEES *");
        Scanner lecteur = new Scanner(System.in);
        boolean c;
        do {
            System.out.print("--> Saisir une année : ");
            int an = lecteur.nextInt(); lecteur.nextLine();
            System.out.println("* Auteurs ayant écrit en "+ an);
            System.out.println(Utilitaire.auteursDeAn(lesPolars, an));
            System.out.print("Voulez-vous continuez ? {true/false} ");
            c = lecteur.nextBoolean(); lecteur.nextLine();
        }while(c);
    }
}
