package modelCroyantNuit;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
*
* This is a class for a card Croyant. it extend to the class Croyant
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Demons2 extends Croyant {
	private String[] dogemes={"Mystique","Humain","Chaos"};
	Joueur j=Partie.getJoueurHumain();

	
	public Demons2() {
		super();
		this.peutJouerParAI=true;
		this.setNomCroyant("Demon");
		this.setOrigine("Nuit");
		this.setDogmes(dogemes);
		this.setDescription("Donne un point d'Action d'Origine Nuit.");
		this.setNbPriere(2);
		Image image = null;
		try {
			image = ImageIO.read(new File ("demons2.jpg"));
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
		Partie.getJoueurHumain().setPointActionNuit(Partie.getJoueurHumain().getPointActionNuit()+1);
		Partie.getJoueurHumain().mettreAJourGraphique();

		
	}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		listeJ.add(Partie.getJoueurHumain());
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}
	public void sacrifierAI(Joueur jai){
		jai.setPointActionNuit(jai.getPointActionNuit()+1);
	}
		
}

