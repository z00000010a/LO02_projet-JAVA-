package modelJoueur;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.Divinite;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Pile;
import vueAffichage.VueEspace;
/**
 * Classe abstraite permettant d'appeler des méthodes que l'on va surcharger dans les classe qui héritent de Joueur. Certaines méthodes existent en version AI 
 * et en version Joueur Humain. En effet pour des humain et des AI on a parfois besoin d'avoir des paramètres ou au contraires de les demander par un scanner à l'intérieur 
 * de la méthode.
 * @see JoueurPhysique
 * @see JoueurVirtual
 * 
 *
 */
public abstract class Joueur extends Observable {
	/**
	 * La vue de l'espace du Joueur, créé dans le constructeur
	 */
	protected VueEspace vueEspace;
	protected String nomJoueur;
	protected int numJoueur;
	protected int numTour;
	protected int pointActionJour;
	protected int pointActionNuit;
	protected int pointActionNeant;
	protected Divinite divinite;
	protected boolean aJoue;
	protected String OrigineDivinite;
	protected boolean peutSacrifierCroyant;
	protected boolean peutSacrifierGuideSP;
	protected String[] dogme;
	protected EspaceJoueur espaceDuJoueur;// = new EspaceJoueur();
	protected CarteCentreTable espaceCentre = CarteCentreTable.getCentreTable();
	protected int nombreDePriere;
	protected CarteMain mainDuJoueur;//= new CarteMain();
	protected Pile pilePartie = Pile.getInstance();
	
	public abstract void defausserCarte() throws InterruptedException;
	
	public abstract void piocherCarte() throws InterruptedException;

	public abstract void sacrifierCroyant();

	public abstract void poserCroyant() throws InterruptedException;
	
	public abstract void poserGuideSpirituel() throws InterruptedException;
	
	public abstract void sacrifierGuideSpirituel();
	
	public abstract void jouerDeusEx() throws InterruptedException;
	
	public abstract void jouerApocalypse();
	public abstract boolean jouerApocalypseAI();
		
	public abstract int compterLesPrieres ();
	public abstract boolean dogmeCommun (GuideSpirituel guide , Croyant croyant);
	
	public abstract void mettreAJourGraphique();

	public abstract void utiliser() throws InterruptedException;
//==================================================================
	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

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

	public Divinite getDivinite() {
		return divinite;
	}

	public void setDivinite(Divinite divinite) {
		this.divinite = divinite;
	}

	public boolean isaJoue() {
		return aJoue;
	}

	public void setaJoue(boolean aJoue) {
		this.aJoue = aJoue;
	}

	public String getOrigineDivinite() {
		return OrigineDivinite;
	}

	public void setOrigineDivinite(String origineDivinite) {
		OrigineDivinite = origineDivinite;
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
		return dogme;
	}

	public void setDogme(String[] dogme) {
		this.dogme = dogme;
	}

	public EspaceJoueur getEspaceDuJoueur() {
		return espaceDuJoueur;
	}

	public void setEspaceDuJoueur(EspaceJoueur espaceDuJoueur) {
		this.espaceDuJoueur = espaceDuJoueur;
	}

	public CarteCentreTable getEspaceCentre() {
		return espaceCentre;
	}

	public void setEspaceCentre(CarteCentreTable espaceCentre) {
		this.espaceCentre = espaceCentre;
	}

	public int getNombreDePriere() {
		return nombreDePriere;
	}

	public void setNombreDePriere(int nombreDePriere) {
		this.nombreDePriere = nombreDePriere;
	}

	public CarteMain getMainDuJoueur() {
		return mainDuJoueur;
	}

	public void setMainDuJoueur(CarteMain mainDuJoueur) {
		this.mainDuJoueur = mainDuJoueur;
	}

	public Pile getPilePartie() {
		return pilePartie;
	}

	public void setPilePartie(Pile pilePartie) {
		this.pilePartie = pilePartie;
	}

	public VueEspace getVueEspace() {
		return vueEspace;
	}

	public void setVueEspace(VueEspace vueEspace) {
		this.vueEspace = vueEspace;
	}
	
	
	
}
