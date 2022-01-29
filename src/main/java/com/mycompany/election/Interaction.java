package com.mycompany.election;

/**
 * Classe représentant la dynamique Interaction socio-politiques
 * @author Sacha
 */
public class Interaction extends TypeEvolution{
    
    /**
     * Modification des préférences de chacun des électeurs par intéraction avec une autre personne choisie aléatirement 
     * @param simulation Simulation
     */
    public void evoluer (Simulation simulation) {
        //Atention, on peut avoir deux fois le mm électeur
        int nbElecteurs = simulation.getElecteurs().length;
        int nbCandidats = simulation.getCandidats().length;
        for(Electeur electeur : simulation.getElecteurs()) {
            int ElecCand = (int)(Math.random()*2);
            Personne personne;
            if (ElecCand == 0) {
                int rand = (int)(Math.random()*(nbElecteurs));
                personne = simulation.getElecteurs()[rand];
            }
            else {
                int rand = (int)(Math.random()*(nbCandidats));
                personne = simulation.getCandidats()[rand];
            }
        super.deplacement(electeur, personne);
        }
    }
}
