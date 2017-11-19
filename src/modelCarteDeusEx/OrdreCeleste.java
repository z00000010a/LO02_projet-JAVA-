package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
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

public class OrdreCeleste extends DeusEx {
	
/**
 * 
 * This is a construction method
 *@see OrdreCeleste.modelCarteDeuxEx#OrdreCeleste
 * 
 * **/
	private Joueur joueur=Partie.getJoueurHumain();
	
	public OrdreCeleste() {
		super();
		this.peutJouerParAI=true;
		this.setNomDeusEx("OrdreCeleste");
		this.setOrigine("Null");
		this.setDescription("Vous r閏up閞ez un des Guides Spirituels pos閟 devant une autre"
				+ " Divinit� et le placez devant vous avec les Croyants qui y sont attach閟.");
		Image image = null;
		try {
			image = ImageIO.read(new File ("ordreceleste.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	 * the capacity:Vous récupérez un des Guides Spirituels posés devant une autre
	 *Divinité et le placez devant vous avec les Croyants qui
 	 *y sont attachés.
	 * @param numJ this is target player you chose.
	 * @see OrdreCeleste.modelCarteDeuxEx#OrdreCeleste
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
			Partie.getJoueurHumain().getEspaceDuJoueur().ajouterGuideSpirituel(gs.getCroyants(), gs);
				j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				j.mettreAJourGraphique();
				Partie.getJoueurHumain().mettreAJourGraphique();
				break out;
	}}
	/**
	 * This method is for you to choose a player whit the button on you Frame
	 * @param numJ 
	 * this is target player you chose.
	 * @see OrdreCeleste.modelCarteDeuxEx#OrdreCeleste
	 * */
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
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
	/**
	 * 
	 * This is a method for AI to use, When the strategy of AI to use this card
	 * it will call this method and it can directly get a player from the list of AI.
	 *@see OrdreCeleste.modelCarteDeuxEx#OrdreCeleste
	 * 
	 * **/
	public void sacrifierAI(){
		LinkedList<Joueur> js = Partie.getJoueurs();
		out:for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (js.get(i).getNumJoueur()!=this.getJoueur().getNumJoueur()) {
					this.getJoueur().getEspaceDuJoueur().ajouterGuideSpirituel(gs.getCroyants(), gs);
					it.remove();
					for (int j = 0; j < gs.getCroyants().length; j++) {
						js.get(i).getEspaceDuJoueur().getListeDesCroyants().remove(gs.getCroyants()[i]);
						Partie.getJoueurs().get(i).mettreAJourGraphique();
					}
					break out;
				}
		}
	}
}
}
