package sources.ihm;

import sources.hex.Plateau;

import sources.hex.Plateau;

import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {
        int taille = recupererTaille();
        Plateau plateau = new Plateau(taille);
        while(true) {
            affichePlateau(plateau);
            String coord = recupererCase(plateau);
            plateau.jouer(coord);
        }
    }

    public static int recupererTaille() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la taille de votre plateau ");
        int taille = sc.nextInt();
        while(!(taille < Plateau.getTailleMax() && taille > 0)) {
            System.out.println("Mauvaise entrez. Entrez la taille");
            taille = sc.nextInt();
        }
        return taille;
    }

    public static String recupererCase(Plateau p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
        String position = sc.next();
        while(!p.estValide(position)) {
            System.out.println("Mauvaise entrez. Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
            position = sc.next();
        }
        return position;
    }


    public static void affichePlateau(Plateau p) {
        System.out.println(p);
    }

}
