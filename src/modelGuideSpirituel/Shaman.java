package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import modelCarte.CarteAction;
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
public class Shaman extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	private String description ="Sacrifie tous les Croyants d'Origine Né–�nt d'une Divinitï¿½ ayant le Dogme Humain. Les capacité–Ÿ spé–�iales sont joué–‘s normalement.";
	
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
	public void sacrifierAI(Joueur jai){
		System.out.println("AI joueur GuideSp");
		this.getJoueurQuiJoueLaCarte().setPointActionJour(this.getJoueurQuiJoueLaCarte().getPointActionJour()+2);
		Partie.getJoueurHumain().mettreAJourGraphique();
	}
	
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Shaman ()
	{
		this.setNom("Shaman");
		this.setNbCroyantMax(3);
		this.setOrigine("Nuit");
		String [] tab = {"Symboles","Nature"};
		this.setDogmes(tab);
		Image image = null;
		try {
			image = ImageIO.read(new File ("shaman.jpg"));
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
