
package modelCroyantJour;
import modelCarte.Croyant;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

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
public class Travailleurs1 extends Croyant{
	private String[] dogemes={"Symboles","Humain","Chaos"};
	private String[] dogemesDivinite={"Nature","Mistique"};
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();
	
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Travailleurs1() {
		super();
		this.peutJouerParAI=true;
		this.setNomCroyant("Travailleurs");
		this.setOrigine("Jour");
		this.setDogmes(dogemes);
		this.setDescription("Empêche une Divinit� poss閐ant le Dogme Nature ou Mystique "
				+ "de sacrifier une de ses cartes de Croyants durant ce tour.");
		this.setNbPriere(2);
		Image image = null;
		try {
			image = ImageIO.read(new File ("travailleurs1.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.In this method target is yourselves.
	 * @param numJ 
	 * this is target player you chose.
	 * */
	public void sacrifier(int numJ){
		Joueur j = null; //j is the player choosen
		System.out.println(numJ);
		for (int i =0; i<Partie.getJoueurs().size(); i++)
		{
			if (Partie.getJoueurs().get(i).getNumJoueur()==numJ)
			{
				j=Partie.getJoueurs().get(i);
			}
		}
		Partie.getJoueurHumain().mettreAJourGraphique();
		
	}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		System.out.println(Partie.getJoueurHumain().getDogme());
		for (int i = 0; i <js.size(); i++) {
			Joueur j=Partie.getJoueurs().get(i);
			out:for (int k = 0; k < 3; k++) {
				if (j.getDogme()[k]=="Nature"||j.getDogme()[k]=="Mystique") {
					listeJ.add(Partie.getJoueurs().get(i));
					listeJ.remove(Partie.getJoueurHumain());
					break out;
				}
			}
		}
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}
	public void sacrifierAI(Joueur jai){
		jai.setPointActionJour(jai.getPointActionJour()+1);
	}

	
//=============================================================================
	public String[] getDogemesDivinite() {
		return dogemesDivinite;
	}

	public void setDogemesDivinite(String[] dogemesDivinite) {
		this.dogemesDivinite = dogemesDivinite;
	}

}
