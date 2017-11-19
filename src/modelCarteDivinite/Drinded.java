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
public class Drinded extends Divinite {
	String[] dogmes={"Nature","Humain","Symboles"};
	public Drinded() {
		this.setNom("Drinded");
		this.setOrigine("Jour");
		this.setDescriptionDivinite("D�fenseur des hommes, quelles que soient leurs"
				+ "croyances, Drinded prot��ge les chefs religieux");
		this.setDescriptionCapacite("Peut emp��cher le sacrifice d'un des Guides "
				+ "Spirituels de n'importe quel joueur.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("drinded.jpg"));
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
