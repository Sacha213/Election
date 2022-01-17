package com.mycompany.election;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Alternatif extends Scrutin {

    @Override
    public List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats) {

        //Classemment final des candidats
        Map<Candidat, Integer> classement = new HashMap<>();

        while (candidats.length > 1) {

            //Initialisation des votes pour les différents candidats
            Map<Candidat, Integer> votes = super.initialisationVotes(candidats);

            //On parcours la liste des électeurs 
            for (Electeur electeur : electeurs) {
                //On classe les candidats
                List<Map.Entry<Candidat, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);
                Candidat candidat = listCandidatsVecteur.get(0).getKey();

                //On met à jour les votes
                int nbVotes = votes.get(candidat) + 1;
                votes.replace(candidat, nbVotes);
            }

            //On trie les votes
            List<Map.Entry<Candidat, Integer>> votesTrier = super.trierVotes(votes);

            //On met à jour la liste des candidtas
            int nbCandidatRestant = candidats.length - 1;
            candidats = new Candidat[nbCandidatRestant];

            for (int i = 0; i < nbCandidatRestant; i++) {
                Candidat candidat = votesTrier.get(i).getKey();
                candidats[i] = candidat;
            }
            //On récupère le candidat éjecté
            classement.put(votesTrier.get(votesTrier.size() - 1).getKey(), nbCandidatRestant + 1);

        }

        //On récupère le candidat élu
        classement.put(candidats[0], 1);

        List<Map.Entry<Candidat, Integer>> classementCandidat = super.trierVotes(classement);
        Collections.reverse(classementCandidat);

        return classementCandidat;
    }

    public void afficherResultat(List<Map.Entry<Candidat, Integer>> resultatSondage, int nbElecteurs) {
        System.out.print("Le candidat en première position du sondage est " + resultatSondage.get(0).getKey().getPrenom() + " " + resultatSondage.get(0).getKey().getNom());
        for (int i = 1; i < resultatSondage.size(); i++) {
            System.out.println("Le candidat en " + (i + 1) + " ème position est " + resultatSondage.get(i).getKey().getPrenom() + " " + resultatSondage.get(i).getKey().getNom());
        }
    }

}
