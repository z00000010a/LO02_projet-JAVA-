package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelApocalypse.Apocalypse;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
/**
*
* This is a class for a card GuideSpirituel. it extend to the class GuideSpirituel 
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Martyr extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	private String description ="Sacrifice : Equivalent 锟� la pose d'une carte Apocalypse.";
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.In this method target is yourselves.
	 * @param numJ 
	 * this is target player you chose.
	 * */
	public void sacrifier ()
	{
		Apocalypse.cEstLapocalypse();
	}
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Martyr (String dogme1, String dogme2, String origine)
	{
		this.setNbCroyantMax(2);
		String [] tab = {dogme1,dogme2};
		this.setDogmes(tab);
		this.setNom("Martyr");
		this.setOrigine(origine);
		if (origine =="Jour")
		{
			Image image = null;
			try {
				image = ImageIO.read(new File ("martyr1.jpg"));
			}catch (IOException ie) {
				ie.printStackTrace();
				System.out.println("erreur sur le chargement de l'image");
			}
			this.setImage(image);
		}
		else if (origine =="Nuit")
		{
			Image image = null;
			try {
				image = ImageIO.read(new File ("martyr2.jpg"));
			}catch (IOException ie) {
				ie.printStackTrace();
				System.out.println("erreur sur le chargement de l'image");
			}
			this.setImage(image);
		}
		else if (origine=="Neant")
		{
			Image image = null;
			try {
				image = ImageIO.read(new File ("martyr3.jpg"));
			}catch (IOException ie) {
				ie.printStackTrace();
				System.out.println("erreur sur le chargement de l'image");
			}
			this.setImage(image);
		}
	}	
	public Joueur getJoueurQuiJoueLaCarte() {
		return Partie.getJoueurHumain();
	}
	public void setJoueurQuiJoueLaCarte(Joueur joueurQuiJoueLaCarte) {
		JoueurQuiJoueLaCarte = joueurQuiJoueLaCarte;
	}
}
