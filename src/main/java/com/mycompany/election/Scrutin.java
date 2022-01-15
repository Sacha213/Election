package com.mycompany.election;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Scrutin {

	
        
        public abstract List<Map.Entry<Candidat, Integer>> election(Electeur[] electeurs, Candidat[] candidats);
                
        //Ou mettre ces méthodes ?
        public List<Map.Entry<Candidat, Double>> getClassement(Electeur electeur, Candidat[] candidats){
        
        Map<Candidat, Double> mapCandidatVecteur = new HashMap<>();
        
            for(Candidat candidat : candidats){
                Double diffVecteur = 0.0;
                
                //On parcours les différents critères 
                for(int j=0;j<candidat.getOpinions().length;j++){
                   diffVecteur+=abs(electeur.getOpinions()[j] - candidat.getOpinions()[j]);
                }
                
                mapCandidatVecteur.put(candidat, diffVecteur);
                
                
                
            }
            List<Map.Entry<Candidat, Double>> listCandidatVecteur = new ArrayList<>(mapCandidatVecteur.entrySet());
            listCandidatVecteur.sort(Map.Entry.comparingByValue());
            
            return listCandidatVecteur;
        
    }
        
        public List<Map.Entry<Candidat, Integer>> trierVotes(Map<Candidat, Integer> votes){
                
            List<Map.Entry<Candidat, Integer>> listVoteTrier = new ArrayList<>(votes.entrySet());
            listVoteTrier.sort(Map.Entry.comparingByValue());
            Collections.reverse(listVoteTrier);
            
            return listVoteTrier;
            
        }
        
        public Map<Candidat, Integer> initialisationVotes(Candidat[] candidats){
            Map<Candidat, Integer> votes = new HashMap<Candidat, Integer>();
            for(Candidat candidat : candidats){
                votes.put(candidat, 0);
            }
            return votes;
        }
	
}
