package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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
public class Messie extends GuideSpirituel 
{	private static Joueur JoueurQuiJoueLaCarte; 
	String description = "Le joueur pose le d锟� de Cosmogonie sur la face qu'il d闁焛re et commence un nouveau tour de jeu.";
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * then call the capacity of this card.In this method target is yourselves.
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
	public void sacrifierAI(Joueur jai){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		js.remove(Partie.getJoueurHumain());
		out:for (int i = 0; i < js.size(); i++) {
			JoueurVirtual j=(JoueurVirtual)js.get(i);
			if (j.getEspaceDuJoueur().getListeDesCroyants().size()!=0) {
				try {
					j.sacrifierCroyantAI();
					break out;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Messie ()
	{
		this.setNom("Messie");
		this.setNbCroyantMax(3);
		this.setOrigine("Jour");
		String [] tab = {"Mystique","Humain"};
		this.setDogmes(tab);
		Image image = null;
		try {
			image = ImageIO.read(new File ("messie.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	public Joueur getJoueurQuiJoueLaCarte() {
		return Partie.getJoueurHumain();
	}
	public void setJoueurQuiJoueLaCarte(Joueur joueurQuiJoueLaCarte) {
		JoueurQuiJoueLaCarte = joueurQuiJoueLaCarte;
	}
}
