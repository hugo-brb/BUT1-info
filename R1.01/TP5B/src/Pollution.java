import java.util.ArrayList;
import java.util.Arrays;

public class Pollution {
    public static void main(String[] args) {
        /*ArrayList<ReleveMensuel> testReleve = new ArrayList<>();
        testReleve.add(new ReleveMensuel("janvier", 20.6f));
        testReleve.add(new ReleveMensuel("mars", 40f));
        testReleve.add(1, new ReleveMensuel("février", 45f));
        System.out.println(testReleve);*/
        ArrayList<String> semestre1 = new ArrayList<>(Arrays.asList("janvier", "février", "mars", "avril", "mai", "juin"));
        ArrayList<String> semestre2 = new ArrayList<>(Arrays.asList("juillet", "août", "septembre", "octobre", "novembre", "decembre"));
        final float SEUIL_ALERTE = 40.0f;
        ArrayList<ReleveMensuel> bilanSem1 = new ArrayList<>();
        ArrayList<ReleveMensuel> bilanSem2 = new ArrayList<>();

        //Bilan semestre 1
        System.out.println("-------------------------------------\n" +
                "Bilan 1er semestre\n" +
                "-------------------------------------");
        for (String mois : semestre1){
            bilanSem1.add(new ReleveMensuel(mois, Utilitaire.saisieConc(mois)));
        }
        System.out.println("-----------------------------------------------------------------------\n" +
                "Concentration en dioxyde d'azote sur le 1er semestre\n" +
                "-----------------------------------------------------------------------");
        System.out.println("* Taux moyen : " + Utilitaire.moyConc(bilanSem1));
        System.out.println("* Nombre de mois pollués : " + Utilitaire.nbPoll(bilanSem1, SEUIL_ALERTE));
        System.out.println("* Nom du premier mois pollué : " + Utilitaire.poll1(bilanSem1, SEUIL_ALERTE));
        System.out.println("* Taux maximum relevé : " + Utilitaire.maxConc(bilanSem1));
        System.out.println("+ Taux minimum : " + Utilitaire.minConc(bilanSem1));
        System.out.println("* Analyse mois par mois...");
        for (ReleveMensuel unReleve : bilanSem1){
            System.out.println("- " + unReleve.getMois() + " : " + unReleve.getConcentration() + " --> " + Utilitaire.niveauPol(unReleve));
        }

        //Bilan semestre 2
        System.out.println("-------------------------------------\n" +
                "Bilan 2ème semestre\n" +
                "-------------------------------------");
        for (String mois : semestre2){
            bilanSem2.add(new ReleveMensuel(mois, Utilitaire.saisieConc(mois)));
        }
        System.out.println("-----------------------------------------------------------------------\n" +
                "Concentration en dioxyde d'azote sur le 2ème semestre\n" +
                "-----------------------------------------------------------------------");
        System.out.println("* Taux moyen : " + Utilitaire.moyConc(bilanSem2));
        System.out.println("* Nombre de mois pollués : " + Utilitaire.nbPoll(bilanSem2, SEUIL_ALERTE));
        System.out.println("* Nom du premier mois pollué : " + Utilitaire.poll1(bilanSem2, SEUIL_ALERTE));
        System.out.println("* Taux maximum relevé : " + Utilitaire.maxConc(bilanSem2));
        System.out.println("+ Taux minimum : " + Utilitaire.minConc(bilanSem2));
        System.out.println("* Analyse mois par mois...");
        for (ReleveMensuel unReleve : bilanSem2){
            System.out.println("- " + unReleve.getMois() + " : " + unReleve.getConcentration() + " --> " + Utilitaire.niveauPol(unReleve));
        }

        // Comparaison
        System.out.println("-----------------------------------------------------------------------\n" +
                "Comparaison entre les 2 semestres\n" +
                "-----------------------------------------------------------------------");
        if (Utilitaire.moyConc(bilanSem1) > Utilitaire.moyConc(bilanSem2)){
            System.out.println("Amélioration de l'émission de NO2 sur le second semestre");
        }else {
            System.out.println("Dégradation de l'émission de N02 sur le second semestre");
        }
    }
}
