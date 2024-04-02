import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.List.*;

public class CompareTris {
    public static void main(String[] args) {
        ArrayList<Integer> vInt =  new ArrayList<>();
        ArrayList<Integer> vIntCroissant =  new ArrayList<>(List.of(5, 6, 7, 8, 9, 12, 14, 17, 18));
        ArrayList<Integer> vIntNonTrie =  new ArrayList<>(List.of(12, 7, 9, 14, 5, 17, 6, 8, 18));
        ArrayList<Integer> vIntDecroissant =  new ArrayList<>(List.of(18, 17, 14, 12, 9, 8, 7, 6, 5));
        int nbComp;
        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 1\n" +
                "-----------------------------------------------------------");

        for (int x : vIntCroissant){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCroissant);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Insertions **");
        vInt = new ArrayList<>(vIntCroissant);
        nbComp = Utilitaire.triInsert_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Selection **");
        vInt = new ArrayList<>(vIntCroissant);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 2\n" +
                "-----------------------------------------------------------");

        for (int x : vIntNonTrie){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntNonTrie);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Insertions **");
        vInt = new ArrayList<>(vIntNonTrie);
        nbComp = Utilitaire.triInsert_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Selection **");
        vInt = new ArrayList<>(vIntNonTrie);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 3\n" +
                "-----------------------------------------------------------");

        for (int x : vIntDecroissant){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntDecroissant);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Insertions **");
        vInt = new ArrayList<>(vIntDecroissant);
        nbComp = Utilitaire.triInsert_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);
        System.out.println("** Tri Selection **");
        vInt = new ArrayList<>(vIntDecroissant);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 4\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas4 =  new ArrayList<>(List.of(1, 1, 1, 1, 1, 6, 7, 8, 9));
        for (int x : vIntCas4){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas4);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 5\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas5 =  new ArrayList<>(List.of(1, 2, 3, 4, 9, 9, 9, 9, 9));
        for (int x : vIntCas5){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas5);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 6\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas6 =  new ArrayList<>(List.of(18, 17, 16, 15, 0, 0, 0, 0, 0));
        for (int x : vIntCas6){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas6);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 7\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas7 =  new ArrayList<>(List.of(18, 18, 18, 15, 9, 4, 3, 0, -2));
        for (int x : vIntCas7){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas7);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 8\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas8 =  new ArrayList<>(List.of(18, 18, 18, 18, 18, 4, 3, 0, -2));
        for (int x : vIntCas8){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas8);
        nbComp = Utilitaire.triBulle_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                        CAS 9\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas9 =  Utilitaire.genVectSansDoublons(10);
        for (int x : vIntCas9){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas9);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                       CAS 10\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas10 =  Utilitaire.genVectSansDoublons(40);
        for (int x : vIntCas10){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas10);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


        System.out.println("-----------------------------------------------------------\n" +
                "                       CAS 11\n" +
                "-----------------------------------------------------------");
        ArrayList<Integer> vIntCas11 =  Utilitaire.genVectSansDoublons(160);
        for (int x : vIntCas11){
            System.out.print(x+" ");
        }
        System.out.println("\n-----------------------\n" +
                "** Tri à bulles **");
        vInt = new ArrayList<>(vIntCas11);
        nbComp = Utilitaire.triSelect_O(vInt);
        System.out.print("vInt = (");
        for (int x : vInt){
            System.out.print(x+" ");
        }
        System.out.println(")\n --> Nb_Comparaisons : " + nbComp);


    }
}
