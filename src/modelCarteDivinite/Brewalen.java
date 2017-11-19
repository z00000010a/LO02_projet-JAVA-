package modelCarteDivinite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelCarte.Divinite;
/**
*
* This is a class for a divinity. it extend to the class Divinite
* we give him the attributes and write the capacity for player to use it
* but for this divinity his capacity can't work.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Brewalen extends Divinite {
	String[] dogmes={"Nature","Humain","Mystique"};
	public Brewalen() {
		this.setNom("Brewalen");
		this.setOrigine("Jour");
		this.setDescriptionDivinite("Premier enfant du Jour, Brewalen cherche �� repr�senter une certaine image de l'harmonie, "
				+ "et tente de mettre en place un statu quo entre les Divinit�s.");
		this.setDescriptionCapacite("Peut emp�cher l'utilisation d'une carte Apocalypse."
				+ "La carte est d�fauss�e.");
		this.setDogmes(dogmes);
		
		Image image = null;
		try {
			image = ImageIO.read(new File ("brewalen.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	*Haven't finish now 
	* */
	public void capacite() {
		this.setCapaciteUtilise(true);
	}
}
