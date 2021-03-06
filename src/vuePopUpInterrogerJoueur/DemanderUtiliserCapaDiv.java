package vuePopUpInterrogerJoueur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;

import Test.Test;
import modelCarte.Divinite;
import modelJoueur.Joueur;
import vueAction.ActionKillThreadFenetreInterrogeJoueur;
import vueAction.ActionUtiliserCapaDiv;
/**
 * Cette classe permet de demander au joueur si il veut utiliser sa capacit� divine. Le fait qu'elle implemente runnable permet de stopper le thread 
 * principal, de faire les actions 関entuelle demander par le joueur puis de reprendre le thread principal.
 *
 */
public class DemanderUtiliserCapaDiv implements Runnable
{
	private static Thread t;
	private static Divinite div;
	/**
	 * Cr閑r une instance avec la divinit� du joueur humain en attribut
	 * @param divi la divinit� dont on souhaite demander au joueur si il souhaite utiliser sa capacit�
	 */
	public DemanderUtiliserCapaDiv(Divinite divi) 
	{
		this.div=divi;
	}
	@Override
	/**
	 * La m閠hode appel la m閠hode demander() de cette classe.
	 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCapaDiv#demander(t)
	 */
	public void run() 
	{
		try {
			demander(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Cette m閠hode est synchronis� sur le l'objet Test.VerrouThread sur lequel toutes les m閠hodes runnable sont synchronis�, ce qui permet de 
	 * ne pas avoir plusieurs thread qui s'閤閏ute en m阭e temps. L'action ActionUtiliserCapaDiv lancera la capacit� de la divinit� du joueur si 
	 * il choisit de le faire. 
	 * @param t Permet de le transmetre � l'action ActionKillThreadFenetreInterrogeJoueur , qui elle interrompera le thread et fermera la fenetre
	 * @throws InterruptedException
	 * @see vueAction.ActionKillThreadFenetreInterrogeJoueur
	 */
	private static void demander(Thread t) throws InterruptedException
	{	
		synchronized (Test.getVerrouThread())
		{
			//cr閍tion de la fen鑤re qui va acceuillir les boutons
			JFrame frame = new JFrame("Voulez-vous utiliser votre capacité divine ?");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2, dim.height/2);
			frame.setLayout(new FlowLayout());
			JButton boutonOui = new JButton ("Utiliser ma capacité");
			JButton boutonNon = new JButton ("Ne pas utiliser ma capacité");
			boutonOui.addActionListener(new ActionUtiliserCapaDiv(div));
			boutonOui.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t, frame));
			boutonNon.addActionListener(new ActionKillThreadFenetreInterrogeJoueur(t, frame));
			frame.add(boutonOui);
			frame.add(boutonNon);
			frame.setDefaultCloseOperation(0);
			frame.pack();
			frame.setVisible(true);
		}
	}
	
	
	
	
	
	
	public static Thread getT() {
		return t;
	}
	public static void setT(Thread t) {
		DemanderUtiliserCapaDiv.t = t;
	}
	public static Divinite getDiv() {
		return div;
	}
	public static void setDiv(Divinite dive) {
		div = dive;
	}
	
	
}
