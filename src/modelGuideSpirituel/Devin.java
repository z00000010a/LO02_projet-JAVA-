package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
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
public class Devin extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	String description = "Oblige une Divinit锟� ayant le Dogme Nature ou Mystique 锟� sacrifier l'un de ses Guides Spirituels.";
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
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Devin ()
	{
		this.setNom("Devin");
		String [] d = {"Mystique","Nature"};
		this.setDogmes(d);
		this.setOrigine("Neant");
		this.setNbCroyantMax(1);
		Image image = null;
		try {
			image = ImageIO.read(new File ("devin.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	public void sacrifierAI(Joueur jai){
		System.out.println("AI joueur GuideSp");
		this.getJoueurQuiJoueLaCarte().setPointActionJour(this.getJoueurQuiJoueLaCarte().getPointActionJour()+2);
		this.getJoueurQuiJoueLaCarte().mettreAJourGraphique();
	}
	public Joueur getJoueurQuiJoueLaCarte() {
		return Partie.getJoueurHumain();
	}
	public void setJoueurQuiJoueLaCarte(Joueur joueurQuiJoueLaCarte) {
		JoueurQuiJoueLaCarte = joueurQuiJoueLaCarte;
	}
}
