package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
public class Exorciste extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	String description = "Une Divinit锟� d'Origine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y 闁燼ient attach闁� sont d闁抋uss闁�.";
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
		Iterator<GuideSpirituel> it = j.getEspaceDuJoueur().getListeDesGuides().iterator();
		out:while(it.hasNext()){
			GuideSpirituel gs=it.next();
				j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				 j.mettreAJourGraphique();
				 Partie.getJoueurHumain().mettreAJourGraphique();
				break out;
	}}
	
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
	public Exorciste ()
	{
		this.setNom("Exorciste");
		this.setNbCroyantMax(1);
		String [] d = {"Mystique","Chaos"};
		this.setDogmes(d);
		this.setOrigine("Jour");
		Image image = null;
		try {
			image = ImageIO.read(new File ("exorciste.jpg"));
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
