package modelCarteDivinite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import modelCarte.Divinite;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurVirtual;
/**
*
* This is a class for a divinity. it extend to the class Divinite
* we give him the attributes and write the capacity for player to use it
* for this capacite it will be asked if you choose to use with a button when you can use it.
* We use Construction method to create different attributes for a type of the card and we 
* use IO input the picture.
* 
* */
public class Sbingva extends Divinite {
	String[] dogmes={"Humain","Mystique","Chaos"};
	public Sbingva() {
		this.setNom("Sbingva");
		this.setOrigine("Crepuscule");
		this.setDescriptionDivinite("Perverse et retorse, Shingva est une Divinit� que "
				+ "toutes les autres d�testent.");
		this.setDescriptionCapacite("Peut imposer le sacrifice d'un Guide Spirituel "
				+ "ayant le Dogme Symboles ou Nature.");
		this.setDogmes(dogmes);
		Image image = null;
		try {
			image = ImageIO.read(new File ("sbingva.jpg"));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		this.setImage(image);
	}
	/**
	*When we call this method in package control. This method will be called
	*at first it will choose a AI which has the card in his Espase as a target 
	*and use this capacity on this AI.
	* */
	public void CapaciteDivine() {
		this.setCapaciteUtilise(true);	
		if (true) {
			LinkedList<Joueur> js=Partie.getJoueurs();
			js.remove(Partie.getJoueurHumain());
			out:for (int i = 0; i < js.size(); i++) {
					if (Partie.getJoueurs().get(i).getEspaceDuJoueur().getListeDesCroyants().size()!=0) {
						if (Partie.getJoueurs().get(i).getEspaceDuJoueur().getListeDesCroyants().get(0).isPeutJouerParAI()) {
							JoueurVirtual jv=(JoueurVirtual)Partie.getJoueurs().get(i);
							
								try {
									jv.sacrifierCroyantAI();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
//							Partie.getJoueurs().get(i).mettreAJourGraphique();
							break out;
						}
						
					}
			}
		}
		}
}
