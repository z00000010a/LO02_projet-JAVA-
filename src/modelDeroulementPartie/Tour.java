package modelDeroulementPartie;
import java.util.*;

import Test.Test;
import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarteDeusEx.ColereDivine1;
import modelCarteDeusEx.ColereDivine2;
import modelCarteDeusEx.Concentration;
import modelCarteDeusEx.InfluenceJour;
import modelCarteDeusEx.InfluenceNulle1;
import modelCroyantJour.Moines1;
import modelGuideSpirituel.Clerc;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;
import vueAffichage.VueEspace;
import vueAffichage.VueEspaceCentral;
import vueAffichage.VueMainJoueurP;
import vuePopUpInterrogerJoueur.DemanderDebutPartie;
import vuePopUpInterrogerJoueur.DemanderUtiliserCapaDiv;
import vuePopUpInterrogerJoueur.DemanderUtiliserCarteONull;

/**
 * Tour est la classe qui va gérer tout le jeu et appeler les autres méthodes et les autres classes. C'est le grand ordonnanceur.
 * Elle est composée de deux constructeurs et de la méthode servant à lancer le dés.
 * On notera la présence de l'instruction Thread.sleep() qui sert à fournir une meilleur visibilité des evenement se déroulant sur la console.
 * 
 *
 */
public class Tour  {
	
	
	/**
	 * Numéro de Tour, utile pour certaine règle de Pandocréon, spécifier à la construction, dispenser à chaque nouveau tour
	 */
	private static int numeroDeTour;
	/*
	 * La liste des joueurs physique et virtuels sous la forme d'une LinkedList
	 */
	private LinkedList<Joueur> joueurs;
/**
 * 	 Attribut contenant le 1er joueur pour ce tour, choisit lors de la construction
 */
	private Joueur PremierJoueur;
	/**
	 * Le singleton Partie, utilisé par Tour, de plus certaine carte ont besoin d'une instance de Partie pour être créées.
	 */
	private static Partie partie;//créé pour créer les cartes, pour être mis en argument dans ajouterToutesLesCartes
	
/**
 * 1er constructeur de Tour, sert uniquement pour le 1er tour
 * On créé la partie, le joueur physique et les joueurs virtuel, ces derniers n'ont pas de nom, juste un numéro
 * On fait piocher tous les joueurs pour qu'il puissent commencer leur 1 tour avec des cartes
 * Puis on lance le dés et on itère sur la linkedList Joueur en appellant Phase si le joueur est Humain et PhaseVirtual si le joueur est virtuel.
 * On met le booléen aJoue sur Vrai après chaque phase de jeu de chaque joueur
 * @throws InterruptedException
 */

	
	public Tour() throws InterruptedException{
		/*
		System.out.println("Préparez-vous à affrontez d'innombrables ennemis !");
		Thread.sleep(1250);
		System.out.println("A vous battre pour la domination de ce monde et des mortels qui le composent !");
		Thread.sleep(1000);
		System.out.print("Bienvenue ");
		Thread.sleep(1000);
		System.out.print("dans PANDOCREON DIVINAE ! \n");
		Thread.sleep(1000);
		System.out.println("Il est maintenant temps d'entrer votre nom !");
		Scanner s1=new Scanner(System.in);
		String nom=s1.nextLine();
		System.out.println("Combien de divinités désirez-vous affrontez ? (pas plus de 5)");
		int numbre=s1.nextInt();
		*/
		partie = Partie.getInstancePartie (DemanderDebutPartie.getNbJV(),DemanderDebutPartie.getNomJ()); /// CHANGEMENT CAR SINGLETON IMPL2MENT2
		Partie.setApoJoue(true); //empecher jouer apo au 1er tour
		this.joueurs=Partie.joueurs;
		Iterator <Joueur> iterateurCartePiocher = joueurs.iterator();
		while (iterateurCartePiocher.hasNext())
		{
			Joueur j = iterateurCartePiocher.next();
			j.piocherCarte();
		}
		this.numeroDeTour=1;
		System.out.println("Tour"+this.numeroDeTour);
		lanceDeDe();
		
//===========================================================================================================================	
		
//		Partie.getJoueurs().get(1).piocherCarte();
//		InfluenceNulle1 d1=new InfluenceNulle1();
////		ColereDivine2 d2=new ColereDivine2();
////		ColereDivine2 d3=new ColereDivine2();
////		ColereDivine2 d4=new ColereDivine2();
////		ColereDivine2 d5=new ColereDivine2();
////		ColereDivine2 d6=new ColereDivine2();
////		ColereDivine2 d7=new ColereDivine2();
////		ColereDivine2 d8=new ColereDivine2();
//		Moines1 m1=new Moines1();
//		Moines1 m2=new Moines1();
//		Croyant[] cry={m1,m2};
//		Clerc clerc1 = new Clerc("Humain","Chaos","Jour",1);
//		Partie.getJoueurs().get(1).getEspaceDuJoueur().ajouterGuideSpirituel(cry, clerc1);
////		
//		Partie.getJoueurHumain().getMainDuJoueur().getMainDuJoueur().add(d1);
//		((JoueurPhysique) Partie.getJoueurHumain()).mettreAJourGraphique();
//
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d2);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d3);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d4);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d5);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d6);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d7);
//		Partie.getJoueurs().get(1).getMainDuJoueur().getMainDuJoueur().add(d8);
//		Partie.getJoueurs().get(1).setPointActionNuit(2);

		
		
		
//=======================================================================================================
		
		
		Iterator <Joueur> ite = joueurs.iterator();
		while (ite.hasNext()){
		Joueur j=ite.next();
		if (j instanceof JoueurPhysique)
		{
			Phase phase=new Phase(j);
			phase.joueDePhase();
		}
		else
		{
			PhaseVirtual phaseV = new PhaseVirtual ((JoueurVirtual) j);
			phaseV.joueDePhase();
			
			if (!(Partie.getJoueurHumain().getDivinite().isCapaciteUtilise()))
			{
				DemanderUtiliserCapaDiv dem = new DemanderUtiliserCapaDiv(Partie.getJoueurHumain().getDivinite());
				Thread t = new Thread (dem);
				DemanderUtiliserCapaDiv.setT(t);
				t.start();
				Test.getVerrouThread().notifyAll();
				Test.getVerrouThread().wait();
			}
		}

		j.setaJoue(true);
		this.PremierJoueur=joueurs.getLast();
		
		}
	}
	/**Après le 1er tour on utilise le 1er tour pour le mettre en argument de ce constructeur. Voir la classe Test du Package Test pour voir comment les tours sont créés.
	 * Le 1er if/else sert à définir si oui ou non l'on pourra jouer des cartes apocalypses pendant ce tour, en effet si ApoJoue est vrai, la méthode cEstLApocalypse 
	 * de Apocalypse ne pourra pas fonctionner.
	 * @param tour
	 * Le 1er tour est utilisé pour créer le second tour, puis le 2ème est utilisé en argument pour créer le 3ème...
	 * Le roulement des joueurs est réalisé en prenant le dernier joueur du tour en argument et en le mettant en 1er dans la liste des joueurs
	 * Dans cette méthode on rétablit les aJoue à faux
	 * On annonce le 1er joueur, on fait défiler tous les joueurs si ils n'ont pas joue (booléen aJoue) en fonction de leur type on créer une Phase ou une PhaseVirtual
	 * et on appelle leur méthode joueDePhase()
	 * Pour chaque phase de chaque joueur, cette méthode propose au joueur si il y a lieu d'utiliser une carte apocalypse 
	 * sans origine ou une carte deusEx ou sa capacité divine
	 * @throws InterruptedException
	 */
	public Tour(Tour tour) throws InterruptedException{
		if (Partie.getNbTourPrive()>0 && Partie.isApoJoue()) //si Apojoue est true et que tour privé est >0 on remet a zéro les flag
		{
			Partie.setApoJoue(false); // si tour privé d'apocalypse est > 0 on enlève le flag qui empèche apocalypse
			Partie.setNbTourPrive(0); //on le remet à zéro
		}
		else if (Partie.getNbTourPrive()==0 && Partie.isApoJoue()) //si une apo à été joué mais qu'il n'a a pas eu de tour privé d'apo
		{
			Partie.setApoJoue(true); //sinon on le laisee
			Partie.setNbTourPrive(1); //on incrément le nb de tour comme ça au prochain, le flag sera supprimé
		}
		Tour.numeroDeTour=Tour.numeroDeTour+1;
		this.joueurs=Partie.joueurs;
		Iterator <Joueur> itera = joueurs.iterator();
		while (itera.hasNext())
		{
			Joueur j = itera.next();
			j.setaJoue(false);
		}
		this.PremierJoueur=tour.PremierJoueur;
		joueurs.removeLast();
		joueurs.addFirst(PremierJoueur);
		System.out.println("Tour: "+this.numeroDeTour+"\n"+". Le premier joueur "
					+ "pour ce tour: joueur"+joueurs.getFirst().getNumJoueur());
		lanceDeDe();
		if(!joueurs.get(0).isaJoue())  
		{
			
		
		for(int k=0;k<joueurs.size();k++){
			if (joueurs.get(k).isaJoue()==false) {
				
			
			Joueur j=this.joueurs.get(k);
			System.out.println("Index: "+k+" Joueur: "+joueurs.get(k).getNumJoueur()+" Point Action: Jour:"+j.getPointActionJour()
			+" Nuit"+j.getPointActionNuit()+" Neant"+j.getPointActionNeant());
			if (j instanceof JoueurPhysique)
			{
				Phase phase=new Phase(j);
				phase.joueDePhase();
				/*//DEMANDE SI VEUX USER CAPA DIV
				if (!(j.getDivinite().isCapaciteUtilise()))
				{
					DemanderUtiliserCapaDiv dem = new DemanderUtiliserCapaDiv(j.getDivinite());
					Thread t = new Thread (dem);
					DemanderUtiliserCapaDiv.setT(t);
					t.start();
					Test.getVerrouThread().notify();
					Test.getVerrouThread().wait();
				}
				*/
			}
			else
			{
				PhaseVirtual phaseV = new PhaseVirtual ((JoueurVirtual) j);
				phaseV.joueDePhase();
				/*
				if (!(j.getDivinite().isCapaciteUtilise()))
				{
					DemanderUtiliserCapaDiv dem = new DemanderUtiliserCapaDiv(j.getDivinite());
					Thread t = new Thread (dem);
					DemanderUtiliserCapaDiv.setT(t);
					t.start();
					Test.getVerrouThread().notify();
					Test.getVerrouThread().wait();
				}
				*/
			}
			
			
			//Ademande si le joueur physique veut utiliser une  Apocalypse sans Origine

					Joueur jou=Partie.getJoueurHumain();
					Iterator <CarteAction> ite3 = jou.getMainDuJoueur().getMainDuJoueur().iterator();
					while(ite3.hasNext()) {
						CarteAction carte=ite3.next();
						if (carte.getOrigine()=="Null"&&carte instanceof Apocalypse) {
							System.out.println("Voulez vous utiliser votre carte Apocalypse sans origine ?"+"\nOui:1/Non:2");
							Scanner s=new Scanner(System.in);
							int in1=s.nextInt();
							if(in1==1){
								Apocalypse.cEstLapocalypse();
								jou.getMainDuJoueur().supprimerCarte(carte);
								Pile.getInstance().ajouterCarteDansPile(carte);
							}
						}
					}
					/*
					LinkedList <CarteAction> listeC = new LinkedList<CarteAction>();
					listeC.addAll(jou.getMainDuJoueur().getMainDuJoueur());
					Iterator <CarteAction> ite4 = listeC.iterator();
					while(ite4.hasNext()) {
						CarteAction carte=ite4.next();
						if (carte.getOrigine()=="Null"&&carte instanceof DeusEx) {
////							System.out.println("Vous avez "+(DeusEx)carte);
//							System.out.println("Vous avez ");
//							System.out.println("Voulez vous l'utiliser ?"+carte+"\nOui:1/Non:2");
//							Scanner s23=new Scanner(System.in);
//							int in5=s23.nextInt();
//							if(in5==1){
//								carte.sacrifier();
//								jou.getMainDuJoueur().supprimerCarte(carte);
//								Pile.getInstance().ajouterCarteDansPile(carte);
//							}
							DemanderUtiliserCarteONull demande = new DemanderUtiliserCarteONull(carte);
							Thread t = new Thread(demande);
							demande.setT(t);
							t.start();
							Test.getVerrouThread().notifyAll();
							Test.getVerrouThread().wait();
						}
					}
					*/
				
			
			//demande capacitéDivine en dehors du tour
			if (Partie.getJoueurHumain().getDivinite().isCapaciteUtilise()==false)
			{
				System.out.println("Désirez-vous utiliser votre capacité Divine ?");
				System.out.println("Description de la capacité : "+Partie.getJoueurHumain().getDivinite().getDescriptionCapacite());
					boolean choixPasBon=true;
					int iff=0;
				do {
					System.out.println("1 pour déchainer la colère divine | 0 pour préserver les mortels de votre courroux.");
					Scanner s2=new Scanner(System.in);
					iff=0;
					if (iff==1 || iff==0)
					{
						choixPasBon=false;
					}
				}while(choixPasBon);
				if (iff == 1)
				{
					Partie.getJoueurHumain().getDivinite().CapaciteDivine();
					Partie.getJoueurHumain().getDivinite().setCapaciteUtilise(true);
				}
				
				
			}
			j.setaJoue(true);
			}}}this.PremierJoueur=joueurs.getLast();
	}
	/**
	 * Une méthode dés pour lancer le dés de cosmogonie. La méthode pseudo-aléatoire nextInt(n) est utilisé pour générer un lancement de dés. 
	 * Les joueurs reçoivent ensuite les points correspondant au jet du dés en fonction de leur origines.
	 * Le thread.sleep() sert toujours à rendre les évenements à l'écrant plus visible
	 * @throws InterruptedException
	 */
	public void lanceDeDe() throws InterruptedException{
		Random r=new Random();
		int n=r.nextInt(3);
		switch(n) {
		case 0:
			System.out.println("Pour ce tour le résultat du dés est : jour"+"\n");
			int i = 0;
			while(i < joueurs.size()) {
				Joueur j = joueurs.get(i);
				if(j.getOrigineDivinite() == "Jour") {
					j.setPointActionJour(j.getPointActionJour()+2);
				} else if(j.getOrigineDivinite() == "Aube") {
					j.setPointActionJour(j.getPointActionJour() + 1);
				}
				i++;
			}
			break;
		case 1:
			System.out.println("Pour ce tour le résultat du dés est : neant"+"\n");
			int k = 0;
			while(k < joueurs.size()) {
				Joueur j = joueurs.get(k);
				if(j.getOrigineDivinite() == "Aube" || j.getOrigineDivinite()
						== "Crepuscule") {
					j.setPointActionNeant(j.getPointActionNeant() + 1);
				}
				k++;
			}
			break;
		case 2:
			System.out.println("Pour ce tour le résultat du dés est : nuit"+"\n");
			int s = 0;
			while(s < joueurs.size()) {
				Joueur j = joueurs.get(s);
				if(j.getOrigineDivinite() == "Nuit") {
					j.setPointActionNuit(j.getPointActionNuit() + 2);
				} else if(j.getOrigineDivinite() == "Crepuscule") {
					j.setPointActionNuit(j.getPointActionNuit() + 1);
				}
				s++;
			}
			break;
		default:
			return;
		
		}
		//Thread.sleep(2000);
		((JoueurPhysique) Partie.getJoueurHumain()).mettreAJourGraphique();
	}
//====================================================================================
	
	public static Partie getPartie() {
		return partie;
	}

	public static void setPartie(Partie partie) {
		Tour.partie = partie;
	}
	public static int getNumeroDeTour() {
		return numeroDeTour;
	}


}