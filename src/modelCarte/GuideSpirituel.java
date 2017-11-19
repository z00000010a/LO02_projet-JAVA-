package modelCarte;
import java.util.*;

import modelJoueur.EspaceJoueur;
import modelJoueur.Joueur;
/**
 * H�rite de CarteAction
 * Classe dont h�ritent tous les GuideSpirituel. Introduit �galement plusieurs attribut essentielle aux Guide Spirituel
 *  qui seront initialiser par les constructeurs des classes 
 * sont les descendants de cette classe.
 * 
 *
 */
public class GuideSpirituel extends CarteAction
{
	/**
	 * Le nom du Guide. Utilis� par la m�thode toString.
	 */
	private String nom;
	/**
	 * La description de la capacit� du sacrifice du guide. Utilis�e par la m�thode toString.
	 */
	private String description;
	/**
	 * Les dogmes du guide. Utilis�e par la m�thode toString. Utile �galement pour savoir quel Croyant est compatibles avec ce guide.
	 */
	private String dogmes [];
	/**
	 * Le nombre de croyants maximum pouvant �tre supporter par cette carte. Utile pour savoir combien de croyant 
	 * guider par GuideSpirituel quand on le pose.
	 */
	private int nbCroyantMax;
	/**
	 * Nombre de croyant actuellement guid�s par le guide. Mis-�-jour par la m�thodes servant � ajouter les guides spirituels 
	 * dans l'espace du joueur.
	 * @see modelJoueur.EspaceJoueur#ajouterGuideSpirituel(Croyant[], GuideSpirituel)
	 */
	private int nbCroyantGuide=0;
	/**
	 * Espace contenant la carte. Pour une future impl�mentation de la capacit� des IA � jou� utilis� des DeusEx et des sacrifices. 
	 * M�thodes mettant � jour non impl�ment�s.
	 */
	private EspaceJoueur espaceQuiContientlaCarte;
	/**
	 * Bool�en v�rifiant si le guide est dans la pile. MaJ par les m�thodes dans pile.
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see modelDeroulementPartie.Pile#piocher()
	 */
	private boolean dansPile;
	/**
	 * Les croyants actuellement guid�s par le guide. Mis-�-jour par la m�thodes servant � ajouter les guides spirituels 
	 * dans l'espace du joueur.
	 * @see modelJoueur.EspaceJoueur#ajouterGuideSpirituel(Croyant[], GuideSpirituel)
	 */
	private Croyant[] croyants;
	public void sacrifier (){}
	public void sacrifierAI(Joueur jai){};
	public String toString()
	{
		System.out.println(this.nom);
		System.out.println("Origine: "+this.origine);
		for (int i=0; i<this.dogmes.length; i++)
		{
			System.out.print("Dogmes n�"+i+" "+this.dogmes[i]+" ");
		}
		return "\n";
	}

	//============================================================================
	public int getNbCroyantGuide() {
		return nbCroyantGuide;
	}
	public void setNbCroyantGuide(int nbCroyantGuide) {
		this.nbCroyantGuide = nbCroyantGuide;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getDogmes() {
		return dogmes;
	}

	public void setDogmes(String[] dogmes) {
		this.dogmes = dogmes;
	}

	public int getNbCroyantMax() {
		return nbCroyantMax;
	}

	public void setNbCroyantMax(int nbCroyantMax) {
		this.nbCroyantMax = nbCroyantMax;
	}
	public EspaceJoueur getEspaceQuiContientlaCarte() {
		return espaceQuiContientlaCarte;
	}
	public void setEspaceQuiContientlaCarte(EspaceJoueur espaceQuiContientlaCarte) {
		this.espaceQuiContientlaCarte = espaceQuiContientlaCarte;
	}
	public boolean isDansPile() {
		return dansPile;
	}
	public void setDansPile(boolean carteDansPile) {
		dansPile = carteDansPile;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Croyant[] getCroyants() {
		return croyants;
	}
	public void setCroyants(Croyant[] croyants) {
		this.croyants = croyants;
	}
}
