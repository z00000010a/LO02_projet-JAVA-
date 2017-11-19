package modelCroyantNeant;
import modelCarte.Croyant;
import modelDeroulementPartie.Partie;
import modelJoueur.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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
public class Nihillistes extends Croyant{
	private String[] dogemes={"Symboles","Mistique","Chaos"};
	private	LinkedList<Joueur> joueurs=Partie.getJoueurs();
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	
	public Nihillistes() {
		super();
		this.setNomCroyant("Nihillistes");
		this.setOrigine("Neant");
		this.setDogmes(dogemes);
		this.setDescription("Jusqu'脿 la fin du tour, plus aucune Divinit茅 ne re莽oit"
				+ " de points d'Action.");
		this.setNbPriere(4);
		Image image = null;
		try {
			image = ImageIO.read(new File ("nihillistes.jpg"));
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
		for (int i = 0; i < joueurs.size(); i++) {
			for (int j = 0; j < joueurs.size(); j++) {
				joueurs.get(j).setPeutSacrifierGuideSP(false);
			}
		}
	}

}
