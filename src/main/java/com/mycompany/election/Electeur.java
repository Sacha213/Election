package com.mycompany.election;

/**
 *
 * Classe électeur
 * @author Sacha
 */
public class Electeur extends Personne{

    /**
     *
     * Crée un électeur
     * 
     * @param n nom 
     * @param p prénom
     * @param o liste d'opinions
     */
    public Electeur(String n, String p, Double[] o) {
		super(n, p, o);
	}
	
}
