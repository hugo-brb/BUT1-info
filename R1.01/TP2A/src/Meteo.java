import java.util.Scanner;

public class Meteo {
    public static void main(String[] args) {
        boolean pluie, parapluie, tongs, chapeau, bonEquipement;
        Scanner lecteur = new Scanner(System.in);

        System.out.print("Est-ce qu'il pleut ? (true pour oui, false pour non) ? ");
        pluie = lecteur.nextBoolean(); lecteur.nextLine();
        System.out.print("Avez-vous un parapluie (true pour oui, false pour non) ?");
        parapluie = lecteur.nextBoolean(); lecteur.nextLine();
        System.out.print("Avez-vous un chapeau (true pour oui, false pour non) ?");
        chapeau = lecteur.nextBoolean(); lecteur.nextLine();
        System.out.print("Portez-vous des tongs (true pour oui, false pour non) ?");
        tongs = lecteur.nextBoolean(); lecteur.nextLine();

        if (pluie) {
            if ((parapluie | chapeau) & !tongs){
                System.out.print("Vous êtes bien équipé !");
            }else{
                System.out.print("Vous n'êtes pas bien équipé !");
            }
        }else {
            System.out.print("Vous êtes bien équipé !");

        }
    }
}
