package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurVirtual;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
*
* This is a class for a card GuideSpirituel. it extend to the class GuideSpirituel 
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Clerc extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	private String description ="Fait gagner un nombre depoints d'Action 間al au nombre de cartes de Croyants rattach閑s. L'Origine des points d'Action est au choix du joueur.";

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
		this.getJoueurQuiJoueLaCarte().setPointActionJour(this.getJoueurQuiJoueLaCarte().getPointActionJour()+2);
		this.getJoueurQuiJoueLaCarte().mettreAJourGraphique();

	}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		listeJ.add(Partie.getJoueurHumain());
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}
	public void sacrifierAI(Joueur jai){
		System.out.println("AI joueur GuideSp");
		this.getJoueurQuiJoueLaCarte().setPointActionJour(this.getJoueurQuiJoueLaCarte().getPointActionJour()+2);
		this.getJoueurQuiJoueLaCarte().mettreAJourGraphique();
	}

	
	/**
	 * 
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * @param dogme1
	 * @param dogme2
	 * @param origine
	 * @param numCarte Le num閞o du clerc sur le PDF du jeu (si c'est la 1ere carte de type clerc mettre 1,
	 */
	public Clerc (String dogme1, String dogme2, String origine, int numCarte)
	{
		this.setNbCroyantMax(2);
		this.setNom("clerc");
		String [] tab= {dogme1,dogme2};
		this.setDogmes(tab);
		this.setOrigine(origine);
		Image image = null;
		try {
			image = ImageIO.read(new File ("clerc"+numCarte+".jpg")); //VERIFIER QUE CA MARCHE SINON FAIRE SWITCH
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);

	}
	
	//=====================================================================================
	public Joueur getJoueurQuiJoueLaCarte() {
		return Partie.getJoueurHumain();
	}
	public void setJoueurQuiJoueLaCarte(Joueur joueurQuiJoueLaCarte) {
		JoueurQuiJoueLaCarte = joueurQuiJoueLaCarte;
	}
}
