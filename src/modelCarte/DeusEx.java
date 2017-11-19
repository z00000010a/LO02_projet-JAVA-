package modelCarte;

import modelJoueur.Joueur;

/**
 * Classe dont vont h�riter toutes les classes DeusEx.  Elle ne sera pas instanci�e en temps que telle.
 *
 */
public class DeusEx extends CarteAction{
	/**
	 * D�finie par le constructeur des cartes qui h�riteront de cette classe. Pour que le joueur puisse savoir quelle est cette carte.
	 */
	private String nomDeusEx;
	/**
	 * D�finie par le constructeur des cartes qui h�riteront de cette classe. 
	 */
	private String origine;
	/**
	 * D�finie par le constructeur des cartes qui h�riteront de cette classe. Pour que le joueur puissent connaitre la capacit� de la carte.
	 */
	private String Description;
	

	
	

	public String toString()
	{
		System.out.println(this.nomDeusEx);
		System.out.println("Origine: "+this.origine);
		System.out.println("Description: "+this.Description);
		return "\n";
	}
	
	
	
	public void sacrifier () {}
	public void sacrifierAI(){}
	
	
	
	
	
	
//====================================================================================================
	
	
	public String getNomDeusEx() {
		return nomDeusEx;
	}
	public void setNomDeusEx(String nomDeusEx) {
		this.nomDeusEx = nomDeusEx;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}






	

}
