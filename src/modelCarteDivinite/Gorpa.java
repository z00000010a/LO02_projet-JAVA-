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
public class Gorpa extends Divinite {
	String[] dogmes={"Chaos","Mystique","Symboles"};
	public Gorpa() {
		this.setNom("Gorpa");
		this.setOrigine("Jour");
		this.setDescriptionDivinite("Dernier pur du jour, Yarstur combat le N�ant"
				+ "sous toutes ses formes.");
		this.setDescriptionCapacite("Peut d�truire toutes les cartes de Croyants au"
				+ "centre de la table d'Origine N�ant.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("gorpa.jpg"));
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
