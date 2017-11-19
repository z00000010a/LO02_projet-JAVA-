package modelCarte;

import java.awt.Image;

/**
 * Classe dont héritent toutes les divinités.
 *
 */
public class Divinite extends Carte{
	/**
	 * Définie par le constructeur des divinités qui hériteront de cette classe. Attribut permettant de proposer ou non au joueur humain d'utiliser 
	 * sa capacité.
	 * @see modelDeroulementPartie.Phase#joueDePhase()
	 */
	private boolean capaciteUtilise=false;
	/**
	 * Définie par le constructeur des divinités qui hériteront de cette classe. Permet au joueur de connaitre un peu plus la divinité, contribut 
	 * à l'esprit RP.
	 */
	private String descriptionDivinite;
	/**
	 * Définie par le constructeur des divinités qui hériteront de cette classe. Permet au joueur de 
	 * connaitre la capacité avant de décider de l'utiliser ou non.
	 * @see modelDeroulementPartie.Phase#joueDePhase()
	 */
	private String descriptionCapacite;
	/**
	 * Définie par le constructeur des divinités qui hériteront de cette classe. Attribut servant pour 
	 * beaucoup de capacité DeusEx, Croyan et GuideSpirituel. Ces dogmes seront attribués au joueur liés à cette divinité.
	 */
	private String [] dogmes;
	private Image image;
	
	
	
	/**
	 * Surchargé par les divinités qui hériteront de cette classe.
	 */
	public void CapaciteDivine ()
	{
		
	}

	public String [] getDogmes() {
		return dogmes;
	}

	public void setDogmes(String [] dogmes) {
		this.dogmes = dogmes;
	}

	public boolean isCapaciteUtilise() {
		return capaciteUtilise;
	}

	public void setCapaciteUtilise(boolean capaciteUtilise) {
		this.capaciteUtilise = capaciteUtilise;
	}

	public String getDescriptionDivinite() {
		return descriptionDivinite;
	}

	public void setDescriptionDivinite(String descriptionDivinite) {
		this.descriptionDivinite = descriptionDivinite;
	}

	public String getDescriptionCapacite() {
		return descriptionCapacite;
	}

	public void setDescriptionCapacite(String descriptionCapacite) {
		this.descriptionCapacite = descriptionCapacite;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
