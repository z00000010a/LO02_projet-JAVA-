package modelCarte;

import java.awt.Image;

/**
 * Classe dont h�ritent toutes les divinit�s.
 *
 */
public class Divinite extends Carte{
	/**
	 * D�finie par le constructeur des divinit�s qui h�riteront de cette classe. Attribut permettant de proposer ou non au joueur humain d'utiliser 
	 * sa capacit�.
	 * @see modelDeroulementPartie.Phase#joueDePhase()
	 */
	private boolean capaciteUtilise=false;
	/**
	 * D�finie par le constructeur des divinit�s qui h�riteront de cette classe. Permet au joueur de connaitre un peu plus la divinit�, contribut 
	 * � l'esprit RP.
	 */
	private String descriptionDivinite;
	/**
	 * D�finie par le constructeur des divinit�s qui h�riteront de cette classe. Permet au joueur de 
	 * connaitre la capacit� avant de d�cider de l'utiliser ou non.
	 * @see modelDeroulementPartie.Phase#joueDePhase()
	 */
	private String descriptionCapacite;
	/**
	 * D�finie par le constructeur des divinit�s qui h�riteront de cette classe. Attribut servant pour 
	 * beaucoup de capacit� DeusEx, Croyan et GuideSpirituel. Ces dogmes seront attribu�s au joueur li�s � cette divinit�.
	 */
	private String [] dogmes;
	private Image image;
	
	
	
	/**
	 * Surcharg� par les divinit�s qui h�riteront de cette classe.
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
