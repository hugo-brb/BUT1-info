public class ListePolarsUtilitaire {

    /**
     * FOURNIE
     *
     * @param lesPolarsProduits
     * @return true si lesPolarsProduits sont bien lesPolarsAttendus
     */
    public static boolean estTriee(ListeChaineeTriee<Polar> lesPolarsProduits) {
        // on vérifie que l'on obtient bien la liste attendue
        ListeChaineeTriee<Polar> lesPolarsAttendus = CreationListePolars.chargementFichierTie();
        Cellule<Polar> celluleProduite = lesPolarsProduits.getTete();
        Cellule<Polar> celluleReference = lesPolarsAttendus.getTete();
        while (celluleProduite != null && celluleReference != null && celluleReference.getInfo().compareTo(celluleProduite.getInfo()) == 0) {
            celluleProduite = celluleProduite.getCelluleSuivante();
            celluleReference = celluleReference.getCelluleSuivante();
        }
        return celluleProduite == null && celluleReference == null;
    }

    /**
     * A ECRIRE : WORKER RÉCURSIF - Exercice 12
     *
     * @param unPolar Polar courant
     * @param annee   année de référence
     * @return nombre de livres de annee dans la liste de Cellules dont la tête est unPolar
     */
    public static int nbLivresAnneeRecWorker(Cellule<Polar> unPolar, int annee) {
        if (unPolar.getCelluleSuivante() == null){
            if (unPolar.getInfo().getAnnee() == annee){
                return 1;
            }else {
                return 0;
            }
        }else {
            if (unPolar.getInfo().getAnnee() == annee){
                return 1 + nbLivresAnneeRecWorker(unPolar.getCelluleSuivante(), annee);
            }else {
                return nbLivresAnneeRecWorker(unPolar.getCelluleSuivante(), annee);
            }
        }
    }

    /**
     * FOURNIE
     *
     * @param lesPolars liste chaînée utilisée
     * @param annee     année de référence
     * @return nombre de livres référencés pour annee
     */
    public static int nbLivresAnneeRec(ListeChaineeTriee<Polar> lesPolars, int annee) {
        // { } => { résultat = nombre de livres référencés pour annee }
        return nbLivresAnneeRecWorker(lesPolars.getTete(), annee);
    }

    /**
     * A ECRIRE - Exercice 13
     *
     * @param lesPolars liste chaînée utilisée
     * @param auteur    auteur de référence
     * @return nombre de livres référencés pour auteur
     */
    public static int nbLivresAuteurIter(ListeChaineeTriee<Polar> lesPolars, String auteur) {
        // { lesPolars non vide, triée selon l'ORDRE(auteur, annee) } =>
        // { résultat = nombre de livres référencés pour auteur}
        // NOTE : veiller à minimiser le nombre de comparaisons
        int result = 0;
        Cellule<Polar> cel = lesPolars.getTete();
        while (cel != null){
            if (cel.getInfo().getAuteur().equals(auteur)){
                result+=1;
            }
            cel = cel.getCelluleSuivante();
        }
        return result;
    }

    /**
     * A ECRIRE - Exercice 14
     *
     * @param lesPolars liste chaîneée de référence
     */
    public static void afficheNbLivresAuteurs(ListeChaineeTriee<Polar> lesPolars) {
        // { lesPolars non vide, triée selon l'ORDRE(auteur, annee) } =>
        // { le nombre de livres référencés pour chaque auteur a été affiché }
        // NOTE : il ne faut pas utiliser la fonction nbLivresAuteurIter()
        Cellule<Polar> cel = lesPolars.getTete();
        String aut = cel.getInfo().getAuteur();
        while (cel != null) {
            System.out.print(aut + " : ");
            int i = 0;
            while (cel.getInfo().getAuteur().equals(aut)){
                i++;
            }
            System.out.print(i);
            cel = cel.getCelluleSuivante();
            aut = cel.getInfo().getAuteur();
        }

        // supprimer cette ligne et la suivante après complétion
        System.out.println("Je dois afficher des auteurs et un nombre de livres");

    }

}
