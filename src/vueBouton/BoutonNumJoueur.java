package vueBouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread;
/**
 * Cette classe est un JButton qui contient un num�ro de joueur, uniquement instanci� par la classe DemanderUtiliserCarteSansThread.
 * @see vuePopUpInterrogerJoueur.DemanderUtiliserCarteSansThread
 */
public class BoutonNumJoueur extends JButton {
	/**
	 * Le constructeur de la classe. Cr�er un bouton avec le num�ro du joueur dessus. Une action est automatiquement 
	 * impl�ment�e sur ce bouton : modifier l'attribut statique choixJoueur de DemanderUtiliserCarteSansThread.
	 * @param num Un entier repr�sentant le num�ro du joueur
	 */
	public BoutonNumJoueur(int num)
	{
		super("Joueur n�"+num);
		this.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent e) {DemanderUtiliserCarteSansThread.setChoixJoueur(num);}} );
	}
}
