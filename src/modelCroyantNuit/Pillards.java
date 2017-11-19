package modelCroyantNuit;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
/**
*
* This is a class for a card Croyant. it extend to the class Croyant
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Pillards extends Croyant {
	private String[] dogemes={"Mistique","Nature","Symboles"};
	private Joueur joueur=Partie.getJoueurHumain();
	private LinkedList<modelJoueur.Joueur> joueurs=Partie.getJoueurs();
	
	
	public Pillards() {
		super();
		this.setNomCroyant("Pillards");
		this.setOrigine("Nuit");
		this.setDogmes(dogemes);
		this.setDescription("R閏up閞ez les points d'Action d'une Divinit� n'ayant pas encore "
				+ "jou� durant ce tour. Les points d'Action gardent leur Origine. La Divinit� perd ses points.");
		this.setNbPriere(4);
		Image image = null;
		try {
			image = ImageIO.read(new File ("pillards.jpg"));
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
		System.out.println("Entrer l'index du joueur que vous souhaiter.\n");
		Scanner sa=new Scanner(System.in);
		int a=sa.nextInt();
		joueur.setPointActionJour(joueurs.get(a).getPointActionJour());
		joueur.setPointActionNuit(joueurs.get(a).getPointActionNuit());
		joueur.setPointActionNeant(joueurs.get(a).getPointActionNeant());
		joueurs.get(a).setPointActionJour(0);
		joueurs.get(a).setPointActionNuit(0);
		joueurs.get(a).setPointActionNeant(0);
		
	}
		
}

