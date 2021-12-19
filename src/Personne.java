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
}
