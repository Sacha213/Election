package com.mycompany.election;

import java.util.List;
import java.util.Map;

/**
 *
 * Classe représentant la méthode de scrutin Borda
 * 
 * @author Sacha
 */
public class Borda extends Scrutin {

    /**
     *
     * Obtient une liste de candidats avec leurs nombre de points triée
     * 
     * @param electeurs liste d'électeurs
     * @param candidats liste de candidats
     * @return
     */
    @Override
    public List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats) {

        //Initialisation des votes pour les différents candidats
        Map<Candidat, Integer> votes = super.initialisationVotes(candidats);

        //On parcours la liste des électeurs 
        for (Electeur electeur : electeurs) {
            //On classe les candidats
            List<Map.Entry<Candidat, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);

            //On parcours la liste du classement
            for (int i = 0; i < listCandidatsVecteur.size(); i++) {
                Candidat candidat = listCandidatsVecteur.get(i).getKey();

                //On met à jour les votes
                int nbVotes = votes.get(candidat) + (listCandidatsVecteur.size() - i);
                votes.replace(candidat, nbVotes);
            }

        }

        //On trie les votes
        List<Map.Entry<Candidat, Integer>> votesTrier = super.trierVotes(votes);
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
        System.out.println(" avec " + resultatSondage.get(0).getValue() + " points");
        for (int i = 1; i < resultatSondage.size(); i++) {
            System.out.print("Le candidat en " + (i + 1) + " ème position est " + resultatSondage.get(i).getKey().getPrenom() + " " + resultatSondage.get(i).getKey().getNom());
            System.out.println(" avec " + resultatSondage.get(i).getValue() + " points");
        }
    }

}
