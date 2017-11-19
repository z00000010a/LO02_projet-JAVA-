package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */

public class Fourberie extends DeusEx {
	private LinkedList<Joueur> joueurs=Partie.getJoueurs();
	
/**
 * 
 * This is a construction method
 *@see Fourberie.modelCarteDeuxEx#Fourberie 
 * 
 * **/
	
	
	public Fourberie() {
		super();
		this.setNomDeusEx("Fourberie");
		this.setOrigine("Nuit");
		this.setDescription("Sacrifiez 2 cartes Croyants d'une autre Divinit�. "
				+ "Les capacit閟 sp閏iales ne sont pas jou閑s.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("fourberie.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * the capacity:Sacrifiez 2 cartes Croyants d'une autre Divinité.
	 *  Les capacités spéciales ne sont pas jouées.
	 * @param numJ this is target player you chose.
	 * @see Fourberie.modelCarteDeuxEx#Fourberie
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
		Iterator<GuideSpirituel> it = j.getEspaceDuJoueur().getListeDesGuides().iterator();
		out:while(it.hasNext()){
			GuideSpirituel gs=it.next();
				j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				 j.mettreAJourGraphique();
				 Partie.getJoueurHumain().mettreAJourGraphique();
				break out;
	}}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * @param numJ 
	 * this is target player you chose.
	 * @see Fourberie.modelCarteDeuxEx#Fourberie
	 * */
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		js.remove(this.getJoueur());
		for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			out:while(it.hasNext()){
				GuideSpirituel gs=it.next();
					listeJ.add(js.get(i));
					listeJ.remove(Partie.getJoueurHumain());
					break out;
					
				}
			}
	
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}

}
