package modelCroyantNuit;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelCroyantJour.Ermite1;
import modelCroyantJour.Moines1;
import modelDeroulementPartie.Partie;
import modelJoueur.*;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
*
* This is a class for a card Croyant. it extend to the class Croyant
* In this class we have 3 method for use his capacity in different ways.
* For AI for a target and for button on GUI.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Lycanthropes extends Croyant {
	private String[] dogemes={"Humain","Nature","Chaos"};
	
	public Lycanthropes() {
		super();
		this.setNomCroyant("Lycanthropes");
		this.setOrigine("Nuit");
		this.setDogmes(dogemes);
		this.setDescription("Donne un point d'Action d'Origine Nuit.");
		this.setNbPriere(4);
		Image image = null;
		try {
			image = ImageIO.read(new File ("lycanthropes.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
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
	
			GuideSpirituel gs=j.getEspaceDuJoueur().getListeDesGuides().getFirst();
				CarteCentreTable cst = CarteCentreTable.getCentreTable();
					Moines1 m1=new Moines1();
					Ermite1 m2=new Ermite1();
					cst.ajouterCroyant(m1);
					cst.ajouterCroyant(m2);
				j.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
				j.mettreAJourGraphique();
				Partie.getJoueurHumain().mettreAJourGraphique();
		
	}


	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		for (int i = 0; i <js.size(); i++) {
			Iterator<GuideSpirituel> it = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
			out:while(it.hasNext()){
				GuideSpirituel gs=it.next();
				if (true) {
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

