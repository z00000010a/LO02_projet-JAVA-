package modelCarte;
/**
 * La classe Carte, toutes les cartes descende de cette classe. Elle introduit les notions de nom et d'origines.
 *
 */
public abstract class Carte 
{
	private String nom;
	private String origine;

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() 
	{
		return nom;
	}

}
