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
                int index =(int)(Math.random() * (electeurs.length + 1));
                if (indexSondes.contains(index)) i--;
                else {
                    electeursSondage[i] = electeurs[index];
                    indexSondes.add(i);
                    System.out.println(electeursSondage[i].getNom());
                }       
            }
            return scrutin.election(electeursSondage, candidats);
	}

	public void evolution(TypeEvolution te) {
            
	}

	public List<Map.Entry<Candidat, Integer>> election() {
            return scrutin.election(electeurs, candidats);
	}
        
        public void afficherResultat(List<Map.Entry<Candidat, Integer>> resultatSondage, int nbElecteurs){
            System.out.print("Le candidat en première position du sondage est " + resultatSondage.get(0).getKey().getPrenom() + " " + resultatSondage.get(0).getKey().getNom());
            System.out.println(" avec " + resultatSondage.get(0).getValue()*100/nbElecteurs + " % des votes");
            for (int i = 1; i < candidats.length; i++){
                System.out.println("Le candidat en " + (i+1) + " ème position est " + resultatSondage.get(i).getKey().getPrenom() + " " + resultatSondage.get(i).getKey().getNom());
                System.out.println(" avec " + resultatSondage.get(i).getValue()*100/nbElecteurs + " % des votes");
            }
        }

}
