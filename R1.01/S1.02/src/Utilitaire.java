import java.util.ArrayList;
import java.util.Map;

public class Utilitaire {
    public static int triBulle_O (ArrayList<Integer> vInt) {
        // { vInt quelconque } =>
        // { * vInt a été trié par la méthode du TRI À BULLES AMÉLIORÉ
        // * résultat = nombre de comparaisons entre deux éléments de vInt }
        int j, i=0, compteur=0;
        boolean permute=true;
        while (permute) {
            j = vInt.size() - 1;
            permute = false;
            while (j > i) {
                if (vInt.get(j).compareTo(vInt.get(j - 1)) < 0) {
                    int temp = vInt.get(j);
                    vInt.set(j, vInt.get(j - 1));
                    vInt.set(j - 1, temp);
                    permute = true;
                }
                compteur++;
                j--;
            }
            i++;
        }
        return compteur;
    }

    public static int triInsert_O(ArrayList<Integer> vInt) {
        // { vInt quelconque } =>
        // { * vInt a été trié par la méthode du TRI PAR INSERTION
        // * résultat = nombre de comparaisons entre deux éléments de vInt }
        int j, i=1, c=0;
        int val;
        while (i<vInt.size()){
            j=i;
            val = vInt.get(i);
            while (j > 0 && vInt.get(j-1) > val){
                vInt.set(j, vInt.get(j-1));
                j--;
                c++;
            }
            if (j>0){
                c++;
            }
            vInt.set(j, val);
            i++;
        }
        return c;
    }

    public static int triSelect_O (ArrayList<Integer> vInt) {
        // { vInt quelconque } =>
        // { * vInt a été trié par la méthode du TRI PAR SÉLECTION
        // * résultat = nombre de comparaisons entre deux éléments de vInt }
        int i = 0;
        int c = 0;
        while (i < vInt.size() - 1) {
            int min = i, j = i + 1;
            while (j < vInt.size()) {
                if (vInt.get(j) < vInt.get(min)) {
                    min = j;
                }
                j++;
                c++;
            }
            if (min != i) {
                int temp = vInt.get(i);
                vInt.set(i, vInt.get(min));
                vInt.set(min, temp);
            }
            i++;
        }
        return c;

    }
    public static ArrayList<Integer> genVectSansDoublons(int n) {
        // { } =>
        // { résultat = vecteur de n entiers, sans doublons, dont les valeurs
        // sont choisies aléatoirement dans [0..2*n] }
        ArrayList<Integer> result = new ArrayList<>();
        int i=0;
        while (i<n){
            int temp = (int) (Math.random() * (n * 2));
            if(!result.contains(temp)){
                result.add(temp);
                i++;
            }
        }
        return result;
    }

    public static PaireResCompteur<Integer> rechSeqIt_O(ArrayList<Integer> vInt, int unInt) {
        // { vInt non vide, trié } =>
        // { résultat = variable de type PaireResCompteur avec :
        // res = * indice de la 1ère occurrence de unInt dans vInt
        // * -1 si non trouvé
        // compteur = nombre de comparaisons entre unInt
        // et un élément de vInt }
        int i=0, compteur=0;
        while (i < vInt.size() && vInt.get(i) != unInt){
            i++;
            compteur++;
        }
        if (i == vInt.size()){
            return new PaireResCompteur<>(-1, compteur);
        }else {
            return new PaireResCompteur<>(i, compteur+1);
        }
    }

    public static PaireResCompteur<Integer> rechDichoIt_O(ArrayList<Integer> vInt, int unInt) {
        // { vInt non vide, trié } =>
        // { résultat = variable de type PaireResCompteur avec :
        // res = * indice de la 1ère occurrence de unInt dans vInt
        // * -1 si non trouvé
        // compteur = nombre de comparaisons effectuées entre unInt
        // et un élément de vInt }
        int compteur = 0;
        if (vInt.get(vInt.size()-1) < unInt){
            return new PaireResCompteur<>(-1, 1);
        }else {
            compteur++;
            int inf=0, sup= vInt.size()-1, m;
            while (inf < sup){
                m = (inf+sup)/2;
                if (vInt.get(m) >= unInt){
                    sup = m;
                    compteur++;
                }else {
                    inf = m+1;
                    compteur++;
                }
            }
            if (vInt.get(sup) == unInt){
                return new PaireResCompteur<>(sup, compteur+1);
            }else {
                return new PaireResCompteur<>(-1, compteur+1);
            }
        }
    }

    public static PaireResCompteur<Integer> rechDichoRec_O(ArrayList<Integer> vInt, int unInt) {
        // { vInt non vide, trié } =>
        // { résultat = variable de type PaireResCompteur avec :
        // res = * indice de la 1ère occurrence de unInt dans vInt
        // * -1 si non trouvé
        // compteur = nombre de comparaisons effectuées entre unInt
        // et un élément de vInt }
        return rechDichoRec_O_wk(vInt, unInt, 0, vInt.size()-1);
    }

    public static PaireResCompteur<Integer> rechDichoRec_O_wk(ArrayList<Integer> vInt, int unInt, int inf, int sup) {
        // { 0<=inf<=sup<=vInt.size()-1, vInt[inf..sup] non vide et trié } =>
        // { résultat = variable de type PaireResCompteur avec :
        // res = * inf (ou sup) si inf=sup et unInt trouvé à l'indice inf
        // * -1 si non trouvé
        // compteur = nombre de comparaisons effectuées... }
        if (inf == sup){
            if(inf != unInt){
                return new PaireResCompteur<>(-1, 0);
            }else {
                return new PaireResCompteur<>(inf, 0);
            }
        }else {
            PaireResCompteur<Integer> compteur;
            int m=(inf+sup)/2;
            if (unInt > vInt.get(m)){
                compteur = rechDichoRec_O_wk(vInt, unInt, m+1, sup);
                compteur.setCompteur(compteur.getCompteur()+1);
            }else {
                compteur = rechDichoRec_O_wk(vInt, unInt, inf, m);
                compteur.setCompteur(compteur.getCompteur()+1);
            }
            return compteur;
        }
    }




}
