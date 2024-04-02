import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public  ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
    public void initLexique(String nomFichier) {
        lexique = new ArrayList<>();
        // Initialise un attribut lexique
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                int index = ligne.indexOf(":");

                if (index != -1) { // Vérifie si le séparateur ":" est présent dans la ligne
                    String chaine = ligne.substring(0, index);
                    try {
                        int entier = Integer.parseInt(ligne.substring(index + 1).trim());
                        lexique.add(new PaireChaineEntier(chaine, entier));
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format pour la partie entière : " + ligne);
                    }
                } else {
                    System.err.println("Séparateur ':' manquant dans la ligne : " + ligne);
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        int comparaison=0;
        int totalScore = 0;
        ArrayList<String> motsDepeche = d.getMots();
        for (String mot : motsDepeche) {
            int poidsMot = UtilitairePaireChaineEntier.entierPourChaine(lexique, mot);
            totalScore += poidsMot;
            comparaison++;
        }
        System.out.println("Nb de comparaions score : " + comparaison);
        return totalScore;
    }

}
