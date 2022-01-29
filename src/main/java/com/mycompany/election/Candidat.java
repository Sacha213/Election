package com.mycompany.election;

/**
 *
 * Classe candidat
 * 
 * @author Sacha
 */
public class Candidat extends Personne{

    /**
     *
     * Crée un candidat
     * 
     * @param n nom
     * @param p prénom
     * @param o liste d'opinions
     */
    public Candidat(String n, String p, Double[] o) {
		super(n, p, o);
	}
	
}
