package vueBouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
 * Cette classe est un JButton qui contient un numéro de joueur, uniquement instancié par la classe DemanderUtiliserCarteSansThread.
 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread
 */
public class BoutonNumJoueur extends JButton {
	/**
	 * Le constructeur de la classe. Créer un bouton avec le numéro du joueur dessus. Une action est automatiquement 
	 * implémentée sur ce bouton : modifier l'attribut statique choixJoueur de DemanderUtiliserCarteSansThread.
	 * @param num Un entier représentant le numéro du joueur
	 */
	public BoutonNumJoueur(int num)
	{
		super("Joueur n°"+num);
		this.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent e) {DemanderUtiliserCarteSansThread.setChoixJoueur(num);}} );
	}
}
