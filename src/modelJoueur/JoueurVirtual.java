package modelJoueur;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import Test.Test;
import controller.Controler;
import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelCarteDivinite.Brewalen;
import modelCarteDivinite.Drinded;
import modelCarteDivinite.Gorpa;
import modelCarteDivinite.Gwengbelen;
import modelCarteDivinite.Killinstred;
import modelCarteDivinite.Llewella;
import modelCarteDivinite.PuiTara;
import modelCarteDivinite.Romtec;
import modelCarteDivinite.Sbingva;
import modelCarteDivinite.Yarstur;
import modelDeroulementPartie.Partie;
import modelDeroulementPartie.Pile;
import modelStrategy.PeutJouer;
import modelStrategy.StrategyInterface;
import vueAffichage.VueEspace;
import vueAffichage.VueMainJoueurP;
import vueLabel.Prevent;
import vueLabel.VueLabelDefausser;
import vueLabel.VueLabelDeusEx;
import vueLabel.VueLabelPiocher;
import vueLabel.VueLabelPoserCro;
import vueLabel.VueLabelPoserGS;
import vueLabel.VueLabelSacrifierCry;
import vuePopUpInterrogerJoueur.DemanderPrevent;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteONull;
/**
 * 
 * Cette méthode est pour l'I.A. Quand un joueur virtuel est instancié on créé un objet de cette classe.
 * Cette classes comtient les méthodes que l'I.A va utiliser pour jouer.
 * 
 * 
 * 
 */
public class JoueurVirtual extends Joueur{
	
	private StrategyInterface strategie;

	/**
	 * Quand on créé une joueur virtuel on lui attribut une divinité aléatoire. Les attributs de cette divinité sont donnée au joueur.
	 * On prends garde d'initialiser le booléen aJoue à faux.
	 * @param numJoueur
	 * Numéro du joueur virtuel, fourni par la classe tour quand elle fait appel au constructeur au premier tour.
	 */

	private JoueurVirtual (int numJoueur){
		this.numJoueur=numJoueur;
		this.numTour=1;
		this.aJoue=false;

		Random r=new Random();
		int n=r.nextInt(9);
		switch (n){
		case (0):
			this.divinite=new Gwengbelen();
		break;
		case (1):
			this.divinite = new Brewalen();
		break;
		case (2):
			this.divinite = new Drinded();
		break;
		case (3):
			this.divinite = new Gorpa();
		break;
		case (4):
			this.divinite = new Killinstred();
		break;
		case (5):
			this.divinite = new Llewella();
		break;
		case (6):
			this.divinite = new PuiTara();
		break;
		case (7):
			this.divinite = new Romtec();
		break;
		case (8):
			this.divinite = new Sbingva();
		break;
		case (9):
			this.divinite = new Yarstur();
		break;
		}
		this.dogme = this.divinite.getDogmes();
		this.OrigineDivinite = this.divinite.getOrigine();
		this.pointActionJour=0;
		this.pointActionNuit=0;
		this.pointActionNeant=0;
	}
	// TODO Auto-generated constructor stub

	/**
	 * ON N'APPELLE PAS LE CONSTRUCTEUR DE JOUEUR Virtuel, on appelle plutot cette methode, elle construit le joueur et associe le joueur à son espace et à sa main.
	 * @param numJoueur
	 * @param nom
	 * @return
	 */
	public static JoueurVirtual constructeurJoueurAvecAssociation (int numJoueur)
	{
		JoueurVirtual joueur = new JoueurVirtual(numJoueur);
		joueur.espaceDuJoueur = new EspaceJoueur();
		joueur.espaceDuJoueur.setJoueur(joueur);//met le joueur dans son espace (utile pour capa)
		joueur.mainDuJoueur = new CarteMain();
		joueur.mainDuJoueur.setJoueur(joueur);//met le joueur dans son espace(utile pour capa)
		joueur.vueEspace = new VueEspace(numJoueur, joueur);//créer la vue de l'espace du joueur
		joueur.addObserver(joueur.vueEspace);
		return joueur;
	} 

	/**
	 * A chaque fois que l'I.A veut défausser des cartes, elle va défausser un nombre de cartes aléatoire compris entre 1 et 5.
	 */
	public void defausserCarte() throws InterruptedException
	{
		//Thread.sleep(2000);
		System.out.println();
		System.out.println("Le joueur virtuel n°"+this.numJoueur+" défausse une ou plusieurs carte(s).\n");
		System.out.println();
		//Thread.sleep(2000);
		boolean pasBon=true;
		int nbCarte=0;
		do {
			Random r=new Random();
			nbCarte=r.nextInt(6);
			if (nbCarte < 8 && nbCarte >0)
			{
				pasBon=false;
			}
		}while (pasBon);
		System.out.println("Ai defausser"+nbCarte+" Cartes");
		for (int q=1; q<=nbCarte;q++)
		{
			Pile.getInstance().ajouterCarteDansPile(mainDuJoueur.getMainDuJoueur().getFirst());
			mainDuJoueur.supprimerCarte(mainDuJoueur.getMainDuJoueur().getFirst());				
		}
		VueLabelDefausser.avertirJoueur(this);
		setChanged();
		notifyObservers();
	}
	/**
	 * L'I.A complète sa main.
	 */
	public void piocherCarte() throws InterruptedException
	{//Thread.sleep(2000);
	System.out.println();
	System.out.println("Le joueur virtuel n°"+this.numJoueur+" complète sa main.\n");
	System.out.println();
	//Thread.sleep(2000);
	mainDuJoueur.piocherCarte();	
	VueLabelPiocher.avertirJoueur(this);
	setChanged();
	notifyObservers();
	}
	/**
	 * Quand l'I.A décide de poser un croyant, elle va d'abord vérifier qu'elle à une carte croyant dans sa main.
	 * Après, l'I.A va poser tous les croyant que ses points d'action lui permettent.
	 * @return 
	 */
	public void poserCroyant() throws InterruptedException
	{	

		//Thread.sleep(2000);
		System.out.println();
		System.out.println("Le joueur virtuel n°"+this.numJoueur+" pose un croyant.\n");
		System.out.println();
		//Thread.sleep(2000);		
		LinkedList<CarteAction> listCj = new LinkedList();
		listCj.addAll(mainDuJoueur.getMainDuJoueur());	

		Iterator <CarteAction> iterateur4= listCj.iterator();
		boolean aJouer = false; //boolen qui définira aJoue (boolen utile dans utiliser) pour renvoyer si la méthode a été éfficace ou pas
		while (iterateur4.hasNext() && aJouer==false)
		{
			CarteAction objettest=iterateur4.next(); 
			if (objettest instanceof Croyant && objettest.getOrigine()=="Jour" && this.pointActionJour>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				this.pointActionJour--;
				this.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				System.out.println("AI "+this.numJoueur+" pose \n\n"+objettest+" au centre de la table.");
				aJouer=true;
				VueLabelPoserCro.avertirJoueur(this, objettest);
			}
			if (objettest instanceof Croyant && objettest.getOrigine()=="Neant" && this.pointActionNeant>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				this.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				this.pointActionNeant--;
				System.out.println("AI "+this.numJoueur+" pose "+(objettest)+" au centre de la table.");
				aJouer=true;
				VueLabelPoserCro.avertirJoueur(this, objettest);
			}
			if (objettest instanceof Croyant && objettest.getOrigine()=="Nuit" && this.pointActionNuit>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				this.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				this.pointActionNuit--;
				System.out.println("AI "+this.numJoueur+" pose "+(objettest)+" au centre de la table.");
				aJouer=true;
				VueLabelPoserCro.avertirJoueur(this, objettest);
			}
			
		}
		aJoue=aJouer ;
		setChanged();
		notifyObservers();
	}


	/**
	 * On vérifie si l'I.A peut utiliser un guide spirituel, si c'est le cas on va iterer toutes les cartes de la main du joueur virtuel
	 * et poser les guides qui compte tenu des point d'action peuvent être posé, en les posant on selectionne le maximum de croyant pour être guidés.
	 * 
	 * @throws InterruptedException
	 */
	public void poserGuideSpirituel() throws InterruptedException
	{
		boolean aJouer = false;
		//Thread.sleep(2000);
		System.out.println();
		System.out.println("Le joueur virtuel n°"+this.numJoueur+" pose un guide spirituel.\n");
		System.out.println();
		//Thread.sleep(2000);
		LinkedList <CarteAction> mainJ = new LinkedList();
		mainJ.addAll(mainDuJoueur.getMainDuJoueur());
		Iterator <CarteAction> iterateur1= mainJ.iterator();
		
		int n1=0; //mis a 1 si un GS guide des croyants
		//GuideSpirituel[] tabGSSelec = new GuideSpirituel[7]; //la tableau dans lequel
		while ( iterateur1.hasNext() && n1==0 ) // itére tout la main du JV
		{
			CarteAction objettest=iterateur1.next(); 
			if (objettest instanceof GuideSpirituel)
			{
					GuideSpirituel leGuide = (GuideSpirituel) objettest;

					//this.pointActionJour-=1;								//retranche les points
					

					Croyant[] tabCroyant1=new Croyant[CarteCentreTable.getCentreTable().compterCroyant()];
					for (int i = 0; i < tabCroyant1.length; i++) {
						for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
						{
							if (dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)) && !(JoueurPhysique.tableauContient(tabCroyant1, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k))))
							{ //vérifie que le Cro n'a pas déja été selectionné et que il y a un dogmes commun
								tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
								CarteCentreTable.getCentreTable().supprimerCroyant(CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k));
								n1++;
								//le 1er For itère sur chaque case de tabCroyant1 (ou on va stocker les futurs croyant guidé)
								//le second For itére sur la linkedList MAIS on réapelle la méthode pour réajuster la taille à chaque boucle
							}

						}

					}
					if (n1!=0) 	//si n1 != 0 c'est  que toutes les conditions pour ajouter le Guide et ses croyant sont reunis, 
								//on va donc le poser et retrancher les points
						
					{
						espaceDuJoueur.ajouterGuideSpirituel(tabCroyant1, leGuide);
						System.out.println("L'I.A pose"+leGuide+"\n");
						VueLabelPoserGS.avertirJoueur(this, leGuide);
						//retranchons les points correspondant
						if (leGuide.getOrigine()=="Jour"&& this.pointActionJour>0)
						{
							this.pointActionJour--;
						}
						else if (leGuide.getOrigine()=="Nuit"&& this.pointActionNuit>0)
						{
							this.pointActionNuit--;
						}
						else if (leGuide.getOrigine()=="Neant"&& this.pointActionNeant>0)
						{
							this.pointActionNeant--;
						}
						//on enlève le Guide de la main du JV
						this.getMainDuJoueur().supprimerCarte(leGuide);
						//iterateur1.remove(); 									//retire le guide de la liste temporaire
						aJouer=true;
					}
				}
		}
		this.aJoue=aJouer;
		setChanged();
		notifyObservers();
		CarteCentreTable.getCentreTable().notifier(); //update aussi l'espace central
	}
		
		/*
		 * 
		Iterator <CarteAction> iterateur2= mainDuJoueur.getMainDuJoueur().iterator();
		while (iterateur2.hasNext())
		{
			CarteAction objettest=iterateur2.next(); 
			if (objettest instanceof GuideSpirituel&&objettest.getOrigine()=="Neant"&& this.pointActionNeant>0 && CarteCentreTable.getCentreTable().compterCroyant()>0)
			{GuideSpirituel leGuide = (GuideSpirituel) objettest;
			iterateur2.remove();
			this.pointActionNeant-=1;
			int n1=0;
			if (leGuide.getNbCroyantMax()<CarteCentreTable.getCentreTable().compterCroyant() )
			{
				int n=leGuide.getNbCroyantMax();
				Croyant[] tabCroyant1=new Croyant[n];
				for (int i = 0; i < tabCroyant1.length; i++) {
					for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
					{
						if (dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)))
						{
							tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
							CarteCentreTable.getCentreTable().getEspaceCentreTable().removeFirst();
							n1++;
							System.out.println("L'I.A pose"+leGuide+"\n");
						}

					}

				}
				if (n1!=0)
				{
					espaceDuJoueur.ajouterGuideSpirituel(tabCroyant1, leGuide);
				}
			}
			else 
			{
				Croyant[] tabCroyant1=new Croyant[CarteCentreTable.getCentreTable().compterCroyant()];
				for (int i = 0; i < tabCroyant1.length; i++) {
					for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
					{
						if (dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)))
						{
							tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
							CarteCentreTable.getCentreTable().getEspaceCentreTable().removeFirst();
							n1++;
							System.out.println("L'I.A pose"+leGuide+"\n");
						}

					}

				}
				if (n1!=0)
				{
					for (int i = 0; i < tabCroyant1.length; i++) {
						CarteCentreTable.getCentreTable().supprimerCroyant(tabCroyant1[i]);							
					}
					espaceDuJoueur.ajouterGuideSpirituel(tabCroyant1, leGuide);
				}
			}



			}}


		Iterator <CarteAction> iterateur3= mainDuJoueur.getMainDuJoueur().iterator();
		while (iterateur3.hasNext())
		{
			CarteAction objettest=iterateur3.next(); 
			if (objettest instanceof GuideSpirituel&&objettest.getOrigine()=="Nuit"&& this.pointActionNuit>0 && CarteCentreTable.getCentreTable().compterCroyant()>0)
			{GuideSpirituel leGuide = (GuideSpirituel) objettest;
			iterateur3.remove();
			this.pointActionNuit-=1;
			int n1=0;
			if (leGuide.getNbCroyantMax()<CarteCentreTable.getCentreTable().compterCroyant() )
			{
				int n=leGuide.getNbCroyantMax();
				Croyant[] tabCroyant1=new Croyant[n];
				for (int i = 0; i < tabCroyant1.length; i++) {
					for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
					{
						if (dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)))
						{
							tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
							CarteCentreTable.getCentreTable().getEspaceCentreTable().removeFirst();
							n1++;
							System.out.println("L'I.A pose"+leGuide+"\n");
						}

					}

				}
				if (n1!=0)
				{
					espaceDuJoueur.ajouterGuideSpirituel(tabCroyant1, leGuide);
				}
			}
			else 
			{
				Croyant[] tabCroyant1=new Croyant[CarteCentreTable.getCentreTable().compterCroyant()];
				for (int i = 0; i < tabCroyant1.length; i++) {
					for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
					{
						if (dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)))
						{
							tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
							CarteCentreTable.getCentreTable().getEspaceCentreTable().removeFirst();
							n1++;
							System.out.println("L'I.A pose"+leGuide+"\n");
						}

					}

				}
				if (n1!=0)
				{
					for (int i = 0; i < tabCroyant1.length; i++) {
						CarteCentreTable.getCentreTable().supprimerCroyant(tabCroyant1[i]);							
					}
					espaceDuJoueur.ajouterGuideSpirituel(tabCroyant1, leGuide);
				}
			}



			}}
		*/



	/**
	 * Le joueur tente de jouer une carte apocalypse, le booléen renvoi vrai si la carte Apocalypse à fonctionné
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 */
	public boolean jouerApocalypseAI() 
	{
		boolean apocalypseUtilisee=false;
		Iterator <CarteAction> it1 = this.mainDuJoueur.getMainDuJoueur().iterator();
		while (it1.hasNext())
		{
			CarteAction c = it1.next();
			if (c instanceof Apocalypse && !apocalypseUtilisee)
			{
				if (c.getOrigine()==null)
				{
					Apocalypse.cEstLapocalypse();
					it1.remove();
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+this.numJoueur+" joue une carte Apocalypse.\n");
				}
				else if (c.getOrigine().equals("Jour") && pointActionJour>0)
				{
					Apocalypse.cEstLapocalypse();
					it1.remove();
					Pile.getInstance().ajouterCarteDansPile(c);
					pointActionJour-=1;
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+this.numJoueur+" joue une carte Apocalypse.\n");

				}
				else if (c.getOrigine().equals("Nuit") && pointActionNuit>0)
				{
					Apocalypse.cEstLapocalypse();
					it1.remove();
					pointActionNuit-=1;
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+this.numJoueur+" joue une carte Apocalypse.\n");
				}
				else if (c.getOrigine().equals("Jour") && pointActionNeant>0)
				{
					Apocalypse.cEstLapocalypse();
					it1.remove();
					pointActionNeant-=1;
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+this.numJoueur+" joue une carte Apocalypse.\n");
				}
				
			}
		}
		setChanged();
		notifyObservers();
		return apocalypseUtilisee; //pour continuer dans phase virtual si pas utilisé apocalypse
		

	}

	/**
	 * Méthodes identiques à celle du joueur Physique 
	 * @see modelJoueur.JoueurPhysique#compterLesPrieres()
	 */
	public int compterLesPrieres ()
	{
		nombreDePriere = espaceDuJoueur.compterToutesLesPrieres();
		return espaceDuJoueur.compterToutesLesPrieres();
	}
	/**
	 * L'I.A qui appelle cette méthode à décider d'utiliser ses cartes. Elle va choisir entre poser un GuideSpirituel et poser un Croyant.
	 */

	public void utiliser() throws InterruptedException {
		System.out.println("Le joueur virtuel utilise ses cartes");
		boolean aFaitQqc = false; // ce booléen servira à vérifier que le J à bien effectué une action, le if/else suivant s'en servira pour choisir si il doit jouer DeusEx/apo
		if (!Partie.verifieSiJoueurDansMoyenneSup(this)) // si le joueur n'est pas dans la moyenne haute => pose des croyant et des GS
		{
			boolean peutJouerGS = PeutJouer.peutJouerGS(this); //ON ne teste pas directement la fonction car sinon on itère deux fois sur la même linkedList
			
			
			if (peutJouerGS) 
			{ //UTILISE  LA FONCTION DE CARTE MAIN PEUT POSER GS)
				System.out.println("Le joueur virtuel n°"+this.getNumJoueur()+" poser un guide spirituel.");
				poserGuideSpirituel();
//				aFaitQqc = aJoue ;
				aFaitQqc = true ;
			}
			boolean peutJouerCroyant = PeutJouer.peutJouerCroyant(this); //ON ne teste pas directement la fonction car sinon on itère deux fois sur la même linkedList
			if (peutJouerCroyant )
			{
				System.out.println("Le joueur virtuel n°"+this.getNumJoueur()+" poser un croyant.");
				poserCroyant(); 
//				aFaitQqc = aJoue;
				aFaitQqc = true;
			}	
			
		}
		
			jouerDeusEx();
			sacrifierCroyant();
		
		


		
		aJoue=false;//reset le booleen
	}

	/**
	 * @deprecated
	 */
	@Override
	public void sacrifierGuideSpirituel() {
		// TODO Auto-generated method stub
		setChanged();
		notifyObservers();

	}

	/**
	 * Cette méthode va jouer la première DeusEx que le joueur Virtuel possède.
	 * @throws InterruptedException 
	 */
	
	public void jouerDeusEx() throws InterruptedException {
		boolean canUse = false;
		for (int i = 0; i < Partie.getJoueurs().size(); i++) {
			for (int j = 0; j < Partie.getJoueurs().get(i).getEspaceDuJoueur().getListeDesGuides().size(); j++) {
				if (Partie.getJoueurs().get(i).getNumJoueur() != this.numJoueur) {
					canUse = true;
					
				}
			}
		}

		if (canUse) {
			LinkedList<CarteAction> listeC = new LinkedList<CarteAction>();
			listeC.addAll(this.mainDuJoueur.getMainDuJoueur());
			Iterator<CarteAction> it = listeC.iterator();
			out: while (it.hasNext()) 
			{
				CarteAction c = it.next();
				if (c instanceof DeusEx) 
				{   
					DeusEx deusEx = (DeusEx) c;
					boolean beUsed = false;
					boolean bePrevented = false;

					if (deusEx.getOrigine() == "Jour" && deusEx.isPeutJouerParAI()) 
					{	
						if (this.getPointActionJour() >= 1) 
						{
							this.setPointActionJour(this.pointActionJour--);
							VueLabelDeusEx.avertirJoueur(this, deusEx);
							beUsed = true;
							try {
								bePrevented = Controler.getInstanceControler().prevent(deusEx, this);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (bePrevented == false) {
								deusEx.sacrifierAI();
							}
							if (beUsed) {
							
								this.mainDuJoueur.supprimerCarte(deusEx);;
							}
							setChanged();
							notifyObservers();
							break out;
						}
					}

					if (deusEx.getOrigine() == "Nuit" && deusEx.isPeutJouerParAI()) {
						if (this.getPointActionNuit() >= 1) {
							this.setPointActionNuit(this.pointActionNuit--);
							VueLabelDeusEx.avertirJoueur(this, deusEx);
							beUsed = true;
							try {
								bePrevented = Controler.getInstanceControler().prevent(deusEx, this);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (bePrevented == false) {
								deusEx.sacrifierAI();
							}
							if (beUsed) {
								
								this.mainDuJoueur.supprimerCarte(deusEx);;
							}
							setChanged();
							notifyObservers();
							break out;

						}
					}

					if (deusEx.getOrigine() == "Neant" && deusEx.isPeutJouerParAI()) {
						if (this.getPointActionNeant() >= 1) {
							this.setPointActionNeant(this.pointActionNeant--);
							VueLabelDeusEx.avertirJoueur(this, deusEx);
							beUsed = true;
							
							
							
							//bePrevented = this.prevent(deusEx);
							try {
								bePrevented = Controler.getInstanceControler().prevent(deusEx, this);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if (bePrevented == false) {
								deusEx.sacrifierAI();
							}
							if (beUsed) {
							
								this.mainDuJoueur.supprimerCarte(deusEx);;
							}
							setChanged();
							notifyObservers();
							break out;

						}
					}

				
				}
			}
		}
	}

	public static boolean prevent(CarteAction caUse) throws InterruptedException {
		LinkedList <CarteAction> listeM = new LinkedList<CarteAction>();
		listeM.addAll(Partie.getJoueurHumain().mainDuJoueur.getMainDuJoueur());
		Iterator<CarteAction> iterateur = listeM.iterator();
		while (iterateur.hasNext()) 
		{
			CarteAction ca = iterateur.next();
			if (ca instanceof DeusEx) 
			{	
				DeusEx deusEx = (DeusEx) ca;
				if (deusEx.getNomDeusEx() == "InfluenceNulle"
						|| (deusEx.getNomDeusEx() == "InfluenceJour" && caUse.getOrigine() == "Jour")
						|| (deusEx.getNomDeusEx() == "InfluenceNeant" && caUse.getOrigine() == "Neant")
						|| (deusEx.getNomDeusEx() == "InfluenceNuit" && caUse.getOrigine() == "Nuit")) 
				{	
					DemanderPrevent demCarte = new DemanderPrevent(deusEx);
					Thread threadDemJoueur = new Thread(demCarte);
					DemanderPrevent.setT(threadDemJoueur);
					System.out.println("Le thread à été créer");
					threadDemJoueur.start();
					Test.getVerrouThread().notifyAll();
					Test.getVerrouThread().wait(); //att qu'on lui redonne le verrou
					
					
					
					if (DemanderPrevent.getChoixJoueur())
					{
						Prevent p1=new Prevent();
						Partie.getJoueurHumain().getMainDuJoueur().supprimerCarte(deusEx);
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Vérifie si il y a au moins un dogmes en commun entre un guide spirituel et un croyant
	 * @param guide
	 * Le guide que l'on souhaire tester.
	 * @param croyant
	 * Le Croyant que l'on souhaire tester.
	 * @return
	 * Vrai si il y a au moins un dogme en commun. Faux si il n'y en a pas.
	 */
	public boolean dogmeCommun (GuideSpirituel guide , Croyant croyant)
	{
		boolean commun = false;
		for (int i = 0; i < guide.getDogmes().length; i++) 
		{
			for (int j = 0; j < croyant.getDogmes().length; j++) 
			{
				if (croyant.getDogmes()[j].equals(guide.getDogmes()[i]))
				{
					commun = true;
				}
			}
		}
		return commun;
	}
	/**
	 * @deprecated
	 */
	@Override
	public void jouerApocalypse() {
		// TODO Auto-generated method stub
		setChanged();
		notifyObservers();

	}
	public void sacrifierCroyantAI() throws InterruptedException  {
		LinkedList <Croyant> listeCro = new LinkedList<Croyant>();
		listeCro.addAll(this.getEspaceDuJoueur().getListeDesCroyants());
		Iterator<Croyant> it = listeCro.iterator();
		out:while (it.hasNext()) 
		{
			//Croyant cro = it.next();
			//if (it.next() instanceof Croyant) 
			//{
				Croyant croyant = it.next();
				if (croyant.isPeutJouerParAI()) 
				{
					boolean prevent = false;
//					prevent = prevent(croyant);
					if (prevent == false) 
					{
						croyant.sacrifierAI(this);
					}
					this.getEspaceDuJoueur().getListeDesCroyants().remove(croyant);
					VueLabelSacrifierCry.avertirJoueur(this, croyant);
//					break out;
				
				}
			//}

		}

		setChanged();
		notifyObservers();

	}

	@Override
	public void sacrifierCroyant() {
		// TODO Auto-generated method stub
		}
	public void mettreAJourGraphique()
	{
		this.setChanged();
		this.notifyObservers();
	}

	public StrategyInterface getStrategie() {
		return strategie;
	}

	public void setStrategie(StrategyInterface strategie) {
		this.strategie = strategie;
	}
}
