package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelCroyantJour.Moines1;
import modelCroyantJour.Travailleurs3;
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
public class Sorcier extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	Joueur jHum = null;

	String description = "Echangez l'un de vos Guides Spirituels avec un d'une autre Divinit锟�. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attach闁� aux m闃璭s cartes.";
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
			Exorciste exorciste=new Exorciste();
			Moines1 m=new Moines1();
			Travailleurs3 m1=new Travailleurs3();
			Croyant[] cry={m,m1};
			Partie.getJoueurHumain().getEspaceDuJoueur().ajouterGuideSpirituel(cry, exorciste);	
			
			j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				Moines1 m2=new Moines1();
				Travailleurs3 m3=new Travailleurs3();
				Croyant[] cry1={m2,m3};
				j.getEspaceDuJoueur().ajouterGuideSpirituel(cry1, this);
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
		System.out.println("AI joueur GuideSp");
		this.getJoueurQuiJoueLaCarte().setPointActionJour(this.getJoueurQuiJoueLaCarte().getPointActionJour()+2);
		this.getJoueurQuiJoueLaCarte().mettreAJourGraphique();
	}
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Sorcier ()
	{
		this.setNom("Sorcier");
		this.setNbCroyantMax(3);
		this.setOrigine("Nuit");
		String [] tab = {"Mystique","Symboles"};
		this.setDogmes(tab);
		Image image = null;
		try {
			image = ImageIO.read(new File ("sorcier.jpg"));
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
	public Joueur getjHum() {
		return jHum;
	}
	public void setjHum() {
		this.jHum = Partie.getJoueurHumain();
	}
}
