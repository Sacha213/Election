package com.mycompany.election;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *Classe Simulation
 * 
 * @author Sacha
 */
public class Simulation {
	private Scrutin scrutin;
	private Electeur[] electeurs;
	private Candidat[] candidats;

    /**
     * Crée une simulation
     */
    public Simulation() {
		super();
	}

    /**
     * Retourne le scrutin
     * @return
     */
    public Scrutin getScrutin() {
		return this.scrutin;
	}

    /**
     * Modifie le srutin
     * @param scrutin Scrutin
     */
    public void setScrutin(Scrutin scrutin) {
		this.scrutin = scrutin;
	}

    /**
     * Retourne la liste d'électeur de la simulation
     * @return
     */
    public Electeur[] getElecteurs() {
		return this.electeurs;
	}

    /**
     * Modifie la liste d'électeur de la simulation
     * @param electeurs liste d'électeurs
     */
    public void setElecteurs(Electeur[] electeurs) {
		this.electeurs = electeurs;
	}

    /**
     * Retourne la liste de candidats de la simulation
     * @return
     */
    public Candidat[] getCandidats() {
		return this.candidats;
	}

    /**
     * Modifie la liste de candidats de la simulation
     * @param candidats liste de candidats
     */
    public void setCandidats(Candidat[] candidats) {
		this.candidats = candidats;
	}

    /**
     * Retourne la liste des candidats triée en fonction de leurs évaluation 
     * après un sondage portant sur 10% des électeurs
     * @return
     */
    public List<Map.Entry<Candidat, Integer>> sondage() {
            int nbSondes = (int)(0.1 * electeurs.length);
            ArrayList<Integer> indexSondes = new ArrayList<Integer>();
            Electeur[] electeursSondage = new Electeur[nbSondes];
            for(int i = 0; i<nbSondes; i++){
                int index =(int)(Math.random() * (electeurs.length));
                if (indexSondes.contains(index)) i--;
                else {
                    electeursSondage[i] = electeurs[index];
                    indexSondes.add(i);
                }       
            }
            return scrutin.election(electeursSondage, candidats);
	}

    /**
     * Modifie le type d'évolution
     * @param te Type d'évolution
     */
    public void evolution(TypeEvolution te) {
            te.evoluer(this);
	}

    /**
     * Retourne la liste des candidats triée en fonction de leurs évaluation 
     * après une élection
     * @return
     */
    public List<Map.Entry<Candidat, Integer>> election() {
            return scrutin.election(electeurs, candidats);
	}
        


}
