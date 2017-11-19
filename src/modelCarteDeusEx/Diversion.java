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

public class Diversion extends DeusEx {
	private Joueur joueur=Partie.getJoueurHumain();
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();
	
/**
 * 
 * This is a construction method
 *@see Diversion.modelCarteDeuxEx#Diversion 
 * 
 * **/
		
	public Diversion() {
		super();
		this.setNomDeusEx("Diversion");
		this.setOrigine("Nuit");
		this.setDescription("Prenez 3 cartes dans la main d'un autre joueur"
				+ " et incluez-les à votre main.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("diversion.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * the capacity:Prenez 3 cartes dans la main d'un autre joueur et incluez-les à votre main.
	 * @param numJ  this is target player you chose.
	 * @see Diversion.modelCarteDeuxEx#Diversion
	 * */
	public void sacrifier()
	{
		System.out.println("Choississez une divinit�(Index) pour sacrifier un de ses guides spirituel.");
		Scanner s4= new Scanner(System.in);
		int in4=s4.nextInt();
		joueur.getMainDuJoueur().getMainDuJoueur().add(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(0));
		joueur.getMainDuJoueur().getMainDuJoueur().add(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(1));
		joueur.getMainDuJoueur().getMainDuJoueur().add(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(2));
		joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().remove(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(0));
		joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().remove(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(1));
		joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().remove(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(3));


		
		}

}
