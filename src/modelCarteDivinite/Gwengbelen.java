package modelCarteDivinite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelCarte.Divinite;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
/**
*
* This is a class for a divinity. it extend to the class Divinite
* we give him the attributes and write the capacity for player to use it
* for this capacite it will be asked if you choose to use with a button when you can use it.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Gwengbelen extends Divinite {
	String[] dogmes={"Humain","Mystique","Symboles"};
	public Gwengbelen() {
		this.setNom("Gwengbelen");
		this.setOrigine("Aube");
		this.setDescriptionDivinite("Premi�re Divinit� � recevoir l'influence du Néant,"
				+ "Gwenghelen est celle qui en a reçu le plus de puissance.");
		this.setDescriptionCapacite("Récupère autant de points d'Action supplémentaires d'Origine "
				+ "Néant que le nombre de Guides Spirituels que la Divinité possède.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("gwengbelen.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	*When we call this method in package control. This method will be called
	*it will get the pointAction of this player.
	*at last of this method it will update the graphic of player.
	* */
	public void CapaciteDivine() {
		
		if (true) {
			if (Partie.getJoueurHumain().getEspaceDuJoueur().getListeDesGuides().size()!=0) {
			GuideSpirituel gs=Partie.getJoueurHumain().getEspaceDuJoueur().getListeDesGuides().get(0);
			Partie.getJoueurHumain().setPointActionNeant(Partie.getJoueurHumain().getPointActionNeant()+gs.getNbCroyantMax());
			Partie.getJoueurHumain().mettreAJourGraphique();
			}
			
		}
	}
}
