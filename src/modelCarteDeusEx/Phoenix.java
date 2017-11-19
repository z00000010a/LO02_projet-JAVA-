package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;

public class Phoenix extends DeusEx {
	private Joueur joueur=Partie.getJoueurHumain();
	
	/**
	*
	* This is a class for a card DeusEx. it extend to the class DeusEx
	* This a different type of DeusEX it can be use when the phase of other player
	* We will call this void method when we use this card to prevent other card action
	* when we call this method we can prevent a CarteAction.
	* we use IO input the picture.
	* */

	
	public Phoenix() {
		super();
		this.setNomDeusEx("Phoenix");
		this.setOrigine("Neant");
		this.setDescription("Permet de b閚閒icier de la capacit� sp閏iale de l'un de vos Croyants"
				+ " ou Guides Spirituels sans sacrifier la carte.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("phoenix.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}

}
