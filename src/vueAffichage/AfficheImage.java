package vueAffichage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import vueAction.ActionDefausserCarte;
import vueAction.ActionPoserApocalypse;
import vueAction.ActionPoserCroyant;
import vueAction.ActionPoserGS;
import vueAction.ActionSacrifierCarte;
import vueBouton.BoutonCarte;
/**
 * Un composant qui h�rite de JPanel et qui permet d'afficher l'image d'une carte. Il fourni �galement plusieurs m�thode
 * permettant de modifier ce composant pour permettre de lui ajouter des boutons, de changer ou supprimer ces boutons...
 * 
 *
 */
public class AfficheImage extends JPanel  {
	/**
	 * L'image de la carte qui doit �tre affich�e
	 */
	private BufferedImage image;
	/**
	 * Le JLabel qui contiendra l'image
	 */
	JLabel label;
	/**
	 * La carte qui devra �tre affich�. Permet de l'envoyer � plusieurs m�thodes et actions utilis�es dans cette classe.
	 */
	CarteAction carte;
	/**
	 * Un des deux BoutonCarte qui seront ajout�/supprimer/modifi� tout au long de la vie du composant
	 */
	BoutonCarte b1=null;
	/**
	 * Un des deux BoutonCarte qui seront ajout�/supprimer/modifi� tout au long de la vie du composant
	 */
	BoutonCarte b2=null;
	/**
	 * Un premier constructeur de cette classe. Elle va cr�er un composant sans bouton avec seulement une image
	 * Utilis� principalement pour l'affichage de la main du joueur par VueMainJoueurP
	 * @param nomFichier Le nom de l'image � charg�e. ATTENTION si l'image n'est pas dans le m�me fichier que le dossier
	 * source on doit mettre le chemin complet vers ce fichier.
	 * @see vueAffichage.VueMainJoueurP
	 */
	public AfficheImage (String nomFichier)
	{
		this.setPreferredSize(new Dimension(272 , 386));
		
		try {
			image = ImageIO.read(new File (nomFichier));
		}catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("erreur sur le chargement de l'image");
		}
		ImageIcon icon = new ImageIcon(image);
		this.label = new JLabel(icon);
		label.setVisible(true);
		add(label,0);
		setComponentZOrder(label,0);
		this.setVisible(true);
	}
	/**
	 * Un second constructeur qui permet de cr�er ce composant directement � partir de l'image. Le composant cr�er ne 
	 * disposera d'aucun bouton.
	 * @param img L'image que l'on souhaite afficher
	 */
	public AfficheImage (Image img)             
	{
		this.setPreferredSize(new Dimension(272 , 386));
		image = (BufferedImage) img;
		ImageIcon icon = new ImageIcon(image);
		this.label = new JLabel(icon);
		add(label,0);
		setComponentZOrder(label,0);
		this.setVisible(true);
	}
	/**
	 * Cette m�thode est appell� par la VueMainJoueurP pour renouveler l'affichage des carte pendant le tour du joueur 
	 * en fonction du choix de celui-ci. Cette m�thode va ,pour les carte Croyant et Guide Spirituel, ajouter des boutons
	 * poser avec les m�thodes correspondantes. Et pour les carte Apocalypse et DeusEx des bouton jouer avec les action 
	 * correspondantes. 
	 * @param carte La carte � laquelle on souhaite ajouter des boutons, en fonction de son type on lui ajoutera un bouton avec l'action 
	 * correspondante. ATTENTION si l'argument est nul on charge l'image default.jpg qui repr�sente un emplacement de carte vide. On retire
	 * �galement tous les boutons qu'il pouvait y avoir sur cette carte.
	 */
	public void changerCarteDeMain (CarteAction carte)
	{	
		if (carte != null)
		{
			this.carte = carte;
			Image img = carte.getImage();
			image = (BufferedImage) img;
			//ImageIcon icon = new ImageIcon(img);
			ImageIcon icon = new ImageIcon(image); 
			icon.getImage().flush();
			//System.out.println("Il y a "+this.getComponents().length+" composants dans ce affiche image."); //POUR DEBUGAGE TEST
			for (int i = 0; i < this.getComponents().length; i++) //va it�rer tout les composant pour trouver le JLabel et changer l'image
			{
				if (this.getComponent(i) instanceof JLabel)
				{
					((JLabel) this.getComponent(i)).setIcon(icon);
					//System.out.println("L'INDEX DU JLABEL EST "+i+"\n");
				}
			}
			int ind=0; //it�rera sur le tab des composants
			while (this.getComponents().length != 1) //retire tout les boutons
			{
				if (this.getComponent(ind) instanceof BoutonCarte)
				{
					this.remove(ind);	
				}
				ind++;
			}
			if (carte instanceof Croyant )
			{
				b1 = new BoutonCarte("Poser",this.carte);
				b1.addActionListener(new ActionPoserCroyant((Croyant) carte));
				b1.setVisible(true);
				add(b1,1);
				setComponentZOrder(b1,0);
				this.revalidate();
			}
			else if (carte instanceof GuideSpirituel)
			{
				b1 = new BoutonCarte("Poser",this.carte);
				b1.addActionListener(new ActionPoserGS((GuideSpirituel) carte)); 
				b1.setVisible(true);
				add(b1,1);
				setComponentZOrder(b1,0);
			}
			else if (carte instanceof Apocalypse )
			{
				BoutonCarte b1 = new BoutonCarte("Utiliser", this.carte);
				b1.addActionListener(new ActionPoserApocalypse((Apocalypse) carte)); 
				b1.setVisible(true);
				add(b1,1);
				setComponentZOrder(b1,0);
				this.revalidate();
			}
			else if (carte instanceof DeusEx )
			{
				BoutonCarte b1 = new BoutonCarte("Utiliser", this.carte);
				b1.addActionListener(new ActionSacrifierCarte(carte)); 
				b1.setVisible(true);
				add(b1,1);
				setComponentZOrder(b1,0);
			}
			this.revalidate();
		}
		else //si la carte en arg est null, on met l'image default et on ne met pas de bouton dans le panel ET on supprimer les pr�c�dents
		{

			try {
				image = ImageIO.read(new File ("default.jpg"));
			}catch (IOException ie) {
				ie.printStackTrace();
				System.out.println("erreur sur le chargement de l'image");
			}
			ImageIcon icon = new ImageIcon(image);
			icon.getImage().flush();
			System.out.println("Il y a "+this.getComponentCount()+" composants");
			((JLabel) this.getComponent(2)).setIcon(icon); //l'index du JLabel est maintenant 2
			if (this.carte instanceof Croyant || this.carte instanceof GuideSpirituel)
			{
				remove(0);
			}
			else if (this.carte instanceof Apocalypse || this.carte instanceof DeusEx)
			{
				remove(0);
			}


			this.carte = carte;
		}
		this.getComponent(0).revalidate();
	}
	/**
	 *  M�thode qui va enlever tout les boutons du composant puis en rajouter un unique : d�fausser. 
	 *  Ce bouton est impl�menter ave toutes les m�thodes pour d�fausser la carte.
	 * @param carte 
	 */
	public void changerCarteDefausser(CarteAction carte)
	{
		this.carte = carte;
		Image img = carte.getImage();
		image = (BufferedImage) img;
		ImageIcon icon = new ImageIcon(image); 
		icon.getImage().flush();
		for (int i = 0; i < this.getComponents().length; i++) //va it�rer tout les composant pour trouver le JLabel et changer l'image
		{
			if (this.getComponent(i) instanceof JLabel)
			{
				((JLabel) this.getComponent(i)).setIcon(icon);
			}
		}
		int ind=0; //it�rera sur le tab des composants
		while (this.getComponents().length != 1) //retire tout les boutons
		{
			if (this.getComponent(ind) instanceof BoutonCarte)
			{
				this.remove(ind);	
			}
			ind++;
		}
		b1 = new BoutonCarte("D�fausser",this.carte);
		b1.addActionListener(new ActionDefausserCarte(carte));
		b1.setVisible(true);
		add(b1,1);
		setComponentZOrder(b1,0);	
	}
	/**
	 *Cette m�thode est appel� par l'espace du joueur humain : VueEspace. Elle permet de donner au composant un boutons appropri�s
	 *en fonction de la carte qui est donn�e en argument. Ce bouton est un bouton sacrifier, avec les m�thodes ad�quates en fonction
	 *du type de la carte.
	 * @param carte la carte � laquelle on doit attribuer des boutons.
	 * @see vueAffichage.VueEspace
	 */
	public void changerCarteDansEspace(CarteAction carte)
	{
		this.carte = carte;
		Image img = carte.getImage();
		image = (BufferedImage) img;
		//ImageIcon icon = new ImageIcon(img);
		ImageIcon icon = new ImageIcon(image); 
		icon.getImage().flush();
		//System.out.println("Il y a "+this.getComponents().length+" composants dans ce affiche image."); //POUR DEBUGAGE TEST
		for (int i = 0; i < this.getComponents().length; i++) //va it�rer tout les composant pour trouver le JLabel et changer l'image
		{
			if (this.getComponent(i) instanceof JLabel)
			{
				((JLabel) this.getComponent(i)).setIcon(icon);
				//System.out.println("L'INDEX DU JLABEL EST "+i+"\n");
			}
		}
		int ind=0; //it�rera sur le tab des composants
		while (this.getComponents().length != 1) //retire tout les boutons
		{
			if (this.getComponent(ind) instanceof BoutonCarte)
			{
				this.remove(ind);	
			}
			ind++;
		}
		if (carte instanceof Croyant || carte instanceof GuideSpirituel)
		{
			b1 = new BoutonCarte("Sacrifier",this.carte);
			b1.addActionListener(new ActionSacrifierCarte(carte)); 
			b1.setVisible(true);
			add(b1,1);
			setComponentZOrder(b1,0);
			/* PAS DE BOUTON SACRIFIER ICI
			b2 = new BoutonCarte("Sacrifier");
			b2.setVisible(true);
			add(b2,2);
			setComponentZOrder(b2,0);
			 */
			this.revalidate();
		}
		this.revalidate();
	}
	/**
	 * Cette m�thode permet d'afficher les cartes de l'espace du joueur sans aucun bouton (si il a choisit de 
	 * piocher ou bien si ce n'est pas son tour). Elle est appel� par VueMainJoueurP
	 * @param carte la carte que l'on souhaite afficher sans bouton
	 * @see vueAffichage.VueMainJoueurP
	 */
	public void changerCarteSansBouton (CarteAction carte)
	{
		this.carte = carte;
		Image img = carte.getImage();
		image = (BufferedImage) img;
		//ImageIcon icon = new ImageIcon(img);
		ImageIcon icon = new ImageIcon(image); 
		icon.getImage().flush();
		for (int i = 0; i < this.getComponents().length; i++) 
		{
			if (this.getComponents()[i] instanceof JLabel)
			{
				((JLabel) this.getComponent(i)).setIcon(icon);
			}		
		}
		
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	public CarteAction getCarte() {
		return carte;
	}

	public void setCarte(CarteAction carte) {
		this.carte = carte;
	}

	
}
