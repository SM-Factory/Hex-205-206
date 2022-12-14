package sources.ihm;

import sources.hex.IJoueur;
import sources.hex.Plateau;
import sources.joueurs.Humain;
import sources.joueurs.OrdinateurAleatoire;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IHM implements IIHM{

    public static void main(String[] args) throws InterruptedException {
        IIHM ihm = new IHM();
        int partie = ihm.choixPartie();
        int taille = ihm.recupererTaille();
        Plateau plateau = new Plateau(taille, ihm.creationJoueur(partie,ihm));
        while(!(plateau.estFinie())) {
            //clear
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //affiche plateau
            ihm.affichePlateau(plateau);
            System.out.println("Je reflechis");

            //sleep
            TimeUnit.SECONDS.sleep(1);

            //joue
            plateau.jouer();
        }
        ihm.affichePlateau(plateau);
        System.out.println("Partie Finie ! Le joueur gagnant est celui jouant les " + plateau.getGagnant());
    }

    public int recupererTaille() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la taille de votre plateau ");
        int taille = sc.nextInt();
        while(!(taille < Plateau.getTailleMax() && taille > 0)) {
            System.out.println("Mauvaise entrez. Entrez la taille");
            taille = sc.nextInt();
        }
        return taille;
    }


    public String recupererCase(Plateau p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
        String position = sc.next();
        while(!p.estValide(position)) {
            System.out.println("Mauvaise entrez. Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
            position = sc.next();
        }
        return position;
    }


    public void affichePlateau(Plateau p) {
        System.out.println(p);
    }

    public int choixPartie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numéro de la partie que vous souhaitez jouer\n1 - Partie Classique (Humain vs Humain)\n2 - Partie Contre Ordinateur (Humain vs Ordi)\n3 - Partie Ordinateur (Ordinateur vs Ordinateur)");
        int choix = sc.nextInt();
        while(choix < 0 && choix > 4) {
            System.out.println("Mauvaise entrez. Entrez la taille");
            choix = sc.nextInt();
        }
        return choix;
    }

    public IJoueur[] creationJoueur(int partie, IIHM ihm) {
        assert(partie < 4 && partie > 0);
        IJoueur joueurs[] = new IJoueur[2];

        switch(partie){
            case 1:
                joueurs[0] = new Humain(ihm);
                joueurs[1] = new Humain(ihm);
                break;

            case 2:
                joueurs[0] = new Humain(ihm);
                joueurs[1]= new OrdinateurAleatoire();
                break;

            case 3:
                joueurs[0] = new OrdinateurAleatoire();
                joueurs[1]= new OrdinateurAleatoire();
                break;

            default:
                throw new IllegalArgumentException("Numero de partie invalide");
        }
        return joueurs;
    }
}
