import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PaysDeContinent {
    public static void main(String[] args) {
        ArrayList<Pays> mondeT = new ArrayList<>(InitMondeT.creerMondeT());
        ArrayList<String> vContinents = new ArrayList<>(Arrays.asList("Afrique", "Amériques", "Antarctique", "Asie", "Europe", "Océanie"));
        ListeChainee<PaysDeCont> listeAfrique;
        ListeChainee<PaysDeCont> listeAmeriques;
        ListeChainee<PaysDeCont> listeAntarctique;
        ListeChainee<PaysDeCont> listeAsie;
        ListeChainee<PaysDeCont> listeEurope;
        ListeChainee<PaysDeCont> listeOceanie;
        listeAfrique = Utilitaire.countries(mondeT, "Afrique");
        listeAmeriques = Utilitaire.countries(mondeT, "Amériques");
        listeAntarctique = Utilitaire.countries(mondeT, "Antarctique");
        listeAsie = Utilitaire.countries(mondeT, "Asie");
        listeEurope = Utilitaire.countries(mondeT, "Europe");
        listeOceanie = Utilitaire.countries(mondeT, "Océanie");

        System.out.println("Afrique :" + listeAfrique.getLongueur());
        System.out.println("Amériques :" + listeAmeriques.getLongueur());
        System.out.println("Antarctique :" + listeAntarctique.getLongueur());
        System.out.println("Asie :" + listeAsie.getLongueur());
        System.out.println("Europe :" + listeEurope.getLongueur());
        System.out.println("Océanie :" + listeOceanie.getLongueur());

        ArrayList<ListeChainee<PaysDeCont>> vListesPdeC = new ArrayList<>();
        vListesPdeC.add(listeAfrique);
        vListesPdeC.add(listeAmeriques);
        vListesPdeC.add(listeAntarctique);
        vListesPdeC.add(listeAsie);
        vListesPdeC.add(listeEurope);
        vListesPdeC.add(listeOceanie);

        Scanner lecteur = new Scanner(System.in);
        int indContinent = Utilitaire.indString(lecteur, vContinents);
        System.out.println("------------------------------------------------------------------\n" +
                "Pays du continent " + vContinents.get(indContinent).toUpperCase() + "\n" +
                "------------------------------------------------------------------");
        Utilitaire.affichePaysDeCont(vListesPdeC.get(indContinent));
        Utilitaire.contExtremes(vContinents, vListesPdeC);

        ListeChainee<String> lNomPaysDuMonde = new ListeChainee<>();
        lNomPaysDuMonde = Utilitaire.listeNomsPaysDuMonde(vContinents, vListesPdeC);
        System.out.println("------------------------------------------------------------------\n" +
                "Liste des noms des pays du monde\n" +
                "------------------------------------------------------------------");
        for(int i=1; i<11; i++) {
            try {
                System.out.println(lNomPaysDuMonde.getInfoAtPosit(i));
            } catch (ExceptionMauvaisIndice e) {
                throw new RuntimeException(e);
            }
        }
        Utilitaire.lexiquePaysDuMonde(lNomPaysDuMonde);

    }
}
