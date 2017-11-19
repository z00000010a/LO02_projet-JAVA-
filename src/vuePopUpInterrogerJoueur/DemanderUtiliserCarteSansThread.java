package vuePopUpInterrogerJoueur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Test.Test;
import modelCarte.CarteAction;
import modelJoueur.Joueur;
import vueAction.ActionFermeFenetre;
import vueBouton.BoutonNumJoueur;
/**
 * Cette classe permet d'interroger le joueur pendant qu'il joue sa phase. Ses m�thodes ne sont pas synchronis�es car
 * elle doivent �tre appel� pendant la phase du joueur humain, un moment ou le d�roulement de la partie est fig�, 
 * en attente de la fin du tour du joueur humain.
 * Cette classe est instanci� par toutes les carte Croyant et GuideSpirituel quand elles sont sacrifier et les carte DeusEx quand
 * elles sont utilis�es.
 *
 */
public class DemanderUtiliserCarteSansThread 
{
	/**
	 * Le choix du joueur, modifi� par les BoutonNumJoueur. Et r�cup�r� gr�ce aux getters par les cartes qui instancie cette classe pour 
	 * r�cup�rer le choix du joueur.
	 */
	private static  int choixJoueur;
	/**
	 * La linkedList des joueurs, permet de cr�er autant de BoutonJoueur qu'il y a de joueur � cibler pour la 
	 * capacit� de la carte qui � instanci�e la classe. Sp�cifi� par le constructeur.
	 */
	private  LinkedList<Joueur> listeJ;
	/**
	 * La carte qui � instanci� la classe. Utilis� par la m�thode demander(). Sp�cifi�e par le constructeur.
	 */
	private static  CarteAction carte;
	/**
	 * Le constructeur de la classe.
	 * @param listeJoueur La LinkedList de joueur que l'on souhaite proposer au joueur pour qu'il d�signe l'un d'entre eux
	 * @param carte2	La CarteAction qui � instanci� l'objet. Elle sera utilis�e par la m�thode appelerSacrifice() de 
	 * cette classe. 
	 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread#appelerSacrifice()
	 */
	public DemanderUtiliserCarteSansThread(LinkedList <Joueur> listeJoueur, CarteAction carte2) 
	{
		this.listeJ=listeJoueur;
		carte=carte2;
	}
	
	/**
	 * Cette m�thode va cr�er autant de bouton qu'il y a de joueur dans l'attribut listeJ de cette classe et va ajouter
	 * un bouton Valider pour que le joueur valide son choix. La validation entrainera l'appel de la m�thode AppelerSacrifice().
	 * Elle renvoie un pop up d'erreur si listeJ est vide.
	 */
	public void demander()
	{
			if (listeJ.size()!=0)
			{
				DemanderUtiliserCarteSansThread.choixJoueur=-1;
				JFrame frame = new JFrame("Choisissez le joueur que vous d�sirer ciblez :");
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width/2, dim.height/2 + 100);
				frame.setLayout(new FlowLayout());
				Iterator <Joueur> iteBouton = listeJ.iterator();
				while (iteBouton.hasNext())
				{
					Joueur j = iteBouton.next();
					frame.getContentPane().add(new BoutonNumJoueur(j.getNumJoueur()));
				}
				JButton valider = new JButton("Valider votre choix");
				valider.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent e) {DemanderUtiliserCarteSansThread.appelerSacrifice();}} );
				valider.addActionListener(new ActionFermeFenetre(frame));
				frame.getContentPane().add(valider);
				frame.pack();
				this.listeJ=null;
				frame.setDefaultCloseOperation(0);
				frame.setVisible(true);
			}
			else
			{
				JFrame frameErreur = new JFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
				JOptionPane.showMessageDialog(frameErreur , 
						"Vous ne pouvez pas effectuer ceci"
						  +"\n ",
				         "Non.",
				         JOptionPane.WARNING_MESSAGE);
			}

			
		
	}
	/**
	 * Cette m�thode va appeler la m�thode sacrifier(int i) de la carte (Note : cette m�thode est redefini pour chaque carte). Avec en argument le num�ro du joueur que le joueur 
	 * humain souhaite cibler avec cette carte.
	 * @see modelCarte.CarteAction#sacrifier(int)
	 */
	public static  void appelerSacrifice()
	{
		carte.sacrifier(DemanderUtiliserCarteSansThread.getChoixJoueur());
	}


	public static int getChoixJoueur() {
		return choixJoueur;
	}


	public static void setChoixJoueur(int choixJoueur) {
		DemanderUtiliserCarteSansThread.choixJoueur = choixJoueur;
	}


	public LinkedList<Joueur> getListeJ() {
		return listeJ;
	}


	public  void setListeJ(LinkedList<Joueur> listeJ) {
		this.listeJ = listeJ;
	}
	

}
