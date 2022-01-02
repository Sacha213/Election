package com.mycompany.election;

import java.util.List;
import java.util.Map;

public class Approbation extends Scrutin {
    private int critere;
    
    public Approbation(int critere){
        super();
        this.critere = critere;
    }

    @Override
    public Candidat election(Electeur[] electeurs, Candidat[] candidats) {
        
        //Initialisation des votes pour les différents candidats
        Map<Candidat, Integer> votes = super.initialisationVotes(candidats);
        
        
        //On parcours la liste des électeurs 
        for(Electeur electeur : electeurs){
            //On classe les candidats
            List<Map.Entry<Candidat, Double>> listCandidatsVecteur = super.getClassement(electeur, candidats);
            //On parcours la liste du classement
            for(Map.Entry<Candidat, Double> mapCandidat : listCandidatsVecteur){
                Double diffVecteur = mapCandidat.getValue();
                if(diffVecteur<critere){
                    Candidat candidat = mapCandidat.getKey();
                    //On met à jour les votes
                    int nbVotes = votes.get(candidat) + 1;
                    votes.replace(candidat, nbVotes);}
            }
            
        }
        
        //On trie les votes
        List<Map.Entry<Candidat, Integer>> votesTrier = super.trierVotes(votes);
        Candidat candidat = votesTrier.get(0).getKey();
        
        return candidat;
    }
	
}
