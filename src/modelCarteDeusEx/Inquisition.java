package modelCarteDeusEx;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.*;
import modelJoueur.*;

/**
*
* This is a class for a card DeusEx. it extend to the class DeusEx
* This a different type of DeusEX it can be use when the phase of other player
* We will call this void method when we use this card to prevent other card action
* when we call this method we can prevent a CarteAction.
* we use IO input the picture.
* */


public class Inquisition extends DeusEx {
	private Joueur joueur=Partie.getJoueurHumain();
	
/**
 * 
 * This is a construction method
 *@see ColereDivine2.modelCarteDeuxEx#ColereDivine2 
 * 
 * **/
	

	public Inquisition() {
		super();
		this.setNomDeusEx("Inquisition");
		this.setOrigine("Null");
		this.setDescription("Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, "
				+ "sur Nuit le votre est sacrifi�, sur N閍nt rien ne se passe. ");
		Image image = null;
		try {
			image = ImageIO.read(new File ("inquisition.jpg"));
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
	 * @param numJ  this is target player you chose.
	 * @see ColereDivine2.modelCarteDeuxEx#ColereDivine2
	 * */
	public void sacrifier(){
		LinkedList<Joueur> js = Partie.getJoueurs();
		System.out.println("choisissez un Guide Spirituel de vous:");
		Iterator<GuideSpirituel> it = this.joueur.getEspaceDuJoueur().getListeDesGuides().iterator();
		int count = 0;
		while(it.hasNext()){
			System.out.println(count + ":");
			System.out.println(it.next().getNbCroyantGuide());
		}
		Scanner sc = new Scanner(System.in);
		int n1 = sc.nextInt();
		GuideSpirituel gs = this.joueur.getEspaceDuJoueur().getListeDesGuides().get(n1);
		Iterator<GuideSpirituel> it2;
		int count2;
		System.out.println("choisissez un Guide Spirituel d'un autre joueur:");
		for(int i = 0; i < js.size(); i++) {
			if(i != js.indexOf(this.joueur)) {
				System.out.println("joueur" + i + ":");
				it2 = js.get(i).getEspaceDuJoueur().getListeDesGuides().iterator();
				count2 = 0;
				while(it2.hasNext()){
					System.out.println("Guide Spirituel " + count2 + ":");
					System.out.println(it2.next().getNbCroyantGuide());
				}
			}
		}
		System.out.println("choisissez un joueur:");
		int n2 = sc.nextInt();
		System.out.println("choisissez un Guide Spirituel:");
		int n3 = sc.nextInt();
		GuideSpirituel gs2 = js.get(n2).getEspaceDuJoueur().getListeDesGuides().get(n3);
		Random r=new Random();
		int n=r.nextInt(3);
		if(n == 0){
			System.out.println("Nuit");
			gs.sacrifier();
			this.joueur.getEspaceDuJoueur().getListeDesGuides().remove(gs);
		}
		if(n == 2){
			System.out.println("Jour");
			gs2.sacrifier();
			js.get(n2).getEspaceDuJoueur().getListeDesGuides().remove(gs2);		}
		if(n == 1){
		}
		
		
	}


}
