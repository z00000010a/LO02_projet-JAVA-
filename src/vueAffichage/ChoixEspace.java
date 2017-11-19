package vueAffichage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;

import java.awt.Dimension;
/**
 * Un fenetre qui va contenir tout les VueEspace de chaque joueur, ainsi qu'un VueStatJoueur et un VueEspaceCentral.
 * Elle les stock dans un JTabbedPane, ce qui permet de naviguer entre ces vues avec simplicité. Elle est implémenté 
 * avec le patron de conception Singleton.
 *@see vueAffichage.VueEspace
 *@see vueAffichage.VueStatJoueur
 *@see vueAffichage.VueEspaceCentral
 */
public class ChoixEspace extends JFrame 
{	/**
	* Un attribut contenant les joueur, utilisé pour créer afficher les VueEspace qui sont contenu dans ces joueurs
	* 
	*/
	private Joueur [] lesJoueurs;
	/**
	 * La vue des statistiques du joueur humain
	 */
	private VueStatJoueur vueStatJoueur;

	/**
	 * Ce constructeur va créer la fenètre principale et appeler la méthode initier avec comme arguments toutes
	 * les vues des espaces de chaque joueur de la partie.
	 * @param tabJ Les joueur dont on va récupérer l'attribut EspaceJoueur
	 * @see vueAffichage.ChoixEspace#initier(VueEspaceCentral, VueEspace[])
	 */
	private ChoixEspace ( Joueur [] tabJ)
	{
		super("Choississer votre espace");
		this.lesJoueurs = tabJ;
		//this.VueDesJoueurs = new VueEspace[lesJoueurs.length];
		this.setTitle("Pandocréon Divinae : Le jeu des luttes divines");
		this.setSize(new Dimension(1920,680));
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		this.setResizable(true);
		this.setVisible(true);
		VueEspace [] tabEspaceJ = new VueEspace[tabJ.length]; //initialise tab qui stock les vue des J
		for (int i = 0; i < tabJ.length; i++)
		{
			tabEspaceJ[i]=tabJ[i].getVueEspace();
		}
		initier(CarteCentreTable.getCentreTable().getVueCentreTable(), tabEspaceJ);
	}
	/**
	 * L'instance de ce singleton
	 */
	private static ChoixEspace instanceChoixEspace;
	/**
	 * La méthode pour obtenir l'instance
	 * @param tabJ les joueurs dont on souhaite figurer l'espace
	 * @return	l'instance de cette classe
	 */
	public static ChoixEspace getInstanceChoixEspace(Joueur [] tabJ)
	{
		if (instanceChoixEspace == null)
		{
			instanceChoixEspace = new ChoixEspace(tabJ);
		}
		return instanceChoixEspace;
	}
	
	/**
	 * Cette méthode va remplir la fenetre créer par le constructeur. Elle va stocker ces VueEspace dans un JTabbedPane
	 * @param vueEspaceCentral
	 * @param tabEspaceJ
	 * @see vueAffichage.ChoixEspace#ChoixEspace(Joueur[])
	 */
	public void initier(VueEspaceCentral vueEspaceCentral, VueEspace [] tabEspaceJ)
	{
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		JPanel panneau = new JPanel();
		Joueur j=null;
		for (int i = 0; i < lesJoueurs.length; i++) 
		{
			if (lesJoueurs[i] instanceof JoueurPhysique)
			{
				j = lesJoueurs[i];
			}
		}
		onglets.addTab("Vos données", ((JoueurPhysique) j).getVueStatJoueur());
		this.vueStatJoueur=((JoueurPhysique) j).getVueStatJoueur();
		onglets.addTab("Espace central", vueEspaceCentral);
		for (int i = 0; i < tabEspaceJ.length; i++) 
		{
			if (tabEspaceJ[i].getJoueur() instanceof JoueurPhysique)
			{
				tabEspaceJ[i].setNom("Votre espace");
			}
			onglets.addTab(tabEspaceJ[i].getNom(), tabEspaceJ[i]);
		}
		onglets.setOpaque(true);
		panneau.add(onglets);
		this.getContentPane().add(panneau, BorderLayout.WEST);
		this.setVisible(true);
		
		this.validate();
	}
	
	/**
	 * Un méthode qui va être appelé par les VueEspace lorsqu'ils auront besoin de se mettre à jour. Automatiquement
	 * appelée par la méthode update de ces derniers.
	 * @see vueAffichage.VueEspace#update(Observable, Object)
	 */
	public void rafraichirSimple()
	{
		this.validate(); 
	}


	public VueStatJoueur getVueStatJoueur() {
		return vueStatJoueur;
	}


	public void setVueStatJoueur(VueStatJoueur vueStatJoueur) {
		this.vueStatJoueur = vueStatJoueur;
	}
}
