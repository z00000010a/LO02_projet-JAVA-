package modelGuideSpirituel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.CarteAction;
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
public class Paladin extends GuideSpirituel
{	private static Joueur JoueurQuiJoueLaCarte; 
	private String description = "Tous les Croyants, d'Origine Nuit ou N閍nt et ayant le Dogme Nature, "
			+ "actuellement sur la table sont d閒auss閟. Les capacit閟 sp閏iales ne sont pas jou閑s.";
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
				if (cro.getOrigine()=="Nuit"||cro.getOrigine()=="Neant"	) {
					CarteCentreTable.getCentreTable().supprimerCroyant(cro);
				}
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
	public Paladin ()
	{
		this.setNom("Paladin");
		this.setNbCroyantMax(3);
		this.setOrigine("Jour");
		String [] tab = {"Mystique","Humain"};
		this.setDogmes(tab);
		Image image = null;
		try {
			image = ImageIO.read(new File ("paladin.jpg"));
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
