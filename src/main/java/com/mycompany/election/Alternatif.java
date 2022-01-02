package com.mycompany.election;

import java.util.List;
import java.util.Map;

public class Alternatif extends Scrutin{

    @Override
    public Candidat election(Electeur[] electeurs, Candidat[] candidats) {
        
        while(candidats.length>1){
        
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
        
        //On met à jour la liste des candidtas
        int nbCandidatRestant = candidats.length -1;
        candidats = new Candidat[nbCandidatRestant];
        
        for(int i =0; i<nbCandidatRestant; i++){
            Candidat candidat = votesTrier.get(i).getKey();
            candidats[i]=candidat;
            }
        
        }
        
        Candidat candidat = candidats[0];
        
        return candidat;
    }
	
}
