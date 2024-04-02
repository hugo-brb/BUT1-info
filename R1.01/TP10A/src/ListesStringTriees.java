public class ListesStringTriees {
    public static void main(String[] args) {
        ListeTrieeC<String> listStringC = new ListeTrieeC<>();
        listStringC.insereTrie("milou");
        listStringC.insereTrie("tintin");
        listStringC.insereTrie("rantanplan");
        listStringC.afficheGD();
        System.out.println("* Recherche de la chaîne portée par une cellule de la liste");
        try {
            for (int i=1; i<= listStringC.getLongueur(); i++)
                System.out.println("    --> chaîne portée par la cellule n°" + i + " ? " + listStringC.getInfoAtPosit(i));
            System.out.println("!!! Consultation impossible, pas d'élément en position 10 dans la liste !!!");
        } catch (ExceptionMauvaisIndice e) {
            throw new RuntimeException(e);
        }
        try {
            listStringC.setInfoAtPosit(1, "bécassine");
            Utilitaire.affich(listStringC);
            listStringC.setInfoAtPosit(1, "zorro");
            Utilitaire.affich(listStringC);
            listStringC.setInfoAtPosit(2, "spirou");
            Utilitaire.affich(listStringC);
            listStringC.setInfoAtPosit(2, "fantasio");
            Utilitaire.affich(listStringC);
            listStringC.setInfoAtPosit(4, "bécassine");
            Utilitaire.affich(listStringC);
        } catch (ExceptionMauvaisIndice | ExceptionViolationTri e) {
            throw new RuntimeException(e);
        }


    }
}
