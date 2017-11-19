package vueBouton;

import javax.swing.JButton;
import javax.swing.JFrame;

import vueAction.ActionKillThread;
/**
 * Un JButton qui à déja l'action ActionKillThread implémenté dedans, il permet donc de fermer le thread et la fenetre
 * qu'on lui met en argument.
 *
 */
public class BoutonFinTour extends JButton
{
	/**
	 * Le constructeur de la classe. Tout objet de la classe a une ActionKillThread implémentée de base pour
	 * fermer la fenetre et le thread qui sont en argument.
	 * 
	 * @param t
	 * @param frame
	 * @see vueAction.ActionKillThread
	 */
	public BoutonFinTour(Thread t, JFrame frame)
	{
		super("Fin du tour");
		this.setSize(200, 200);
		this.addActionListener(new ActionKillThread(t, frame));
	}
}
