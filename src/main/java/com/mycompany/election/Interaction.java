package com.mycompany.election;

public class Interaction extends TypeEvolution{
    //return simulation ?
    public void evolution (Simulation simulation) {
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
            
            
            
        }
    }
}
