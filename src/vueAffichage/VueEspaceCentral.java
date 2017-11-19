package vueAffichage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import modelCarte.Croyant;
import modelJoueur.CarteCentreTable;
/**
 * Cette classe permet de repr�senter l'espace central. Elle impl�mente Observer pour pouvoir �tre mis-a-jour 
 * l'espace centrale. Elle est instanci� par l'espace central lorsque ce dernier est cr�er.
 *
 */
public class VueEspaceCentral extends JPanel implements Observer{
	/**
	 * L'espace centrale, que cet objet observe.
	 */
	private CarteCentreTable espace;
	/**
	 * Le constructeur de cette classe. Permet de cr�er la vue de l'espace central. 
	 * @param centre L'espace central, que le constructeur va repr�senter.
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
	 * Cette m�thode fait le presque le m�me travail que le constructeur en enlevant tout les composant puis en 
	 * les recr�ant tous avec les nouvelles donn�es. 
	 * Cette m�thode est appel� par la m�thode update(Observable o, Object arg).
	 * @param centre L'espace central, que le constructeur va repr�senter. 
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
	 * Appelle la m�thode rafraichir de cette classe.
	 * Et la m�thode rafraichirSimple du ChoixEspace.
	 * Est appel� par la m�thode notifyObserver() de l'espace central.
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
