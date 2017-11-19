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
 * Ceci est la classe qui permet au joueur humain d'intéragir avec le jeu. Plusieurs méthodes de Joueur implémenté. Certaines méthodes de 
 * Joueur sont implémenter mais reste vide. Ceci est dû au fait que certaines méthodes sont utiles à l'IA et pas au joueur ou inversement. Elle 
 * sont implémentées ici pour que la classe ne soit abstraites elle aussi.
 * @see Joueur
 * @see JoueurVirtual
 *
 */
public class JoueurPhysique extends Joueur
{
	/**
	 * La vue de la main du joueur, créer dans le second constructeur
	 */
	private VueMainJoueurP vueMainJoueur;
	/**
	 * Differents statistiques concernant le Joueur sont montrer par cette vue.
	 */
	private VueStatJoueur vueStatJoueur;
	private boolean valide=true;
	/**
	 * Contructeur de la classe JoueurPhysique, cette méthode attribut aussi une divinité au joueur. En instanciant un objet de la classe correspondant à la divinité.
	 * Elle attribut ensuite au joueur les dogmes et l'origine de sa divinité.
	 * Elle renseigne également le joueur sur la divinité qui lui à été attribué. Notons que plusieurs joueurs peuvent tomber sur la même divinités.
	 * @param numJoueur
	 * Donné par la classe Tour quand le joueur humain est construit
	 * @param nom
	 * Demandé au joueur en début de partie
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
		System.out.println("Votre divinité est "+this.divinite.getNom()+".\n Il/Elle est d'origine "+this.divinite.getOrigine()+".\n");
		for (int k=0; k<this.divinite.getDogmes().length ; k++)
		{
			System.out.println("Son dogme n°"+k+" est : "+this.divinite.getDogmes()[k]);
		}
		this.pointActionJour=0;
		this.pointActionNuit=0;
		this.pointActionNeant=0;


	}
	/**
	 * ON N'APPELLE PAS LE CONSTRUCTEUR DE JOUEUR PHYSIQUE, on appelle plutot cette methode, elle construit le joueur et associe le joueur à son espace et à sa main.
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
		joueur.vueEspace = new VueEspace(numJoueur, joueur);//créer la vue de l'espace du joueur
		joueur.vueMainJoueur = new VueMainJoueurP(joueur); // créer la vue de la main du joueur
		joueur.vueStatJoueur = new VueStatJoueur (joueur); //créer la vue des stats du joueur
		joueur.addObserver(joueur.vueEspace);
		joueur.addObserver(joueur.vueMainJoueur);
		joueur.addObserver(joueur.vueStatJoueur);
		return joueur;
	}




	/**
	 * Implémente la méthode éponyme de Joueur.
	 * On demande au joueur combien de carte doivent être défaussées (Le do/while sert à s'assurer que le choix du joueur est cohérent.)
	 * Après cela on appelle autant de fois la méthode supprimerCarte() de CarteMain que le joueur l'a désiré.
	 * @see modelJoueur.CarteMain#supprimerCarte()
	 * @see modelJoueur.Joueur#defausserCarte()
	 */
	public void defausserCarte()
	{
		System.out.println("Combien de cartes souhaitez-vous défaussez ?\n");
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
	 * Implémente la méthode de Joueur, appelant PiocherCarte, une méthode de CarteMain.
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
	 * Implémente la méthode de Joueur
	 * Demande au joueur de choisir un croyant présent dans sa main. On vérifie que son choix est cohérent.
	 * Ensuite on fait appel à la méthode sacrifier du croyant. Cepandant la classe Croyant n'est jamais instanciée. 
	 * On appel donc la méthode surchargée des classes qui héritent de Croyant.
	 * Après avoir fait appel au sacrifier, on retire la carte de l'espace du Joueur. (Nous avons considéré que les croyants ne pouvait être sacrifié
	 * que si il était sur le terrain du Joueur. En effet certaine carte sont serait déséquilibré sans cela. Cependant ce choix prive des cartes comme Esprit ou Moines 
	 * d'une grande partie de leur utilité. Nous affinerons cette règles pour le Livrable 3.)
	 * Notons qu'on utilise la méthode supprimerCroyant() de EspaceJoueur, elle permet de mettre a jour certain atrribut gérant la relation entre les cartes.
	 * (Par exemple quel guide détient quels croyants).
	 * Après avoir retiré la carte de l'espace du joueur il ne reste plus qu'a la remettre dans la pile.
	 * La méthode ajouterCarteDansPile() permet aussi de mettre à jour certaine propriété des CartesAction.
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
	 * Implémente la méthode poserCroyant() de Joueur.
	 * C'est une version sans argument ne pouvant servir qu'un joueur physique.
	 * On commence par faire choisir le joueur en lui présentant les croyants de sa main
	 * Le booleen peutJoue permet de vérifier si le joueur a suffisament de point d'action pour jouer la carte qu'il à choisit.
	 * (Si ce n'est pas le cas, la méthode saute directement à la fin et informe le joueur qu'il n'a pas la possibilité de jouer ce croyant.)
	 * Ensuite ajoute cette carte dans l'espace du Joueur et on l'enlève de sa main.
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
			System.out.println("Choissisez le croyant que vous désirez poser, entrer son INDEX.\n");			//Verifie si l'INDEX entré est bien celui d'un croyant qui appartient a la main du joueur
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
			mainDuJoueur.supprimerCarte(mainDuJoueur.getMainDuJoueur().get(idChoix)); 			// enlève de la main du joueur

		}
		else 
		{
			System.out.println("\nVous n'avez pas les points requis pour jouer ce croyant.\n");
		}
		setChanged();
		notifyObservers();
	}
	/**
	 * Implémente la méthode de Joueur
	 * On commence par montrer au joueur les guide dont il dispose, puis on lui fait faire son choix et on vérifie qu'il soit cohérent avec le Do/while.
	 * Comme pour la méthode PoserCroyant() de cette même classe. On commence par vérifier si le joueur à les point d'action correspondant à la carte qu'il désire jouer.
	 * Si ce n'est pas le cas la méthode saute à la fin et
	 * Ensuite pour chaque Croyant au centre de la table (dans le singleton EspaceCentreTable) on vérifie si : 
	 * - Il y a un dogme en commun
	 * - Si le nombre de croyant que le joueur désire/que le guide peut supporter n'est pas dépasser
	 * - Si le joueur est d'accord pour guider ce croyant
	 * Si les conditions  sont reunis il ajoute le croyant dans un tableau et itère sur le prochain croyant de l'espace centrale.
	 * Et on supprime le croyant de l'espace du joueur
	 * A la fin, si au moins un croyant à été placé dans le tableau alors on retranche le point correspondant à l'origine du Guide.
	 * On met ensuite le tableau et le guide en argument de la méthode ajouterGuideSpirituel() de EspaceJoueur. On retranche au joueur les points correspondant à la carte.
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
			while (   iterateur.hasNext() && n<=leGuide.getNbCroyantMax() ) //tant que nb de croyant ajouter est inférieur a [nb croyant que on veut, nb croyant max que guide peut guider]
			{
				Croyant leTest = iterateur.next();

						if (this.dogmeCommun(leGuide, leTest) && !(JoueurPhysique.tableauContient(tabCroyant, leTest)) ) //méthode dédiée à la vérification des dogmes
						{
							System.out.println("Voulez-vous ajouter ce croyant à la liste des adeptes de ce guides. ?");
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
									//Donc pas celui sur lequel on itère => pas de prob
								}
							}while(choixPasBon);
							choixPasBon=false;


						}
					}
			
			if (leGuide.getOrigine().equals("Jour")&& gsUtilise)
			{
				this.pointActionJour--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enlève le guide de la main
			}
			else if (leGuide.getOrigine().equals("Nuit")&& gsUtilise)
			{
				this.pointActionNuit--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enlève le guide de la main
			}
			else if (leGuide.getOrigine().equals("Neant")&& gsUtilise)
			{
				this.pointActionNeant--;
				espaceDuJoueur.ajouterGuideSpirituel(tabCroyant, leGuide);
				this.getMainDuJoueur().supprimerCarte(leGuide);    			//enlève le guide de la main
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
	 * Implémente la méthode de Joueur 
	 * Affiche les guides sacrifiables que le joueur possède. Vérifie que son choix est correcte.
	 * Utilise la méthode SupprimerGuideSpirituel() de EspaceJoueur qui met a jour les information dans le guide et qui défausse les croyant
	 * Appel la capacité sacrifier() du guide
	 * Place le guide dans la pile grace à la méthode de Pile
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
		// on a retirer les croyant qui y sont attachï¿½s
		pilePartie.ajouterCarteDansPile(guideSelect);//on remet dans pile
		
		if (this.valide=true) {
		 guideSelect.sacrifier();//on utilise la capacitï¿½ sacrifice
	}
		this.valide=true;
		setChanged();
		notifyObservers();


	} 
	/**
	 * Implémente la méthode de Joueur
	 * Affiche les cartes Deus Ex du joueur
	 * Recupère son choix en s'assurant qu'il soit cohérent
	 * Appel la méthode de la carte DeusEx
	 * Met la carte dans la pile
	 * @see modelDeroulementPartie.Pile#ajouterCarteDansPile(CarteAction)
	 * @see Model.DeuxEx#sacrifier()
	 * 
	 */
	public void jouerDeusEx()
	{

		System.out.println("Voici les cartes deus ex que vous posséder :\n");
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
	 * Implémente la méthode de Joueur
	 * affiche les cartes apocalypse a disposition du joueur, récupère son choix et vérifie qu'il est cohérent.
	 * Utilise la méthode static si les conditions  concernant le cout en point de la carte sont reunient
	 * Enlève la carte de de la mainDuJoueur
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
			if (mainDuJoueur.getMainDuJoueur().contains(mainDuJoueur.getMainDuJoueur().get(idChoix)) && mainDuJoueur.getMainDuJoueur().get(idChoix) instanceof Apocalypse )//vérifie que le joueur rentre un index d'une carte qu'il a ET que c'est une carte Apocalypse  
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
	 * Implémente la méthode de Joueur, cette méthode permet à l'IA de choisir quand mettre une carte apocalypse, 
	 * et au carte Apocalypse de désigner le vainqueur ou celui qui sera exclu du jeu. Elle fait appel à l'espace du joueur
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 * @see modelJoueur.EspaceJoueur#compterToutesLesPrieres()
	 */
	public int compterLesPrieres ()
	{
		return espaceDuJoueur.compterToutesLesPrieres();
	}
	/**
	 * Méthode qui sera appeller par la classe Phase dès que le joueur désirera faire autre chose que Défausser des cartes ou compléter sa main.
	 * C'est essentiellement un ordonnanceur qui ne fait qu'appeler d'autre méthode présente dans joueur. A part le choix n°7.
	 * Il permet de convertir des point Jour/Nuit en point de néant, conformément aux règles de Pandocréon Divinae
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
			System.out.println("7:changer des points Jour/Nuit en point néant");
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
//					notEnd=false; //précise que le tour est fini	
//				}
//				jouerDeusEx();
//				
//				break;
//			case 4:
//				jouerApocalypse();
//				notEnd=false; //précise que le tour est fini
//				break;
//			case 5:
//				if (this.getMainDuJoueur().peutJouerGS())
//				{
//					notEnd=false; //précise que le tour est fini	
//				}
//				poserGuideSpirituel();
				
//				break;
			case 6:
//				if (this.getMainDuJoueur().peutJouerCroyant())
//				{
//					notEnd=true; //précise qu'on va sortir du do/while					FAIRE LES METHODES POUR TESTER LE SACRIFICE OU CR2ER UN BOOLEAN
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
	 * Implémentation de la méthode de Joueur,
	 * cette méthode ne sert pas à cette classe, mais elle est nécéssaire et donc présente dans Joueur
	 * Elle doit donc être implémenté ici 
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
