import java.util.Scanner;

import static java.lang.Math.random;

public class TestListInt {
    public static void main(String[] args){
        ListeChainee<Integer> listInt = new ListeChainee<>();
        for (int i = 1; i < 16; i++) {
            listInt.insereAtPosit(i, (int) (Math.random()*100));
        }
        System.out.println("Somme itérative : " + Utilitaire.sumIter(listInt));
        System.out.println("Somme récursive : " + Utilitaire.sumRec(listInt));
        Scanner lecteur = new Scanner(System.in);
        int unInt = Utilitaire.getInt_IME(lecteur);
        Utilitaire.existIntRec(listInt, unInt);
        int posPremSup = Utilitaire.posFirstSup(listInt, unInt);
        if (posPremSup != 0){
            try{
                System.out.println(listInt.getInfoAtPosit(posPremSup));
            } catch (ExceptionMauvaisIndice e) {
                System.out.println("Aucun entier de listInt n'a une valeur supérieure à "+unInt);
            }
        }else {
            System.out.println("Aucun entier de listInt n'a une valeur supérieure à "+unInt);
        }

        System.out.println("PLUS GRAND ENTIER DE LISTINT : "+Utilitaire.bigger(listInt));
        int pos = Utilitaire.getIntMinMax_IME(lecteur, 1, listInt.getLongueur());
        ListeChainee<Integer> subListInt = Utilitaire.subList(listInt, pos);
        subListInt.afficheGaucheDroiteRec();
        System.out.println(Utilitaire.nbMult2(subListInt));








    }
}
