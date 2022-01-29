package com.mycompany.election;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Classe représentant la méthode de scrutin majoritaire à deux tours
 * 
 * @author Sacha
 */
public class Majoritaire2t extends Scrutin {

    /**
     *
     * Obtient une liste de candidats avec leurs nombre de votes triée
     * 
     * @param electeurs liste d'électeurs
     * @param candidats liste de candidats 
     * @return
     */
    @Override
    public List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats) {

        //1er tour
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

        //On ne garde que les 2 meilleurs candiats 
        Candidat candidat1 = votesTrier.get(0).getKey();
        Candidat candidat2 = votesTrier.get(1).getKey();

        //2ème tour
        //On met à jour la liste des candidats
        candidats = new Candidat[2];
        candidats[0] = candidat1;
        candidats[1] = candidat2;
        //vérifier la taille du tableau

        //Initialisation des votes pour les différents candidats
        votes = super.initialisationVotes(candidats);

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
        votesTrier = super.trierVotes(votes);
        //Candidat candidat = votesTrier.get(0).getKey();

        return votesTrier;
    }

    /**
     *
     * Affiche les résultats de l'élection
     * 
     * @param resultatSondage résultat du sondage ou de l'élection
     * @param nbElecteurs nombre d'électeurs
     */
    public void afficherResultat(List<Map.Entry<Candidat, Integer>> resultatSondage, int nbElecteurs) {
        System.out.print("Le candidat en première position du sondage est " + resultatSondage.get(0).getKey().getPrenom() + " " + resultatSondage.get(0).getKey().getNom());
        System.out.println(" avec " + resultatSondage.get(0).getValue() * 100 / nbElecteurs + " % des votes");
        for (int i = 1; i < resultatSondage.size(); i++) {
            System.out.print("Le candidat en " + (i + 1) + " ème position est " + resultatSondage.get(i).getKey().getPrenom() + " " + resultatSondage.get(i).getKey().getNom());
            System.out.println(" avec " + resultatSondage.get(i).getValue() * 100 / nbElecteurs + " % des votes");
        }
    }

}
