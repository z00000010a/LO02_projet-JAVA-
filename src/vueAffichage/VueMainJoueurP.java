package vueAffichage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Test.Test;
import controller.Controler;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelDeroulementPartie.Tour;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;
import vueAction.ActionChoixDebutTour;
import vueAction.ActionKillThread;
import vueBouton.BoutonCarte;

/**
 *	Cette classe permet de montrer la vue de la main du joueur physique. Elle impl閙ente Observer et l'utilise
 *pour observer le joueur physique. Elle impl閙ente 間alement Runnable pour que l'on puisse lancer un Thread � partir 
 *d'un objet de cette classe.
 *
 */
public class VueMainJoueurP extends JFrame  implements Observer, Runnable 
{ 
	/**
	 * Le choix du joueur pour ce tour, d閒ini par la m閠hode demanderChoixAction(Thread t).
	 */
	static int choix=5;
	/**
	 * Le joueur humain, que l'objet observe.
	 */
	private Joueur joueur;
	/**
	 * Le num閞o du tour actuel, utile pour savoir comment mettre � jour la vue. Mis � jour par update(Observable, Object).
	 * @see vueAffichage.VueMainJoueurP#update(Observable, Object)
	 */
	private int numTour=-1; 
	/**
	 * Boolean, permet d'interdir au joueur d'utiliser les boutons de sa main en les d閟activant en dehors de son tour.
	 * Mis a jour par  Action Kill Thread, reset par la m閠hode run.
	 */
	private static boolean boutonDisabled=false;
	/**
	 * Le Thread lanc� � partir de cette objet, permet � la m閠hode ActionKillThread de l'interrompre.
	 */
	private static Thread t;
	/**
	 * Met � jour le boolean boutonDisabled. Appel la m閠hode synchroniser demanderChoixAction(Thread t)
	 * @see vueAffichage.VueMainJoueurP#demanderChoixAction(Thread)
	 */
	public void run() 
	{
		this.boutonDisabled=false;

		{   
			try {
				try {
					demanderChoixAction(t);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //on interrompera le thread avec le bouton fin tour
		}

	}
	/**
	 * Le constructeur de cette classe. Appel la m閠hode initialisation() pour finir l'instanciation de l'objet.
	 * 
	 * @param joueur le joueur que l'on observe
	 * @see vueAffichage.VueMainJoueurP#initialisation()
	 */
	public VueMainJoueurP (Joueur joueur) //throws IOException, InterruptedException
	{
		this.joueur = joueur;
		this.setTitle("Votre main :");//titre fenetre
		this.setSize(1920, 400); // taille
		this.setLocation(0, 640); // position dasn l'閏ran, origine rep鑢e est dans coin sup閞ieur gauche
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		this.setVisible(true);
		setResizable(false);//peut pas modifier taille fenetre
		initialisation();
	}
	/**
	 * Cr閑r les AfficheImage dont on a besoin pour afficher les cartes de la main du joueur. Si le joueur n'a
	 * pas 7 cartes alors l'image affich� sera "default.jpg"
	 */
	public void initialisation() //throws InterruptedException
	{
		FlowLayout leFlux = new FlowLayout();
		this.getContentPane().setLayout(leFlux);
		AfficheImage afficheImage1 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage2 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage3 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage4 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage5 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage6 = new AfficheImage ("default.jpg");
		AfficheImage afficheImage7 = new AfficheImage ("default.jpg");
		this.getContentPane().add(afficheImage1, 0);
		this.getContentPane().add(afficheImage2, 1);
		this.getContentPane().add(afficheImage3, 2);
		this.getContentPane().add(afficheImage4, 3);
		this.getContentPane().add(afficheImage5, 4);
		this.getContentPane().add(afficheImage6, 5);
		this.getContentPane().add(afficheImage7, 6);
		this.update(getGraphics());
		this.pack();
		this.update(getGraphics());
		this.pack();
		validate();
	}
	/**
	 * Permet de mettre � jour la carte du composant qui est � l'index pr閏is�, en rajoutant les boutons ad閝uats.
	 * @param carte La nouvelle carte � affich�
	 * @param index	L'index du composants � mettre � jour
	 * @throws InterruptedException
	 */
	public void changerCarte (CarteAction carte, int index) throws InterruptedException
	{
		((AfficheImage) this.getContentPane().getComponent(index)).changerCarteDeMain(carte);
		this.update(getGraphics());
		validate();//r閍ffiche les composants
		this.pack();
	}
	/**
	 * Permet de mettre � jour la carte du composant qui est � l'index pr閏is�, rajout� aucun bouton.
	 * @param carte La nouvelle carte � affich�
	 * @param index	L'index du composants � mettre � jour
	 */
	public void changerCarteSansBouton (CarteAction carte, int index)
	{
		((AfficheImage) this.getContentPane().getComponent(index)).changerCarteSansBouton(carte);
		this.update(getGraphics());
		validate();//r閍ffiche les composants
		this.pack();
	}
	/**
	 * Permet de mettre � jour la main du joueur sans afficher aucun de ses boutons. 
	 * @param choix Non utilis�
	 * @param o		Non utilis�
	 */
	public void rafraichir(int choix, Observable o)
	{

		switch (choix)
		{
		case 0: 			//defausser
				break;
		case 1:	afficherMainSansBouton(); 	//piocher
				break;
		case 2: 			//utiliser carte
				break;
		}
	}
	/**
	 * Permet de d閟activer tout les boutons de toutes les cartes de la main du joueur.
	 */
	public void desactiverBoutonMain()
	{	//va it閞er sur tout les affiche image
		for (int i = 0; i < ((JoueurPhysique) Partie.getJoueurHumain()).getVueMainJoueur().getContentPane().getComponents().length; i++) 
		{
			if (((JoueurPhysique) Partie.getJoueurHumain()).getVueMainJoueur().getContentPane().getComponents()[i] instanceof AfficheImage )
			{	//il y a un cast sur Joueur pour le considerer comme JoueurPhysique et un sur le component pour le considerer comme afficheImage
				for (int j = 0; j < ((AfficheImage)((JoueurPhysique) Partie.getJoueurHumain()).getVueMainJoueur().getContentPane().getComponents()[i]).getComponents().length; j++)
				{
					if (((AfficheImage)((JoueurPhysique) Partie.getJoueurHumain()).getVueMainJoueur().getContentPane().getComponents()[i]).getComponents()[j] instanceof BoutonCarte)
					{	//on rajoute un cast sur ce millefeuille pour disable les bouton pendant que le joueur ne joue pas
						((BoutonCarte)((AfficheImage)((JoueurPhysique) Partie.getJoueurHumain()).getVueMainJoueur().getContentPane().getComponents()[i]).getComponents()[j]).setEnabled(false);
					}
				}
			}
		}
	}
	/**
	 * Permet de remplacer les bouton du composant � l'index i par un bouton d閒ausser
	 * @param carte La carte que l'on souhaite remplacer mettre sur ce composant.
	 * @param i L'index du composant que l'on souhaite modifier
	 */
	public void changerCarteDefausser(CarteAction carte, int i)
	{
		((AfficheImage) this.getContentPane().getComponent(i)).changerCarteDefausser(carte);
		this.update(getGraphics());
		validate();//r閍ffiche les composants
		this.pack();
	}
	
	/**
	 * Permet d'appliquer la m閠hode changerCarteDefausser(CarteAction carte, int i) � tous les composants 
	 * AffichImage de cet objet.
	 * @see vueAffichage.VueMainJoueurP#changerCarteDefausser(CarteAction, int)
	 */
	public void afficherMainBoutonDefausser()
	{
		LinkedList<CarteAction> listeC = joueur.getMainDuJoueur().getMainDuJoueur();
		Iterator <CarteAction> iteC = listeC.iterator();
		while (iteC.hasNext())
		{
			CarteAction carte = iteC.next();	
			changerCarteDefausser(carte, listeC.indexOf(carte));	//utilise la m閠hode pour appeler changerDeCarteDefausser de AfficheImage
		}
	}
	
	/**
	 * Affiche la main du joueur sans aucun bouton, utile pour piocher des carte puis afficher la main du joueur
	 * Appel la m閠hode changerCarteSansBouton(CarteAction, int) pour toutes les composants de cet objet.
	 * @see vueAffichage.VueMainJoueurP#changerCarteSansBouton(CarteAction, int)
	 */
	public void afficherMainSansBouton()
	{
		LinkedList<CarteAction> listeC = joueur.getMainDuJoueur().getMainDuJoueur();
		Iterator <CarteAction> iteC = listeC.iterator();
		while (iteC.hasNext())
		{
			CarteAction carte = iteC.next();
			changerCarteSansBouton(carte, listeC.indexOf(carte));
		}
	}
	/**
	 * Permet en d閎ut de tour de demander quel type d'action effectuer.
	 * L'action de chaque bouton update la m閠hode du joueur humain de mani鑢e diff閞ente. Ainsi le joueur fait les actions qui l'interresse,
	 * puis permet au Thread principal de reprendre son cours avec le bouton "Fin du tour"
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void demanderChoixAction(Thread t) throws InterruptedException, IOException
	{	synchronized (Test.getVerrouThread())
		{
				JFrame frameChoix = new JFrame("Choisissez le type d'action que vous voulez effectuer pendant ce tour");
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frameChoix.setLocation(dim.width/2-frameChoix.getSize().width/2, dim.height/2-frameChoix.getSize().height/2);
				JButton boutonDefausser = new JButton("Défausser des cartes");
				JButton boutonPiocher = new JButton("Completer ma main");
				JButton boutonJouer = new JButton("Utiliser mes cartes");
				JButton finTour = new JButton ("Fin du tour");
				
				ActionChoixDebutTour aDef = new ActionChoixDebutTour(0,frameChoix, (JoueurPhysique) joueur);
				ActionChoixDebutTour aPio = new ActionChoixDebutTour(1,frameChoix, (JoueurPhysique) joueur);
				ActionChoixDebutTour aUtil = new ActionChoixDebutTour(2,frameChoix, (JoueurPhysique) joueur);
				
				boutonDefausser.addActionListener(aDef);
				boutonPiocher.addActionListener(aPio);
				boutonJouer.addActionListener(aUtil);
				finTour.addActionListener(new ActionKillThread(t,frameChoix));
				
				frameChoix.setLayout(new FlowLayout());
				frameChoix.add(boutonJouer,0);
				frameChoix.add(boutonDefausser,1);
				frameChoix.add(boutonPiocher,2);
				frameChoix.add(finTour,3);
				boutonDefausser.setSize(200, 100);
				boutonJouer.setSize(200, 100);
				boutonPiocher.setSize(200, 100);
				finTour.setSize(200, 100);
				
				frameChoix.pack();
				frameChoix.setResizable(false);
				frameChoix.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				frameChoix.setAlwaysOnTop (true);
				frameChoix.setVisible(true);
		}
	}
	/**
	 * Cette m閠hode permet au joueur d'utiliser ces cartes. Elle va appliquer la m閠hode changerCarte(CarteAction, int)
	 * � tous les composants de cet objet.
	 * @see vueAffichage.VueMainJoueurP#changerCarte(CarteAction, int)
	 */
	public void afficherMainUtiliser()
	{
		LinkedList<CarteAction> listeC = joueur.getMainDuJoueur().getMainDuJoueur();
		Iterator <CarteAction> iteC = listeC.iterator();
		while (iteC.hasNext())
		{
			CarteAction carte = iteC.next();
			try {
				changerCarte(carte, listeC.indexOf(carte));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * En fonction du choix du joueur cette m閠hode va afficher la main du joueur avec ou sans boutons.
	 * Appeler automatiquement par la m閠hode notifyObservers() du joueur humain.
	 * @see modelJoueur.JoueurPhysique#notifyObservers()
	 */
	public void update(Observable o, Object arg) {
		
		this.getContentPane().removeAll();
		initialisation();
		afficherMainSansBouton();

			numTour=Tour.getNumeroDeTour();
			
		if (choix==0)
		{
			afficherMainBoutonDefausser();
			if (boutonDisabled)
			{
				desactiverBoutonMain();
			}
		}
		else if (choix==1)
		{
			afficherMainSansBouton();
		}
		else if (choix==2)
		{
			afficherMainUtiliser();
			if (boutonDisabled)
			{
				desactiverBoutonMain();
			}
		}
		validate();
	}
	public static int getChoix() {
		return choix;
	}
	public static void setChoix(int choix) {
		VueMainJoueurP.choix = choix;
	}

	public static Thread getT() {
		return t;
	}

	public static void setT(Thread t) {
		VueMainJoueurP.t = t;
	}

	public static boolean isBoutonDisabled() {
		return boutonDisabled;
	}

	public static void setBoutonDisabled(boolean boutonDisabled) {
		VueMainJoueurP.boutonDisabled = boutonDisabled;
	}
}
