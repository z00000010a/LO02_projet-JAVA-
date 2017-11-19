package modelCarte;

import modelJoueur.EspaceJoueur;
import modelJoueur.Joueur;
/**
 * Classe servant définir les attributs des croyant. Tous les croyants héritent de cette classe.
 * 
 *
 */
public class Croyant extends CarteAction
{	/**
	* Cet attribut sera défini dans le constructeur des croyant. Il permet au Joueur humain de savoir quelle est cette carte.
	*/
	private String nomCroyant;
	/**
	* Cet attribut sera défini dans le constructeur des croyant. Il permet de savoir si il est guidé (utile pour certaine capacité).
	*  MaJ par la méthode qui ajoute les cartes dans l'espace du joueur.
	* @see modelJoueur.EspaceJoueur#ajouterGuideSpirituel(Croyant[], GuideSpirituel)
	*/
	private boolean EstGuide;
	/**
	* Cet attribut sera défini dans le constructeur des croyant. Le nombre de prière apporter par le croyant.
	*/
	private int nombreDePriere;
	/**
	* Cet attribut sera défini dans le constructeur des croyant. Les dogmes que suit le croyant. Utile pour poser des guides spirituel.
	*/
	private String dogmes [];
	/**
	* Cet attribut sera défini dans le constructeur des croyant. Ceci sert au joueur
	*  humain, pour qu'il connaissent l'effet du sacrifice du croyant.
	*/
	private String description;
	/**
	* Cet attribut sera défini dans le constructeur des croyant.
	*/
	private int nbPriere;
	/**
	* Cet attribut sera défini dans le constructeur des croyant. Il est automatiquement mis-a-jour par la méthode qui ajoute 
	* les cartes dans l'espace du joueur
	* @see modelJoueur.EspaceJoueur#ajouterGuideSpirituel(Croyant[], GuideSpirituel)
	*/
	private GuideSpirituel guidePar = new GuideSpirituel();
	
	public void sacrifier () {}
	public void sacrifierAI () {}
	/**
	 * Afficher l'origines, les dogmes et le nom du croyant.
	 */
	public String toString()
	{
		System.out.println(this.nomCroyant);
		System.out.println("Origine: "+this.origine);
		for (int i=0; i<this.dogmes.length; i++)
		{
			System.out.print("Dogmes n°"+i+" "+this.dogmes[i]+" ");
		}
		return "\n";
	}
	public void sacrifierAI(Joueur joueur){};
	
	

//=====================================================================================================
	
	
	public String getNomCroyant() {
		return nomCroyant;
	}

	public void setNomCroyant(String nomCroyant) {
		this.nomCroyant = nomCroyant;
	}

	public boolean isEstGuide() {
		return EstGuide;
	}

	public void setEstGuide(boolean estGuide) {
		EstGuide = estGuide;
	}

	public int getNombreDePriere() {
		return nombreDePriere;
	}

	public void setNombreDePrites(int nombreDePriere) {
		this.nombreDePriere = nombreDePriere;
	}

	public String[] getDogmes() {
		return dogmes;
	}

	public void setDogmes(String[] dogmes) {
		this.dogmes = dogmes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNbPriere() {
		return nbPriere;
	}

	public void setNbPriere(int nbPriere) {
		this.nbPriere = nbPriere;
	}

	public GuideSpirituel getGuidePar() {
		return guidePar;
	}

	public void setGuidePar(GuideSpirituel guidePar) {
		this.guidePar = guidePar;
	}

	
	
//=====================================================================================================
	
	
}
