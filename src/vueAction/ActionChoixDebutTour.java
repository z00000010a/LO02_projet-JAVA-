package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

import controller.Controler;
import modelCarte.CarteAction;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import vueAffichage.VueMainJoueurP;

public class ActionChoixDebutTour implements ActionListener  {
	private int choix;
	private JFrame frame;
	private JoueurPhysique joueur;
	private VueMainJoueurP vue;
	public ActionChoixDebutTour (int choix, JFrame frame, JoueurPhysique joueur)
	{
		this.choix=choix;
		this.frame=frame;
		this.joueur=joueur;
		this.vue=joueur.getVueMainJoueur();
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		VueMainJoueurP.setChoix(choix);	//utile pour l'update de VueMainJoueur
		
		if (choix==0)
		{
			vue.afficherMainBoutonDefausser();
		}
		if (choix==1)
		{
			try {
				Controler.getInstanceControler().piocherCarte();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vue.afficherMainSansBouton();
		}
		if (choix==2)
		{
			vue.afficherMainUtiliser();
			/*
			LinkedList<CarteAction> listeC = joueur.getMainDuJoueur().getMainDuJoueur();
			Iterator <CarteAction> iteC = listeC.iterator();
			while (iteC.hasNext())
			{
				CarteAction carte = iteC.next();
				vue.changerCarteSansBouton(carte, listeC.indexOf(carte));
			}
			*/
		}
		//les 3 lignes suivantes servent à désactiver les autre bouton
		frame.getContentPane().getComponent(0).setEnabled(false);
		frame.getContentPane().getComponent(1).setEnabled(false);
		frame.getContentPane().getComponent(2).setEnabled(false);
		//frame.dispose();
	}

}
