package modelDeroulementPartie;
import java.util.*;

import Test.Test;
import controller.Controler;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import vueAffichage.VueMainJoueurP;
import vuePopUpInterrogerJoueur.DemanderUtiliserCapaDiv;
/**
 * Phase est une classe qui sera appel� par la classe Tour.
 * Elle est r�serv� au joueur humain, son homologue pour les I.A est la classe PhaseVirtual.
 * 
 * Cette classe g�re le d�roulement du tour du joueur humain
 *
 */



public class Phase {
	/**
	 * Chaque Phase cr��e contient le joueur humain
	 */
	private static JoueurPhysique joueur;
	/*
	 * Contructeur de la phase, requiert un joueur humain, ce consructeur est appel� par la classe Tour lorsque c'est au tour du joueur humain de jouer
	 * @parameter : joueur
	 * @see Control.Tour
	 */
	public Phase(Joueur joueur){
		this.joueur = (JoueurPhysique) joueur;
	}
/**
 * joueDePhase() est une m�thode qui sert � d�rouler le tour du joueur mis en attribut lors de l'instanciation de la classe.
 * Le menu de cette m�thode appelle des m�thodes pr�sentes dans JoueurPhysique
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

