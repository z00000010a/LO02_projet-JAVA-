package modelJoueur;
import java.util.*;

import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Pile;
/**
 * Classe servant à représenter l'espace d'un joueur réel ou virtuel. Elle est en attribut de chaque Joueur.
 * Contient une  LinkedList Croyant et une LinkedList GuideSpirituel. Ainsi que les méthodes pour 
 * ajouter les differents éléments dans ces listes en mettant leurs attributs à jour.
 *
 */
public class EspaceJoueur {
	private Joueur joueur;
	/**
	* Liste contenant les Guides spirituels, essentiellement manipulé par les méthodes de cette classe.
	*/
	private	LinkedList <GuideSpirituel> listeDesGuides = new LinkedList();
	/**
	* Liste contenant les croyants, essentiellement manipulé par les méthodes de cette classe.
	*/
	private LinkedList <Croyant> listeDesCroyants = new LinkedList();
/**
 * Méthode ajoutant les croyants dans la listeDesCroyants et le guide spirituel dans la listeDesGuides.
 * Met à jour le tableau de croyant dans guides, l'attribut guidePar des croyant et l'attribut estGuide des croyants.
 * @param tabCroyant
 * Un tableau rempli de croyant, dont la compatibilité dogmatique avec le guide à été vérifiée préalablement.
 * @param guide
 * Le guide qui doit guider les croyants contenu dans le tableau fourni en argument.
 */
	public void ajouterGuideSpirituel (Croyant [] tabCroyant, GuideSpirituel guide )
	{

			int nbCroGuide=0;
			for (int i=0; i < tabCroyant.length ; i++)//parcoure toute les cartes croyant et les ajoute à la liste, puis on ajoute DANS les objets croyant le guide spirituel qui est transmis avec
			{
				if (tabCroyant[i]!=null) //la méthode poser GS du JV peut envoyer des tab avec des cases null
				{
					listeDesCroyants.add(tabCroyant [i]);
					tabCroyant[i].setGuidePar(guide); 
					tabCroyant[i].setEstGuide(true);
					nbCroGuide++;
				}

			}
			
			guide.setEspaceQuiContientlaCarte(this);
			guide.setNbCroyantGuide(nbCroGuide); // ou tabCroyant.lenght()
			guide.setCroyants(tabCroyant);
     		listeDesGuides.add(guide);

		

	}
/**
 * Sert à compter le nombre de prières pour un guide spirituel donnés. Utiliser par compterToutesLesPrieres() de cette même classe.
 * @param guide
 * Le guide dont on souhaite compter les prières.
 * @return
 * Le nombre de prières de ce guide.
 * @see modelJoueur.EspaceJoueur#compterToutesLesPrieres()
 */
	public int compterNbPriereParGuide (GuideSpirituel guide)
	{
		int nbPriere=0;
		Iterator <Croyant> iterateur = listeDesCroyants.iterator();
		while (iterateur.hasNext())
		{
			Croyant objettest=iterateur.next();
			if (objettest.getGuidePar()==guide )
			{
				nbPriere+=objettest.getNbPriere();
			}
		}
		return nbPriere;
	}
/**
 * Utilise la méthode compterNbPriereParGuide() de la même classe et l'itère sur tout les guide de ce joueur. Cette méthode est utilisé 
 * par l'I.A et par la méthode cEstLapocalypse().
 * @return
 * Le nombre total de prières du joueur détenant cette instance d'espaceJoueur.
 * @see modelApocalypse.Apocalypse#cEstLapocalypse()
 * @see modelDeroulementPartie.PhaseVirtual#joueDePhase()
 */
	public int compterToutesLesPrieres ()
	{
		int nbPrieresJoueur = 0;
		Iterator <GuideSpirituel> iterateur = listeDesGuides.iterator();
		while (iterateur.hasNext())
		{
			GuideSpirituel objettest=iterateur.next();
			nbPrieresJoueur=nbPrieresJoueur + compterNbPriereParGuide (objettest);
		}
		return nbPrieresJoueur;

	}
	/**
	 * Permet de supprimer un croyant en mettant à jour les informations qui y sont rattachés. Aussi bien sur ce croyant que sur le guide
	 * à qui il était attaché. Cette méthode est notamment utilisé quand le joueur sacrifie un croyant dans sont espace et doit mettre à
	 * les informations de son espace. La remise dans la pile se fait séparement à cause d'une capacité de guide spirituel qui necessite 
	 * cette non-remise dans la pile.
	 * @param croyant
	 * Le croyant que l'on souhaite enlever de l'espace de joueur.
	 * @see modelJoueur.JoueurPhysique#sacrifierCroyant()
	 * 
	 */
	public void supprimerCroyant(Croyant croyant)
	{
		listeDesCroyants.remove(croyant);
		croyant.getGuidePar().setNbCroyantGuide(croyant.getGuidePar().getNbCroyantGuide()-1);//réduit de 1 le nb de croyant guidé
		Croyant [] tab = croyant.getGuidePar().getCroyants();
		for (int y=0; y < tab.length;y++)
		{
			if (tab[y].equals(croyant))//update le [] de croyant du GS
			{
				tab[y]=null;
			}
		}
		int tailleReelTabCro=0;//on va enlever les cases vides de tabCro
		for (int j = 0; j < tab.length; j++) 
		{
			if (tab[j] != null)
			{
				tailleReelTabCro++;
			}
		}
		if (!(tailleReelTabCro==0))
		{
			Croyant[] tabCro2 = new Croyant [tailleReelTabCro];
			int h=0;
			for (int j = 0; j < tab.length; j++) 
			{
				if (h<tabCro2.length)
				{
					if (tab[j] != null)
					{
						tabCro2[h]=tab[j];
					}
				}	
			}
		}
		else
		{
			this.supprimerGuideSpirituel(croyant.getGuidePar());
		}
		
		croyant.getGuidePar().setCroyants(tab);
		croyant.setGuidePar(null);
		croyant.setEstGuide(false);
	}
	/**
	 * Permet de supprimer un guide en mettant à jour les informations qui y sont rattachés. Cette méthode est notamment utilisé quand 
	 * le joueur sacrifie un guide dans sont espace et doit mettre à jour
	 * les informations de son espace. La remise dans la pile se fait séparement à cause d'une capacité de guide spirituel qui necessite 
	 * cette non-remise dans la pile. Les croyant rattachés à ce guide sont défaussés et remis dans la pile.
	 * @param guideSpirituel
	 * Le guide que l'on souhaite enlever
	 * @see modelJoueur.JoueurPhysique#sacrifierGuideSpirituel()
	 */
	public void supprimerGuideSpirituel(GuideSpirituel guideSpirituel) 
	{
		listeDesGuides.remove(guideSpirituel);
		guideSpirituel.setNbCroyantGuide(0);                         //met setNBcroyantguide a 0
		Iterator <Croyant>iterateur = listeDesCroyants.iterator();//enlève les croyant lié au guide
		while (iterateur.hasNext())
		{
			Croyant objDuTest = iterateur.next();
			if (objDuTest.getGuidePar()==guideSpirituel)
			{
				iterateur.remove();
				Pile.getInstance().ajouterCarteDansPile(objDuTest);
			}
		}
		guideSpirituel.setCroyants(null);

	}
	/**
	 * Affiche tous les croyants de l'espace du joueur. Utile pour le sacrifice de l'un d'entre eux. 
	 *  @see modelJoueur.JoueurPhysique#sacrifierCroyant() 
	 */
	public void afficherToutLesCroyants()
	{
		System.out.println("Voici les croyants dont vous disposer :\n");
		Iterator <Croyant> iterateur = listeDesCroyants.iterator();
		while (iterateur.hasNext())
		{
			Croyant ob = iterateur.next();
			System.out.println(ob);
			System.out.println("Son index est : "+listeDesCroyants.indexOf(ob));
		}
	}
	/**
	 * Affiche tous les guides de l'espace du joueur. Utile pour le sacrifice de l'un d'entre eux. 
	 *  @see modelJoueur.JoueurPhysique#sacrifierGuideSpirituel() 
	 */
	public void afficherToutLesGuideSpirituel()
	{
		System.out.println("Voici les guide spirituel dont vous disposer :\n");
		Iterator <GuideSpirituel> iterateur = listeDesGuides.iterator();
		while (iterateur.hasNext())
		{
			GuideSpirituel ob = iterateur.next();
			System.out.println(ob);
			System.out.println("Son index est : "+listeDesGuides.indexOf(ob));
		}
	}

	public LinkedList<GuideSpirituel> getListeDesGuides() {
		return listeDesGuides;
	}

	public void setListeDesGuides(LinkedList<GuideSpirituel> listeDesGuides) {
		this.listeDesGuides = listeDesGuides;
	}

	public LinkedList<Croyant> getListeDesCroyants() {
		return listeDesCroyants;
	}

	public void setListeDesCroyants(LinkedList<Croyant> listeDesCroyants) {
		this.listeDesCroyants = listeDesCroyants;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}


