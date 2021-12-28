package com.mycompany.election;

public abstract class Personne {
	private static int nombrePersonne=0;
	private int id;
	private String nom;
	private String prenom;
	private Float[] opinions;

	public Personne(String n, String p, Float[] o){
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

	public Float[] getOpinions() {
		return this.opinions;
	}

	public void setOpinions(Float[] opinions) {
		this.opinions = opinions;
	}
	
	
}
