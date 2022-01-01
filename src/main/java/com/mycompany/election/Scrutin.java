package com.mycompany.election;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Scrutin {

	
        
        public abstract String election(Electeur[] electeurs, Candidat[] candidats);
        
        //Ou mettre cette méthode ?
        public List<Map.Entry<String, Double>> getClassement(Electeur electeur, Candidat[] candidats){
        
        List<Map.Entry<String, Double>> listCandidatVecteur = null;
        Map<String, Double> mapCandidatVecteur = new HashMap<String, Double>();
        
            for(Candidat candidat : candidats){
                Double diffVecteur = 0.0;
                
                //On parcours les différents critères 
                for(int j=0;j<candidat.getOpinions().length;j++){
                   diffVecteur+=electeur.getOpinions()[j] - candidat.getOpinions()[j];
                }
                
                mapCandidatVecteur.put(candidat.getNom(), diffVecteur);
                listCandidatVecteur = new ArrayList<>(mapCandidatVecteur.entrySet());
		listCandidatVecteur.sort(Map.Entry.comparingByValue());
                
                
            }
        
            
            return listCandidatVecteur;
        
    }
	
}
