package com.mycompany.election;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Simulation {
	private Scrutin scrutin;
	private Electeur[] electeurs;
	private Candidat[] candidats;

	public Simulation() {
		super();
	}

	public Scrutin getScrutin() {
		return this.scrutin;
	}

	public void setScrutin(Scrutin scrutin) {
		this.scrutin = scrutin;
	}

	public Electeur[] getElecteurs() {
		return this.electeurs;
	}

	public void setElecteurs(Electeur[] electeurs) {
		this.electeurs = electeurs;
	}

	public Candidat[] getCandidats() {
		return this.candidats;
	}

	public void setCandidats(Candidat[] candidats) {
		this.candidats = candidats;
	}

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

	public void evolution(TypeEvolution te) {
            te.evoluer(this);
	}

	public List<Map.Entry<Candidat, Integer>> election() {
            return scrutin.election(electeurs, candidats);
	}
        


}
