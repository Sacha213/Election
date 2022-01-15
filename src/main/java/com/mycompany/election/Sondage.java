package com.mycompany.election;

import static java.lang.Math.abs;
import java.util.List;
import java.util.Map;

public abstract class Sondage extends TypeEvolution{
	public void Sondage1(int n, Simulation simulation){
            List<Map.Entry<Candidat, Integer>> resultatSondage = simulation.sondage();
            
            //On parcours tous les électeurs
            for (int i = 0; i < simulation.getElecteurs().length; i++){
                
                //On récupère le candidat le plus proche de l'électeur
                Electeur electeur = simulation.getElecteurs()[i];
                Candidat meilleurCandidat = resultatSondage.get(0).getKey();
                Double[] opinionsP1 = electeur.getOpinions();
                Double[] opinionsP2 = meilleurCandidat.getOpinions();
                double minDistance = diffCritere(opinionsP1, opinionsP2);
                
                for (int j = 1; j<n; j++){
                    //On récupère le candidat
                    Candidat candidat = resultatSondage.get(j).getKey();
                    
                    opinionsP1 = electeur.getOpinions();
                    opinionsP2 = candidat.getOpinions();
                    double distance = diffCritere(opinionsP1, opinionsP2);
                    
                    if(distance<minDistance)meilleurCandidat=candidat;
                }
                super.approcher(electeur, meilleurCandidat, minDistance);
                
            }
        }
        
        public void Sondage2 (Simulation simulation) {
            List<Map.Entry<Candidat, Integer>> resultatSondage = simulation.sondage();
            
            //On parcours tous les électeurs
            for (int i = 0; i < simulation.getElecteurs().length; i++){
                //On récupère le candidat ayant l'utilité la plus élevée
                Electeur electeur = simulation.getElecteurs()[i];
                Candidat meilleurCandidat = resultatSondage.get(0).getKey();
                int nbVotes = resultatSondage.get(0).getValue();
                Double[] opinionsP1 = electeur.getOpinions();
                Double[] opinionsP2 = meilleurCandidat.getOpinions();
                double minDistance = diffCritere(opinionsP1, opinionsP2);
                double maxUtilite = 1/minDistance * nbVotes;
                
                
                //On parcours tous les candidats
                for (int j = 1; j<simulation.getCandidats().length; j++){
                    //On récupère le candidat
                    Candidat candidat = resultatSondage.get(j).getKey();
                    nbVotes = resultatSondage.get(j).getValue();
                    
                    opinionsP1 = electeur.getOpinions();
                    opinionsP2 = candidat.getOpinions();
                    double distance = diffCritere(opinionsP1, opinionsP2);
                    double utilite = 1/distance * nbVotes/(int) 0.1*simulation.getElecteurs().length;
                    if(utilite>maxUtilite) {
                        meilleurCandidat=candidat;
                        minDistance = distance;
                    }
                }
                super.approcher(electeur, meilleurCandidat, minDistance);
            }
        }
        
        
       //Fonction sondage3 à réfléchir ou pas 
        
}
