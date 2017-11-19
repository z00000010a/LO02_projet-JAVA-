package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* This a different type of DeusEX it can be use when the phase of other player
* We will call this void method when we use this card to prevent other card action
* when we call this method we can prevent a CarteAction.
* we use IO input the picture.
* */

public class InfluenceJour extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see ColereDivine2.modelCarteDeuxEx#ColereDivine2 
 * 
 * **/
	
	public InfluenceJour() {
		super();
		this.setNomDeusEx("InfluenceJour");
		this.setOrigine("Null");
		this.setDescription("Sacrifiez 2 cartes Croyants d'une autre Divinit�. "
				+ "Les capacit閟 sp閏iales ne sont pas jou閑s.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("influencejour.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	


}
