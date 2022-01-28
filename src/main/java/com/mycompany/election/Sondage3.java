package com.mycompany.election;

import java.util.List;
import java.util.Map;

public class Sondage3 extends TypeEvolution {
    // Le sondage 3 rapproche les électeurs de chaque candidat proportionnellement
    // à leur utilité

    public void evoluer(Simulation simulation) {
        List<Map.Entry<Candidat, Integer>> resultatSondage = simulation.sondage();

        //On parcours tous les électeurs
        for (int i = 0; i < simulation.getElecteurs().length; i++) {
            //On parcours tous les candidats
            Electeur electeur = simulation.getElecteurs()[i];
            for (int j = 0; j < simulation.getCandidats().length; j++) {
                //On récupère les opinions de l'electeur a chaque fois car elles
                // changent
                Double[] opinionsP1 = electeur.getOpinions();
                //On récupère le candidat
                Candidat candidat = resultatSondage.get(j).getKey();
                int nbVotes = resultatSondage.get(j).getValue();
                Double[] opinionsP2 = candidat.getOpinions();
                double distance = diffCritere(opinionsP1, opinionsP2);
                double utilite = 1 / distance * nbVotes / (int) (0.1 * simulation.getElecteurs().length);
                super.approcherUtilite(electeur, candidat, utilite);
            }

        }
    }
}
