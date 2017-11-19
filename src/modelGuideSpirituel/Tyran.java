package modelGuideSpirituel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.CarteCentreTable;
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
public class Tyran extends GuideSpirituel
{
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
		LinkedList<Croyant> listeC = new LinkedList<Croyant>();
		listeC.addAll(CarteCentreTable.getCentreTable().getEspaceCentreTable());
			for (int i = 0; i < listeC.size(); i++) {
				Croyant cro=listeC.get(i);
					CarteCentreTable.getCentreTable().supprimerCroyant(cro);
				
			}

			Partie.getJoueurHumain().mettreAJourGraphique();
			
				
				
	}
	
	public void sacrifierGUI(){
		LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
		LinkedList<Joueur> js = Partie.getJoueurs();
		listeJ.add(Partie.getJoueurHumain());
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
	
	private static Joueur JoueurQuiJoueLaCarte; 
	private String description = "D閒ausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la table.";
	/**
	 * 
	 * This is a construction method in this method we give some the attributes and 
	 * Input the picture of the card.
	 * @exception IOException  If we can't find the picture of this card.
	 * **/
	public Tyran ()
	{
		this.setNom("Tyran");
		this.setNbCroyantMax(3);
		this.setOrigine("Neant");
		String [] tab = {"Symboles","Chaos"};
		this.setDogmes(tab);
		Image image = null;
		try {
			image = ImageIO.read(new File ("tyran.jpg"));
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
