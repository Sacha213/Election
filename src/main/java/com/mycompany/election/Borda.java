package com.mycompany.election;

import java.util.List;
import java.util.Map;

public class Borda extends Scrutin {

    @Override
    public List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats) {
        
        //Initialisation des votes pour les différents candidats
        Map<Candidat, Integer> votes = super.initialisationVotes(candidats);
        
        //On parcours la liste des électeurs 
        for(Electeur electeur : electeurs){
            //On classe les candidats
            List<Map.Entry<Candidat, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);
            
            //On parcours la liste du classement
            for(int i = 0; i<listCandidatsVecteur.size();i++){
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
	
}
