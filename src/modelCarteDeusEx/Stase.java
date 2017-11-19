package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */

public class Stase extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see Stase.modelCarteDeuxEx#Stase 
 * 
 * **/
	public Stase() {
		super();
		this.setNomDeusEx("Stase");
		this.setOrigine("Jour");
		this.setDescription("Permet de b閚閒icier de la capacit� sp閏iale de l'un de vos Croyants"
				+ " ou Guides Spirituels sans sacrifier la carte.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("stase.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * the capacity:Détruit une carte Guide Spirituel d'Origine Jour ou Néant,
	 *  dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent 
	 *  au centre de la table.
	 * @param numJ this is target player you chose.
	 * @see Stase.modelCarteDeuxEx#Stase
	 * */
	public void sacrifier(int numJ){
		
		Joueur j = null; //j is the player choosen
		for (int i =0; i<Partie.getJoueurs().size(); i++)
		{
			if (Partie.getJoueurs().get(i).getNumJoueur()==numJ)
			{
				j=Partie.getJoueurs().get(i);
			}
		}

			Partie.getJoueurHumain().mettreAJourGraphique();
	}


	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * @param numJ 
	 * this is target player you chose.
	 * @see Stase.modelCarteDeuxEx#Stase
	 * */
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		listeJ.add(Partie.getJoueurHumain());	
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}

}
