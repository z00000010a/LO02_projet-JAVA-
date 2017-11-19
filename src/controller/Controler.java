package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Test.Test;
import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.Divinite;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.*;
import modelJoueur.*;
import modelStrategy.PeutJouer;
import vueAffichage.VueMainJoueurP;
import vueLabel.Prevent;
import vuePopUpInterrogerJoueur.DemanderPrevent;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteONull;
/**
 * @author Crowbar
 *
 */
public class Controler 
{
	private VueMainJoueurP vueMain;
	private static JoueurPhysique joueur;
	private Partie partie;
	private boolean tourFini=false;
	private static Controler instanceControler=null; 
	/**
	 * gaffe de créer la partie avant et d'initialiser la partie avant.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private Controler () throws IOException, InterruptedException
	{
		this.joueur = (JoueurPhysique) Partie.getJoueurHumain();
		this.partie = Partie.getInstancePartie(0, null);
	}
	public static Controler getInstanceControler() throws IOException, InterruptedException
	{
		if (Controler.instanceControler == null)
		{
			Controler.instanceControler = new Controler();
		}
		return Controler.instanceControler;
	}
	/**
	 * Utile à la vue de la main du Joueur, affiche tout les croyant jouable
	 * @return
	 */
	private  LinkedList<Croyant> getCroyantJouable()
	{
		LinkedList <CarteAction> listeMain = new <CarteAction> LinkedList();
		listeMain.addAll(joueur.getMainDuJoueur().getMainDuJoueur());
		Iterator<CarteAction> ite = listeMain.iterator();
		LinkedList <Croyant> listeCro = new <Croyant> LinkedList(); //liste qui va recevoir les croyant pouvant être utilisé

		while (ite.hasNext())
		{
			CarteAction obTest = ite.next();
			if(obTest instanceof Croyant)
			{
				if (joueur.getPointActionJour() != 0)
				{
					if (obTest.getOrigine()=="Jour")
					{
						listeCro.add((Croyant) obTest); //ajoute le croyant si on a les points pour le jouer et que c'est un croyant
					}
				}
				if (joueur.getPointActionNuit() != 0)
				{
					if (obTest.getOrigine()=="Nuit")
					{
						listeCro.add((Croyant) obTest); //ajoute le croyant si on a les points pour le jouer et que c'est un croyant
					}
				}
				if (joueur.getPointActionNeant() != 0)
				{
					if (obTest.getOrigine()=="Neant")
					{
						listeCro.add((Croyant) obTest); //ajoute le croyant si on a les points pour le jouer et que c'est un croyant
					}
				}
			}
			
		}	
		return listeCro;
	}
	
	public boolean prevent(CarteAction carte, JoueurVirtual jV) throws InterruptedException
	{
		
			LinkedList <CarteAction> listeM = new LinkedList<CarteAction>();
			listeM.addAll(Partie.getJoueurHumain().getMainDuJoueur().getMainDuJoueur());
			Iterator<CarteAction> iterateur = listeM.iterator();
			while (iterateur.hasNext()) 
			{
				CarteAction ca = iterateur.next();
				if (ca instanceof DeusEx) 
				{	
					DeusEx deusEx = (DeusEx) ca;
					if (deusEx.getNom() == "InfluenceNulle"
							|| (deusEx.getNom() == "InfluenceJour" && carte.getOrigine() == "Jour")
							|| (deusEx.getNom() == "InfluenceNeant" && carte.getOrigine() == "Neant")
							|| (deusEx.getNom() == "InfluenceNuit" && carte.getOrigine() == "Nuit")) 
					{	
						DemanderPrevent demCarte = new DemanderPrevent(carte);
						Thread threadDemJoueur = new Thread(demCarte);
						DemanderPrevent.setT(threadDemJoueur);
						System.out.println("Le thread est créer");
						threadDemJoueur.start();
						Test.getVerrouThread().notifyAll();
						Test.getVerrouThread().wait(); //att qu'on lui redonne le verrou
						
						if (DemanderPrevent.getChoixJoueur()) 
						{
							Prevent p1=new Prevent();
							Partie.getJoueurHumain().getMainDuJoueur().supprimerCarte(deusEx);
							((JoueurPhysique) Partie.getJoueurHumain()).mettreAJourGraphique();
							return true;
						}

					}
				}
			}
			return false;
		}
	
	public void utiliserCapaDiv (Divinite div)
	{
		if (div.isCapaciteUtilise())
		{
			JFrame frameErreur = new JFrame();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			JOptionPane.showMessageDialog(frameErreur , 
					"Erreur",
			         " Votre capacité divine a déjà été utilisée ",
			         JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			div.CapaciteDivine();
			div.setCapaciteUtilise(true);
		}
	}
	
	public void finirTour()
	{
		partie.getJoueurs();
	}
	
	public void poserCroyant(Croyant cro)
	{
		if (getCroyantJouable().contains(cro))
		{
			Controler.joueur.getEspaceCentre().ajouterCroyant(cro);
			Controler.joueur.getMainDuJoueur().supprimerCarte(cro);
			enleverPoint(cro);
			joueur.mettreAJourGraphique();
		}
		else
		{
			JFrame frameErreur = new JFrame();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			JOptionPane.showMessageDialog(frameErreur , 
					"Les conditions pour jouer ce croyant ne sont pas reunies"
					  +"\n Vérifier vos points d'action de type "+cro.getOrigine(),
			         " Erreur sur la pose du croyant ",
			         JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * Vérifie que le joueur à les points pour jouer ce guide et renvoie les croyant guidable de ce guide, si n'y en a pas, elle retourne une liste vide
	 * @param guide
	 * @return
	 */
	private LinkedList<Croyant> verifierCroyantPossibleGuideSpirituel(GuideSpirituel guide)
	{
		boolean peutJoue=false;
		LinkedList <Croyant> listeCroGuidable = new LinkedList<Croyant>();			//on stock les croyant guidable la dedans
		
		if (guide.getOrigine().equals("Jour"))
		{
			if (joueur.getPointActionJour() > 0)
			{
				peutJoue=true;
			}
		}
		else if (guide.getOrigine().equals("Nuit"))
		{
			if (joueur.getPointActionNuit() > 0)
			{
				peutJoue=true;
			}
		}
		else if (guide.getOrigine().equals("Neant"))
		{
			if (joueur.getPointActionNeant() > 0)
			{
				peutJoue=true;
			}
		}
		
		if (peutJoue)
		{
			LinkedList <Croyant> listeCro = new LinkedList<Croyant>();
			listeCro.addAll(CarteCentreTable.getCentreTable().getEspaceCentreTable());
			Iterator <Croyant> iteListeCro = listeCro.iterator();
			while (iteListeCro.hasNext())
			{
				Croyant cro = iteListeCro.next();
				if (joueur.dogmeCommun(guide, cro) && listeCroGuidable.size()<guide.getNbCroyantMax())
					{
						listeCroGuidable.add(cro);
					}
			}
		}
		
		return listeCroGuidable; 
	}
	
	public void poserGuideSpirituel (GuideSpirituel guide)
	{
		LinkedList <Croyant> listeCroGuidable = new LinkedList<Croyant>();
		listeCroGuidable.addAll(verifierCroyantPossibleGuideSpirituel(guide)); //dans cette liste on stock tout les croyant guidablae par
		//ce joueur CETTE METHODE VERIFIE AUSSI SI LE JOUEUR A LES POINTS POUR POSER CE GUIDE
		
		if (listeCroGuidable.size()==0)
		{
			JFrame frameErreur = new JFrame();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			JOptionPane.showMessageDialog(frameErreur , 
					"Les conditions pour jouer ce guide ne sont pas reunies"
					  +"\n Vérifier vos points d'action de type "+guide.getOrigine()+" ou bien si les croyant disponibles sont compatibles.",
			         " Erreur sur la pose du guide spirituel ",
			         JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			Iterator<Croyant> iteListeCroGuidable = listeCroGuidable.iterator();
			Croyant [] tabCro = new Croyant [listeCroGuidable.size()]; //créer le tableau nécessaire à la méthode de EspaceJoueur.ajouteGuideSpirituel
			int i=0;
			while (iteListeCroGuidable.hasNext())
			{
				Croyant cro = iteListeCroGuidable.next();
				CarteCentreTable.getCentreTable().supprimerCroyant(cro);//enlève les croyants de l'espace centrale et les place dans le tableau
				tabCro[i]=cro;
				i++;
			}
			int tailleReelTabCro=0;//on va enlever les cases vides de tabCro
			for (int j = 0; j < tabCro.length; j++) 
			{
				if (tabCro[j] != null)
				{
					tailleReelTabCro++;
				}
			}
			Croyant[] tabCro2 = new Croyant [tailleReelTabCro];
			int h=0;
			for (int j = 0; j < tabCro.length; j++) 
			{
				if (h<tabCro2.length)
				{
					if (tabCro[j] != null)
					{
						tabCro2[h]=tabCro[j];
						h++;
					}
				}	
			} //on s'assure ainsi que toutes les cases du tableau sont pleines
			joueur.getEspaceDuJoueur().ajouterGuideSpirituel(tabCro2, guide);

			joueur.getMainDuJoueur().supprimerCarte(guide);
			enleverPoint(guide);
			joueur.mettreAJourGraphique();
		}
		
	}
	public void poserDeusEx(DeusEx carte)
	{	boolean peutJoue=false;
		if (carte.getOrigine()==null)
		{
				peutJoue=true;
		}
		else if (carte.getOrigine().equals("Jour"))
		{
			if (joueur.getPointActionJour() > 0)
			{
				peutJoue=true;
			}
		}
		else if (carte.getOrigine().equals("Nuit"))
		{
			if (joueur.getPointActionNuit() > 0)
			{
				peutJoue=true;
			}
		}
		else if (carte.getOrigine().equals("Neant"))
		{
			if (joueur.getPointActionNeant() > 0)
			{
				peutJoue=true;
			}
		}
		//a ce stade on a vérifié que le joueur ne voulait pas jouer un DeusEx sans avoir les points
		carte.sacrifier();
		Pile.getInstance().ajouterCarteDansPile(carte);
		joueur.getMainDuJoueur().supprimerCarte(carte);
	}
	public void poserApocalypse (Apocalypse carte)
	{
		
		if (PeutJouer.PeutJouerApocalypse(joueur))
		{
			Apocalypse.cEstLapocalypse();
			joueur.getMainDuJoueur().supprimerCarte(carte);
			enleverPoint(carte);
		}
		else
		{
			JFrame frameErreur = new JFrame();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			JOptionPane.showMessageDialog(frameErreur , 
					"Les conditions pour jouer cette carte ne sont pas reunies",
			         " Erreur sur la pose de l'apocalypse ",
			         JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public void defausserCarte(CarteAction carte)
	{
		joueur.getMainDuJoueur().supprimerCarte(carte);
		joueur.mettreAJourGraphique();
	}
	
	public void defausserCarteTXT()
	{
		System.out.println("Combien de cartes souhaitez-vous défaussez ?\n");
		boolean pasBon=true;
		int nbCarte=0;
		do {
			Scanner sc = new Scanner(System.in);
			nbCarte = sc.nextInt();
			if (nbCarte < 8 && nbCarte >0)
			{
				pasBon=false;
			}
		}while (pasBon);
		System.out.println(nbCarte);
		for (int q=0; q<nbCarte;q++)
		{
			joueur.getMainDuJoueur().supprimerCarte();
			joueur.mettreAJourGraphique();
		}
	}
	public void piocherCarte()
	{
		joueur.piocherCarte();
	}
	public void enleverPoint(CarteAction carte)
	{
		if (carte.getOrigine()==null)
		{
			
		}
		else if (carte.getOrigine().equals("Jour"))
		{
			joueur.setPointActionJour(joueur.getPointActionJour()-1);
		}
		else if (carte.getOrigine().equals("Nuit"))
		{
			joueur.setPointActionNuit(joueur.getPointActionNuit()-1);
		}
		else if (carte.getOrigine().equals("Neant"))
		{
			joueur.setPointActionNeant(joueur.getPointActionNeant()-1);
		}
	}
	
	public void UtiliserLeSacrifice(CarteAction carte)
	{
		carte.sacrifierGUI(); //this method dont exist npw you will have to implement it
		
		if (carte instanceof Croyant) {
			Croyant cro=(Croyant) carte;
			joueur.getEspaceDuJoueur().supprimerCroyant(cro);
		}else if (carte instanceof GuideSpirituel) {
			GuideSpirituel gs=(GuideSpirituel) carte;
			joueur.getEspaceDuJoueur().supprimerGuideSpirituel(gs);
		}else if (carte instanceof DeusEx){
			DeusEx de=(DeusEx) carte;
			joueur.getMainDuJoueur().supprimerCarte(de);
			enleverPoint(carte);
		}
			
	}
	
	public boolean isTourFini() {
		return tourFini;
	}
	public void setTourFini(boolean tourFini) {
		this.tourFini = tourFini;
	}
}
