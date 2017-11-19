package modelCroyantJour;
import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Integristes extends Croyant {
	private String[] dogemes={"Humain","Mystique","Chaos"};
	Joueur j=Partie.getJoueurHumain();
	LinkedList<Joueur> joueurs=Partie.getJoueurs();
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Integristes() {
		super();
		this.setNomCroyant("Integristes");
		this.setOrigine("Jour");
		this.setDogmes(dogemes);
		this.setDescription("Impose le sacrifice d'un Guide Spirituel "
				+ "d'un autre joueur,"
				+ " qui choisit la carte. "
				+ "La capacit� sp閏iale du sacrifice est jou閑.");
		this.setNbPriere(1);
		Image image = null;
		try {
			image = ImageIO.read(new File ("integristes.jpg"));
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
		JoueurVirtual j1=(JoueurVirtual) j;
		Iterator<Croyant> it = j.getEspaceDuJoueur().getListeDesCroyants().iterator();
		out:while(it.hasNext()){
			Croyant cro=it.next();
				if (cro.isPeutJouerParAI()) {
					try {
						j1.sacrifierCroyantAI();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Partie.getJoueurHumain().mettreAJourGraphique();
					break out;
				}
				
	}}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		for (int i = 0; i <js.size(); i++) {
			Iterator<Croyant> it = js.get(i).getEspaceDuJoueur().getListeDesCroyants().iterator();
			out:while(it.hasNext()){
				Croyant cor=it.next();
				if (cor.isPeutJouerParAI()) {
					listeJ.add(js.get(i));
					listeJ.remove(Partie.getJoueurHumain());
					break out;
				}	
				}
			}
	
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}		
		
}

