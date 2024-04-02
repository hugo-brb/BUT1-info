import java.util.ArrayList;

public class Utilitaire {
    public static Pays plusGrandPaysIter(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = élément de vPays ayant la plus grande superficie }
        Pays result = vPays.get(0);
        for(Pays p:vPays){
            if (p.getSuperficie() > result.getSuperficie()){
                result=p;
            }
        }
        return result;
    }

    public static Pays plusGrandPaysDPR(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = élément de vPays ayant la plus grande superficie }
        return maxPaysDPRWorker(vPays, 0, vPays.size()-1);
    }
    public static Pays maxPaysDPRWorker(ArrayList<Pays> vPays, int inf, int sup) {
        // { vPays non vide, 0<=inf<=sup<vPays.size() } =>
        // { résultat = élément de plus grande superficie dans vPays[inf..sup] }
        if(inf == sup){
            return vPays.get(inf);
        }else {
            int m = (inf + sup)/2;
            Pays gauche = maxPaysDPRWorker(vPays, inf, m);
            Pays droite = maxPaysDPRWorker(vPays,m+1, sup);

            if (gauche.getSuperficie() < droite.getSuperficie() ){
                return droite;
            }else {
                return gauche;
            }
        }
    }

    public static int indMinPopIter(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays de l'élément de population la plus faible }
        int indice = 0;
        for (int i =1; i<vPays.size(); i++){
            if (vPays.get(indice).getPopulation()>vPays.get(i).getPopulation()){
                indice=i;
            }
        }
        return indice;
    }

    public static int indMinPopDPR(ArrayList<Pays> vPays) {
        // { vPays non vide } =>
        // { résultat = indice dans vPays de l'élément de population la plus faible }
        return indMinPopDPRWorker(vPays, 0, vPays.size()-1);
    }
    public static int indMinPopDPRWorker(ArrayList<Pays> vPays, int inf, int sup) {
        // { vPays non vide, 0<=inf<=sup<vPays.size() } =>
        // { résultat = indice dans vPay[inf..sup] de l'élément de population la plus faible }
        if (inf==sup) {
            return inf;
        }else {
            int m = (inf+sup)/2;
            int gauche = indMinPopDPRWorker(vPays, inf, m);
            int droite = indMinPopDPRWorker(vPays, m+1, sup);

            if (vPays.get(gauche).getPopulation() < vPays.get(droite).getPopulation()){
                return gauche;
            }else {
                return droite;
            }
        }
    }

    public static void triBulleContNom(ArrayList<Pays> vPays) {
        // { } => { résultat = vecteur contenant les éléments de vPays,
        // triés selon l'ORDRE(continent, nom) }
        // ALGORITHME : tri à bulles amélioré
        int j, i=0;
        boolean permute=true;
        while (permute){
            j = vPays.size()-1;
            permute=false;
            while (j>i){
                if (vPays.get(j).compareTo(vPays.get(j - 1)) < 0){
                    Pays temp = vPays.get(j); vPays.set(j, vPays.get(j-1)); vPays.set(j-1, temp);
                    permute = true;
                }
                j--;
            }
            i++;
        }
    }

    public static boolean verifTriContNom(ArrayList<Pays> vPays) {
        // { } => { résultat = vrai si vPays trié selon l'ORDRE(continent, nom) }
        boolean estTrie=true; int i=1;
        while (i < vPays.size() && estTrie){
            if (vPays.get(i).compareTo(vPays.get(i-1))<0){
                estTrie=false;
            }
            i++;
        }
        return estTrie;
    }

    public static int indDichoIter(ArrayList<Pays> vPays, String contP, String nomP) {
        // { vPays trié selon l'ORDRE(continent, nom) } =>
        // { résultat = * indice dans vPays du pays de continent contP et de nom nomP,
        // si trouvé
        // * -1 si non trouvé }
        Pays rech = new Pays(nomP, contP);
        if(vPays.get(vPays.size()-1).compareTo(rech)<0){
            return -1;
        }else{
            int inf=0, sup= vPays.size()-1, m;
            while (inf<sup){
                m=(inf+sup)/2;
                if (vPays.get(m).compareTo(rech)>=0){
                    sup=m;
                }else {
                    inf=m+1;
                }
            }
            if(vPays.get(sup).compareTo(rech)==0){
                return sup;
            }else {
                return -1;
            }
        }
    }

    public static int indDichoRec(ArrayList<Pays> vPays, String contP, String nomP) {
        // { vPays trié selon l'ORDRE(continent, nom) } =>
        // { résultat = * indice dans vPays du pays de continent contP et de nom nomP,
        // si trouvé
        // * -1 si non trouvé }
        if (vPays.isEmpty()){
            return -1;
        }else {
            return indDichoWorker(vPays, contP, nomP, 0, vPays.size()-1);
        }
    }
    public static int indDichoWorker(ArrayList<Pays> vPays, String contP, String nomP, int inf, int sup) {
        // { vPays trié selon l'ORDRE(continent,nom), 0<=inf<=sup<vPays.size() } =>
        // { résultat = * indice dans vPays[inf..sup] du pays de continent contP et de
        // nom nomP, si trouvé
        // * -1 si non trouvé }
        Pays r = new Pays(nomP, contP);
        if(inf==sup){
            return inf;
        }else {
            int m=(inf+sup)/2;
            if (vPays.get(m).compareTo(r)>=0){
                return indDichoWorker(vPays, contP,nomP, inf, m);
            }else {
                return indDichoWorker(vPays, contP, nomP, m+1, sup);
            }
        }
    }

    public static int nbPaysDeContInfNbHabIter(ArrayList<Pays> vPays, String unCont, int popMax) {
        // { vPays trié selon l'ORDRE(continent, nom), popMax > 0 } =>
        // { résultat = nombre d'éléments de vPays de continent unCont
        // et de population < popMax }
        int compteur=0;
        for (Pays p : vPays){
            if(unCont.compareTo(p.getContinent())==0 && p.getPopulation() < popMax){
                compteur++;
            }
        }
        return compteur;
    }
    public static int nbPaysDeContInfNbHabRec(ArrayList<Pays> vPays, String unCont, int popMax) {
        // { vPays trié selon l'ORDRE(continent, nom), popMax > 0 } =>
        // { résultat = nombre d'éléments de vPays de continent unCont
        // et de population < popMax }
        return nbPaysDeContInfNbHabWorker(vPays, unCont, popMax, 0);
    }

    public static int nbPaysDeContInfNbHabWorker(ArrayList<Pays> vPays, String unCont,int popMax, int ind) {
        // { vPays[ind..vPays.size()-1] trié selon l'ORDRE(continent, nom),
        // 0<=ind<vPays.size(), popMax > 0 } =>
        // { résultat = nombre d'éléments de vPays[ind..vPays.size()-1]
        // de continent unCont et de population < popMax }
        if(ind == vPays.size()){
            return 0;
        }else {
            if (vPays.get(ind).getContinent().compareTo(unCont)==0 && vPays.get(ind).getPopulation() < popMax){
                return 1 + nbPaysDeContInfNbHabWorker(vPays, unCont, popMax, ind+1);
            }else {
                return nbPaysDeContInfNbHabWorker(vPays, unCont, popMax, ind + 1);
            }
        }
    }

    public static void triFusion(ArrayList<Pays> vPays, int inf, int sup) {
        // { vInt[inf..sup] non vide } => { vInt[inf..sup] trié }
        if(inf < sup){
            int m = (inf+sup)/2;
            triFusion(vPays, inf, m);
            triFusion(vPays, m+1, sup);
            fusionTabGTabD(vPays, inf, m, sup);
        }
    }

    public static void fusionTabGTabD(ArrayList<Pays> vPays, int inf, int m, int sup) {
        // { inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié, vInt[m+1..sup] trié }
        // => { VInt[inf..sup] trié }
        ArrayList<Pays> vTemp = new ArrayList<>(vPays);
        int i1 = inf;
        int i2 = m + 1;
        int i = inf;
        while (i1 <= m && i2 <= sup) {
            if (vTemp.get(i1).getSuperficie() <= vTemp.get(i2).getSuperficie()) {
                vPays.add(i, vTemp.get(i1));
                i1++;
                vPays.remove(i + 1);
            } else {
                vPays.add(i, vTemp.get(i2));
                vPays.remove(i + 1);
                i2++;
            }
            i++;
        }
        if (i <= sup) {
            while (i1 <= m) {
                vPays.add(i, vTemp.get(i1));
                vPays.remove(i + 1);
                i1++;
                i++;
            }
            while (i2 <= sup) {
                vPays.add(i, vTemp.get(i2));
                vPays.remove(i + 1);
                i2++;
                i++;
            }
        }
    }


}