package com.mycompany.election;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe scrutin
 * @author Sacha
 */
public abstract class Scrutin {

    /**
     * Retourne une liste de candidats triéer avec leurs évaluation
     * @param electeurs liste d'électeurs 
     * @param candidats liste de candidats 
     * @return
     */
    public abstract List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats);

    /**
     * Affiche les résulatat du sondage ou de l'élection
     * 
     * @param resultatSondage résultat du sondage ou de l'élection
     * @param nbElecteurs nombre d'électeurs
     */
    public abstract void afficherResultat(List<Map.Entry<Candidat, Integer>> resultatSondage, int nbElecteurs);


    /**
     *
     * Retourne la liste des candidats classée avec la moyenne des différence de critère pour un électeur
     * 
     * @param electeur électeur
     * @param candidats liste de candidat 
     * @return
     */
    public List<Map.Entry<Candidat, Double>> getClassement(Electeur electeur, Candidat[] candidats) {

        Map<Candidat, Double> mapCandidatVecteur = new HashMap<>();

        for (Candidat candidat : candidats) {
            Double diffVecteur = 0.0;

            //On parcours les différents critères 
            for (int j = 0; j < candidat.getOpinions().length; j++) {
                diffVecteur += abs(electeur.getOpinions()[j] - candidat.getOpinions()[j]);
            }
            double moyenne = diffVecteur / candidat.getOpinions().length;

            mapCandidatVecteur.put(candidat, moyenne);

        }
        List<Map.Entry<Candidat, Double>> listCandidatVecteur = new ArrayList<>(mapCandidatVecteur.entrySet());
        listCandidatVecteur.sort(Map.Entry.comparingByValue());

        return listCandidatVecteur;

    }

    /**
     * Retourne une liste de candidats triée avec leurs évaluation
     * @param votes
     * @return
     */
    public List<Map.Entry<Candidat, Integer>> trierVotes(Map<Candidat, Integer> votes) {

        List<Map.Entry<Candidat, Integer>> listVoteTrier = new ArrayList<>(votes.entrySet());
        listVoteTrier.sort(Map.Entry.comparingByValue());
        Collections.reverse(listVoteTrier);

        return listVoteTrier;

    }

    /**
     *
     * Retourne une map de candidat initialisée avec toutes les valeurs à 0 
     * 
     * @param candidats Liste de candidats
     * @return
     */
    public Map<Candidat, Integer> initialisationVotes(Candidat[] candidats) {
        Map<Candidat, Integer> votes = new HashMap<Candidat, Integer>();
        for (Candidat candidat : candidats) {
            votes.put(candidat, 0);
        }
        return votes;
    }

}
