	package modelJoueur;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.Divinite;
import modelCarte.GuideSpirituel;
import modelCarteDivinite.*;
import modelDeroulementPartie.Partie;
import modelDeroulementPartie.Phase;
import modelDeroulementPartie.Pile;
import modelJoueur.*;
import vueAffichage.VueEspace;
import vueAffichage.VueMainJoueurP;
import vueAffichage.VueStatJoueur;
/**
 * 
 * Ceci est la classe qui permet au joueur humain d'int�ragir avec le jeu. Plusieurs m�thodes de Joueur impl�ment�. Certaines m�thodes de 
 * Joueur sont impl�menter mais reste vide. Ceci est d� au fait que certaines m�thodes sont utiles � l'IA et pas au joueur ou inversement. Elle 
 * sont impl�ment�es ici pour que la classe ne soit abstraites elle aussi.
 * @see Joueur
 * @see JoueurVirtual
 *
 */
public class JoueurPhysique extends Joueur
{
	/**
	 * La vue de la main du joueur, cr�er dans le second constructeur
	 */
	private VueMainJoueurP vueMainJoueur;
	/**
	 * Differents statistiques concernant le Joueur sont montrer par cette vue.
	 */
	private VueStatJoueur vueStatJoueur;
	private boolean valide=true;
	/**
	 * Contructeur de la classe JoueurPhysique, cette m�thode attribut aussi une divinit� au joueur. En instanciant un objet de la classe correspondant � la divinit�.
	 * Elle attribut ensuite au joueur les dogmes et l'origine de sa divinit�.
	 * Elle renseigne �galement le joueur sur la divinit� qui lui � �t� attribu�. Notons que plusieurs joueurs peuvent tomber sur la m�me divinit�s.
	 * @param numJoueur
	 * Donn� par la classe Tour quand le joueur humain est construit
	 * @param nom
	 * Demand� au joueur en d�but de partie
	 */
	private JoueurPhysique(int numJoueur, String nom){
		this.numJoueur=numJoueur;
		this.nomJoueur=nom;
		this.numTour=1;
		this.setaJoue(false);
		Random r=new Random();
		int n=r.nextInt(9);
		switch (n){
		case (0):
			this.divinite=new Gwengbelen();
		break;
		case (1):
			this.divinite=new Gwengbelen();
		break;
		case (2):
			this.divinite=new Gwengbelen();
		break;
		case (3):
			this.divinite = new Yarstur();
		break;
		case (4):
			this.divinite = new Yarstur();
		break;
		case (5):
			this.divinite = new Yarstur();
		break;
		case (6):
			this.divinite = new PuiTara();
		break;
		case (7):
			this.divinite = new PuiTara();
		break;
		case (8):
			this.divinite = new PuiTara();
		break;
		case (9):
			this.divinite = new PuiTara();
		break;
		}
		this.dogme = this.divinite.getDogmes();
		this.OrigineDivinite = this.divinite.getOrigine();
		System.out.println("Votre divinit� est "+this.divinite.getNom()+".\n Il/Elle est d'origine "+this.divinite.getOrigine()+".\n");
		for (int k=0; k<this.divinite.getDogmes().length ; k++)
		{
			System.out.println("Son dogme n�"+k+" est : "+this.divinite.getDogmes()[k]);
		}
		this.pointActionJour=0;
		this.pointActionNuit=0;
		this.pointActionNeant=0;


	}
	/**
	 * ON N'APPELLE PAS LE CONSTRUCTEUR DE JOUEUR PHYSIQUE, on appelle plutot cette methode, elle construit le joueur et associe le joueur � son espace et � sa main.
	 * @param numJoueur
	 * @param nom
	 * @return
	 */
	public static JoueurPhysique constructeurJoueurAvecAssociation (int numJoueur, String nom)
	{
		JoueurPhysique joueur = new JoueurPhysique(numJoueur, nom);
		joueur.espaceDuJoueur = new EspaceJoueur();
		joueur.espaceDuJoueur.setJoueur(joueur);//met le joueur dans son espace (utile pour capa)
		joueur.mainDuJoueur = new CarteMain();
		joueur.mainDuJoueur.setJoueur(joueur);//met le joueur dans son espace(utile pour capa)
		joueur.vueEspace = new VueEspace(numJoueur, joueur);//cr�er la vue de l'espace du joueur
		joueur.vueMainJoueur = new VueMainJoueurP(joueur); // cr�er la vue de la main du joueur
		joueur.vueStatJoueur = new VueStatJoueur (joueur); //cr�er la vue des stats du joueur
		joueur.addObserver(joueur.vueEspace);
		joueur.addObserver(joueur.vueMainJoueur);
		joueur.addObserver(joueur.vueStatJoueur);
		return joueur;
	}




	/**
	 * Impl�mente la m�thode �ponyme de Joueur.
	 * On demande au joueur combien de carte doivent �tre d�fauss�es (Le do/while sert � s'assurer que le choix du joueur est coh�rent.)
	 * Apr�s cela on appelle autant de fois la m�thode supprimerCarte() de CarteMain que le joueur l'a d�sir�.
	 * @see modelJoueur.CarteMain#supprimerCarte()
	 * @see modelJoueur.Joueur#defausserCarte()
	 */
	public void defausserCarte()
	{
		System.out.println("Combien de cartes souhaitez-vous d�faussez ?\n");
		boolean pasBon=true;
		int nbCarte=0;
		do {
			Scanner sc = new Scanner(System.in);
			nbCarte = sc.nextInt();
			if (nbCarte < 8 && nbCarte >0)
			{
				pasBon=false;
			}
		}while (pasBon);
		System.out.println(nbCarte);
		for (int q=0; q<nbCarte;q++)
		{
			System.out.println("ITERATOON "+q);//A CRAMER TEST DEBUG
			mainDuJoueur.supprimerCarte();
			setChanged();
			notifyObservers();
		}
//		setChanged();
//		notifyObservers();

	}
	/**
	 * Impl�mente la m�thode de Joueur, appelant PiocherCarte, une m�thode de CarteMain.
	 * @see modelJoueur.Joueur
	 * @see modelJoueur.CarteMain#piocherCarte()
	 */
	public void piocherCarte()
	{
		mainDuJoueur.piocherCarte();		
		setChanged();
		notifyObservers();
	}
	/**
	 * Impl�mente la m�thode de Joueur
	 * Demande au joueur de choisir un croyant pr�sent dans sa main. On v�rifie que son choix est coh�rent.
	 * Ensuite on fait appel � la m�thode sacrifier du croyant. Cepandant la classe Croyant n'est jamais instanci�e. 
	 * On appel donc la m�thode surcharg�e des classes qui h�ritent de Croyant.
	 * Apr�s avoir fait appel au sacrifier, on retire la carte de l'espace du Joueur. (Nous avons consid�r� que les croyants ne pouvait �tre sacrifi�
	 * que si il �tait sur le terrain du Joueur. En effet certaine carte sont serait d�s�quilibr� sans cela. Cependant ce choix prive des cartes comme Esprit ou Moines 
	 * d'une grande partie de leur utilit�. Nous affinerons cette r�gles pour le Livrable 3.)
	 * Notons qu'on utilise la m�thode supprimerCroyant() de EspaceJoueur, elle permet de mettre a jour certain atrribut g�rant la relation entre les cartes.
	 * (Par exemple quel guide d�tient quels croyants).
	 * Apr�s avoir retir� la carte de l'espace du joueur il ne reste plus qu'a la remettre dans la pile.
	 * La m�thode ajouterCarteDansPile() permet aussi de mettre � jour certaine propri�t� des CartesAction.
	 * @see Model.croyant
	 * @see modelJoueur.EspaceJoueur#supprimerCroyant(Croyant)
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see modelJoueur.EspaceJoueur#afficherToutLesCroyants()
	 */
	public void sacrifierCroyant()
	{
		espaceDuJoueur.afficherToutLesCroyants();
		System.out.println("Quel croyant voulez-vous sacrifier ? \n");
		boolean userFacetieux = true;
		int idCr=0;
		do {
			System.out.println("Rentrer l'INDEX du croyant que vous souhaitez sacrifier.");
			Scanner sc = new Scanner(System.in);
			int idCroyant = sc.nextInt();
			if (espaceDuJoueur.getListeDesCroyants().contains(espaceDuJoueur.getListeDesCroyants().get(idCroyant)))
			{
				userFacetieux=false;
				idCr=idCroyant;
			}
		}while(userFacetieux);
		out:for (int i = 1; i < Partie.getJoueurs().size(); i++) {
			Iterator <CarteAction> iterateur= Partie.getJoueurs().get(i).mainDuJoueur.getMainDuJoueur().iterator();
			while (iterateur.hasNext())
			{
				CarteAction objettest=iterateur.next(); 
				if (objettest instanceof DeusEx) {
					objettest=(DeusEx)objettest;
					if (objettest.getNom()=="InfluenceNulle"
						||(objettest.getNom()=="InfluenceJour"&&espaceDuJoueur.getListeDesCroyants().get(idCr).getOrigine()=="Jour")
						||(objettest.getNom()=="InfluenceNeant"&&espaceDuJoueur.getListeDesCroyants().get(idCr).getOrigine()=="Neant")
						||(objettest.getNom()=="InfluenceNuit"&&espaceDuJoueur.getListeDesCroyants().get(idCr).getOrigine()=="Nuit"))
					 {
						this.valide=false;
						iterateur.remove();
						break out;
					}
					
				}
			
			
		}
	}
    	if (this.valide=true) {
		espaceDuJoueur.getListeDesCroyants().get(idCr).sacrifier();//on utilise la capacite sacrifier du croyant
		}
    	this.valide=true;
		
		
		
		
		
		
		
		espaceDuJoueur.supprimerCroyant(espaceDuJoueur.getListeDesCroyants().get(idCr));
		pilePartie.ajouterCarteDansPile(espaceDuJoueur.getListeDesCroyants().get(idCr));
		setChanged();
		notifyObservers();
	}
	/**
	 * Impl�mente la m�thode poserCroyant() de Joueur.
	 * C'est une version sans argument ne pouvant servir qu'un joueur physique.
	 * On commence par faire choisir le joueur en lui pr�sentant les croyants de sa main
	 * Le booleen peutJoue permet de v�rifier si le joueur a suffisament de point d'action pour jouer la carte qu'il � choisit.
	 * (Si ce n'est pas le cas, la m�thode saute directement � la fin et informe le joueur qu'il n'a pas la possibilit� de jouer ce croyant.)
	 * Ensuite ajoute cette carte dans l'espace du Joueur et on l'enl�ve de sa main.
	 * Puis on retranche les points d'action correspondant.
	 * @see modelJoueur.CarteCentreTable#ajouterCroyant(Croyant)
	 * @see modelJoueur.CarteMain#supprimerCarte(CarteAction)
	 * @see modelJoueur.CarteMain#afficherMain(int)
	 */
	public void poserCroyant()
	{
		System.out.println("Voici les croyants dont vous disposez :\n");
		mainDuJoueur.afficherMain(1);
		boolean choixValid = false;
		int idChoix=0;
		do{
			System.out.println("Choissisez le croyant que vous d�sirez poser, entrer son INDEX.\n");			//Verifie si l'INDEX entr� est bien celui d'un croyant qui appartient a la main du joueur
			Scanner sc = new Scanner(System.in);
			int idCroyant = sc.nextInt();
			if (mainDuJoueur.getMainDuJoueur().contains(mainDuJoueur.getMainDuJoueur().get(idCroyant)) && mainDuJoueur.getMainDuJoueur().get(idCroyant) instanceof Croyant)
			{
				choixValid=true;
				idChoix=idCroyant;
			}
		} while (choixValid == false);

		boolean peutJoue=false;
		if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Jour"))
		{
			if (this.pointActionJour > 0)
			{
				peutJoue=true;
			}
		}
		if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Nuit"))
		{
			if (this.pointActionNuit > 0)
			{
				peutJoue=true;
			}
		}
		if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Neant"))
		{
			if (this.pointActionNeant > 0)
			{
				peutJoue=true;
			}
		}
		if (peutJoue)
		{
			if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Jour"))
			{
				this.pointActionJour--;
			}
			else if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Nuit"))
			{
				this.pointActionNuit--;
			}
			else if (mainDuJoueur.getMainDuJoueur().get(idChoix).getOrigine().equals("Neant"))
			{
				this.pointActionNeant--;
			}
			
			espaceCentre.ajouterCroyant((Croyant) mainDuJoueur.getMainDuJoueur().get(idChoix));//rajoute dans EspCentre
			mainDuJoueur.supprimerCarte(mainDuJoueur.getMainDuJoueur().get(idChoix)); 			// enl�ve de la main du joueur

		}
		else 
		{
			System.out.println("\nVous n'avez pas les points requis pour jouer ce croyant.\n");
		}
		setChanged();
		notifyObservers();
	}
	/**
	 * Impl�mente la m�thode de Joueur
	 * On commence par montrer au joueur les guide dont il dispose, puis on lui fait faire son choix et on v�rifie qu'il soit coh�rent avec le Do/while.
	 * Comme pour la m�thode PoserCroyant() de cette m�me classe. On commence par v�rifier si le joueur � les point d'action correspondant � la carte qu'il d�sire jouer.
	 * Si ce n'est pas le cas la m�thode saute � la fin et
	 * Ensuite pour chaque Croyant au centre de la table (dans le singleton EspaceCentreTable) on v�rifie si : 
	 * - Il y a un dogme en commun
	 * - Si le nombre de croyant que le joueur d�sire/que le guide peut supporter n'est pas d�passer
	 * - Si le joueur est d'accord pour guider ce croyant
	 * Si les conditions  sont reunis il ajoute le croyant dans un tableau et it�re sur le prochain croyant de l'espace centrale.
	 * Et on supprime le croyant de l'espace du joueur
	 * A la fin, si au moins un croyant � �t� plac� dans le tableau alors on retranche le point correspondant � l'origine du Guide.
	 * On met ensuite le tableau et le guide en argument de la m�thode ajouterGuideSpirituel() de EspaceJoueur. On retranche au joueur les points correspondant � la carte.
	 *  
	 * @see modelJoueur.CarteMain#afficherMain(int)
	 * @see modelJoueur.EspaceJoueur#ajouterGuideSpirituel(Croyant[], GuideSpirituel)
	 * @see modelJoueur.CarteCentreTable#supprimerCroyant(Croyant)
	 */
	public void poserGuideSpirituel()
	{

		System.out.println("Voici les guides spirituels dont vous disposez :\n");
		mainDuJoueur.afficherMain(2);
		boolean indexValide=false;
		int choixGuide;
		do{
			System.out.println("Quel guide voulez-vous utiliser ? Rentrer son index.\n");
			Scanner sc = new Scanner(System.in);
			int idGuide = sc.nextInt();
			choixGuide=idGuide;
			if (mainDuJoueur.getMainDuJoueur().contains(mainDuJoueur.getMainDuJoueur().get(idGuide))&& (mainDuJoueur.getMainDuJoueur().get(idGuide) instanceof GuideSpirituel))
			{
				indexValide=true;
			}
		} while (indexValide==false);
		GuideSpirituel leGuide = (GuideSpirituel) mainDuJoueur.getMainDuJoueur().get(choixGuide);
		boolean peutJoue=false;
		if (leGuide.getOrigine().equals("Jour"))
		{
			if (this.pointActionJour > 0)
			{
				peutJoue=true;
			}
		}
		if (leGuide.getOrigine().equals("Nuit"))
		{
			if (this.pointActionNuit > 0)
			{
				peutJoue=true;
			}
		}
		if (leGuide.getOrigine().equals("Neant"))
		{
			if (this.pointActionNeant > 0)
			{
				peutJoue=true;
			}
		}
		if (peutJoue)
		{

			espaceCentre.afficherEspaceCentreTable();
			/*
			System.out.println("Combien de croyant voulez-vous guider avec ce guide ? Rappel le nombre de croyant maximum pour cette carte est "+leGuide.getNbCroyantMax()+".\n");
			Scanner sc = new Scanner(System.in);
			int nbC = sc.nextInt();
			
			(iterateur.hasNext() && n<=nbC-1)
			
			*/
			LinkedList <Croyant> listeEspaceCentrale = new LinkedList<Croyant>();	//POUR EVITER EXCEPTION de MODIF concurrente
			listeEspaceCentrale.addAll(espaceCentre.getEspaceCentreTable());
			Iterator <Croyant> iterateur = listeEspaceCentrale.iterator();
			int n=0;
			boolean gsUtilise=false;
			Croyant [] tabCroyant= new Croyant[leGuide.getNbCroyantMax()];
			while (   iterateur.hasNext() && n<=leGuide.getNbCroyantMax() ) //tant que nb de croyant ajouter est inf�rieur a [nb croyant que on veut, nb croyant max que guide peut guider]
			{
				Croyant leTest = iterateur.next();

						if (this.dogmeCommun(leGuide, leTest) && !(JoueurPhysique.tableauContient(tabCroyant, leTest)) ) //m�thode d�di�e � la v�rification des dogmes
						{
							System.out.println("Voulez-vous ajouter ce croyant � la liste des adeptes de ce guides. ?");
							System.out.println(leTest);
							boolean choixPasBon = true;
							do {
								System.out.println("1 pour Oui, 0 pour Non.\n");
								Scanner sc3 = new Scanner(System.in);
								int validation = sc3.nextInt();
								if (validation==1)
								{
									tabCroyant[n]= leTest ;
									n++;
									choixPasBon=false;
									gsUtilise=true;
									CarteCentreTable.getCentreTable().supprimerCroyant(leTest);  //on supprime le coryant sur l'espace centrale
									//Donc pas celui sur lequel on it�re => pas de prob
								}
							}while(choixPasBon);
							choixPasBon=false;


						}
					}
			
			if (leGuide.getOrigine().equals("Jour")&& gsUtilise)
			{
				this.pointActionJour--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enl�ve le guide de la main
			}
			else if (leGuide.getOrigine().equals("Nuit")&& gsUtilise)
			{
				this.pointActionNuit--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enl�ve le guide de la main
			}
			else if (leGuide.getOrigine().equals("Neant")&& gsUtilise)
			{
				this.pointActionNeant--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enl�ve le guide de la main
			}


		}
		else 
		{
			System.out.println("\nVous n'avez pas les points requis pour jouer ce guide.\n");
		}
		setChanged();
		notifyObservers();
		CarteCentreTable.getCentreTable().notifier(); //update aussi l'espace central
	}
	/**
	 * Impl�mente la m�thode de Joueur 
	 * Affiche les guides sacrifiables que le joueur poss�de. V�rifie que son choix est correcte.
	 * Utilise la m�thode SupprimerGuideSpirituel() de EspaceJoueur qui met a jour les information dans le guide et qui d�fausse les croyant
	 * Appel la capacit� sacrifier() du guide
	 * Place le guide dans la pile grace � la m�thode de Pile
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see modelGuideSpirituel.GuideSpirituel#sacrifier()
	 * @see modelJoueur.EspaceJoueur#supprimerGuideSpirituel(GuideSpirituel)
	 * @see modelJoueur.EspaceJoueur#afficherToutLesGuideSpirituel()
	 * 
	 */
	public void sacrifierGuideSpirituel()
	{
		espaceDuJoueur.afficherToutLesGuideSpirituel();
		System.out.println("Quel guide spirituel voulez-vous sacrifier ? \n");
		boolean userFacetieux = true;
		int idG=0;
		do {
			System.out.println("Rentrer l'INDEX du guide que vous souhaitez supprimer.");
			Scanner sc = new Scanner(System.in);
			int idGuide = sc.nextInt();
			if (espaceDuJoueur.getListeDesGuides().contains(espaceDuJoueur.getListeDesGuides().get(idGuide)))
			{
				userFacetieux=false;
				idG=idGuide;
			}
		}while(userFacetieux);
		GuideSpirituel guideSelect = espaceDuJoueur.getListeDesGuides().get(idG);		
		
		out:for (int i = 1; i < Partie.getJoueurs().size(); i++) {
			Iterator <CarteAction> iterateur= Partie.getJoueurs().get(i).mainDuJoueur.getMainDuJoueur().iterator();
			while (iterateur.hasNext())
			{
				CarteAction objettest=iterateur.next(); 
				if (objettest instanceof DeusEx) {
					objettest=(DeusEx)objettest;
					if (objettest.getNom()=="InfluenceNulle"
						||(objettest.getNom()=="InfluenceJour"&&espaceDuJoueur.getListeDesGuides().get(idG).getOrigine()=="Jour")
						||(objettest.getNom()=="InfluenceNeant"&&espaceDuJoueur.getListeDesGuides().get(idG).getOrigine()=="Neant")
						||(objettest.getNom()=="InfluenceNuit"&&espaceDuJoueur.getListeDesGuides().get(idG).getOrigine()=="Nuit"))
					 {
						this.valide=false;
						iterateur.remove();
						break out;
					}
					
				}
		}
	}
		
		
		
		
		
		espaceDuJoueur.supprimerGuideSpirituel(guideSelect); //a ce stade on a retirer le guide de la liste des guides
		// on a retirer les croyant qui y sont attach�s
		pilePartie.ajouterCarteDansPile(guideSelect);//on remet dans pile
		
		if (this.valide=true) {
		 guideSelect.sacrifier();//on utilise la capacit� sacrifice
	}
		this.valide=true;
		setChanged();
		notifyObservers();


	} 
	/**
	 * Impl�mente la m�thode de Joueur
	 * Affiche les cartes Deus Ex du joueur
	 * Recup�re son choix en s'assurant qu'il soit coh�rent
	 * Appel la m�thode de la carte DeusEx
	 * Met la carte dans la pile
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see Model.DeuxEx#sacrifier()
	 * 
	 */
	public void jouerDeusEx()
	{

		System.out.println("Voici les cartes deus ex que vous poss�der :\n");
		mainDuJoueur.afficherMain(3);
		boolean indexValide=false;
		int choixGuide;
		do{
			System.out.println("Quel DeusEx voulez-vous utiliser ? Rentrer son index.\n");
			Scanner sc = new Scanner(System.in);
			int idGuide = sc.nextInt();
			choixGuide=idGuide;
			if (mainDuJoueur.getMainDuJoueur().contains(mainDuJoueur.getMainDuJoueur().get(idGuide))&& (mainDuJoueur.getMainDuJoueur().get(idGuide) instanceof DeusEx))
			{
				indexValide=true;
			}
		} while (indexValide==false);
		boolean valide=true;
		out:for (int i = 1; i < Partie.getJoueurs().size(); i++) {
			Iterator <CarteAction> iterateur= Partie.getJoueurs().get(i).mainDuJoueur.getMainDuJoueur().iterator();
			while (iterateur.hasNext())
			{
				CarteAction objettest=iterateur.next(); 
				if (objettest instanceof DeusEx) {
					objettest=(DeusEx)objettest;
					if (objettest.getNom()=="InfluenceNulle"
						||(objettest.getNom()=="InfluenceJour"&&mainDuJoueur.getMainDuJoueur().get(choixGuide).getOrigine()=="Jour")
						||(objettest.getNom()=="InfluenceNeant"&&mainDuJoueur.getMainDuJoueur().get(choixGuide).getOrigine()=="Neant")
						||(objettest.getNom()=="InfluenceNuit"&&mainDuJoueur.getMainDuJoueur().get(choixGuide).getOrigine()=="Nuit"))
					 {
						valide=false;
						iterateur.remove();
						break out;
					}
					
				}
	}
	}
		
		if (valide=true) {
			//mainDuJoueur.getMainDuJoueur().get(choixGuide).sacrifier();
		
		}
		Pile.getInstance().ajouterCarteDansPile(mainDuJoueur.getMainDuJoueur().get(choixGuide));
		mainDuJoueur.supprimerCarte(mainDuJoueur.getMainDuJoueur().get(choixGuide));
		setChanged();
		notifyObservers();
	}
	/**
	 * Impl�mente la m�thode de Joueur
	 * affiche les cartes apocalypse a disposition du joueur, r�cup�re son choix et v�rifie qu'il est coh�rent.
	 * Utilise la m�thode static si les conditions  concernant le cout en point de la carte sont reunient
	 * Enl�ve la carte de de la mainDuJoueur
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 * @see modelJoueur.CarteMain#afficherMain(int)
	 */
	public void jouerApocalypse()
	{
		mainDuJoueur.afficherMain(4);
		System.out.println("Quelle carte Apocalypse souhaiter vous jouer ?");
		int idChoix=0;
		boolean ChoixPasValide=true;		
		do{
			System.out.println("Rentrer son index.");
			Scanner sc = new Scanner(System.in);
			idChoix = sc.nextInt();
			if (mainDuJoueur.getMainDuJoueur().contains(mainDuJoueur.getMainDuJoueur().get(idChoix)) && mainDuJoueur.getMainDuJoueur().get(idChoix) instanceof Apocalypse )//v�rifie que le joueur rentre un index d'une carte qu'il a ET que c'est une carte Apocalypse  
			{
				ChoixPasValide=false;
			}

		}while(ChoixPasValide);
		Apocalypse carteApo = (Apocalypse) mainDuJoueur.getMainDuJoueur().get(idChoix);
		if (carteApo.getOrigine().equals(null)) 		//EN premier car sinon pour les origines null throw nullPointerException
		{
			Apocalypse.cEstLapocalypse();
			LinkedList <CarteAction> List = mainDuJoueur.getMainDuJoueur();
			List.remove(carteApo);
			mainDuJoueur.setMainDuJoueur(List);
		}
		else if (carteApo.getOrigine()=="Jour" && pointActionJour>0)
		{
			pointActionJour-=1;
			Apocalypse.cEstLapocalypse();
			LinkedList <CarteAction> List = mainDuJoueur.getMainDuJoueur();
			List.remove(carteApo);
			mainDuJoueur.setMainDuJoueur(List);
		}
		else if (carteApo.getOrigine().equals("Nuit")&& pointActionNuit>0)
		{
			pointActionNuit-=1;
			Apocalypse.cEstLapocalypse();
			LinkedList <CarteAction> List = mainDuJoueur.getMainDuJoueur();
			List.remove(carteApo);
			mainDuJoueur.setMainDuJoueur(List);
		}
		else if (carteApo.getOrigine().equals("Neant")&& pointActionNeant>0)
		{
			Apocalypse.cEstLapocalypse();
			LinkedList <CarteAction> List = mainDuJoueur.getMainDuJoueur();
			List.remove(carteApo);
			mainDuJoueur.setMainDuJoueur(List);
			pointActionNeant-=1;
		}
		
		else 
		{
			System.out.println("Vous n'avez pas assez de Point pour utiliser cette carte.\n");
		}
		
		setChanged();
		notifyObservers();
		
	}
	/**
	 * Impl�mente la m�thode de Joueur, cette m�thode permet � l'IA de choisir quand mettre une carte apocalypse, 
	 * et au carte Apocalypse de d�signer le vainqueur ou celui qui sera exclu du jeu. Elle fait appel � l'espace du joueur
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 * @see modelJoueur.EspaceJoueur#compterToutesLesPrieres()
	 */
	public int compterLesPrieres ()
	{
		return espaceDuJoueur.compterToutesLesPrieres();
	}
	/**
	 * M�thode qui sera appeller par la classe Phase d�s que le joueur d�sirera faire autre chose que D�fausser des cartes ou compl�ter sa main.
	 * C'est essentiellement un ordonnanceur qui ne fait qu'appeler d'autre m�thode pr�sente dans joueur. A part le choix n�7.
	 * Il permet de convertir des point Jour/Nuit en point de n�ant, conform�ment aux r�gles de Pandocr�on Divinae
	 * Ce menu permet aussi au joueur de finir son tour.
	 * @see modelDeroulementPartie.Phase#joueDePhase()
	 * @see modelJoueur.JoueurPhysique#jouerApocalypse()
	 * @see modelJoueur.JoueurPhysique#sacrifierCroyant()
	 * @see modelJoueur.JoueurPhysique#sacrifierGuideSpirituel()
	 * @see modelJoueur.JoueurPhysique#jouerDeusEx()
	 * @see modelJoueur.JoueurPhysique#poserCroyant()
	 * @see modelJoueur.JoueurPhysique#poserGuideSpirituel()
	 */
	public void utiliser() {
		boolean notEnd=true;
		while(notEnd){

			System.out.println("Quelle methode choisissez-vous ?");
			System.out.println("1:sacrifierCroyant");
			System.out.println("2:sacrifierGuideSpirituel");
			System.out.println("3:jouerDeusEx");
			System.out.println("4:jouerApocalypse");
			System.out.println("5:poserGuideSpirituel");
			System.out.println("6:poserCroyant");
			System.out.println("7:changer des points Jour/Nuit en point n�ant");
			System.out.println("8:end");

			Scanner sc = new Scanner(System.in);
			int c = sc.nextInt();

			switch(c){
			case 1:
				sacrifierCroyant();
				break;
			case 2:
				sacrifierGuideSpirituel();
				break;
//			case 3:
//				if (this.getMainDuJoueur().peutJouerDeusEx())
//				{
//					notEnd=false; //pr�cise que le tour est fini	
//				}
//				jouerDeusEx();
//				
//				break;
//			case 4:
//				jouerApocalypse();
//				notEnd=false; //pr�cise que le tour est fini
//				break;
//			case 5:
//				if (this.getMainDuJoueur().peutJouerGS())
//				{
//					notEnd=false; //pr�cise que le tour est fini	
//				}
//				poserGuideSpirituel();
				
//				break;
			case 6:
//				if (this.getMainDuJoueur().peutJouerCroyant())
//				{
//					notEnd=true; //pr�cise qu'on va sortir du do/while					FAIRE LES METHODES POUR TESTER LE SACRIFICE OU CR2ER UN BOOLEAN
//				}
				poserCroyant();
				
				break;
			case 7:
				boolean cPB=true;
				do {
					System.out.println("Voulez-vous changer des point de jour (entrez 0) ou changer des point de nuit (entrez 1) ?\n");
					Scanner sc2 = new Scanner(System.in);
					int c2 = sc2.nextInt();
					switch (c2){
					case 0:
						if (this.pointActionJour>=2)
						{
							this.pointActionJour-=2;
							this.pointActionNeant+=1;
							System.out.println("Vous avez maintenant "+this.pointActionJour+" point de jour.");
							System.out.println("Vous avez maintenant "+this.pointActionNeant+" point de neant.");
							cPB=false;
						}
						else 
						{
							System.out.println("Vous n'avez pas assez de point de jour.");
							cPB=false;
						}
						break;
					case 1:
						if (this.pointActionNuit>=2)
						{
							this.pointActionNuit-=2;
							this.pointActionNeant+=1;
							System.out.println("Vous avez maintenant "+this.pointActionNuit+" point de nuit.");
							System.out.println("Vous avez maintenant "+this.pointActionNeant+" point de neant.");
							cPB=false;
						}
						else 
						{
							System.out.println("Vous n'avez pas assez de point de nuit.");
							cPB=false;
						}
						break;
					default :
						cPB=true;
					}}while (cPB);
				break;
			case 8:
				notEnd=false;
				setChanged();
				notifyObservers();
				break;
			default:
				return;
			}}
		/*
		setChanged();
		notifyObservers();
		*/
	}
	
	public void mettreAJourGraphique()
	{
		this.setChanged();
		this.notifyObservers();
	}
	
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
	
	public static boolean tableauContient ( Object[] tabO, Object o)
	{
		boolean contient = false;
		for (int i = 0; i < tabO.length; i++) 
		{
			if (tabO[i]==o )
			{
				contient=true;
			}	
		}
		return contient;
	}

	//=========================================================================================================================================================================
	public int getNumJoueur() {
		return numJoueur;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
	public int getNumTour() {
		return numTour;
	}
	public void setNumTour(int numTour) {
		this.numTour = numTour;
	}
	public int getPointActionJour() {
		return pointActionJour;
	}
	public void setPointActionJour(int pointActionJour) {
		this.pointActionJour = pointActionJour;
	}
	public int getPointActionNuit() {
		return pointActionNuit;
	}
	public void setPointActionNuit(int pointActionNuit) {
		this.pointActionNuit = pointActionNuit;
	}
	public int getPointActionNeant() {
		return pointActionNeant;
	}
	public void setPointActionNeant(int pointActionNeant) {
		this.pointActionNeant = pointActionNeant;
	}
	public String getOrigineDivinite() {
		return OrigineDivinite;
	}
	public void setOrigineDivinite(String origineDivinite) {
		OrigineDivinite = origineDivinite;
	}
	public CarteMain getMainDuJoueur() {
		return mainDuJoueur;
	}
	public void setMainDuJoueur(CarteMain mainDuJoueur) {
		this.mainDuJoueur = mainDuJoueur;
	}

	public Divinite getDivinite() {
		return divinite;
	}
	public void setDivinite(Divinite divinite) {
		this.divinite = divinite;
	}
	public String getNomJoueur() {
		return nomJoueur;
	}
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	public EspaceJoueur getEspaceDuJoueur() {
		return espaceDuJoueur;
	}
	public void setEspaceDuJoueur(EspaceJoueur espaceDuJoueur) {
		this.espaceDuJoueur = espaceDuJoueur;
	}
	public int getNombreDePriere() {
		return nombreDePriere;
	}
	public void setNombreDePriere(int nombreDePriere) {
		this.nombreDePriere = nombreDePriere;
	}
	public boolean isaJoue() {
		return aJoue;
	}
	public void setaJoue(boolean aJoue) {
		this.aJoue = aJoue;
	}
	public boolean isPeutSacrifierCroyant() {
		return peutSacrifierCroyant;
	}
	public void setPeutSacrifierCroyant(boolean peutSacrifierCroyant) {
		this.peutSacrifierCroyant = peutSacrifierCroyant;
	}
	public boolean isPeutSacrifierGuideSP() {
		return peutSacrifierGuideSP;
	}
	public void setPeutSacrifierGuideSP(boolean peutSacrifierGuideSP) {
		this.peutSacrifierGuideSP = peutSacrifierGuideSP;
	}
	public String[] getDogme() {
		return  divinite.getDogmes();
	}
	public void setDogme(String[] dogme) {
		this.dogme = dogme;
	}
	public CarteCentreTable getEspaceCentre() {
		return espaceCentre;
	}
	public void setEspaceCentre(CarteCentreTable espaceCentre) {
		this.espaceCentre = espaceCentre;
	}
	public Pile getPilePartie() {
		return pilePartie;
	}
	public void setPilePartie(Pile pilePartie) {
		this.pilePartie = pilePartie;
	}



	/**
	 * Impl�mentation de la m�thode de Joueur,
	 * cette m�thode ne sert pas � cette classe, mais elle est n�c�ssaire et donc pr�sente dans Joueur
	 * Elle doit donc �tre impl�ment� ici 
	 */
	public boolean jouerApocalypseAI() {
		return false;
	}
	public VueMainJoueurP getVueMainJoueur() {
		return vueMainJoueur;
	}
	public void setVueMainJoueur(VueMainJoueurP vueMainJoueur) {
		this.vueMainJoueur = vueMainJoueur;
	}
	public VueStatJoueur getVueStatJoueur() {
		return vueStatJoueur;
	}
	public void setVueStatJoueur(VueStatJoueur vueStatJoueur) {
		this.vueStatJoueur = vueStatJoueur;
	}


}
