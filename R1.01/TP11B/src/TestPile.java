public class TestPile {
    public static void main(String[] args) {
        PileFloat p = new PileFloat(4);
        try {
            p.empile(1.0f);
            p.empile(2.0f);
            p.empile(3.0f);
        } catch (EPilePleine e) {
            System.out.println("Pile pleine");
        }
        try {
            System.out.println(p.depile());
            System.out.println(p.depile());
        } catch (EPileVide e) {
            throw new RuntimeException(e);
        }
        try {
            p.empile(2.4f);
            p.empile(15.0f);
            p.empile(7.0f);
            p.empile(1.0f);
        } catch (EPilePleine e) {
            System.out.println("Pile pleine");
        }




    }
}
