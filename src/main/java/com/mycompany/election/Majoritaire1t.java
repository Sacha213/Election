package com.mycompany.election;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Majoritaire1t extends Scrutin{
	
    @Override
    public Candidat election(Electeur[] electeurs, Candidat[] candidats){
        
        //Initialisation des votes pour les différents candidats
        Map<Candidat, Integer> votes = super.initialisationVotes(candidats);
        
        
        //On parcours la liste des électeurs 
        for(Electeur electeur : electeurs){
            //On classe les candidats
            List<Map.Entry<Candidat, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);
            Candidat candidat = listCandidatsVecteur.get(0).getKey();
            
            //On met à jour les votes
            int nbVotes = votes.get(candidat) + 1;
            votes.replace(candidat, nbVotes);
        }
        
        //On trie les votes
        List<Map.Entry<Candidat, Integer>> votesTrier = super.trierVotes(votes);
        Candidat candidat = votesTrier.get(0).getKey();
        
        return candidat;
    }
    
    
    
}
