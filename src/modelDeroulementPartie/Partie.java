package modelDeroulementPartie;
import java.util.*;

import modelCarte.Croyant;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;
import vueAffichage.ChoixEspace;
/**
 * 
 * La classe Partie est essentiellement une LinkedList de Joueur avec des méthodes permettant de déterminer des 
 * informations sur les joueurs présent dans la partie
 *
 */
public class Partie {
/**
 * LinkedList contenant les joueurs phsique et virtuel présent dans la partie
 */
	private ChoixEspace choixEspace;
	
	protected static LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	/**
	 * Sert à la méthode cEstLApocalypse() de Apocalypse, relativement au règles concernant l'apocalypse
	 */
	private static boolean ApoJoue;
	/**
	 * Sert à la méthode cEstLApocalypse() de Apocalypse, relativement au règles concernant l'apocalypse
	 */
	private static int nbTourPrive =1;
	/**
	 * Le joueur humain est le seul à pouvoir sacrifier des cartes et a pouvoir jouer des DeusEx. C'est pourquoi certaines cartes prennent en argument directement 
	 * le retour du getter de cet attribut.
	 */
	private static Joueur joueurHumain;
	
	public static Joueur getJoueurHumain ()
	{
		for ( int i=0 ; i < joueurs.size(); i++  ) 
		{
			if (joueurs.get(i) instanceof JoueurPhysique)
			{
				Partie.joueurHumain = joueurs.get(i);
			}
		}
		return joueurHumain;
	}
	/**
	 * Le singleton de la pile est necessaire, il est donc présent en attribut
	 */
	private Pile pilePartie = Pile.getInstance();
	
	//patron SINGLETON
	/**
	 * Constructeur de la partie (Privé pour l'implémentation du patron singleton)
	 * @param nbAIJoueurs
	 * le nombre de joueur virtuels que l'on désire dans la partie
	 * @param nomJoueur
	 * le nom du joueur physique, sert pour l'instanciation de ce dernier
	 */
	private Partie(int nbAIJoueurs,String nomJoueur)
	{
		joueurs=new LinkedList<Joueur>();
		joueurHumain = JoueurPhysique.constructeurJoueurAvecAssociation(0,nomJoueur);
		joueurs.add(joueurHumain);
		for ( int i=1 ; i<=nbAIJoueurs ; i++)
		{
			Joueur joueur=JoueurVirtual.constructeurJoueurAvecAssociation(i);
			joueurs.add(joueur);
		}
		//CONVERTIR LA LINKEDLIST EN TABLEAU DE J
		//SI LE RAFRAICHIRSIMPLE DE CHOIX ESPACE marche pas choper le component, le remove et le add et validate le tout
		//OU BIEN CREER UNE CLASSE QUI VA OBS ET QUI VA APPELER DES M2THODES POUR update le choix espace
		
		LinkedList <Joueur> listJ = joueurs;
		Iterator <Joueur> iteJ = listJ.iterator();
		Joueur[] tabJ = new Joueur[listJ.size()];
		int i=0;
		while (iteJ.hasNext())
		{
			Joueur j = iteJ.next();
			tabJ[i]=j;
			i++; 									//ce bloc de ligne créer un tab de Joueur a partir de la linkedlist, requis pour le constructeur suivant
		}
		
		this.choixEspace = ChoixEspace.getInstanceChoixEspace(tabJ); // est censé créer le premier choixEspace
	}
	private static Partie instancePartie = null;
	public static Partie getInstancePartie (int nbJoueurs, String nomJoueur)
	{
		if (instancePartie == null)
		{
			instancePartie = new Partie(nbJoueurs, nomJoueur);
		}
		return instancePartie;
	}
	/**
	 * Méthode simple permettant de pouvoir avoir le nombre de joueur sans avoir besoin de posseder la partie en attribut
	 * @return
	 * Le nombre de joueur dans cette partie
	 */
	public static int compterLesJoueurs ()
	{
		int nbJoueurs=joueurs.size();		
		return nbJoueurs;
	}
	public Pile getPilePartie() {
		return pilePartie;
	}
	/**
	 * Donne le meilleur Joueur de la partie (en nombre de prières données par les croyants). Utilisé par cEstLapocalypse() et par la classe PhaseVirtual
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 * @see modelDeroulementPartie.PhaseVirtual#joueDePhase()
	 * @return
	 * Soit le meilleur joueur soit un objet Joueur null si il y a une égalité, la méthode cEstLapocalypse() et l'I.A choissisent leurs comportement en fonction de ce résultat
	 */
	public static Joueur getMeilleurJoueur ()
	{	boolean egalite = false;
		int nombrePriereActuelle;
		Joueur JoueurQuiALePlusDePriere = null;
		int nombrePriereMax = 0;
		Iterator <Joueur> iterateur = joueurs.iterator();
		while (iterateur.hasNext()) //pour toutes les joueurs, les prière sont compté et on place le plus grand dans JoueurQuiALePlusDePriere
		{
			Joueur objetTest = iterateur.next();
			nombrePriereActuelle=(objetTest).compterLesPrieres ();
			if (nombrePriereActuelle>nombrePriereMax)
			{
				JoueurQuiALePlusDePriere=objetTest;
				egalite=false;
			}
			if (nombrePriereActuelle==nombrePriereMax)
			{
				egalite=true;
			}
		}
		if (egalite)
		{
			JoueurQuiALePlusDePriere =null;
			
		}
		return JoueurQuiALePlusDePriere;
				
	}
	/**
	 * Donne le pire Joueur de la partie (en nombre de prières données par les croyants). Utilisé par cEstLapocalypse() et par la classe PhaseVirtual.
	 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
	 * @see modelDeroulementPartie.PhaseVirtual#joueDePhase()
	 * @return
	 * Soit le pire joueur soit un objet Joueur null si il y a une égalité, la méthode cEstLapocalypse() et l'I.A choissisent leurs comportement en fonction de ce résultat
	 */
	public static Joueur getPlusMauvaisJoueur()
	{	boolean egalite = false;
		Joueur JoueurQuiALeMoinsDePriere = null;
		int nombrePriereMin = 1000;
		Iterator<Joueur> iterateur = joueurs.iterator();
		while (iterateur.hasNext()) //pour toutes les joueurs, les prière sont compté et on place le plus petit dans JoueurQuiALeMoinsDePriere
		{
			Joueur objetTest = iterateur.next();
			int nombrePriereActuelle=objetTest.compterLesPrieres ();
			if (nombrePriereActuelle<nombrePriereMin)
			{
				JoueurQuiALeMoinsDePriere=objetTest;
				egalite=false;
			}
			if (nombrePriereActuelle==nombrePriereMin)
			{
				egalite=true;
			}
			
		}
		if (egalite)
		{
			JoueurQuiALeMoinsDePriere =null;
			
		}
		return JoueurQuiALeMoinsDePriere;
	}
	/**
	 * Cette méthode renvoie TRUE si le Joueur est dans les première moitié des Joueur
	 */
	public static boolean verifieSiJoueurDansMoyenneSup(Joueur joueur)
	{
		boolean dansPremiereMoitie = false;
		LinkedList <Joueur> listJ = joueurs;
		Iterator <Joueur> iteJ = listJ.iterator();
		int jEnDessous=0;
		int jAuDessus=0;
		while (iteJ.hasNext())
		{
			Joueur jTest = iteJ.next();
			if (jTest.compterLesPrieres() < joueur.compterLesPrieres())
			{
				jEnDessous++;
			}
			if (jTest.compterLesPrieres() > joueur.compterLesPrieres())
			{
				jAuDessus++;
			}
		}
		if (jAuDessus > jEnDessous)//vérifie si il y a plus de J au dessus ou en dessous, il renverra vrai ou faux en fonction de ceci
		{
			dansPremiereMoitie = true;
		}
		return dansPremiereMoitie;
	}
	
	public static LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public static void setJoueurs(LinkedList<Joueur> joueurs) {
		Partie.joueurs = joueurs;
	}

	
	public static boolean isApoJoue() {
		return ApoJoue;
	}

	public static void setApoJoue(boolean apoJoue) {
		ApoJoue = apoJoue;
	}

	public static int getNbTourPrive() {
		return nbTourPrive;
	}

	public static void setNbTourPrive(int nbTourPrive) {
		Partie.nbTourPrive = nbTourPrive;
	}
}
