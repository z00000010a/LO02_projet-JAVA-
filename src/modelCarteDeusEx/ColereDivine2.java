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


public class ColereDivine2 extends DeusEx {

	
	
	
/**
 * 
 * This is a construction method
 *@see ColereDivine2.modelCarteDeuxEx#ColereDivine2 
 * 
 * **/
	
	
	public ColereDivine2() {
		super();
		this.peutJouerParAI=true;
		this.setNomDeusEx("ColereDivine");
		this.setOrigine("Nuit");
		this.setDescription("D閠ruit une carte Guide Spirituel d'Origine Jour ou N閍nt, dont la capacit� sp閏iale n'a pas effet. "
				+ "Les Croyants attach閟 reviennent au centre de la table.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("coleredivine2.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	
	/**
	 * the capacity:Détruit une carte Guide Spirituel d'Origine Jour ou Néant,
	 *  dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent 
	 *  au centre de la table.
	 * @param numJ 
	 * this is target player you chose.
	 * @see ColereDivine2.modelCarteDeuxEx#ColereDivine2
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
			if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Jour")) {
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
	 * @param numJ  this is target player you chose.
	 * @see ColereDivine2.modelCarteDeuxEx#ColereDivine2
	 * */
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			out:while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Jour")) {
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
	 *@see ColereDivine2.modelCarteDeuxEx#ColereDivine2 
	 * **/
	public void sacrifierAI(){
		LinkedList<Joueur> js = Partie.getJoueurs();
		js.remove(this.getJoueur());
		out:for (int i = 0; i <js.size(); i++) {
			
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (gs.getOrigine().equals("Neant") || gs.getOrigine().equals("Jour")) {
					CarteCentreTable cst = CarteCentreTable.getCentreTable();
					for (int j = 0; j < gs.getNbCroyantGuide(); j++) {
						cst.ajouterCroyant(gs.getCroyants()[j]);
					}
					System.out.println(i);
					Partie.getJoueurs().get(i).getEspaceDuJoueur().supprimerGuideSpirituel(gs);
					Partie.getJoueurs().get(i).mettreAJourGraphique();
					break out;
				}
}}}
}


