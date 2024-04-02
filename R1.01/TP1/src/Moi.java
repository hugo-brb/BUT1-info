import java.util.Scanner;
public class Moi {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);
        String nom, prenom, temp, sous_grp;
        int age;
        char grp;

        System.out.print("Saisissez votre Nom : ");
        nom = lecteur.nextLine();
        System.out.print("Saisissez votre Prénom : ");
        prenom = lecteur.nextLine();
        System.out.print("Saisissez votre Age : ");
        age = lecteur.nextInt();
        System.out.print("Saisissez votre Groupe (une lettre) : ");
        grp = lecteur.next().charAt(0); lecteur.nextLine();
        System.out.print("Saisissez votre Sous-Groupe (un seul chiffre) : \n");
        temp = lecteur.nextLine();
        sous_grp = String.valueOf(grp + temp);

        System.out.print("-----------------------------------------------------------------\n" +
                "RENSEIGNEMENTS VOUS CONCERNANT\n" +
                        "-----------------------------------------------------------------\n" +
                        "Identité : " + prenom + " " + nom + "\n " +
                        "Âge : " + age + "ans\n" +
                " Groupe : " + grp + "\n" +
                " Sous-groupe : " + sous_grp + "\n" +
                "-----------------------------------------------------------------");

    }
}
