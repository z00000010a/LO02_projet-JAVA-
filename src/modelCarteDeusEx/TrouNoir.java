package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */

public class TrouNoir extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see TrouNoir.modelCarteDeuxEx#TrouNoir 
 * 
 * **/
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();

	public TrouNoir() {
		super();
		this.setNomDeusEx("TrouNoir");
		this.setOrigine("Neant");
		this.setDescription("Aucun autre joueur ne gagne de points "
				+ "d'Action durant ce tour.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("trounoir.jpg"));
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
	 * @see TrouNoir.modelCarteDeuxEx#TrouNoir
	 * */
	public void sacrifier(){
			for (int i = 0; i < joueurs.size(); i++) {
				for (int j = 0; j < joueurs.size(); j++) {
					joueurs.get(j).setPeutSacrifierGuideSP(false);
				}
			}
		}
}
