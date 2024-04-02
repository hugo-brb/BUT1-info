import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Classification {


    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                StringBuilder lignes = new StringBuilder(ligne.substring(3));
                while (scanner.hasNextLine() && !ligne.isEmpty()) {
                    ligne = scanner.nextLine();
                    if (!ligne.isEmpty()) {
                        lignes.append('\n').append(ligne);
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes.toString());
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }


    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        try (FileWriter writer = new FileWriter(nomFichier)) {
            int pEnvi = 0, pCult = 0, pEco = 0, pPol = 0, pSports = 0;
            for (Depeche d : depeches) {
                ArrayList<PaireChaineEntier> scores = new ArrayList<>();

                for (Categorie categorie : categories) {
                    int score = categorie.score(d);
                    scores.add(new PaireChaineEntier(categorie.getNom(), score));
                }

                String categorieMax = UtilitairePaireChaineEntier.chaineMax(scores);

                writer.write(d.getId() + " : " + categorieMax + "\n");

                if (categorieMax.equalsIgnoreCase(d.getCategorie())) {
                    if (d.getCategorie().equalsIgnoreCase("Environnement-Sciences")) {
                        pEnvi++;
                    } else if (d.getCategorie().equalsIgnoreCase("Culture")) {
                        pCult++;
                    } else if (d.getCategorie().equalsIgnoreCase("Economie")) {
                        pEco++;
                    } else if (d.getCategorie().equalsIgnoreCase("Politique")) {
                        pPol++;
                    } else if (d.getCategorie().equalsIgnoreCase("Sports")) {
                        pSports++;
                    }
                }
            }
            calculMoyenne(pEnvi, pCult, pEco, pPol, pSports, writer, depeches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void calculMoyenne(int pEnvi, int pCult, int pEco, int pPol,int pSports, FileWriter writer, ArrayList<Depeche> depeches){
        float e=0f, c=0f, ec=0f, p=0f, s=0f;
        try {
            if (pEnvi != 0){
                e = ((float) pEnvi/nbCat(depeches, "Environnement-Sciences"))*100;
                writer.write("Environnement-Sciences : " + e + "%\n");
            }
            if (pCult != 0) {
                c = ((float) pCult/nbCat(depeches, "Culture"))*100;
                writer.write("Culture : " + c + "%\n");
            }
            if (pEco != 0) {
                ec = ((float) pEco/nbCat(depeches, "Economie"))*100;
                writer.write("Economie : " + ec + "%\n");
            }
            if (pPol != 0) {
                p = ((float) pPol/nbCat(depeches, "Politique"))*100;
                writer.write("Politique : " + p + "%\n");
            }
            if (pSports != 0) {
                s = ((float) pSports/nbCat(depeches, "Sports"))*100;
                writer.write("Sport : " + s + "%\n");
            }
            writer.write("==> Moyenne : " + (e+c+ec+p+s)/5 + "%");
            System.out.println(" --> Pourcentage global de réussite : " + (e+c+ec+p+s)/5 + "%");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static int nbCat(ArrayList<Depeche> depeches, String cat){
        int result=0;
        for (Depeche d:depeches){
            if (d.getCategorie().equalsIgnoreCase(cat)){
                result++;
            }
        }
        return result;
    }
    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();

        for (Depeche depeche : depeches) {
            if (depeche.getCategorie().equals(categorie)) {
                for (String mot : depeche.getMots()) {
                    if (mot.length() >= 5 && !contientMot(resultat, mot)) {
                        resultat.add(new PaireChaineEntier(mot, 0));
                    }
                }
            }
        }
        return resultat;
    }

    private static boolean contientMot(ArrayList<PaireChaineEntier> dictionnaire, String mot) {
        for (PaireChaineEntier paire : dictionnaire) {
            if (paire.getChaine().equals(mot)) {
                return true;
            }
        }
        return false;
    }


    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        int comparaions = 0;
        for (Depeche depeche : depeches) {
            ArrayList<String> mot = depeche.getMots();
            for (PaireChaineEntier p : dictionnaire){
                for (String m:mot){
                    comparaions ++;
                    if (p.getChaine().equals(m) && depeche.getCategorie().equals(categorie)){
                        p.setEntier(p.getEntier()+1);
                    }else if (p.getChaine().equals(m) && !depeche.getCategorie().equals(categorie)){
                        p.setEntier(p.getEntier()-1);
                    }
                }
            }
        }
        System.out.println("Nb de comparaisons calculScores : " + comparaions);
    }


    public static int poidsPourScore(int score) {
        if (score >= 1 && score <= 2) {
            return 1;
        } else if (score == 3) {
            return 2;
        } else if (score > 3 ) {
            return 3;
        }else {
            return 0;
        }
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {
        ArrayList<PaireChaineEntier> lexique = initDico(depeches, categorie);
        calculScores(depeches, categorie, lexique);

        // Utilisation de poidsPourScore pour obtenir les poids finaux
        ArrayList<PaireChaineEntier> lexiqueFinal = new ArrayList<>();
        for (PaireChaineEntier paire : lexique) {
            int poids = poidsPourScore(paire.getEntier());
            lexiqueFinal.add(new PaireChaineEntier(paire.getChaine(), poids));
        }

        // Écriture du lexique dans le fichier
        try (FileWriter writer = new FileWriter(nomFichier)) {
            for (PaireChaineEntier paire : lexiqueFinal) {
                writer.write(paire.getChaine() + ":" + paire.getEntier() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //Chargement des dépêches en mémoire
        System.out.println("* chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        System.out.println("* dépêches chargées");
//        for (int i = 0; i < depeches.size(); i++) {
//            depeches.get(i).afficher();
//        }

//      //Utilisation de lexique MANUELLE
        System.out.println("\n\n-------------------------------------\n"
                +"** UTILISATION D'UN LEXIQUE MANUEL **\n"
                +"-------------------------------------\n");
        Categorie environnement = new Categorie("Environnement-Sciences");
        environnement.initLexique("./lexique_manuel/ENVIRONNEMENT.txt");
        Categorie culture = new Categorie("Culture");
        culture.initLexique("./lexique_manuel/CULTURE.txt");
        Categorie economie = new Categorie("Economie");
        economie.initLexique("./lexique_manuel/ECONOMIE.txt");
        Categorie politique = new Categorie("Politique");
        politique.initLexique("./lexique_manuel/POLITIQUE.txt");
        Categorie sport = new Categorie("Sports");
        sport.initLexique("./lexique_manuel/SPORTS.txt");

//        System.out.println("Score dépeches environnement");
//        for(int i=0;i<depeches.size();i++){
//            if (environnement.score(depeches.get(i)) != 0){
//                System.out.println("Dépeche n°" + i + " : " + environnement.score(depeches.get(i)));
//            }
//        }
//        System.out.println("\nScore dépeches culture");
//        for(int i=0;i<depeches.size();i++){
//            if (culture.score(depeches.get(i)) != 0){
//                System.out.println("Dépeche n°" + i + " : " + culture.score(depeches.get(i)));
//            }
//        }
//        System.out.println("\nScore dépeches economie");
//        for(int i=0;i<depeches.size();i++){
//            if (economie.score(depeches.get(i)) != 0){
//                System.out.println("Dépeche n°" + i + " : " + economie.score(depeches.get(i)));
//            }
//        }
//        System.out.println("\nScore dépeches politique");
//        for(int i=0;i<depeches.size();i++){
//            if (politique.score(depeches.get(i)) != 0){
//                System.out.println("Dépeche n°" + i + " : " + politique.score(depeches.get(i)));
//            }
//        }
//        System.out.println("\nScore dépeches sport");
//        for(int i=0;i<depeches.size();i++){
//            if (sport.score(depeches.get(i)) != 0){
//                System.out.println("Dépeche n°" + i + " : " + sport.score(depeches.get(i)));
//            }
//        }

        ArrayList<Categorie> categories = new ArrayList<>();
        categories.add(environnement);
        categories.add(culture);
        categories.add(economie);
        categories.add(politique);
        categories.add(sport);
        int i=0;
        while (i< depeches.size()){
            ArrayList<PaireChaineEntier> s = new ArrayList<>();
            s.add(new PaireChaineEntier(environnement.getNom(), environnement.score(depeches.get(i))));
            s.add(new PaireChaineEntier(culture.getNom(), culture.score(depeches.get(i))));
            s.add(new PaireChaineEntier(economie.getNom(), economie.score(depeches.get(i))));
            s.add(new PaireChaineEntier(politique.getNom(), politique.score(depeches.get(i))));
            s.add(new PaireChaineEntier(sport.getNom(), sport.score(depeches.get(i))));
            i++;
        }
        classementDepeches(depeches, categories, "./RESULTATS/resultat_manuel.txt");


//      //Utilisation de lexique AUTOMATIQUE
        System.out.println("\n------------------------------------------\n"
                +"** UTILISATION D'UN LEXIQUE AUTOMATIQUE **\n"
                +"------------------------------------------");
        generationLexique(depeches, "ENVIRONNEMENT-SCIENCES", "./lexique_automatique/Environnement-Sciences_automatique.txt");
        generationLexique(depeches, "CULTURE", "./lexique_automatique/Culture_automatique.txt");
        generationLexique(depeches, "ECONOMIE", "./lexique_automatique/Economie_automatique.txt");
        generationLexique(depeches, "POLITIQUE", "./lexique_automatique/Politique_automatique.txt");
        generationLexique(depeches, "SPORTS", "./lexique_automatique/Sports_automatique.txt");
        System.out.println("* Lexiques générés : ok\n");
        ArrayList<Categorie> categories_automatique = new ArrayList<>();
        Categorie environnement_a = new Categorie("Environnement-Sciences");
        environnement_a.initLexique("./lexique_automatique/Environnement-Sciences_automatique.txt");
        Categorie culture_a = new Categorie("Culture");
        culture_a.initLexique("./lexique_automatique/Culture_automatique.txt");
        Categorie economie_a = new Categorie("Economie");
        economie_a.initLexique("./lexique_automatique/Economie_automatique.txt");
        Categorie politique_a = new Categorie("Politique");
        politique_a.initLexique("./lexique_automatique/Politique_automatique.txt");
        Categorie sport_a = new Categorie("Sports");
        sport_a.initLexique("./lexique_automatique/Sports_automatique.txt");
        categories_automatique.add(environnement_a);
        categories_automatique.add(culture_a);
        categories_automatique.add(economie_a);
        categories_automatique.add(politique_a);
        categories_automatique.add(sport_a);
        classementDepeches(depeches, categories_automatique, "./RESULTATS/resultat_automatique.txt");

//      //Utilisation des flux RSS
        System.out.println("\n------------------------------------------\n"
                +"** UTILISATION DES FLUX RSS **\n"
                +"------------------------------------------\n" +
                "   I/ FranceInfos\n"+
                "   ---------------");
        //Chargement des dépêches
        System.out.println("* chargement des dépêches");
        ArrayList<Depeche> depeches_franceinfos = lectureDepeches("./RSS/flux-RSS_franceinfos.txt");
        System.out.println("* dépêches chargées");

        //Génération des lexiques
        generationLexique(depeches_franceinfos, "ENVIRONNEMENT-SCIENCES", "./RSS/lexiques/Environnement-Sciences_franceinfos.txt");
        generationLexique(depeches_franceinfos, "CULTURE", "./RSS/lexiques/Culture_franceinfos.txt");
        generationLexique(depeches_franceinfos, "ECONOMIE", "./RSS/lexiques/Economie_franceinfos.txt");
        generationLexique(depeches_franceinfos, "POLITIQUE", "./RSS/lexiques/Politique_franceinfos.txt");
        generationLexique(depeches_franceinfos, "SPORTS", "./RSS/lexiques/Sports_franceinfos.txt");
        System.out.println("* Lexiques générés : ok\n");

        //Création des catégories
        ArrayList<Categorie> cat_franceinfos = new ArrayList<>();
        Categorie environnement_franceinfos = new Categorie("Environnement-Sciences");
        environnement_franceinfos.initLexique("./RSS/lexiques/Environnement-Sciences_franceinfos.txt");
        Categorie culture_franceinfos = new Categorie("Culture");
        culture_franceinfos.initLexique("./RSS/lexiques/Culture_franceinfos.txt");
        Categorie economie_franceinfos = new Categorie("Economie");
        economie_franceinfos.initLexique("./RSS/lexiques/Economie_franceinfos.txt");
        Categorie politique_franceinfos = new Categorie("Politique");
        politique_franceinfos.initLexique("./RSS/lexiques/Politique_franceinfos.txt");
        Categorie sport_franceinfos = new Categorie("Sports");
        sport_franceinfos.initLexique("./RSS/lexiques/Sports_franceinfos.txt");
        cat_franceinfos.add(environnement_franceinfos);
        cat_franceinfos.add(culture_franceinfos);
        cat_franceinfos.add(economie_franceinfos);
        cat_franceinfos.add(politique_franceinfos);
        cat_franceinfos.add(sport_franceinfos);

        //Classement
        classementDepeches(depeches_franceinfos, cat_franceinfos, "./RSS/RESULTATS/resultat_franceinfos.txt");

        System.out.println("\n   II/ LeMonde\n"+
                "   ---------------");
        //Chargement des dépêches
        System.out.println("* chargement des dépêches");
        ArrayList<Depeche> depeches_leMonde = lectureDepeches("./RSS/flux-RSS_lemonde.txt");
        System.out.println("* dépêches chargées");

        //Génération des lexiques
        generationLexique(depeches_leMonde, "ENVIRONNEMENT-SCIENCES", "./RSS/lexiques/Environnement-Sciences_lemonde.txt");
        generationLexique(depeches_leMonde, "CULTURE", "./RSS/lexiques/Culture_lemonde.txt");
        generationLexique(depeches_leMonde, "ECONOMIE", "./RSS/lexiques/Economie_lemonde.txt");
        generationLexique(depeches_leMonde, "POLITIQUE", "./RSS/lexiques/Politique_lemonde.txt");
        generationLexique(depeches_leMonde, "SPORTS", "./RSS/lexiques/Sports_lemonde.txt");
        System.out.println("* Lexiques générés : ok\n");

        //Création des catégories
        ArrayList<Categorie> cat_leMonde = new ArrayList<>();
        Categorie environnement_leMonde = new Categorie("Environnement-Sciences");
        environnement_leMonde.initLexique("./RSS/lexiques/Environnement-Sciences_lemonde.txt");
        Categorie culture_leMonde = new Categorie("Culture");
        culture_leMonde.initLexique("./RSS/lexiques/Culture_lemonde.txt");
        Categorie economie_leMonde = new Categorie("Economie");
        economie_leMonde.initLexique("./RSS/lexiques/Economie_lemonde.txt");
        Categorie politique_leMonde = new Categorie("Politique");
        politique_leMonde.initLexique("./RSS/lexiques/Politique_lemonde.txt");
        Categorie sport_leMonde = new Categorie("Sports");
        sport_leMonde.initLexique("./RSS/lexiques/Sports_lemonde.txt");
        cat_leMonde.add(environnement_leMonde);
        cat_leMonde.add(culture_leMonde);
        cat_leMonde.add(economie_leMonde);
        cat_leMonde.add(politique_leMonde);
        cat_leMonde.add(sport_leMonde);

        //Classement
        classementDepeches(depeches_leMonde, cat_leMonde, "./RSS/RESULTATS/resultat_lemonde.txt");



        //1992ms
        long endTime = System.currentTimeMillis();
        System.out.println("\n\nProgramme executé en : " + (endTime-startTime) + "ms");
    }
}