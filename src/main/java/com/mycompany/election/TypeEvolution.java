package com.mycompany.election;

import static java.lang.Math.abs;

public abstract class TypeEvolution {
	

	public void evoluer(Simulation s){

	}
        
        //SI +1 et -0 ???
        public void deplacement(Personne personne1, Personne personne2){
            //On déplace la persone1 par rapport à la personne2
            
            
            //On calcul la somme des différences des critères
            int nbCriteres = personne1.getOpinions().length;
            Double[] opinionsP1 = personne1.getOpinions();
            Double[] opinionsP2 = personne2.getOpinions();
            Double somme = 0.0;
            for(int i = 0; i< nbCriteres; i++){
                somme +=abs(opinionsP1[i]-opinionsP2[i]);
            }
            
            double moyenne = somme/nbCriteres;
            
            //Approchement 
            if(moyenne<=0.3){
                //On parcours tous les critères
                for(int i = 0; i< nbCriteres; i++){
                    double difference = opinionsP1[i]-opinionsP2[i];
                    opinionsP1[i] = difference*moyenne;
                }
            }
            //Eloignement
            else if (moyenne>=0.7){
                //On parcours tous les critères
                for(int i = 0; i< nbCriteres; i++){
                    double difference = 1 -(opinionsP1[i]-opinionsP2[i]);
                    opinionsP1[i] = difference*moyenne;
                }
            }
        }
}
