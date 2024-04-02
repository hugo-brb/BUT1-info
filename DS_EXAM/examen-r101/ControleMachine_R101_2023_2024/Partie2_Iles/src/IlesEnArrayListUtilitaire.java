import java.util.ArrayList;
import java.util.List;

public class IlesEnArrayListUtilitaire {

    /**
     * FOURNIE : Nombre d'îles renseignées dans leIles
     * @param lesIles   liste de référence
     * @return Nombre d'îles renseignées dans leIles
     */
    public static int getNombreIles(ArrayList<Ile> lesIles) {
        return lesIles.size();
    }

    /**
     * A ECRIRE - Exercice 4
     * @param lesIles
     * @param latitudeMin
     */
    public static void afficheNomIlesHemisphereNord(ArrayList<Ile> lesIles, float latitudeMin) {
        // { } =>
        // { les noms des éléments de lesIles situés dans l'hémisphère nord ont été affichés }
        //   INDICATION : reportez-vous à la figure 1 du sujet
        System.out.println("Iles de l'hémisphère nord : ");
        for (Ile ile : lesIles){
            if(ile.getLatitude() > latitudeMin){
                System.out.print(ile.getNom() + ", ");
            }
        }
    }

    /**
     * A ECRIRE - Exercice 4
     * @param lesIles
     * @param latitudeMax
     */
    public static void afficheNomIlesHemisphereSud(ArrayList<Ile> lesIles, float latitudeMax) {
        // { } =>
        // { les noms des éléments de lesIles situés dans l'hémisphère sud ont été affichés }
        //   INDICATION : reportez-vous à la figure 1 du sujet
        System.out.println("Iles de l'hémisphère sud : ");
        for (Ile ile : lesIles){
            if(ile.getLatitude() < latitudeMax){
                System.out.print(ile.getNom() + ", ");
            }
        }
    }

    /**
     * A ECRIRE - Exercice 5
     * @param lesIles
     */
    public static void afficheIlesPartagees(ArrayList<Ile> lesIles) {
        // { } =>
        // { les nom des éléments de lesIles pour lesquels l'attribut pays a plus d'un élément
        //   ont été affichés, suivis sur la même ligne par le contenu de leur attribut pays
        //   (cf. Trace_Ile) }
        System.out.println("Iles partagées par plusieurs pays");
        for(Ile ile : lesIles){
            if (ile.getPays().size() > 1){
                System.out.print("- " + ile.getNom() + " -> partagée par : [");
                for (String p : ile.getPays()){
                    System.out.print(p +", ");
                }
                System.out.print("]\n");
            }
        }


    }

    /**
     * A ECRIRE - Exercice 6
     * @param lesIles
     * @param lePays
     */
    public static void afficheNomIlesDePays(ArrayList<Ile> lesIles, String lePays) {
        // { } =>
        // { les nom des éléments de lesIles dont lePays est le premier élément de
        //   leur attribut pays, ont été affichés }
        System.out.println("Nom des îles dont le premier pays est " + lePays);
        for(Ile ile: lesIles){
            if (ile.getPays().get(0).equals(lePays)){
                System.out.println(" - " + ile.getNom());
            }
        }


    }

    /**
     * A ECRIRE - Exercice 7 (b)
     * @param lesIles
     */
    public static void triIlesBullesAmeliore(ArrayList<Ile> lesIles) {
        // { } =>
        // { lesIles a été trié par ordre croissant selon l'ORDRE(premier pays, superficie) }
        //   NOTE : méthode du TRI À BULLES AMÉLIORÉ }
        int j, i=0;
        boolean permute = true;
        while(permute){
            j = lesIles.size()-1;
            permute=false;
            while (j > i){
                if (lesIles.get(j).compareTo(lesIles.get(j-1)) < 0){
                    Ile temp = lesIles.get(j);
                    lesIles.set(j, lesIles.get(j-1));
                    lesIles.set(j-1, temp);
                    permute = true;
                }
                j--;
            }
            i++;
        }
    }

    /**
     * A ECRIRE - Exercice 7 (c)
     * @param lesIles
     * @return cf. postcondition
     */
    public static boolean sontIlesTriees(ArrayList<Ile> lesIles) {
        // { } =>
        // { résultat = true (vrai) si lesIles est trié selon l''ORDRE(premier pays, superficie),
        //                 false (faux) sinon
        int i = 1;
        while (i<lesIles.size() && lesIles.get(i).compareTo(lesIles.get(i-1))>=0){
            i++;
        }
        if (i==lesIles.size()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * A ECRIRE - Exercice 8
     * @param lesIles
     * @param lePays
     * @return cf. postcondition
     */
    public static int indicePremIleDePremPays_seq(ArrayList<Ile> lesIles, String lePays) {
        // { lesIles trié selon l'ORDRE(premier pays, superficie) } =>
        // { résultat = indice de la première île dont le premier pays est lePays,
        //                 -1 si aucune île n'a lePays comme premier pays }
        // NOTE : version ITÉRATIVE de la recherche séquentielle
        int i=0;
        while (i<lesIles.size() && !lesIles.get(i).getPays().get(0).equals(lePays)){
            i++;
        }
        if (i==lesIles.size()){
            return -1;
        }else{
            return i;
        }
    }

    /**
     * A ECRIRE - Exercice 9
     * @param lesIles
     * @param lePays
     * @return cf. postcondition
     */
    public static int indicePremIleDePremPays_dicho(ArrayList<Ile> lesIles, String lePays) {
        // { lesIles trié selon l'ORDRE(premier pays, superficie) } =>
        // { résultat = indice de la première île dont le premier pays est lePays,
        //                 -1 si aucune île n'a lePays comme premier pays }
        // NOTE : version ITÉRATIVE de la recherche dichotomique
        if(lesIles.get(lesIles.size()-1).getPays().get(0).compareTo(lePays) < 0){
            return -1;
        }else {
            int inf = 0, sup = lesIles.size()-1, m;
            while(inf < sup){
                m = (inf+sup) /2;
                if (lesIles.get(m).getPays().get(0).compareTo(lePays) >= 0){
                    sup = m;
                }else {
                    inf = m+1;
                }
            }
            if (lesIles.get(sup).getPays().get(0).compareTo(lePays) == 0){
                return sup;
            }else {
                return -1;
            }
        }
    }

    /**
     * A ECRIRE - Exercice 10
     * @param lesIles
     * @param lePays
     */
    public static void afficheIlesDePaysTrie(ArrayList<Ile> lesIles, String lePays) {
        // { lesIles trié selon l'ORDRE(premier pays, superficie) } =>
        // { * Le nom et la superficie de tous les éléments de lesIles dont lePays
        //     est le premier pays ont été affichés dans l'ordre croissant de superficie,
        //   * Si le lePays n'est le premier pays d'aucun élément de lesIles, un message
        //      approprié a été affiché }
        // Cette procédure doit OBLIGATOIREMENT utiliser indicePremIleDePremPays_seq
        ArrayList<Ile> temp = new ArrayList<>(List.copyOf(lesIles));
        int ind=0;
        do {
            ind = indicePremIleDePremPays_seq(temp, lePays);
            if (ind != -1){
                System.out.println(" - " + temp.get(ind).getNom() + " (Superficie = " + temp.get(ind).getSuperficie() + ")");
                temp.remove(ind);
            }
        }while (ind != -1);
    }

}
