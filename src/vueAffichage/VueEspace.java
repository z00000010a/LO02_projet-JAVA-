package vueAffichage;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelJoueur.EspaceJoueur;
import modelJoueur.Joueur;
/**
 * Cette classe permet de montrer l'espace d'un joueur virtuel ou r�el. Elle impl�mente Observer et est l'observeur
 * du joueur duquel elle affiche l'espace. Elle est instanci� lors de la cr�ation du joueur. 
 * @see modelJoueur.JoueurVirtual#constructeurJoueurAvecAssociation(int)
 * @see modelJoueur.JoueurPhysique#constructeurJoueurAvecAssociation(int, String)
 */
public class VueEspace extends JPanel implements Observer

{ 	/**
	* Le nom du composant.
	*/
	String nom= null;
	/**
	 * Le joueur que la classe observe. Initialis� par le constructeur.
	 */
 	Joueur joueur= null;
 	/**
 	 * L'espace du joueur que la classe observe, initialis� par le constructeur.
 	 */
 	EspaceJoueur espace= null;
 	/**
 	 * Un attribut de l'espace du joueur que la classe observe, initialis� par le constructeur. Mis en attribut pour des
 	 * facilit� de programmation.
 	 */
 	LinkedList<Croyant> carteCro= new LinkedList<Croyant>();
 	/**
 	 * Un attribut de l'espace du joueur que la classe observe, initialis� par le constructeur. Mis en attribut pour des
 	 * facilit� de programmation.
 	 */
 	LinkedList <GuideSpirituel> carteGS= new LinkedList<GuideSpirituel>();
 	/**
 	 * Une LinkedList de la vue des cartes. Initialis�e lors de l'appel du constructeur.
 	 */
 	LinkedList <AfficheImage> listeVueCarte = new LinkedList<AfficheImage>();   //utilis� plus tard pour acc�der � ces composants
	 /**
	  * Le constructeur de la classe, il va initialiser la listeVueCarte. Puis il va parcourir les deux linkedList de Croyant et de Guide Spirituel et 
	  * va mettre cote � cote les Guide et les Croyant qui sont guid� par lui.
	  * @param numJ le num�ro du joueur pour qu'il puisse d�finir son nom comme Joueur n� numJ
	  * @param joueur	Le joueur qu'il observe
	  */
	public VueEspace (int numJ , Joueur joueur)
	{	//MaJ des variables concernant le J
		this.nom= "Joueur n�"+numJ;
		this.joueur = joueur;
		this.espace = joueur.getEspaceDuJoueur();
		this.carteCro = espace.getListeDesCroyants();
		this.carteGS = espace.getListeDesGuides();
		JLabel labelTxt = new JLabel("Espace du joueur n�"+numJ);
		if (numJ==0)
		{
			labelTxt = new JLabel("Votre espace");
		}
		Iterator <GuideSpirituel> iteGS = carteGS.iterator();
		while (iteGS.hasNext())
		{
			GuideSpirituel guide = iteGS.next();
			AfficheImage image = new AfficheImage ("default.jpg");			////////////////on a mis �a car c'est le seul constructeur qui marche
			if(numJ != 0)
			{
				image.changerCarteSansBouton(guide); //on utilise ceci pour charger l'image
			}
			else if (numJ == 0)
			{
				image.changerCarteDansEspace(guide);//ajoute des boutons sur la carte si c'est l'espace du JPhys
			}
			image.setCarte(guide);		//ajoute la carte dasn la vue
			listeVueCarte.add(image); //cr�er et ajoute l'affiche image � partir de l'image du GS
		}
		
		Iterator <Croyant> iteCro = carteCro.iterator();
		while (iteCro.hasNext())
		{
			Croyant croyant = iteCro.next();
			AfficheImage afficheImage = new AfficheImage ("default.jpg");
			if(numJ != 0)
			{
				afficheImage.changerCarteSansBouton(croyant); //on utilise ceci pour charger l'image
			}
			else if (numJ == 0)
			{
				afficheImage.changerCarteDansEspace(croyant);
			}
			afficheImage.setCarte(croyant);	//ajoute la carte dasn la vue
			listeVueCarte.add(afficheImage); //cr�er et ajoute l'affiche image � partir de l'image du croyant
		}
		
		
		JPanel container = new JPanel(); //Pour faire de ceci un scollpane on met dans 1er temps tout les afficheImage dans ce panel
		
		//on va it�rer les 2 collection de mani�re � ce que les GS soit en premier et les Cro qui y sont attach� sont apr�s
		Iterator <AfficheImage> ite1 = listeVueCarte.iterator();
		Iterator <AfficheImage> ite2; 
		while (ite1.hasNext())
		{
			AfficheImage vue = ite1.next();
			if (vue.getCarte() instanceof GuideSpirituel)	//si c'est un GS, on va parcourir toute la collec pour afficher les Cro qui lui sont attach�
			{
				vue.setVisible(true);
				container.add(vue); //ajoute le guide >> normalement par d�faut c'est flowLayout
				ite2 = listeVueCarte.iterator(); //r�cr�er un iterateur � chaque fois >>> le reset
				while (ite2.hasNext())
				{
					AfficheImage vueCro = ite2.next();
					if (vueCro.getCarte() instanceof Croyant &&	((Croyant) vueCro.getCarte()).getGuidePar().equals(vue.getCarte())) // test si la prochaine vue est celle d'un croyant et que croyant est attach� au guide sur lequel on it�re
					{
						vueCro.setVisible(true);
						container.add(vueCro);
					}
				}
			}
		}
		//A ce stade on a ajout� tout les afficheImage
		JScrollPane scrPane = new JScrollPane(container); //cr�er le scroll pane avec tout 
		add(scrPane);
	}
	/**
	 * Cette m�thode permet d'enlever tout les composants de cet objet et de tout r�cr�er avec les nouvelles donn�es
	 * Elle fait le m�me travail que le constructeur. A l'excepetion pr�s qu'elle ne cr��er pas de composants.
	 * Elle est appel� par la m�thode update(Observable, Object).
	 * @param espace L'espace du joueur observ�, on va en tirer les informations n�c�ssaires au rafraichissement
	 * de cette vue.
	 * @see vueAffichage.VueEspace#update(Observable, Object)
	 */
	public void rafraichir(EspaceJoueur espace)
	{
		this.espace = espace;
		
		this.removeAll(); //enl�ve tout les autres afficge images
		
		this.carteCro = espace.getListeDesCroyants();
		this.carteGS = espace.getListeDesGuides();
		this.listeVueCarte = new LinkedList<AfficheImage>(); //remet � z�ro la linkedList
		//Cr�ation de affiche image pour chaque carte GS et  ajout dans la liste des Vue
		Iterator <GuideSpirituel> iteGS = carteGS.iterator();
		while (iteGS.hasNext())
		{
			GuideSpirituel guide = iteGS.next();
			AfficheImage image = new AfficheImage ("default.jpg");			////////////////on a mis �a car c'est le seul constructeur qui marche
			if(this.joueur.getNumJoueur() != 0)
			{
				image.changerCarteSansBouton(guide); //on utilise ceci pour charger l'image
			}
			else if (this.joueur.getNumJoueur() == 0)
			{
				image.changerCarteDansEspace(guide);//ajoute des boutons sur la carte si c'est l'espace du JPhys
			}
			image.setCarte(guide);		//ajoute la carte dasn la vue
			listeVueCarte.add(image); //cr�er et ajoute l'affiche image � partir de l'image du GS
		}
		Iterator <Croyant> iteCro = carteCro.iterator();
		while (iteCro.hasNext())
		{
			Croyant croyant = iteCro.next();
			AfficheImage afficheImage = new AfficheImage ("default.jpg");
			if(this.joueur.getNumJoueur() != 0)
			{
				afficheImage.changerCarteSansBouton(croyant); //on utilise ceci pour charger l'image
			}
			else if (this.joueur.getNumJoueur() == 0)
			{
				afficheImage.changerCarteDansEspace(croyant);
			}
			afficheImage.setCarte(croyant);	//ajoute la carte dasn la vue
			listeVueCarte.add(afficheImage); //cr�er et ajoute l'affiche image � partir de l'image du croyant
		}
		
		JPanel container = new JPanel(); //Pour faire de ceci un scollpane on met dans 1er temps tout les afficheImage dans ce panel
		
		//on va it�rer les 2 collection de mani�re � ce que les GS soit en premier et les Cro qui y sont attach� sont apr�s
		Iterator <AfficheImage> ite1 = listeVueCarte.iterator();
		Iterator <AfficheImage> ite2; 
		while (ite1.hasNext())
		{
			
			AfficheImage vue = ite1.next();
			if (vue.getCarte() instanceof GuideSpirituel)	//si c'est un GS, on va parcourir toute la collec pour afficher les Cro qui lui sont attach�
			{
				vue.setVisible(true);
				this.add(vue); //ajoute le guide >> normalement par d�faut c'est flowLayout
				ite2 = listeVueCarte.iterator(); //r�cr�er un iterateur � chaque fois >>> le reset
				while (ite2.hasNext())
				{
					AfficheImage vueCro = ite2.next();
					if (vueCro.getCarte() instanceof Croyant &&	((Croyant) vueCro.getCarte()).getGuidePar().equals(vue.getCarte())) // test si la prochaine vue est celle d'un croyant et que croyant est attach� au guide sur lequel on it�re
					{
						vueCro.setVisible(true);
						this.add(vueCro);
					}
				}
			}
		}
		JScrollPane scrPane = new JScrollPane(container); //cr�er le scroll pane avec tout 
		add(scrPane);
	}
	
	@Override
	/**
	 *	Est appel� par la m�thode notifyObserver() du joueur que l'objet observe. 
	 *	Appelle la m�thode Rafraichir(EspaceJoueur espace).
	 *	Appelle aussi la m�thode RafaichirSimple() du singleton ChoixEspace
	 *	@see vueAffichage.VueEspace#Rafraichir(EspaceJoueur espace)
	 *
	 */
	public void update(Observable o, Object arg) 
	{
		rafraichir( ((Joueur) o).getEspaceDuJoueur());
		ChoixEspace.getInstanceChoixEspace(null).rafraichirSimple(); 
	}
	
	//=================================================================================================
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	
}
