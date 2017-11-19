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
 * Cette classe permet de montrer l'espace d'un joueur virtuel ou réel. Elle implémente Observer et est l'observeur
 * du joueur duquel elle affiche l'espace. Elle est instancié lors de la création du joueur. 
 * @see modelJoueur.JoueurVirtual#constructeurJoueurAvecAssociation(int)
 * @see modelJoueur.JoueurPhysique#constructeurJoueurAvecAssociation(int, String)
 */
public class VueEspace extends JPanel implements Observer

{ 	/**
	* Le nom du composant.
	*/
	String nom= null;
	/**
	 * Le joueur que la classe observe. Initialisé par le constructeur.
	 */
 	Joueur joueur= null;
 	/**
 	 * L'espace du joueur que la classe observe, initialisé par le constructeur.
 	 */
 	EspaceJoueur espace= null;
 	/**
 	 * Un attribut de l'espace du joueur que la classe observe, initialisé par le constructeur. Mis en attribut pour des
 	 * facilité de programmation.
 	 */
 	LinkedList<Croyant> carteCro= new LinkedList<Croyant>();
 	/**
 	 * Un attribut de l'espace du joueur que la classe observe, initialisé par le constructeur. Mis en attribut pour des
 	 * facilité de programmation.
 	 */
 	LinkedList <GuideSpirituel> carteGS= new LinkedList<GuideSpirituel>();
 	/**
 	 * Une LinkedList de la vue des cartes. Initialisée lors de l'appel du constructeur.
 	 */
 	LinkedList <AfficheImage> listeVueCarte = new LinkedList<AfficheImage>();   //utilisé plus tard pour accéder à ces composants
	 /**
	  * Le constructeur de la classe, il va initialiser la listeVueCarte. Puis il va parcourir les deux linkedList de Croyant et de Guide Spirituel et 
	  * va mettre cote à cote les Guide et les Croyant qui sont guidé par lui.
	  * @param numJ le numéro du joueur pour qu'il puisse définir son nom comme Joueur n° numJ
	  * @param joueur	Le joueur qu'il observe
	  */
	public VueEspace (int numJ , Joueur joueur)
	{	//MaJ des variables concernant le J
		this.nom= "Joueur n°"+numJ;
		this.joueur = joueur;
		this.espace = joueur.getEspaceDuJoueur();
		this.carteCro = espace.getListeDesCroyants();
		this.carteGS = espace.getListeDesGuides();
		JLabel labelTxt = new JLabel("Espace du joueur n°"+numJ);
		if (numJ==0)
		{
			labelTxt = new JLabel("Votre espace");
		}
		Iterator <GuideSpirituel> iteGS = carteGS.iterator();
		while (iteGS.hasNext())
		{
			GuideSpirituel guide = iteGS.next();
			AfficheImage image = new AfficheImage ("default.jpg");			////////////////on a mis ça car c'est le seul constructeur qui marche
			if(numJ != 0)
			{
				image.changerCarteSansBouton(guide); //on utilise ceci pour charger l'image
			}
			else if (numJ == 0)
			{
				image.changerCarteDansEspace(guide);//ajoute des boutons sur la carte si c'est l'espace du JPhys
			}
			image.setCarte(guide);		//ajoute la carte dasn la vue
			listeVueCarte.add(image); //créer et ajoute l'affiche image à partir de l'image du GS
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
			listeVueCarte.add(afficheImage); //créer et ajoute l'affiche image à partir de l'image du croyant
		}
		
		
		JPanel container = new JPanel(); //Pour faire de ceci un scollpane on met dans 1er temps tout les afficheImage dans ce panel
		
		//on va itérer les 2 collection de manière à ce que les GS soit en premier et les Cro qui y sont attaché sont après
		Iterator <AfficheImage> ite1 = listeVueCarte.iterator();
		Iterator <AfficheImage> ite2; 
		while (ite1.hasNext())
		{
			AfficheImage vue = ite1.next();
			if (vue.getCarte() instanceof GuideSpirituel)	//si c'est un GS, on va parcourir toute la collec pour afficher les Cro qui lui sont attaché
			{
				vue.setVisible(true);
				container.add(vue); //ajoute le guide >> normalement par défaut c'est flowLayout
				ite2 = listeVueCarte.iterator(); //récréer un iterateur à chaque fois >>> le reset
				while (ite2.hasNext())
				{
					AfficheImage vueCro = ite2.next();
					if (vueCro.getCarte() instanceof Croyant &&	((Croyant) vueCro.getCarte()).getGuidePar().equals(vue.getCarte())) // test si la prochaine vue est celle d'un croyant et que croyant est attaché au guide sur lequel on itére
					{
						vueCro.setVisible(true);
						container.add(vueCro);
					}
				}
			}
		}
		//A ce stade on a ajouté tout les afficheImage
		JScrollPane scrPane = new JScrollPane(container); //créer le scroll pane avec tout 
		add(scrPane);
	}
	/**
	 * Cette méthode permet d'enlever tout les composants de cet objet et de tout récréer avec les nouvelles données
	 * Elle fait le même travail que le constructeur. A l'excepetion près qu'elle ne crééer pas de composants.
	 * Elle est appelé par la méthode update(Observable, Object).
	 * @param espace L'espace du joueur observé, on va en tirer les informations nécéssaires au rafraichissement
	 * de cette vue.
	 * @see vueAffichage.VueEspace#update(Observable, Object)
	 */
	public void rafraichir(EspaceJoueur espace)
	{
		this.espace = espace;
		
		this.removeAll(); //enlève tout les autres afficge images
		
		this.carteCro = espace.getListeDesCroyants();
		this.carteGS = espace.getListeDesGuides();
		this.listeVueCarte = new LinkedList<AfficheImage>(); //remet à zéro la linkedList
		//Création de affiche image pour chaque carte GS et  ajout dans la liste des Vue
		Iterator <GuideSpirituel> iteGS = carteGS.iterator();
		while (iteGS.hasNext())
		{
			GuideSpirituel guide = iteGS.next();
			AfficheImage image = new AfficheImage ("default.jpg");			////////////////on a mis ça car c'est le seul constructeur qui marche
			if(this.joueur.getNumJoueur() != 0)
			{
				image.changerCarteSansBouton(guide); //on utilise ceci pour charger l'image
			}
			else if (this.joueur.getNumJoueur() == 0)
			{
				image.changerCarteDansEspace(guide);//ajoute des boutons sur la carte si c'est l'espace du JPhys
			}
			image.setCarte(guide);		//ajoute la carte dasn la vue
			listeVueCarte.add(image); //créer et ajoute l'affiche image à partir de l'image du GS
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
			listeVueCarte.add(afficheImage); //créer et ajoute l'affiche image à partir de l'image du croyant
		}
		
		JPanel container = new JPanel(); //Pour faire de ceci un scollpane on met dans 1er temps tout les afficheImage dans ce panel
		
		//on va itérer les 2 collection de manière à ce que les GS soit en premier et les Cro qui y sont attaché sont après
		Iterator <AfficheImage> ite1 = listeVueCarte.iterator();
		Iterator <AfficheImage> ite2; 
		while (ite1.hasNext())
		{
			
			AfficheImage vue = ite1.next();
			if (vue.getCarte() instanceof GuideSpirituel)	//si c'est un GS, on va parcourir toute la collec pour afficher les Cro qui lui sont attaché
			{
				vue.setVisible(true);
				this.add(vue); //ajoute le guide >> normalement par défaut c'est flowLayout
				ite2 = listeVueCarte.iterator(); //récréer un iterateur à chaque fois >>> le reset
				while (ite2.hasNext())
				{
					AfficheImage vueCro = ite2.next();
					if (vueCro.getCarte() instanceof Croyant &&	((Croyant) vueCro.getCarte()).getGuidePar().equals(vue.getCarte())) // test si la prochaine vue est celle d'un croyant et que croyant est attaché au guide sur lequel on itére
					{
						vueCro.setVisible(true);
						this.add(vueCro);
					}
				}
			}
		}
		JScrollPane scrPane = new JScrollPane(container); //créer le scroll pane avec tout 
		add(scrPane);
	}
	
	@Override
	/**
	 *	Est appelé par la méthode notifyObserver() du joueur que l'objet observe. 
	 *	Appelle la méthode Rafraichir(EspaceJoueur espace).
	 *	Appelle aussi la méthode RafaichirSimple() du singleton ChoixEspace
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
