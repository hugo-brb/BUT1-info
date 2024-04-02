import java.util.ArrayList; import java.util.Scanner;

public class Utilitaire {
    public static ListeChainee<PaysDeCont> countries(ArrayList<Pays> mondeT, String cont) {
        // { mondeT non vide, trié selon l'ORDRE (continent, nom) } =>
        // { résultat = liste chainée des pays du continent cont, TRIÉE par nom }
        ListeChainee<PaysDeCont> listePdeC = new ListeChainee<>();
        PaysDeCont pdc;
        for (Pays p : mondeT) {
            if (p.getContinent().equals(cont)) {
                pdc = new PaysDeCont(p.getNom(), p.getPopulation(), p.getSuperficie());
                listePdeC.insereQueue(pdc);
            }
        }
        return listePdeC;
    }

    public static void affichePaysDeCont(ListeChainee<PaysDeCont> listePdeC) {
        // { listePdeC non vide } =>
        // { l'information portée par chaque celle de listePdeC a été affichée
        // ligne à ligne, précédée par sa position dans listePdeC }
        affichePaysDeCont_wk(1, listePdeC.getTete());
    }
    private static void affichePaysDeCont_wk(int pos, Cellule<PaysDeCont> cellCour) {
        PaysDeCont pdc = cellCour.getInfo();
        System.out.println(pos + " " + pdc);
        if (cellCour.getCelluleSuivante() != null) {
            affichePaysDeCont_wk(pos + 1, cellCour.getCelluleSuivante());
        }
    }

    public static int indString(Scanner lecteur, ArrayList<String> vString) {
        // { vString non vide, trié par ordre strictement croissant } =>
        // { résultat = indice dans vString, d'une chaîne saisie par l'utilisateur }
        // La saisie a été répétée tant que la chaîne saisie n'est pas dans vString
        String s;
        int ind;
        do {
            System.out.print("Entrer un nom de continent, choisi parmi :"+vString + " : ");
            s = lecteur.nextLine();
            ind = vString.indexOf(s);
        } while (ind == -1);
        return ind;
    }

    public static void contExtremes(ArrayList<String> vCont, ArrayList<ListeChainee<PaysDeCont>>vListesPdeC) {
        // { * vCont, trié et non vide : vecteur des continents du monde
        // * vListesPdeC, non vide : vecteur des listes de pays de ces continents
        // dans l'ordre des continents de vCont } =>
        // { le nom et le nombre de pays du continent qui a le plus de pays est affiché,
        // ainsi que le nom et le nombre de pays du continent qui a le moins de pays }
        int indMax = 0;
        int indMin = 0;
        int nbMax = vListesPdeC.get(0).getLongueur();
        int nbMin = vListesPdeC.get(0).getLongueur();
        for (int i = 1; i < vListesPdeC.size(); i++) {
            if (vListesPdeC.get(i).getLongueur() > nbMax) {
                nbMax = vListesPdeC.get(i).getLongueur();
                indMax = i;
            }
            if (vListesPdeC.get(i).getLongueur() < nbMin) {
                nbMin = vListesPdeC.get(i).getLongueur();
                indMin = i;
            }
        }
        System.out.println("Continent comptant le plus de pays : " + vCont.get(indMax) + " (" + nbMax + " pays)");
        System.out.println("Continent comptant le moins de pays : " + vCont.get(indMin) + " (" + nbMin + " pays)");
    }

    public static ListeChainee<String> listeNomsPdeC (String unCont, ListeChainee<PaysDeCont> listePDeC) {
        // { * unCont est le nom d'un continent
        // * listePdeC contient les pays de unCont, triés par nom } =>
        // { résultat = liste triée dont chaque élément est une chaîne construite par
        // concaténation de unCont entre parenthèses, aux nom d'un pays
        // EXEMPLE : "Andorre (Europe)" }
        ListeChainee<String> listeNomsPdeC = new ListeChainee<>();
        String nomPays;
        for (Cellule<PaysDeCont> cellCour = listePDeC.getTete(); cellCour != null; cellCour = cellCour.getCelluleSuivante()) {
            nomPays = cellCour.getInfo().getNom();
            listeNomsPdeC.insereQueue(nomPays + " (" + unCont + ")");
        }
        return listeNomsPdeC;
    }

    public static int posInsert(ListeChainee<String> uneListe, String uneChaine) {
        // { uneListe triée, éventuellement vide } =>
        // { résultat = rang que devrait occuper uneChaine lors de son insertion dans
        // uneListe, pour que le tri soit respecté }
        int pos = 1;
        Cellule<String> cellCour = uneListe.getTete();
        while (cellCour != null && uneChaine.compareTo(cellCour.getInfo()) > 0) {
            pos++;
            cellCour = cellCour.getCelluleSuivante();
        }
        return pos;
    }

    public static ListeChainee<String> listeNomsPaysDuMonde(ArrayList<String> vCont, ArrayList<ListeChainee<PaysDeCont>> vListesPdeC ) {
        // { * vCont, trié et non vide : vecteur des continents du monde
        // * vListesPdeC, non vide : vecteur des listes de pays de ces continents } =>
        // { résultat = liste triée dont dont chaque élément est le nom d'un pays du
        // monde, concaténé au nom de son continent entre parenthèses }
        ListeChainee<String> liste_res = new ListeChainee<>();
        ListeChainee<String> listeNomsPdeC;
        for (int i = 0; i < vCont.size(); i++) {
            listeNomsPdeC = listeNomsPdeC(vCont.get(i), vListesPdeC.get(i));
            for (Cellule<String> cellCour = listeNomsPdeC.getTete(); cellCour != null; cellCour = cellCour.getCelluleSuivante()) {
                liste_res.insereAtPosit(posInsert(liste_res, cellCour.getInfo()), cellCour.getInfo());
            }
        }
        return liste_res;
    }

    public static void lexiquePaysDuMonde(ListeChainee<String> lesPays) {
        // { lesPays triée, contient les noms des pays du monde, concaténés
        // au nom de leur continent entre parenthèses } =>
        // { les éléments de lesPays ont été affichés par lots de pays de même initiale,
        // précédés de l'affichage de cette initiale }
        String initialeCour = "";
        for (Cellule<String> cellCour = lesPays.getTete(); cellCour != null; cellCour = cellCour.getCelluleSuivante()) {
            if (!cellCour.getInfo().substring(0, 1).equals(initialeCour)) {
                initialeCour = cellCour.getInfo().substring(0, 1);
                System.out.println(initialeCour);
            }
            System.out.println(" - "+cellCour.getInfo());
        }
    }
}
