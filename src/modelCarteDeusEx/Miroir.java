package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */

public class Miroir extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see Miroir.modelCarteDeuxEx#Miroir 
 * 
 * **/
	public Miroir() {
		super();
		this.setNomDeusEx("Miroir");
		this.setOrigine("Null");
		this.setDescription("Retourne les effets d'une carte d'Action sur la Divinit� qui l'a pos閑.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("miroir.jpg"));
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
	 * @see Miroir.modelCarteDeuxEx#Miroir
	 * */
	public void sacrifier(){
		System.out.println("Choississez une divinit�(Index) pour sacrifier un de ses guides spirituel.");
		Scanner s4= new Scanner(System.in);
		int in4=s4.nextInt();
		
		
	}

}
