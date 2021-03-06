package modelCarteDivinite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelCarte.Divinite;
import modelDeroulementPartie.Partie;
import modelJoueur.CarteCentreTable;

public class Yarstur extends Divinite {
	/**
	*
	* This is a class for a divinity. it extend to the class Divinite
	* we give him the attributes and write the capacity for player to use it
	* for this capacite it will be asked if you choose to use with a button when you can use it.
	* We use Construction method to create different attributes for a type of the card and we 
	* use IO input the picture.
	* 
	* */
	String[] dogmes={"Chaos","Symboles","Mystique"};
	public Yarstur() {
		this.setNom("Yarstur");
		this.setOrigine("Jour");
		this.setDescriptionDivinite("Dernier pur du jour, Yarstur combat le N�ant "
				+ "sous toutes ses formes.");
		this.setDescriptionCapacite("Peut d�truire toutes les cartes de Croyants au "
				+ "centre de la table d'Origine N�ant.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("yarstur.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	*When we call this method in package control. This method will be called
	*it will delete all the cards orgineNeant in center table.
	* */
	public void CapaciteDivine () {
		
		if (true) {
			CarteCentreTable ct=CarteCentreTable.getCentreTable();
		for (int i = 0; i < ct.getEspaceCentreTable().size(); i++) {
			if (ct.getEspaceCentreTable().get(i).getOrigine()=="Neant") {
				ct.supprimerCroyant(ct.getEspaceCentreTable().get(i));
			}
		}
		this.setCapaciteUtilise(true);
		Partie.getJoueurHumain().mettreAJourGraphique();
		}
	}

}
