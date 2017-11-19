package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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

public class Bouleversement extends DeusEx {
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();
	
/**
 * 
 * This is a construction method
 *@see Bouleversement.modelCarteDeuxEx#BBouleversementouleversement 
 * 
 * **/
	
	public Bouleversement() {
		super();
		this.setNomDeusEx("Bouleversement");
		this.setOrigine("Null");
		this.setDescription("Relancez le d� de Cosmogonie. Le tour de jeu se terminera"
				+ " normalement, mais sous la nouvelle influence.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("bouleversement.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}

	public void sacrifier(){
		for (int i = 0; i < joueurs.size(); i++) {
			joueurs.get(i).setaJoue(true);
		}
	}

}
