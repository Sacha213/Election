package com.mycompany.election;

import java.util.List;
import java.util.Map;

public class Sondage1 extends Sondage {
    // Le sondage1 rapproche chaque électeur du Candidat le plus proche de lui parmi
    // les n premiers candidats dans un sondage

    public void evoluer(Simulation simulation) {
        List<Map.Entry<Candidat, Integer>> resultatSondage = simulation.sondage();
        int n = 4;

        //On parcours tous les électeurs
        for (int i = 0; i < simulation.getElecteurs().length; i++) {

            //On récupère le candidat le plus proche de l'électeur
            Electeur electeur = simulation.getElecteurs()[i];
            Candidat meilleurCandidat = resultatSondage.get(0).getKey();
            Double[] opinionsP1 = electeur.getOpinions();
            Double[] opinionsP2 = meilleurCandidat.getOpinions();
            double minDistance = diffCritere(opinionsP1, opinionsP2);

            for (int j = 1; j < n; j++) {
                //On récupère le candidat
                Candidat candidat = resultatSondage.get(j).getKey();

                opinionsP1 = electeur.getOpinions();
                opinionsP2 = candidat.getOpinions();
                double distance = diffCritere(opinionsP1, opinionsP2);

                if (distance < minDistance) {
                    meilleurCandidat = candidat;
                }
            }
            super.approcher(electeur, meilleurCandidat, minDistance);

        }
    }
}
