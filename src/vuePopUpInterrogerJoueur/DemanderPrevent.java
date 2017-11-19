package vuePopUpInterrogerJoueur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Test.Test;
import controller.Controler;

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
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelDeroulementPartie.Tour;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;
import vueAction.ActionKillThreadFenetreInterrogeJoueur;


/**	//to put before a class//to put before a class//to put before a class//to put before a class//to put before a class	
 * 
 * Here you write what is this class and what it does
 * 
 *
 */


/**	//to put before a method//to put before a method//to put before a method//to put before a method//to put before a method
 * For the method you explain what the method is for, what it does and what it returns
 * Also please use this tag if you can : (in this order)
 * 
 *@param (methods and constructors only)						//what are the parametre for this method, their types and how they are used by the method
 *@param //put as much as there is argument
 *
 *@return (methods only)										//what does the method return (what 
 *
 *@exception (@throws is a synonym added in Javadoc 1.2)		//if the method return an exception
 *@exception //put as much as there is exception
 *
 *@see nameOfPackage.ClassName#nameOfMethod(arg 1, arg2 ...)	//if the method call or is called by a this method / class
 *@see //put as much as you need to specify method of class to be notified with this javadoc
 */

/**
 *	//to put before an attribute
 *
 *	just specify what this attributes is for, his type and what it represent
 *
 */

//OTHER NOTES DONT WRITE JAVADOC FOR THE GETTER AND SETTERS UNLESS I OR YOU HAVE MODIFIED THEM
public class DemanderPrevent implements Runnable
{
	
		private static Thread t;
		private CarteAction carte;
		private static boolean choixJoueur=false;
		public DemanderPrevent(CarteAction carte) 
		{
			this.carte=carte;
		}
		@Override
		public void run() 
		{
			
			demander(t, carte);
		}
		public static void demander(Thread t, CarteAction carte)
		{
			synchronized (Test.getVerrouThread())
			{
				//création de la fenètre qui va acceuillir les boutons
				JFrame frame = new JFrame("Voulez-vous utiliser la carte :");
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width/2, dim.height/2);
				frame.setLayout(new FlowLayout());
				
				JButton boutonAccepter = new JButton("Voulez-vous empecher l'adversaire de jouer la carte"+carte.getNom());
				JButton boutonRefuser = new JButton("Ne PAS jouer la carte");
				boutonAccepter.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent arg0) {DemanderPrevent.setChoixJoueur(true);}});
				boutonAccepter.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t,frame));
				boutonRefuser.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t,frame));
				
				frame.getContentPane().add(boutonAccepter);
				frame.getContentPane().add(boutonRefuser);
				frame.setDefaultCloseOperation(0);
				frame.pack();
				frame.setVisible(true);
			}
		}
		public static Thread getT() {
			return t;
		}
		public static void setT(Thread t) {
			DemanderPrevent.t = t;
		}
		public static boolean getChoixJoueur() {
			return DemanderPrevent.choixJoueur;
		}
		public static void setChoixJoueur(boolean choixJoueur) {
			DemanderPrevent.choixJoueur = choixJoueur;
		}		
}
