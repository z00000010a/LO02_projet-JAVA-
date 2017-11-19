package modelJoueur;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import modelCarte.Croyant;
import vueAffichage.VueEspaceCentral;
/**
 * CarteCentreTable est la classe qui gère le centre de la table, comme il n'y en a qu'un nous avons implémenter le patron du singleton.
 * C'est une linkedList de Carte gérant et mettant à jour les Croyant qui arrive dans cette liste.
 *  
 */
public class CarteCentreTable extends Observable
{
	private VueEspaceCentral vueCentreTable;
	
	private static CarteCentreTable centreTable= new CarteCentreTable ();
	
	private CarteCentreTable ()
	{	
		this.espaceCentreTable =  new LinkedList(); 
		this.vueCentreTable = new VueEspaceCentral(this);
		this.addObserver(vueCentreTable);
	}
	public static CarteCentreTable getCentreTable ()
	{
		return centreTable;
	}
	/**
	 * LinkedList composée de Croyant, les guides spirituel s'en serve pour "prospecter" les croyant qu'ils vont guider.
	 */
	private LinkedList <Croyant> espaceCentreTable;
/**
 * Cette méthode est là pour éviter de devoir utiliser des getters et des setters et ainsi simplifier l'écriture.
 * @param objet
 * L'objet que l'on souhaite ajouter.
 */
	public void ajouterCroyant(Croyant objet)
	{
		espaceCentreTable.add(objet);
		setChanged();
		notifyObservers();
	}
	/**
	 * Cette méthode est là pour éviter de devoir utiliser des getters et des setters et ainsi simplifier l'écriture.
	 * @param objet
	 * L'objet que l'on souhaite supprimer.
	 */
	public void supprimerCroyant(Croyant objet) 
	{
		espaceCentreTable.remove(objet);
		setChanged();
		notifyObservers();
	}
	/**
	 * Utiliser dans la carte Paladin (GS) pour notifier les changements effectué.
	 */
	public void notifier()
	{
		setChanged();
		notifyObservers();
	}
	/**
	 * Utiliser par le joueur humain pour selectionner les croyant qu'il souhaite guider avec le guide spirituel qu'il va poser.
	 * @see modelJoueur.JoueurPhysique#poserGuideSpirituel()
	 */
	public void afficherEspaceCentreTable()
	{
		Iterator <Croyant> iterateur = espaceCentreTable.iterator();
		while (iterateur.hasNext())
		{
			Croyant obj = iterateur.next();
			System.out.println(obj);
			System.out.println("Son index est "+espaceCentreTable.indexOf(obj)+".\n");
		}
	}
	/**
	 * Utiliser par le joueur humain pour savoir combien de croyant au maximum il pourra guider avec le guide spirituel qu'il va poser.
	 * @see modelJoueur.JoueurPhysique#poserGuideSpirituel()
	 */
	public int compterCroyant()
	{
		return espaceCentreTable.size();
	}
	public LinkedList<Croyant> getEspaceCentreTable() {
		return espaceCentreTable;
	}

	public void setEspaceCentreTable(LinkedList<Croyant> espaceCentreTable) {
		this.espaceCentreTable = espaceCentreTable;
	}
	public VueEspaceCentral getVueCentreTable() {
		return vueCentreTable;
	}
	public void setVueCentreTable(VueEspaceCentral vueCentreTable) {
		this.vueCentreTable = vueCentreTable;
	}
}
