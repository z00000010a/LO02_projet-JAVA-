package vuePopUpInterrogerJoueur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import Test.Test;
import modelCarte.CarteAction;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import vueAction.ActionKillThreadFenetreInterrogeJoueur;
import vueAction.ActionSacrifierCarte;
import vueBouton.BoutonCarte;
/**
 * Cette classe sert � demander au joueur si il souhaite utiliser la carte action
 *  d'origine nulle qui est mis en attribut dans le constructeur. Elle est instanci� par 
 *  la classe Tour � la fin de la phase de jeu de chaque joueur. 
 *  Elle impl�mente Runnable pour pouvoir stoper le thread prinicipale � chaque fois qu'elle est appel�e. 
 * 	Ainsi la partie patiente le temps qu'il faut au joueur pour choisir.
 *	@see modelDeroulementPartie.Tour
 */
public class DemanderUtiliserCarteONull implements Runnable
{
	/**
	 * Le thread qui est cr�er � partir de cette objet. Permet de l'interrompre.
	 */
	private static Thread t;
	/**
	 * La carte sera utilis� par l'action ActionSacrifierCarte. Elle est sp�cifi�e � l'instanciation de l'objet
	 */
	private CarteAction carte;
	/**
	 * Contructeur de l'objet.
	 * @param carte La carte dont on va executer/ne pas executer la m�thode sacrifier().
	 */
	public DemanderUtiliserCarteONull(CarteAction carte) 
	{
		this.carte=carte;
	}
	@Override
	/**
	 * La m�thode run de Runnable. Elle appelle la m�thode demander de cette classe.
	 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteONull#Demander(Thread t, CarteAction carte)
	 */
	public void run() 
	{
		demander(t, carte);
	}
	/**
	 * Cette m�thode est synchronis� sur le l'objet Test.VerrouThread sur lequel toutes les m�thodes runnable sont synchronis�, ce qui permet de 
	 * ne pas avoir plusieurs thread qui s'�x�cute en m�me temps. L'action ActionSacrifierCarte (CarteAction carte)
	 * sera appel�e si le joueur appuie sur le bouton "Jouer la carte".
	 * @param t Le thread qui est cr�er � partir de cet objet. Permet de l'interrompre gr�ce � ActionKillThreadFenetreInterrogeJoueur(Thread t, JFrame frame)
	 * @param carte La carte qui va �tre utiliser par l'action ActionSacrifierCarte(CarteAction carte)
	 */
	public static void demander(Thread t, CarteAction carte)
	{
		synchronized (Test.getVerrouThread())
		{
			//cr�ation de la fen�tre qui va acceuillir les boutons
			JFrame frame = new JFrame("Voulez-vous utiliser la carte :");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2, dim.height/2);
			frame.setLayout(new FlowLayout());
			
			BoutonCarte boutonAccepter = new BoutonCarte("Jouer la carte", carte);
			BoutonCarte boutonRefuser = new BoutonCarte("Ne PAS jouer la carte", carte);
			boutonAccepter.addActionListener(new ActionSacrifierCarte(carte));
			boutonAccepter.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t,frame));
			boutonRefuser.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t,frame));
			
			frame.getContentPane().add(boutonAccepter);
			frame.getContentPane().add(boutonRefuser);
			
			frame.pack();
			frame.setVisible(true);
		}
	}
	public static Thread getT() {
		return t;
	}
	public static void setT(Thread t) {
		DemanderUtiliserCarteONull.t = t;
	}		
 }


