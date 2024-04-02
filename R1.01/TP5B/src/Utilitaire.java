import java.util.Scanner; import java.util.ArrayList;

public class Utilitaire {
    public static float saisieConc(String unMois) {
        //{ } => { résultat = un float>=0 saisi par l'utilisateur, représentant
        // la concentration en NO2 pour le mois unMois }
        Scanner lecteur = new Scanner(System.in);
        float concentration;
        do {
            System.out.print("* Concentration en dioxyde d'azote relevée en " + unMois + " ? ");
            concentration = lecteur.nextFloat(); lecteur.nextLine();
            if (concentration < 0){
                System.out.println("--> Saisir une concentration >= 0");
            }
        }while (concentration < 0);
        return concentration;
    }

    public static ArrayList<ReleveMensuel> bilanSem(ArrayList<String> desMois) {
        // { desMois contient des noms de mois }
        // => { résultat = un vecteur de ReleveMensuel
        // Pour chaque élément du vecteur résultat:
        // * mois est le nom du mois de même indice dans desMois
        // * concentration est la concentration en NO2, saisie pour ce mois }
        ArrayList<ReleveMensuel> releve = new ArrayList<>();
        for (String mois : desMois) {
            releve.add(new ReleveMensuel(mois, saisieConc(mois)));
        }
        return releve;
    }

    public static float moyConc (ArrayList<ReleveMensuel> desReleves) {
        // { } => { résultat = moyenne des concentrations des éléments
        // du vecteur desReleves }
        float moy=0;
        for (ReleveMensuel desReleve : desReleves) {
            moy += desReleve.getConcentration();
        }
        return moy/desReleves.size();
    }

    public static float maxConc(ArrayList<ReleveMensuel> desReleves) {
        // { desReleves non vide } =>
        // { résultat = concentration la plus élevée dans desReleves }
        float max = desReleves.get(0).getConcentration();
        for (ReleveMensuel desReleve : desReleves) {
             if (max < desReleve.getConcentration()){
                 max=desReleve.getConcentration();
             }
        }
        return max;
    }

    public static float minConc(ArrayList<ReleveMensuel> desReleves) {
        // { des RelevesNonVide } =>
        // { résultat = concentration la moins élevée dans desReleves }
        float min = desReleves.get(0).getConcentration();
        for (ReleveMensuel desReleve : desReleves) {
            if (min > desReleve.getConcentration()) {
                min = desReleve.getConcentration();
            }
        }
        return min;
    }

    public static boolean estPollue(ReleveMensuel unReleve, float seuil) {
        // { } =>
        // { résultat = vrai si la concentration de unReleve > seuil }
        return (unReleve.getConcentration() > seuil);
    }

    public static int nbPoll(ArrayList<ReleveMensuel> desReleves, float seuil) {
        // { } => { résultat = nombre de mois pollués dans desReleves }
        int nb_mois = 0;
        for (ReleveMensuel unReleve : desReleves) {
            if (estPollue(unReleve, seuil)) {
                nb_mois++;
            }
        }
        return nb_mois;
    }

    public static String poll1(ArrayList<ReleveMensuel> desReleves, float seuil) {
        // { } => { résultat = nom du premier mois pollué dans desReleves,
        // une chaîne vide s'il n'y en a pas }
        int i=0;
        while (i<desReleves.size() && !estPollue(desReleves.get(i), seuil)){
            i++;
        }
        if (i == desReleves.size()){
            return desReleves.get(i-1).getMois();
        }
        return "";
    }

    public static String niveauPol(ReleveMensuel unReleve) {
        // { } =>
        // { résultat = * "bon" si concentration <= 10
        // * "moyen" si concentration dans ]10, 25]
        // * "dégradé" si concentration dans ]25, 40]
        // * "mauvais" si concentration dans ]40, 55]
        // * "très mauvais" si concentration dans ]55, 70]
        // * "extrêmement mauvais" si concentration > 70 }
        if (unReleve.getConcentration() <= 10){
            return "bon";
        }else if (unReleve.getConcentration() <= 25){
            return "moyen";
        }else if (unReleve.getConcentration() <= 40){
            return "dégradé";
        }else if (unReleve.getConcentration() <= 55){
            return "mauvais";
        }else if (unReleve.getConcentration() <= 70){
            return "très mauvais";
        }else {
            return "extrêmement mauvais";
        }
    }

    public static int compareReleves(ArrayList<ReleveMensuel> releves1, ArrayList<ReleveMensuel> releves2, float seuil) {
        // { releves1 et releves2 non vides et de même taille }
        // => { Résultat = * -1 si la moyenne des concentrations en NO2 de releves1
        // est inférieure à celle de releves2, ou si les moyennes
        // sont égales et le nombre de mois pollués de releves1
        // est inférieur à celui de releves2
        // * 0 si les moyennes des concentrations en NO2 sont égales
        // et si le nombre de mois pollués est identique
        // * 1 dans tous les autres cas }
        if (moyConc(releves1) < moyConc(releves2)
                || (moyConc(releves1)==moyConc(releves2) && nbPoll(releves1, seuil) < nbPoll(releves2, seuil) )){
            return -1;
        }else if (moyConc(releves1)==moyConc(releves2)){
            return 0;
        }else {
            return 1;
        }
    }
}
