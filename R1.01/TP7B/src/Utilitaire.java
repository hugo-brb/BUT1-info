import java.util.ArrayList;
public class Utilitaire {
    public static boolean verifTri(ArrayList<Polar> vPolar) {
        // { } =>
        // { résultat = vrai si vPolar est trié par titre strictement croissant }
        int i=1;
        while (i<vPolar.size() && vPolar.get(i).getTitre().compareTo(vPolar.get(i-1).getTitre()) >= 0){
            i++;
        }
        return i == vPolar.size();
    }
    public static int triSelectNbComp(ArrayList<Polar> vPolar) {
        // { } => { * vPolar a été trié selon l'ORDRE(auteur, annee)
        // en utilisant la méthode DU TRI PAR SÉLECTION
        //  * résultat = nombre de comparaisons effectuées
        //// entre deux éléments de vPolar }
        int i=0;
        int c=0;
        while (i< vPolar.size()-1){
            int min = i, j=i+1;
            while (j < vPolar.size()){
                if (vPolar.get(j).compareTo(vPolar.get(min)) == -1){
                    min = j;
                }
                j++;
                c++;
            }
            if (min != i){
                Polar temp = vPolar.get(i);
                vPolar.set(i, vPolar.get(min));
                vPolar.set(min, temp);
            }
            i++;
        }
        return c;
    }

    public static void triBulle(ArrayList<Polar> vPolar) {
        // { } => { vPolar est trié selon l'ORDRE(auteur, annee)
        // en utilisant la méthode DU TRI À BULLES AMÉLIORÉ }
        int j, i=0;
        boolean permute = true;
        while(permute){
            j = vPolar.size()-1;
            permute=false;
            while (j > i){
                if (vPolar.get(j).compareTo(vPolar.get(j-1)) < 0){
                    Polar temp = vPolar.get(j);
                    vPolar.set(j, vPolar.get(j-1));
                    vPolar.set(j-1, temp);
                    permute = true;
                }
                j--;
            }
            i++;
        }
    }

    public static int triInsertionNbComp(ArrayList<Polar> vPolar) {
        // { } => { * vPolar est trié selon l'ORDRE(auteur, annee)
        // en utilisant la méthode DU TRI PAR INSERTION
        // * résultat = nombre de comparaisons effectuées
        //// entre deux éléments de vPolar}
        int j, i=1, c=0;
        Polar val;
        while (i<vPolar.size()){
            j=i;
            val = vPolar.get(i);
            while (j > 0 && vPolar.get(j-1).compareTo(val) == 1){
                vPolar.set(j, vPolar.get(j-1));
                j--;
                c++;
            }
            if (j>0){
                c++;
            }
            vPolar.set(j, val);
            i++;
        }
        return c;
    }

    public static void nbPolarAuteur(ArrayList<Polar> vPolar) {
        // { vPolar non vide, trié selon l'ORDRE(auteur, année) } =>
        // { le nombre de romans écrits par chaque auteur a été affiché
        // ligne à ligne, chaque ligne ayant la forme :
        // * Nombre de romans écrits par XXX : nbR
        // (nbR étant le nombre de romans de l'auteur de nom XXX} }
        int i = 0;
        String aut = vPolar.get(i).getAuteur();
        while (i< vPolar.size()){
            int x=0;
            do {
                i++;
                x++;
            }while ((i<vPolar.size() && vPolar.get(i).getAuteur().compareTo(aut) == 0));
            System.out.println("* Nombre de romans écrits par "+ aut + " : " + x);
            if(i!= vPolar.size()){aut = vPolar.get(i).getAuteur();}
        }
    }

    public static ArrayList<String> auteursDeAn(ArrayList<Polar> vPolar, int an) {
        // { vPolar non vide, trié selon l'ORDRE(auteur, annee) } =>
        // { résultat = vecteur contenant les noms des auteurs ayant écrit au
        // moins un roman l'année an }
        ArrayList<String> result = new ArrayList<>();
        for (Polar pol : vPolar){
            if (pol.getAnnee() == an && !result.contains(pol.getAuteur())){
                result.add(pol.getAuteur());
            }
        }
        return result;
    }
    public static int rechSeqT(ArrayList<Polar> vPolar, String aut, int an) {
        // { vPolar trié selon l'ORDRE(auteur, annee) }
        // => { * résultat = indice du premier élément de vPolar
        // écrit par aut, l'année an, si trouvé
        // * résultat = -1, si aucun roman écrit par aut, l'année an }
        // LA RECHERCHE EST SÉQUENTIELLE
        int i=0;
        Polar test = new Polar(an, aut, "");
        while(i < vPolar.size() && !(vPolar.get(i).compareTo(test)==0)){
            i++;
        }
        if (i == vPolar.size()){
            return -1;
        }else {
            return i;
        }
    }

    public static int rechDicho(ArrayList<Polar> vPolar, String aut, int an) {
        // { vPolar trié selon l'ORDRE(auteur, annee) }
        // => { * résultat = indice du premier élément de vPolar
        // écrit par aut, l'année an, si trouvé
        // * résultat = -1, si aucun roman écrit par aut, l'année an }
        // LA RECHERCHE EST DICHOTOMIQUE
        Polar pol = new Polar(an, aut, "");
        if(vPolar.get(vPolar.size()-1).compareTo(pol) < 0){
            return -1;
        }else {
            int inf = 0, sup = vPolar.size()-1, m;
            while(inf < sup){
                m = (inf+sup) /2;
                if (vPolar.get(m).compareTo(pol) >= 0){
                    sup = m;
                }else {
                    inf = m+1;
                }
            }
            if (vPolar.get(sup).compareTo(pol) == 0){
                return sup;
            }else {
                return -1;
            }
        }
    }

    public static PaireResultatCompteur<Integer> rechSeqT_O(ArrayList<Polar> vPolar, String aut, int an) {
        // { vPolar trié selon l'ORDRE(auteur, annee) } =>
        // { * le premier élément de vPolar écrit par aut, l'année an a été cherché
        // à l'aide d'un algorithme de RECHERCHE SÉQUENTIELLE
        // * résultat = un objet de type PaireResCompteur où :
        // - l'attribut res est égal à l'indice dans vPolar du 1er élément d'auteur aut
        // et d'année an, si trouvé / -1 si pas trouvé
        // - l'attribut compteur est égal au nombre de comparaisons effectuées entre
        // un élément du vecteur et ce qui est cherché, pour produire res }
        int i=0, c=0;
        Polar pol = new Polar(an, aut, "");
        while(i < vPolar.size() && !(vPolar.get(i).compareTo(pol)==0)){
            i++;
        }
        if (i == vPolar.size()){
            return new PaireResultatCompteur<>(-1, c+1);
        }else {
            return new PaireResultatCompteur<>(i, c+1);
        }
    }

    public static PaireResultatCompteur<Integer> rechDicho_O(ArrayList<Polar> vPolar, String aut, int an) {
        // { vPolar trié selon l'ORDRE(auteur, annee) } =>
        // { * le premier élément de vPolar écrit par aut, l'année an a été cherché
        // à l'aide d'un algorithme de RECHERCHE DICHOTOMIQUE
        // * résultat = un objet de type PaireResCompteur dont :
        // - l'attribut res est égal à l'indice dans vPolar du 1er élément d'auteur aut
        // et d'année an, si trouvé / -1 si pas trouvé
        // - l'attribut compteur est égal au nombre de comparaisons effectuées entre
        // un élément du vecteur et ce qui est cherché, pour produire res }
        int c=1;
        Polar pol = new Polar(an, aut, "");
        if(vPolar.get(vPolar.size()-1).compareTo(pol) < 0){
            return new PaireResultatCompteur<>(-1, c);
        }else {
            int inf = 0, sup = vPolar.size()-1, m;
            while(inf < sup){
                m = (inf+sup) /2;
                if (vPolar.get(m).compareTo(pol) >= 0){
                    sup = m;
                }else {
                    inf = m+1;
                }
                c++;
            }
            if (vPolar.get(sup).compareTo(pol) == 0){
                return new PaireResultatCompteur<>(sup, c+1);
            }else {
                return new PaireResultatCompteur<>(-1, c+1);
            }
        }
    }
}

