package main.java.joueurs;

import main.java.hex.IJoueur;
import main.java.hex.Pion;
import main.java.ihm.IPlateau;

import java.util.HashMap;

public class OrdinateurIntelligent implements IJoueur {
    private int joueur;

    public OrdinateurIntelligent(int j) {
        joueur = j;
    }

    public String jouer(IPlateau p) {
        String s = "";
        IPlateau test = p.clone();
        HashMap<String,Integer> m = new HashMap<>();
        int nbJouer = test.getNb(Pion.Rond) + test.getNb(Pion.Croix);
        int caseRestante = (int) Math.pow(test.taille(),2) - nbJouer;
        String[] cases = caseAJouer(test,caseRestante);
        int cmpGagnant = 0;
        long cmpPartie = 0;
        for (int i = 0; i<cases.length; i++) {
            test.jouer(cases[i]);
            m.put(cases[i], jouerPlateau(test, cmpGagnant, cmpPartie));
            test = p.clone();
        }
        return m.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }

    private int jouerPlateau(IPlateau p, int cmpGagnant, long cmpPartie) {
        IPlateau test = p.clone();
        long nbPartiePossible = factorial(test.taille()-1);
        int nbJouer = test.getNb(Pion.Rond) + test.getNb(Pion.Croix);
        int caseRestante = (int) Math.pow(test.taille(),2) - nbJouer;
        String[] cases = caseAJouer(p,caseRestante);
        for (int i = 0; i<cases.length; i++) {
            test.jouer(cases[i]);
            if (test.estFinie()) {
                cmpPartie++;
                if (test.getGagnant() == joueur) {
                    cmpGagnant++;
                }
                if (cmpPartie < nbPartiePossible) {
                    test=p.clone();
                }
            } else {
                jouerPlateau(test, cmpGagnant, cmpPartie);
            }
        }

        return cmpGagnant;
    }

    private String[] caseAJouer(IPlateau p, int caseRestante) {
        String[] cases = new String[caseRestante];
        int rempli = 0;
        for (int i = 0; i<p.taille(); i++) {
            for (int j = 0; j<p.taille(); j++) {
                String s = "";
                s+=(char) ('A' + i);
                s+= (j+1);
                //System.out.println("case possible "+s);
                if (p.estLibre(s)) {
                    //System.out.println(s);
                    //System.out.println("valide");
                    cases[rempli] = s;
                    rempli++;
                }
            }
        }
//        System.out.println("Cases");
//        for (String s : cases) {
//            System.out.println(s);
//        }
        return cases;
    }

    private long factorial(int num) {
        long factorial = 1;
        for(int i = 1; i <= num; ++i)
        {
            // factorial = factorial * i;
            factorial *= i;
        }
        return factorial;
    }
}
