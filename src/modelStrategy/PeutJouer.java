package modelStrategy;

import java.util.Iterator;
import java.util.LinkedList;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;

public class PeutJouer 
{

	/**
	 * Methode vérifiant si le joueur a les cartes et le nombre de point necessaires pour jouer ce type de carte
	 * @return
	 */
	public static boolean peutJouerCroyant (Joueur joueur)
	{
			boolean peutJouer=false;
			boolean aPointJour=false;
			boolean aPointNuit=false;
			boolean aPointNeant=false;
			if (joueur.getPointActionJour()!=0)
			{
				aPointJour=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNuit=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNeant=true;
			}
			

			for (int i=0; i<joueur.getMainDuJoueur().getMainDuJoueur().size();i++)
			{
				CarteAction objettest=joueur.getMainDuJoueur().getMainDuJoueur().get(i); 
				if (objettest instanceof Croyant)
				{
					if (objettest.getOrigine().equals("Jour") && aPointJour )
					{
						peutJouer=true;
					}
					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
					{
						peutJouer=true;
					}
					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
					{
						peutJouer=true;
					}
				}
			}
			return peutJouer;		
	}
	/**
	 * Methode vérifiant si le joueur a les cartes et le nombre de point necessaires pour jouer ce type de carte
	 * @return
	 */
	public static boolean peutJouerDeusEx(Joueur joueur)
	{
			boolean peutJouer=false;
			boolean aPointJour=false;
			boolean aPointNuit=false;
			boolean aPointNeant=false;
			if (joueur.getPointActionJour()!=0)
			{
				aPointJour=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNuit=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNeant=true;
			}
			
			for (int i=0; i<joueur.getMainDuJoueur().getMainDuJoueur().size();i++)
			{
				CarteAction objettest=joueur.getMainDuJoueur().getMainDuJoueur().get(i); 
				if (objettest instanceof DeusEx)
				{
					if (objettest.getOrigine().equals("Jour") && aPointJour )
					{
						peutJouer=true;
					}
					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
					{
						peutJouer=true;
					}
					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
					{
						peutJouer=true;
					}
					if (objettest.getOrigine().equals(null))
					{
						peutJouer=true;
					}
				}
			}
			return peutJouer;		
	}
	/**
	 * Methode vérifiant si le joueur a les cartes et le nombre de point necessaires pour jouer un guide spirituel. Elle vérifie aussi
	 * si il y a des croyant prêt à être guider.
	 * @return
	 */
	public static boolean peutJouerGS (Joueur joueur)
	{
			boolean peutJouer=false;
			boolean aPointJour=false;
			boolean aPointNuit=false;
			boolean aPointNeant=false;
			LinkedList <GuideSpirituel>gsCompatible = new LinkedList(); //cette liste contient les GS dont l'origine permet au joueur
			//de le jouer (en terme de point)
			if (joueur.getPointActionJour()!=0)
			{
				aPointJour=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNuit=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNeant=true;
			}
			
			for (int i=0; i<joueur.getMainDuJoueur().getMainDuJoueur().size();i++)
			{
				CarteAction objettest=joueur.getMainDuJoueur().getMainDuJoueur().get(i); 
				if (objettest instanceof GuideSpirituel)
				{
					if (objettest.getOrigine().equals("Jour") && aPointJour )
					{
						gsCompatible.add((GuideSpirituel) objettest);
					}
					if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
					{
						gsCompatible.add((GuideSpirituel) objettest);
					}
					if (objettest.getOrigine().equals("Neant")&& aPointNeant)
					{
						gsCompatible.add((GuideSpirituel) objettest);
					}
				}
			}
			//Iterator <GuideSpirituel> ite = gsCompatible.iterator();
			//LinkedList <Croyant> listeCro = CarteCentreTable.getCentreTable().getEspaceCentreTable(); //recupère les croyant de l'espace central
			//Iterator <Croyant> iteCro = listeCro.iterator();
			for (int k=0; k<gsCompatible.size();k++)//avec cette double boucle on check si il y a au moins un dogmes commun entre les GS compatibles et les croyant
				//présent sur le centre de la table
			{
				//GuideSpirituel gsTest = ite.next();
				for (int l=0; l<CarteCentreTable.getCentreTable().getEspaceCentreTable().size() ; l++)
				{
					//Croyant croTest = iteCro.next();
					if (joueur.dogmeCommun(gsCompatible.get(k), CarteCentreTable.getCentreTable().getEspaceCentreTable().get(l)) )
					{
						peutJouer=true;
					}
				}
			}
			return peutJouer;		
	}
	
	public static boolean PeutJouerApocalypse(Joueur joueur)
	{
		boolean peutJouerApocalypse=false;
		if (!(Partie.getInstancePartie(0, null).isApoJoue()))
		{
			boolean aPointJour=false;
			boolean aPointNuit=false;
			boolean aPointNeant=false;
			if (joueur.getPointActionJour()!=0)
			{
				aPointJour=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNuit=true;
			}
			if (joueur.getPointActionNuit()!=0)
			{
				aPointNeant=true;
			}
			

			for (int i=0; i<joueur.getMainDuJoueur().getMainDuJoueur().size();i++)
			{
				CarteAction objettest=joueur.getMainDuJoueur().getMainDuJoueur().get(i); 
				if (objettest instanceof Apocalypse)
				{
					if (objettest.getOrigine()==null)
					{
						peutJouerApocalypse=true;
					}
					else if (objettest.getOrigine().equals("Jour") && aPointJour )
					{
						peutJouerApocalypse=true;
					}
					else if (objettest.getOrigine().equals("Nuit")&& aPointNuit)
					{
						peutJouerApocalypse=true;
					}
					else if (objettest.getOrigine().equals("Neant")&& aPointNeant)
					{
						peutJouerApocalypse=true;
					}
				}
			}
		}
		
		return peutJouerApocalypse;
	}
	
	public static boolean PeutSacrifierGuide(Joueur joueur)
	{
		if (joueur.getEspaceDuJoueur().getListeDesGuides().size()>=2)
		{
			return true;
		}
		return false;
	}
	/**
	 * Ne peut sacrifier que les croyant dont le guide spirituel guide au moins 1 autre croyant
	 * @param joueur
	 * @return
	 */
	public static boolean PeutSacrifierCroyant(Joueur joueur)
	{
		boolean peutSacrifier=false;
		LinkedList<GuideSpirituel> listeGuideAvecPlusDe2Cro = new LinkedList<GuideSpirituel>();
		for (int i = 0; i < joueur.getEspaceDuJoueur().getListeDesGuides().size(); i++) 
		{
			if (joueur.getEspaceDuJoueur().getListeDesGuides().get(i).getCroyants().length >1 )
			{
				listeGuideAvecPlusDe2Cro.add(joueur.getEspaceDuJoueur().getListeDesGuides().get(i));
			}
		}
		if (!(listeGuideAvecPlusDe2Cro.isEmpty()))
		{
			peutSacrifier=true;
		}
		return peutSacrifier;
	}
}
