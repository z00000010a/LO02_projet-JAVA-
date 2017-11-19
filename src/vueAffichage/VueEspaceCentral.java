package vueAffichage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import modelCarte.Croyant;
import modelJoueur.CarteCentreTable;
/**
 * Cette classe permet de représenter l'espace central. Elle implémente Observer pour pouvoir être mis-a-jour 
 * l'espace centrale. Elle est instancié par l'espace central lorsque ce dernier est créer.
 *
 */
public class VueEspaceCentral extends JPanel implements Observer{
	/**
	 * L'espace centrale, que cet objet observe.
	 */
	private CarteCentreTable espace;
	/**
	 * Le constructeur de cette classe. Permet de créer la vue de l'espace central. 
	 * @param centre L'espace central, que le constructeur va représenter.
	 */
	public VueEspaceCentral (CarteCentreTable centre)
	{
		super();
		this.espace = centre;
		LinkedList <Croyant> listeCro = espace.getEspaceCentreTable(); 
		Iterator <Croyant> iteCro = listeCro.iterator();
		while (iteCro.hasNext())
		{
			Croyant cro = iteCro.next();
			AfficheImage vueCro = new AfficheImage("default.jpg");
			vueCro.changerCarteSansBouton(cro);
			this.add(vueCro);
		}
		this.validate();
	}
	/**
	 * Cette méthode fait le presque le même travail que le constructeur en enlevant tout les composant puis en 
	 * les recréant tous avec les nouvelles données. 
	 * Cette méthode est appelé par la méthode update(Observable o, Object arg).
	 * @param centre L'espace central, que le constructeur va représenter. 
	 * @see vueAffichage.VueEspaceCentral#update(Observable, Object)
	 */
	public void rafraichir(CarteCentreTable centre)
	{
		this.removeAll();
		this.espace = centre;
		LinkedList <Croyant> listeCro = espace.getEspaceCentreTable(); 
		Iterator <Croyant> iteCro = listeCro.iterator();
		while (iteCro.hasNext())
		{
			Croyant cro = iteCro.next();
			AfficheImage vueCro = new AfficheImage("default.jpg");
			vueCro.changerCarteSansBouton(cro);
			this.add(vueCro);
		}
		this.validate();
	}
	/**
	 * Appelle la méthode rafraichir de cette classe.
	 * Et la méthode rafraichirSimple du ChoixEspace.
	 * Est appelé par la méthode notifyObserver() de l'espace central.
	 * @see vueAffichage.VueEspaceCentral#rafraichir(CarteCentreTable)
	 * @see vueAffichage.ChoixEspace#rafraichirSimple()
	 * @see modelJoueur.CarteCentreTable#notifyObservers()
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.espace = ((CarteCentreTable)o);
		rafraichir(this.espace);
		ChoixEspace.getInstanceChoixEspace(null).rafraichirSimple(); //EN TEST 
	}

}
