import java.util.ArrayList;
import java.util.List;

public class TestTriFusion {
    public static void triFusion(ArrayList<Integer> vInt, int inf, int sup) {
        // { vInt[inf..sup] non vide } => { vInt[inf..sup] trié }
        if(inf < sup){
            int m = (inf+sup)/2;
            triFusion(vInt, inf, m);
            triFusion(vInt, m+1, sup);
            fusionTabGTabD(vInt, inf, m, sup);
        }
    }

    public static void fusionTabGTabD(ArrayList<Integer> vInt, int inf, int m, int sup) {
        // { inf <= sup, m = (inf+sup)/2, vInt[inf..m] trié, vInt[m+1..sup] trié }
        // => { VInt[inf..sup] trié }
        ArrayList<Integer> vTemp = new ArrayList<>(vInt);
        int i1 = inf;
        int i2 = m + 1;
        int i = inf;
        while (i1 <= m && i2 <= sup) {
            if (vTemp.get(i1) <= vTemp.get(i2)) {
                vInt.add(i, vTemp.get(i1));
                i1++;
                vInt.remove(i + 1);
            } else {
                vInt.add(i, vTemp.get(i2));
                vInt.remove(i + 1);
                i2++;
            }
            i++;
        }
        if (i <= sup) {
            while (i1 <= m) {
                vInt.add(i, vTemp.get(i1));
                vInt.remove(i + 1);
                i1++;
                i++;
            }
            while (i2 <= sup) {
                vInt.add(i, vTemp.get(i2));
                vInt.remove(i + 1);
                i2++;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> vInt = new ArrayList<>(List.of(2,4,6,1,2,3,5,84,54,6,1));
        System.out.println("NON TRIE");
        for (Integer nb : vInt){
            System.out.print(nb);
        }
        System.out.println("\n\nTRIE");
        triFusion(vInt, 0, vInt.size()-1);
        for (Integer nb : vInt){
            System.out.print(nb+" ");
        }
    }
}
