package main.java.ihm;

import main.java.hex.IJoueur;
import main.java.joueurs.OrdinateurAleatoire;
import main.java.hex.Plateau;
import main.java.joueurs.Humain;
import main.java.joueurs.IIHM;
import main.java.joueurs.OrdinateurIntelligent;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author SM-Factory
 * @version 1.0
 */
public class IHM implements IIHM {
    @Override
    public void lancerPartie(IPlateau plateau) throws InterruptedException {
        afficherRegle();
        while(!(plateau.estFinie())) {
            //clear
            clear();

            //affiche plateau
            affichePlateau(plateau);
            //System.out.println("Je reflechis");

            //sleep
            pauseSeconde(1);

            //joue
            plateau.jouer();
        }
        affichePlateau(plateau);
        afficherGagnant(plateau);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void pauseSeconde(int i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(i);
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void afficherGagnant(IPlateau plateau) {
        System.out.println("Partie Finie ! Le joueur gagnant est celui jouant les " + plateau.getGagnantNom());
    }

    private void afficherRegle() {
        System.out.println("Le joueur 1 a les croix et le joueur 2 a les ronds\nLes croix doivent rejoindre les bords droit et gauche et les ronds doivent rejoindre les bords haut et bas");
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


    public String recupererCase(IPlateau p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
        String position = sc.next();
        while(!p.estLibre(position)) {
            System.out.println("Mauvaise entrez. Entrez la position de votre coups au format Colonne Ligne (ex:B2)");
            position = sc.next();
        }
        return position;
    }


    private void affichePlateau(IPlateau p) {
        System.out.println(p);
    }

    public int choixPartie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numéro de la partie que vous souhaitez jouer\n1 - Partie Classique (Humain vs Humain)\n2 - Partie Contre Ordinateur (Humain vs Ordi)\n3 - Partie Ordinateur (Ordinateur vs Ordinateur)");
        int choix = sc.nextInt();
        while(choix < 0 || choix > 4) {
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
                joueurs[1] = createOrdi(1);
                break;

            case 3:
                joueurs[0] = createOrdi(0);
                joueurs[1]= createOrdi(1);
                break;

            default:
                throw new IllegalArgumentException("Numero de partie invalide");
        }
        return joueurs;
    }

    private int choixOrdinateur() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numéro du type d'ordinateur que vous souhaitez créer\n1 - Ordinateur Aléatoire\n2 - Ordinateur Intelligent\n");
        int choix = sc.nextInt();
        while(choix < 0 || choix > 3) {
            System.out.println("Mauvaise entrez. Entrez la taille");
            choix = sc.nextInt();
        }
        return choix;
    }

    private IJoueur createOrdi(int i) {
        switch(choixOrdinateur()) {
            case 1:
                return new OrdinateurAleatoire();
            case 2:
                return new OrdinateurIntelligent(i);
            default:
                throw new IllegalArgumentException("Numero d'ordinateur invalide");
        }
    }
}
