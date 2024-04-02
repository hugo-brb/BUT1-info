import java.util.ArrayList;

public class UtilitairePaireChaineEntier {


    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i =0;
        try {
            while (i < listePaires.size() && listePaires.get(i).getChaine().compareToIgnoreCase(chaine) != 0) {
                i++;
            }
            if (listePaires.get(i).getChaine().compareToIgnoreCase(chaine)==0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(chaine + " Non trouvée");
            return 0;
        }
    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        int entier = 0;
        try {
            while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
                i++;
            }
            if (listePaires.get(i).getChaine().compareTo(chaine) == 0) {
                entier = listePaires.get(i).getEntier();
                return entier;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        String chaineMax = "";
        int entierMax = 0;
        for (int i = 0; i < listePaires.size(); i++) {
            if (listePaires.get(i).getEntier() > entierMax) {
                entierMax = listePaires.get(i).getEntier();
                chaineMax = listePaires.get(i).getChaine();
            }
        }
        return chaineMax;
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        float moyenne = 0;
        for (int i = 0; i < listePaires.size(); i++) {
            moyenne += listePaires.get(i).getEntier();
        }
        moyenne = moyenne / listePaires.size();
        return moyenne;
    }

}
