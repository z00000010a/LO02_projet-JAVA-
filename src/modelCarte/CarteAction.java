package modelCarte;

import java.awt.Image;

import modelDeroulementPartie.Pile;
import modelJoueur.Joueur;

/**
 * La Classe CarteAction qui hérite de Carte, elle introduit les notion de dogmes et surtout la méthode sacrifier()
 *
 */
public abstract class CarteAction extends Carte
{
	/**
	 * Le joueur qui possèdent la carte. MaJ par la classe CarteMain quand elle pioche.
	 */
	private Joueur joueur;
	private Image image;   //METTRE DANS LE CONSTRUCTEUR 
	protected String origine; 
	protected int idCarte;  
	protected boolean peutJouerParAI=false;
	protected boolean jouable;
	/**
	 * Attribut automatiquement gérer dès que la carte est mis dans la pile avec les méthodes adéquates
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see modelDeroulementPartie.Pile#piocher()
	 */
	private boolean dansPile;
	public boolean isDansPile() {
		return dansPile;
	}
	public void setDansPile(boolean dansPile) {
		this.dansPile = dansPile;
	}
	/**
	 * Méthode très important qui sera surchargé par toutes les cartes.
	 * Ainsi, si on veut appeller la capacité d'une carte on appellera sa méthode sacrifier().
	 */
	public void sacrifier(int ChoixJoueur){}
	public void sacrifierGUI(){};
	public String getOrigine() 
	{
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public void setIdCarte(int idCarte) {
		this.idCarte = idCarte;
	}
	public int getIdCarte() 
	{
		return idCarte;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isPeutJouerParAI() {
		return peutJouerParAI;
	}
	public boolean isJouable() {
		return jouable;
	}
	public void setJouable(boolean jouable) {
		this.jouable = jouable;
	}

}
