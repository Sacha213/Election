package com.mycompany.election;

/**
 * Classe personne
 * @author Sacha
 */
public abstract class Personne {
	private static int nombrePersonne=0;
	private int id;
	private String nom;
	private String prenom;
	private Double[] opinions;

    /**
     * Crée une personne
     * @param n nom de la personne
     * @param p prénom de la personne
     * @param o opinions de la personne
     */
    public Personne(String n, String p, Double[] o){
		id = nombrePersonne;
		nombrePersonne++;
		nom = n;
		prenom = p;
		opinions = o;
	}

    /**
     * Retourne l'idantifiant de la personne
     * @return
     */
    public int getId() {
		return this.id;
	}

    /**
     *
     * Modifie l'idantifiant de la personne
     * 
     * @param id identifiant 
     */
    public void setId(int id) {
		this.id = id;
	}

    /**
     * Retourne le nom de la personne
     * @return
     */
    public String getNom() {
		return this.nom;
	}

    /**
     *Modifie le nom de la personne
     * @param nom nom
     */
    public void setNom(String nom) {
		this.nom = nom;
	}

    /**
     * Retourne le prénom de la personne
     * @return
     */
    public String getPrenom() {
		return this.prenom;
	}

    /**
     * Modifie le prénom de la personne
     * @param prenom prénom
     */
    public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

    /**
     * Retourne les opinions de la personne
     * @return
     */
    public Double[] getOpinions() {
		return this.opinions;
	}

    /**
     * Modifie les opinions de la personne
     * @param opinions opinions
     */
    public void setOpinions(Double[] opinions) {
		this.opinions = opinions;
	}
	
	
}
