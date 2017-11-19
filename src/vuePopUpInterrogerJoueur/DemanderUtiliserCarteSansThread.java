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
 * Cette classe permet d'interroger le joueur pendant qu'il joue sa phase. Ses méthodes ne sont pas synchronisées car
 * elle doivent être appelé pendant la phase du joueur humain, un moment ou le déroulement de la partie est figé, 
 * en attente de la fin du tour du joueur humain.
 * Cette classe est instancié par toutes les carte Croyant et GuideSpirituel quand elles sont sacrifier et les carte DeusEx quand
 * elles sont utilisées.
 *
 */
public class DemanderUtiliserCarteSansThread 
{
	/**
	 * Le choix du joueur, modifié par les BoutonNumJoueur. Et récupéré grâce aux getters par les cartes qui instancie cette classe pour 
	 * récupérer le choix du joueur.
	 */
	private static  int choixJoueur;
	/**
	 * La linkedList des joueurs, permet de créer autant de BoutonJoueur qu'il y a de joueur à cibler pour la 
	 * capacité de la carte qui à instanciée la classe. Spécifié par le constructeur.
	 */
	private  LinkedList<Joueur> listeJ;
	/**
	 * La carte qui à instancié la classe. Utilisé par la méthode demander(). Spécifiée par le constructeur.
	 */
	private static  CarteAction carte;
	/**
	 * Le constructeur de la classe.
	 * @param listeJoueur La LinkedList de joueur que l'on souhaite proposer au joueur pour qu'il désigne l'un d'entre eux
	 * @param carte2	La CarteAction qui à instancié l'objet. Elle sera utilisée par la méthode appelerSacrifice() de 
	 * cette classe. 
	 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread#appelerSacrifice()
	 */
	public DemanderUtiliserCarteSansThread(LinkedList <Joueur> listeJoueur, CarteAction carte2) 
	{
		this.listeJ=listeJoueur;
		carte=carte2;
	}
	
	/**
	 * Cette méthode va créer autant de bouton qu'il y a de joueur dans l'attribut listeJ de cette classe et va ajouter
	 * un bouton Valider pour que le joueur valide son choix. La validation entrainera l'appel de la méthode AppelerSacrifice().
	 * Elle renvoie un pop up d'erreur si listeJ est vide.
	 */
	public void demander()
	{
			if (listeJ.size()!=0)
			{
				DemanderUtiliserCarteSansThread.choixJoueur=-1;
				JFrame frame = new JFrame("Choisissez le joueur que vous désirer ciblez :");
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
	 * Cette méthode va appeler la méthode sacrifier(int i) de la carte (Note : cette méthode est redefini pour chaque carte). Avec en argument le numéro du joueur que le joueur 
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
