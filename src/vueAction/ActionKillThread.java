package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Test.Test;
import modelDeroulementPartie.Partie;
import modelJoueur.JoueurPhysique;
import vueAffichage.AfficheImage;
import vueAffichage.VueMainJoueurP;
import vueBouton.BoutonCarte;

public class ActionKillThread implements ActionListener
{
	private Thread t;
	private JFrame frame;
	
	public ActionKillThread (Thread t, JFrame frame)
	{
		this.t=t;
		this.frame=frame;
	}
	/**
	 * Cette méthode désactive tout les bouton qui pourrait se trouver sur les cartes de la main du joueur
	 */
	public void desactiverBoutonMain()
	{	//va itérer sur tout les affiche image
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

	public void actionPerformed(ActionEvent arg0)
	{ synchronized (Test.getVerrouThread())
		{
			VueMainJoueurP.setBoutonDisabled(true);
			frame.dispose();
			desactiverBoutonMain();
			Test.getVerrouThread().notifyAll();
			//t.notifyAll();
			t.interrupt();
		}
		
	}
}
