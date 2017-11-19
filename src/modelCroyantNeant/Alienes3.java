package modelCroyantNeant;
import modelCarte.Croyant;
import modelDeroulementPartie.Partie;
import modelJoueur.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
/**
*
* This is a class for a card Croyant. it extend to the class Croyant
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Alienes3 extends Croyant{
	private String[] dogemes={"Mystique","Humain","Chaos"};
	private Joueur joueur=Partie.getJoueurHumain();
	private LinkedList<Joueur> joueurs = Partie.getJoueurs();
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Alienes3() {
		super();
		this.peutJouerParAI=true;
		this.setNomCroyant("Aliene");
		this.setOrigine("Neant");
		this.setDogmes(dogemes);
		this.setDescription("Vous piochez deux cartes au hasard dans la main d'une "
				+ "autre Divinit�.");
		this.setNbPriere(2);
		Image image = null;
		try {
			image = ImageIO.read(new File ("alienes3.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.In this method target is yourselves.
	 * @param numJ 
	 * this is target player you chose.
	 * */
	public void sacrifier(){
		System.out.println("Choississez une divinit�(Index) pour sacrifier un de ses guides spirituel.");
		Scanner s4= new Scanner(System.in);
		int in4=s4.nextInt();
		joueur.getMainDuJoueur().getMainDuJoueur().add(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(0));
		joueur.getMainDuJoueur().getMainDuJoueur().add(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(1));
		joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().remove(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(0));
		joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().remove(joueurs.get(in4).getMainDuJoueur().getMainDuJoueur().get(1));

	}

}
