package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Test.Test;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
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

public class ColereDivine1 extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see ColereDivine1.modelCarteDeuxEx#ColereDivine1 
 * 
 * **/
	
	public ColereDivine1() {
		super();
		this.peutJouerParAI=true;
		this.setNomDeusEx("ColereDivine");
		this.setOrigine("Jour");
		this.setDescription("Dé– ruit une carte Guide Spirituel d'Origine Nuit ou Né–�nt, dont la capacitï¿½ spé–�iale n'a pas effet. "
				+ "Les Croyants attaché–Ÿ reviennent au centre de la table.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("coleredivine1.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * the capacity:DÃ©truit une carte Guide Spirituel d'Origine Nuit ou NÃ©ant, dont la 
	 * capacitÃ© spÃ©ciale n'a pas effet.Les Croyants attachÃ©s reviennent 
	 * au centre de la table.
	 * @param numJ this is target player you chose.
	 * @see ColereDivine1.modelCarteDeuxEx#ColereDivine1
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
			if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Nuit")) {
				CarteCentreTable cst = CarteCentreTable.getCentreTable();
				for (int s = 0; s < gs.getNbCroyantGuide(); s++) {
					cst.ajouterCroyant(gs.getCroyants()[s]);
				}
				j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				j.mettreAJourGraphique();
				Partie.getJoueurHumain().mettreAJourGraphique();
				break out;
	}}}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * @param numJ 
	 * this is target player you chose.
	 * @see ColereDivine1.modelCarteDeuxEx#ColereDivine1
	 * */
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			out:while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Nuit")) {
					listeJ.add(js.get(i));
					listeJ.remove(Partie.getJoueurHumain());
					break out;
					}
				}
			}
	
		DemanderUtiliserCarteSansThread dem = new DemanderUtiliserCarteSansThread(listeJ, this);
		dem.demander();
}
	/**
	 * 
	 * This is a method for AI to use, When the strategy of AI to use this card
	 * it will call this method and it can directly get a player from the list of AI.
	 *@see ColereDivine1.modelCarteDeuxEx#ColereDivine1 
	 * **/
	public void sacrifierAI(){
		LinkedList<Joueur> js = Partie.getJoueurs();
		js.remove(this.getJoueur());
		out:for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Nuit")) {
					CarteCentreTable cst = CarteCentreTable.getCentreTable();
					for (int j = 0; j < gs.getNbCroyantGuide(); j++) {
						cst.ajouterCroyant(gs.getCroyants()[j]);
					}
					Partie.getJoueurs().get(i).getEspaceDuJoueur().supprimerGuideSpirituel(gs);
					Partie.getJoueurs().get(i).mettreAJourGraphique();
					break out;
				}
}}}
}
	


