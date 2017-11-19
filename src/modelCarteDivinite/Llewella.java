package modelCarteDivinite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.Divinite;
import modelDeroulementPartie.Partie;
/**
*
* This is a class for a divinity. it extend to the class Divinite
* we give him the attributes and write the capacity for player to use it
* for this capacite it will be asked if you choose to use with a button when you can use it.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Llewella extends Divinite {
	String[] dogmes={"Nature","Mystique","Chaos"};
	public Llewella() {
		this.setNom("Llewella");
		this.setOrigine("Nuit");
		this.setDescriptionDivinite("Divinit� machiavélique et manipulatrice, "
				+ "Killinstred cherche à influencer et contrôler ses ennemis.");
		this.setDescriptionCapacite("Peut obliger un joueur à poser une carte "
				+ "Apocalypse s'il en possède une.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("llewella.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	*When we call this method in package control. This method will be called
	*it will let the player you choose to use apocalypse.
	* */
	public void capacite() {
		System.out.println("Choissez de index pour un joueur");
		Scanner sc=new Scanner(System.in);
		int jo=sc.nextInt();
		for (int i = 0; i < Partie.getJoueurs().get(jo).getMainDuJoueur().getMainDuJoueur().size(); i++) {
			if (Partie.getJoueurs().get(jo).getMainDuJoueur().getMainDuJoueur().get(i).getNom()=="Apocalypse") {
				Partie.getJoueurs().get(jo).jouerApocalypse();
			}
		}
		this.setCapaciteUtilise(true);
	}
	
}
