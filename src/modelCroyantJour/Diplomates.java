package modelCroyantJour;
import modelCarte.Croyant;
import modelCarteDeusEx.ColereDivine2;
import modelDeroulementPartie.*;
import modelJoueur.*;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
public class Diplomates extends Croyant{
	private String[] dogemes={"Symboles","Humain","Chaos"};
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Diplomates() {
		super();
		this.setNomCroyant("Diplomates");
		this.setOrigine("Jour");
		this.setDogmes(dogemes);
		this.setDescription("Relancez le d� de Cosmogonie. "
				+ "Le tour se finit normalement sous la nouvelle influence.");
		this.setNbPriere(4);
		Image image = null;
		try {
			image = ImageIO.read(new File ("diplomates.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.
	 * @param numJ 
	 * this is target player you chose.
	 * */
	public void sacrifier(int numJ){
		
		Joueur j = null; //j is the player choosen
		for (int i =0; i<Partie.getJoueurs().size(); i++)
		{
			if (Partie.getJoueurs().get(i).getNumJoueur()==numJ)
			{
				j=Partie.getJoueurs().get(i);
			}
		}
	
			for (int i = 0; i < Partie.getJoueurs().size(); i++) {
				Partie.getJoueurs().get(i).setaJoue(true);
			}
		
	}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		listeJ.add(Partie.getJoueurHumain());	
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}


}
