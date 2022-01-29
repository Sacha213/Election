package com.mycompany.election;

import static java.lang.Math.abs;
import java.util.HashSet;

/**
 * Classe Type Evolution
 * @author Sacha
 */
public abstract class TypeEvolution {

    /**
     * Modification des préférences de chacun des électeurs
     * @param simulation Simulation
     */
    public abstract void evoluer(Simulation simulation);
        
    /**
     * Déplace les opinions d'une personne en fonction d'une autre
     * 
     * @param personne1 Personne dont les crières vont être modifiés
     * @param personne2 Personne qui va influencer une autre personne
     */
    public void deplacement(Personne personne1, Personne personne2){
            //On déplace la personne1 par rapport à la personne2
            
            
            //On calcul la somme des différences des critères
            int nbCriteres = personne1.getOpinions().length;
            Double[] opinionsP1 = personne1.getOpinions();
            Double[] opinionsP2 = personne2.getOpinions();
            double moyenne = diffCritere(opinionsP1, opinionsP2);
            
            //Approchement
            if(moyenne<=0.3){
                approcher(personne1, personne2, moyenne);
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
        
    /**
     *
     * Retourne la moyenne des différence des critères entre deux personnes
     * 
     * @param opinionsP1 Liste des opinions d'une personne
     * @param opinionsP2 Liste des opinions d'une personne
     * @return
     */
    public double diffCritere(Double[] opinionsP1, Double[] opinionsP2){
            //On calcul la somme des différences des critères
            int nbCriteres = opinionsP1.length;
                Double somme = 0.0;
             for(int i = 0; i< nbCriteres; i++){
                    somme +=abs(opinionsP1[i]-opinionsP2[i]);
                }
             return somme/nbCriteres;
        }
        
    /**
     * Approche les opinions d'une personne en fonction d'une autre
     * avec la moyenne des différence des critères
     * 
     * @param personne1 Personne dont les opinions vont être modifier
     * @param personne2 Personne qui va influencer une autre personne
     * @param distance Moyenne des différence des critères entre deux personnes
     */
    public void approcher(Personne personne1, Personne personne2, double distance){
            int nbCriteres = personne1.getOpinions().length;
            Double[] opinionsP1 = personne1.getOpinions();
            Double[] opinionsP2 = personne2.getOpinions();
            //On parcours tous les critères
            for(int i = 0; i< nbCriteres; i++){
                double difference = opinionsP1[i]-opinionsP2[i];
                opinionsP1[i] -= difference*distance;
            }
            //On applique les modifications des opinions à la personne
            personne1.setOpinions(opinionsP1);
        }
        
    /**
     *
     * Approche les opinions d'une personne en fonction d'une autre
     * avec l'utilité de cette personne
     * 
     * @param personne1 Personne dont les opinions vont être modifier
     * @param personne2 Personne qui va influencer une autre personne
     * @param utilite Utilité de la personne 2
     */
    public void approcherUtilite (Personne personne1, Personne personne2, double utilite){
            int nbCriteres = personne1.getOpinions().length;
            Double[] opinionsP1 = personne1.getOpinions();
            Double[] opinionsP2 = personne2.getOpinions();
            //On parcours tous les critères
            for(int i = 0; i< nbCriteres; i++){
                double difference = opinionsP1[i]-opinionsP2[i];
                opinionsP1[i] = difference*utilite;
            }
            //On applique les modifications des opinions à la personne
            personne1.setOpinions(opinionsP1);
        }
}


