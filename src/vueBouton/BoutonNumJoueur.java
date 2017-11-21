package vueBouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
 * Cette classe est un JButton qui contient un num閞o de joueur, uniquement instanci� par la classe DemanderUtiliserCarteSansThread.
 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread
 */
public class BoutonNumJoueur extends JButton {
	/**
	 * Le constructeur de la classe. Cr閑r un bouton avec le num閞o du joueur dessus. Une action est automatiquement 
	 * impl閙ent閑 sur ce bouton : modifier l'attribut statique choixJoueur de DemanderUtiliserCarteSansThread.
	 * @param num Un entier repr閟entant le num閞o du joueur
	 */
	public BoutonNumJoueur(final int num)
	{
		super("Joueur n'"+num);
		this.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent e) {DemanderUtiliserCarteSansThread.setChoixJoueur(num);}} );
	}
}
