package modelJoueur;
import java.util.*;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Pile;
import modelGuideSpirituel.Clerc;
/**
 * La classe CarteMain contient les cartes qui sont dans la main d'un joueur (physique ou virtuel). Elle contient des m�thode que le joueur appelera.
 */
public class CarteMain 
{	
	/**
	 * Le num�ro du Joueur qui poss�de cette main. Utile pour les capacit�s des Croyants.
	 * Notons que le joueur � dans ces attribut une r�f�rence vers l'objet main.
	 */
	private int numJoueur;
	
	private Joueur joueur;
	/**
	 * Poss�de l'instance du SINGLETON pile pour pouvoir utiliser ses m�thodes (Par exemple pour remettre des cartes dans la pile.).
	 */
	private Pile pile = Pile.getInstance ();
	/**
	 * LinkedList contenant les cartes du joueur.
	 */
	private LinkedList <CarteAction> mainDuJoueur = new LinkedList <CarteAction> (); 
	/**
	 * Cette m�thode permet d'ajouter une carte dans la main du joueur quand le joueur l'appelle. Cela permet de ne pas faire appelle � des getters 
	 * et des setters � chaque fois que l'on veut ajouter une carte. 
	 * @param objet 
	 *  Une carte qui doit �tre ajouter � la main du joueur.
	 */
	public void ajouterDansMain(CarteAction objet)
	{
		mainDuJoueur.add(objet);
		objet.setJoueur(joueur); //chaque Carte connait ainsi le joueur qui le poss�de
	}
	/**
	 * Cette m�thode permet d'afficher les cartes de la main d'un joueur, le param�tre i permet de montrer ou de ne pas montrer certaine cartes.
	 * Utile pour certaine situation ou par exemple on a juste besoin de connaitre les croyants dans notre main.
	 * @param i 
	 * On entre  0 pour afficher toutes les cartes 
	 * On entre 1 pour les croyant
	 * On entre 2 pour les Guide Spirituel
	 * On entre 3 pour les DeusEx 
	 * On entre 4 pour les Apocalypses
	 */
	public void afficherMain(int i)
	{
		if (i==0)
		{
			Iterator <CarteAction> iterateur= mainDuJoueur.iterator();
			while (iterateur.hasNext())
			{
				CarteAction objettest=iterateur.next(); 
				if (objettest instanceof Object)
				System.out.println("Index de la carte : "+mainDuJoueur.indexOf(objettest));
				if (objettest instanceof Croyant)
				{
					System.out.println("Croyant");
				}
				else if (objettest instanceof GuideSpirituel)
				{
					System.out.println("Guide Spirituel");
				}
				else if (objettest instanceof DeusEx)
				{
					System.out.println("Deus Ex");
				}
				else if (objettest instanceof Apocalypse)
				{
					System.out.println("Apocalypse");
				}
				System.out.println(objettest);
			}
		}
		if (i==1)
		{
			Iterator <CarteAction> iterateur2= mainDuJoueur.iterator();
			while (iterateur2.hasNext())
			{
				CarteAction objettest=iterateur2.next(); 
				if (objettest instanceof Croyant)
				{
				System.out.println("Index de la carte : "+mainDuJoueur.indexOf(objettest));
				System.out.println("Nom de la carte : "+objettest);
				}
			}
		}
		if (i==2)
		{
			Iterator <CarteAction> iterateur3= mainDuJoueur.iterator();
			while (iterateur3.hasNext())
			{
				CarteAction objettest=iterateur3.next(); 
				if (objettest instanceof GuideSpirituel)
				{
					System.out.println("Index de la carte : "+mainDuJoueur.indexOf(objettest));
					System.out.println("Nom de la carte : "+objettest);
				}
				
			}
		}
		if (i==3)
		{
			Iterator <CarteAction> iterateur4= mainDuJoueur.iterator();
			while (iterateur4.hasNext())
			{
				CarteAction objettest=iterateur4.next(); 
				if (objettest instanceof DeusEx)
				{
					System.out.println("Index de la carte : "+mainDuJoueur.indexOf(objettest));
					System.out.println("Nom de la carte : "+ objettest);
				}

			}
		}
		if (i==4)
		{
			Iterator <CarteAction> iterateur5= mainDuJoueur.iterator();
			while (iterateur5.hasNext())
			{
				CarteAction objettest=iterateur5.next(); 
				if (objettest instanceof Apocalypse)
				{
					System.out.println("Index de la carte : "+mainDuJoueur.indexOf(objettest));
					System.out.println("Nom de la carte : "+objettest);
				}

			}
		}
		
	}
	/**
	 * Une m�thode simple pour informer le joueur sur le nombre de cartes qu'il poss�de, apr�s avoir piocher ou avoir d�fausser des cartes par exemple.
	 */
	public void compterNombreDeCarte()
	{
		int i=mainDuJoueur.size();
		System.out.println("Vous avez "+i+" cartes.\n");
	}
	/**
	 * Sert � supprimer les cartes sans avoir besoin de mettre quelquechose en argument, cette m�thode ne peut servir qu'au joueur.
	 * La structure Do {} while(boolean) sert � s'assurer que le choix du joueur est coh�rent.
	 */
	public void supprimerCarte()
	{
		afficherMain(0);
		System.out.println("Quelle carte voulez-vous d�faussez ? \n ");
		int idCarte=0;
		boolean jF=true;
		do
		{
			
			System.out.println("Rentrer l'index d'une carte pr�sente dans votre main.\n");
			Scanner sc = new Scanner(System.in);
			idCarte = sc.nextInt();
			
			if (mainDuJoueur.contains(mainDuJoueur.get(idCarte)))
			{
				CarteAction objetTest=mainDuJoueur.get(idCarte);
				Pile.getInstance().ajouterCarteDansPile(objetTest);	//on ajoute la carte dans pile		
				mainDuJoueur.remove(objetTest); //on l'enl�ve de la main du joueur
				jF=false;
			}

		} while (jF);

	}
	/**
	 * Une m�thode simple que l'on va pouvoir utiliser pour un joueur virtuel.
	 * @param carte
	 * La carte que l'on souhaire supprimer de la main du joueur.
	 */
	public void supprimerCarte(CarteAction carte)
	{
		if (mainDuJoueur.contains(carte))
		{	//ENLEVER LES DEUX SYSO APR7S TEST DEBUG
			System.out.println("MAIN AVANT SUPPRESSION");
			this.afficherMain(0);
			mainDuJoueur.remove(carte);
			System.out.println("MAIN APR7S SUPPRESSION");
			this.afficherMain(0);
		}
		else
		{
			System.out.println("ERREUR LA CARTE N'EST PAS DANS LA LINKEDLIST MAIN DU JOUEUR");
		}
	}
	/**
	 * M�thode compl�tant la main du Joueur (Dans la r�gle il est pr�cis� "le joueur compl�te sa main", nous ne laissons donc pas au joueur
	 * le soin de choisir combien de cartes il d�sir.
	 * @see Pile#piocher()
	 */
	public void piocherCarte()
	{ 	
		
		while( mainDuJoueur.size()<7 ) 
		{
			CarteAction A = pile.piocher(); 
			ajouterDansMain(A);
		}

	}
	/**
	 * Utilis� pour sacrifier une carte.
	 * @param index
	 *  L'index de la carte que l'on veut supprimer. On l'aura recherch� avant dans des m�thodes de Joueur. Plac� les m�thodes manipulant les cartes de la main ici 
	 *  nous permet d'�viter les getters et les setters. 
	 */
	public void sacrifierCarte (int index)
	{
		//mainDuJoueur.get(index).sacrifier();
		pile.ajouterCarteDansPile(mainDuJoueur.get(index));
		
	}
	
//	
//	/**
//	 * Methode v�rifiant si le joueur a les cartes et le nombre de point necessaires pour jouer ce type de carte
//	 * @return
//	 */
//	public boolean peutJouerCroyant ()
//	{
//			boolean peutJouer=false;
//			boolean aPointJour=false;
//			boolean aPointNuit=false;
//			boolean aPointNeant=false;
//			if (joueur.getPointActionJour()!=0)
//			{
//				aPointJour=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNuit=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNeant=true;
//			}
//			
//			Iterator <CarteAction> iterateur5= mainDuJoueur.iterator();
//			while (iterateur5.hasNext())
//			{
//				CarteAction objettest=iterateur5.next(); 
//				if (objettest instanceof Croyant)
//				{
//					if (objettest.getOrigine().equals("Jour") && aPointJour )
//					{
//						peutJouer=true;
//					}
//					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
//					{
//						peutJouer=true;
//					}
//					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
//					{
//						peutJouer=true;
//					}
//				}
//			}
//			return peutJouer;		
//	}
//	/**
//	 * Methode v�rifiant si le joueur a les cartes et le nombre de point necessaires pour jouer ce type de carte
//	 * @return
//	 */
//	public boolean peutJouerDeusEx()
//	{
//			boolean peutJouer=false;
//			boolean aPointJour=false;
//			boolean aPointNuit=false;
//			boolean aPointNeant=false;
//			if (joueur.getPointActionJour()!=0)
//			{
//				aPointJour=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNuit=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNeant=true;
//			}
//			
//			Iterator <CarteAction> iterateur5= mainDuJoueur.iterator();
//			while (iterateur5.hasNext())
//			{
//				CarteAction objettest=iterateur5.next(); 
//				if (objettest instanceof DeusEx)
//				{
//					if (objettest.getOrigine().equals("Jour") && aPointJour )
//					{
//						peutJouer=true;
//					}
//					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
//					{
//						peutJouer=true;
//					}
//					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
//					{
//						peutJouer=true;
//					}
//					if (objettest.getOrigine().equals(null))
//					{
//						peutJouer=true;
//					}
//				}
//			}
//			return peutJouer;		
//	}
//	/**
//	 * Methode v�rifiant si le joueur a les cartes et le nombre de point necessaires pour jouer un guide spirituel. Elle v�rifie aussi
//	 * si il y a des croyant pr�t � �tre guider.
//	 * @return
//	 */
//	public boolean peutJouerGS ()
//	{
//			boolean peutJouer=false;
//			boolean aPointJour=false;
//			boolean aPointNuit=false;
//			boolean aPointNeant=false;
//			LinkedList <GuideSpirituel>gsCompatible = new LinkedList(); //cette liste contient les GS dont l'origine permet au joueur
//			//de le jouer (en terme de point)
//			if (joueur.getPointActionJour()!=0)
//			{
//				aPointJour=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNuit=true;
//			}
//			if (joueur.getPointActionNuit()!=0)
//			{
//				aPointNeant=true;
//			}
//			
//			Iterator <CarteAction> iterateur5= mainDuJoueur.iterator();
//			while (iterateur5.hasNext())
//			{
//				CarteAction objettest=iterateur5.next(); 
//				if (objettest instanceof GuideSpirituel)
//				{
//					if (objettest.getOrigine().equals("Jour") && aPointJour )
//					{
//						gsCompatible.add((GuideSpirituel) objettest);
//					}
//					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
//					{
//						gsCompatible.add((GuideSpirituel) objettest);
//					}
//					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
//					{
//						gsCompatible.add((GuideSpirituel) objettest);
//					}
//				}
//			}
//			Iterator <GuideSpirituel> ite = gsCompatible.iterator();
//			LinkedList <Croyant> listeCro = CarteCentreTable.getCentreTable().getEspaceCentreTable(); //recup�re les croyant de l'espace central
//			Iterator <Croyant> iteCro = listeCro.iterator();
//			while (ite.hasNext())//avec cette double boucle on check si il y a au moins un dogmes commun entre les GS compatibles et les croyant
//				//pr�sent sur le centre de la table
//			{
//				GuideSpirituel gsTest = ite.next();
//				while (iteCro.hasNext())
//				{
//					Croyant croTest = iteCro.next();
//					if (joueur.dogmeCommun(gsTest, croTest))
//					{
//						peutJouer=true;
//					}
//				}
//			}
//			return peutJouer;		
//	}

//==================================================================================================================================================================================
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

	public Pile getPile() {
		return pile;
	}

	public void setPile(Pile pile) {
		this.pile = pile;
	}

	public LinkedList<CarteAction> getMainDuJoueur() {
		return mainDuJoueur;
	}

	public void setMainDuJoueur(LinkedList<CarteAction> mainDuJoueur) {
		this.mainDuJoueur = mainDuJoueur;
	}
	
	public int getNumJoueur() {
		return numJoueur;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
