package com.mycompany.election;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Majoritaire1t extends Scrutin{
	
    @Override
    public String election(Electeur[] electeurs, Candidat[] candidats){
        
        Map<String, Integer> votes = new HashMap<String, Integer>();
        for(Candidat candidat : candidats){
            votes.put(candidat.getNom(), 0);
        }
        
        //On parcours la liste des électeurs 
        for(Electeur electeur : electeurs){
            List<Map.Entry<String, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);
            String candidat = listCandidatsVecteur.get(0).getKey();
            int nbVotes = votes.get(candidat) + 1;
            votes.replace(candidat, nbVotes);
        }
        
        //trier les votes
        
        return null;
    }
    
    
    
}
