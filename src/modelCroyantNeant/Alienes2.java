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
public class Alienes2 extends Croyant{
	private String[] dogemes={"Humain","Nature","Symboles"};
	private String[] dogemesDivinite={"Chaos","Mystique"};
	private	LinkedList<Joueur> joueurs=Partie.getJoueurs();

	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Alienes2() {
		super();
		this.peutJouerParAI=true;
		this.setNomCroyant("Alienes");
		this.setOrigine("Neant");
		this.setDogmes(dogemes);
		this.setDescription("Empêche une Divinit� poss閐ant le Dogme Chaos ou Mystique "
				+ "de sacrifier une de ses cartes de Guides Spirituels durant ce tour.");
		this.setNbPriere(2);
		Image image = null;
		try {
			image = ImageIO.read(new File ("alienes2.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.In this method target is yourselves.
	 * @param numJ this is target player you chose.
	 * */
	public void sacrifier(){	
		out:for (int j = 0; j < joueurs.size(); j++) {
		for (int j2 = 0; j2 < joueurs.get(j).getDogme().length; j2++) {
			if (dogemesDivinite[0]==joueurs.get(j).getDogme()[j]|| 
				dogemesDivinite[1]==joueurs.get(j).getDogme()[j]){
				System.out.println("cette Divinite?  Oui:1 Non:2");
				Scanner s3=new Scanner(System.in);
				int a=s3.nextInt();
				if (a==1) {
					joueurs.get(j).setPeutSacrifierGuideSP(false);
					break out;
				}
			}
		}
	}
		
	
	}

}
