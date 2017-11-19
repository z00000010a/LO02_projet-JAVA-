package modelDeroulementPartie;
import java.util.*;

import Test.Test;
import controller.Controler;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import vueAffichage.VueMainJoueurP;
import vuePopUpInterrogerJoueur.DemanderUtiliserCapaDiv;
/**
 * Phase est une classe qui sera appelé par la classe Tour.
 * Elle est réservé au joueur humain, son homologue pour les I.A est la classe PhaseVirtual.
 * 
 * Cette classe gère le déroulement du tour du joueur humain
 *
 */



public class Phase {
	/**
	 * Chaque Phase créée contient le joueur humain
	 */
	private static JoueurPhysique joueur;
	/*
	 * Contructeur de la phase, requiert un joueur humain, ce consructeur est appelé par la classe Tour lorsque c'est au tour du joueur humain de jouer
	 * @parameter : joueur
	 * @see Control.Tour
	 */
	public Phase(Joueur joueur){
		this.joueur = (JoueurPhysique) joueur;
	}
/**
 * joueDePhase() est une méthode qui sert à dérouler le tour du joueur mis en attribut lors de l'instanciation de la classe.
 * Le menu de cette méthode appelle des méthodes présentes dans JoueurPhysique
 * @throws InterruptedException 
 * @see modelJoueur.JoueurPhysique#utiliser()
 * @see modelJoueur.JoueurPhysique#piocherCarte()
 * @see modelJoueur.JoueurPhysique#defausserCarte()
 */
	public void joueDePhase() throws InterruptedException{
			
			Thread threadActionJoueur = new Thread(joueur.getVueMainJoueur());
			VueMainJoueurP.setT(threadActionJoueur);
			threadActionJoueur.start();

			Test.getVerrouThread().notifyAll();

			Test.getVerrouThread().wait();
			if (!(joueur.getDivinite().isCapaciteUtilise()))
			{
				DemanderUtiliserCapaDiv dem = new DemanderUtiliserCapaDiv(joueur.getDivinite());
				Thread t = new Thread (dem);
				DemanderUtiliserCapaDiv.setT(t);
				t.start();
				Test.getVerrouThread().notifyAll();
				Test.getVerrouThread().wait();
			}
		}
	}

