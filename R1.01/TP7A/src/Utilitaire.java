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
    public static void triSelect(ArrayList<Polar> vPolar) {
        // { } => { vPolar a été trié selon l'ORDRE(auteur, annee)
        // en utilisant la méthode DU TRI PAR SÉLECTION }
        int i=0;
        while (i< vPolar.size()-1){
            int min = i, j=i+1;
            while (j < vPolar.size()){
                if (vPolar.get(j).compareTo(vPolar.get(min)) == -1){
                    min = j;
                }
                j++;
            }
            if (min != i){
                Polar temp = vPolar.get(i);
                vPolar.set(i, vPolar.get(min));
                vPolar.set(min, temp);
            }
            i++;
        }
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

    public static void triInsertion(ArrayList<Polar> vPolar) {
        // { } => { vPolar est trié selon l'ORDRE(auteur, annee)
        // en utilisant la méthode DU TRI PAR INSERTION }
        int j, i=1;
        Polar val;
        while (i<vPolar.size()){
            j=i;
            val = vPolar.get(i);
            while (j > 0 && vPolar.get(j-1).compareTo(val) == 1){
                vPolar.set(j, vPolar.get(j-1));
                j--;
            }
            vPolar.set(j, val);
            i++;
        }
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
}
