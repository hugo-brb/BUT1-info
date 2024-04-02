public class Utilitaire {
    public static ListeTrieeC<Integer> lCsansDoublons(ListeTrieeC<Integer> lC) throws ExceptionMauvaisIndice {
        // { lC non vide, avec potentiellement des doublons } =>
        // { résultat = nouvelle liste triée croissante dont les cellules portent
        // les mêmes entiers que lC, mais sans doublons }
        ListeTrieeC<Integer> lCresultat = new ListeTrieeC<>();
        if (!lC.estVide()) {
            lCresultat.insereTrie(lC.getInfoAtPosit(1));
            for (int i = 2; i <= lC.getLongueur(); i++) {
                Integer info = lC.getInfoAtPosit(i);
                if (info.compareTo(lCresultat.getInfoAtPosit(lCresultat.getLongueur())) != 0) {
                    lCresultat.insereTrie(info);
                }
            }
        }
        return lCresultat;
    }

    public static boolean estDansListeC(ListeTrieeC<Integer> lC, int unEnt) {
        // { lC non vide, sans doublons } =>
        // { résultat = vrai s'il existe une cellule de lC portant l'entier unEnt,
        // faux sinon }
        return estDansListeC_wk(lC.getTete(), unEnt);

    }
    private static boolean estDansListeC_wk(Cellule<Integer> cellCour, int unEnt) {
        if (cellCour == null) {
            return false;
        } else {
            if (cellCour.getInfo().compareTo(unEnt) == 0) {
                return true;
            } else {
                return estDansListeC_wk(cellCour.getCelluleSuivante(), unEnt);
            }
        }
    }

    public static ListeTrieeC<Integer> union(ListeTrieeC<Integer> lC1_sdb, ListeTrieeC<Integer> lC2_sdb) {
        // { lC1_sdb et lC2_sdb non vides, sans doublons } =>
        // { résultat = liste triée croissante, contenant, sans doublons,
        // les entiers portés par les cellules de lC1_sdb et
        // les entiers portés par les cellules de lC2_sdb}
        ListeTrieeC<Integer> lCresultat = new ListeTrieeC<>();
        Cellule<Integer> cellCour1 = lC1_sdb.getTete();
        Cellule<Integer> cellCour2 = lC2_sdb.getTete();
        while (cellCour1 != null && cellCour2 != null) {
            if (cellCour1.getInfo().compareTo(cellCour2.getInfo()) < 0) {
                lCresultat.insereTrie(cellCour1.getInfo());
                cellCour1 = cellCour1.getCelluleSuivante();
            } else if (cellCour1.getInfo().compareTo(cellCour2.getInfo()) > 0) {
                lCresultat.insereTrie(cellCour2.getInfo());
                cellCour2 = cellCour2.getCelluleSuivante();
            } else {
                lCresultat.insereTrie(cellCour1.getInfo());
                cellCour1 = cellCour1.getCelluleSuivante();
                cellCour2 = cellCour2.getCelluleSuivante();
            }
        }
        while (cellCour1 != null) {
            lCresultat.insereTrie(cellCour1.getInfo());
            cellCour1 = cellCour1.getCelluleSuivante();
        }
        while (cellCour2 != null) {
            lCresultat.insereTrie(cellCour2.getInfo());
            cellCour2 = cellCour2.getCelluleSuivante();
        }
        return lCresultat;
    }

    public static ListeTrieeC<Integer> intersect(ListeTrieeC<Integer> lC1_sdb, ListeTrieeC<Integer> lC2_sdb) {
        // { lC1_sdb et lC2_sdb non vides, sans doublons } =>
        // { résultat = liste triée croissante, contenant, sans doublons,
        // les entiers portés par les cellules de lC1_sdb qui sont
        // égaux à ceux portés par les cellules de lC2_sdb }
        ListeTrieeC<Integer> lCresultat = new ListeTrieeC<>();
        Cellule<Integer> cellCour1 = lC1_sdb.getTete();
        Cellule<Integer> cellCour2 = lC2_sdb.getTete();
        while (cellCour1 != null && cellCour2 != null) {
            if (cellCour1.getInfo().compareTo(cellCour2.getInfo()) < 0) {
                cellCour1 = cellCour1.getCelluleSuivante();
            } else if (cellCour1.getInfo().compareTo(cellCour2.getInfo()) > 0) {
                cellCour2 = cellCour2.getCelluleSuivante();
            } else {
                lCresultat.insereTrie(cellCour1.getInfo());
                cellCour1 = cellCour1.getCelluleSuivante();
                cellCour2 = cellCour2.getCelluleSuivante();
            }
        }
        return lCresultat;
    }

    public static void affich(ListeTrieeC<String> list) {
        for (int i = 0; i < list.getLongueur(); i++) {
            try {
                System.out.println("liste après modification : " + list.getInfoAtPosit(i));
            } catch (ExceptionMauvaisIndice e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insereTrie(TypeInfo uneInfo) {
        if (this.tete == null || this.tete.getInfo().compareTo(uneInfo) >= 0) {
            tete = new Cellule<TypeInfo>(uneInfo, tete);
            nbCellules++;
        } else {
            Cellule<TypeInfo> courante = tete;
            while (courante.getCelluleSuivante() != null && courante.getCelluleSuivante().getInfo().compareTo(uneInfo) < 0) {
                courante = courante.getCelluleSuivante();
            }
            Cellule<TypeInfo> nouvelleCellule = new Cellule<>(uneInfo, courante.getCelluleSuivante());
            courante.setCelluleSuivante(nouvelleCellule);
            nbCellules++;
        }
    }
}
