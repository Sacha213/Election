package com.mycompany.election;

public abstract class Personne {
	private static int nombrePersonne=0;
	private int id;
	private String nom;
	private String prenom;
	private Double[] opinions;

	public Personne(String n, String p, Double[] o){
		id = nombrePersonne;
		nombrePersonne++;
		nom = n;
		prenom = p;
		opinions = o;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Double[] getOpinions() {
		return this.opinions;
	}

	public void setOpinions(Double[] opinions) {
		this.opinions = opinions;
	}
	
	
}
