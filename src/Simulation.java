public class Simulation {
	private Scrutin scrutin;
	private Electeur[] electeurs;
	private Candidat[] candidats;

	public Simulation(Scrutin s, Electeur[] e, Candidat[] c) {
		scrutin = s;
		electeurs = e;
		candidats = c;
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

	public void sondage() {

	}

	public void evolution(TypeEvolution te) {

	}

	public void election() {

	}

}
