package modelStrategy;

import java.util.Iterator;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelDeroulementPartie.Pile;
import modelJoueur.JoueurVirtual;

public class StrategieApocalypse implements StrategyInterface
{

	private JoueurVirtual joueur;
	public StrategieApocalypse(JoueurVirtual j)
	{
		this.joueur=j;
	}
	public void jouer() 
	{	boolean apocalypseUtilisee=false;
		//Iterator <CarteAction> it1 = joueur.getMainDuJoueur().getMainDuJoueur().iterator();
		
		for (int i=0; i<joueur.getMainDuJoueur().getMainDuJoueur().size(); i++)
		{	
			CarteAction c = joueur.getMainDuJoueur().getMainDuJoueur().get(i);
			if (c instanceof Apocalypse && !apocalypseUtilisee)
			{
				if (c.getOrigine()==null)
				{
					Apocalypse.cEstLapocalypse();
					joueur.getMainDuJoueur().supprimerCarte(c);
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" joue une carte Apocalypse.\n");
				}
				else if (c.getOrigine().equals("Jour") && joueur.getPointActionJour()>0)
				{
					Apocalypse.cEstLapocalypse();
					joueur.getMainDuJoueur().supprimerCarte(c);
					Pile.getInstance().ajouterCarteDansPile(c);
					joueur.setPointActionJour(joueur.getPointActionJour()-1);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" joue une carte Apocalypse.\n");

				}
				else if (c.getOrigine().equals("Nuit") && joueur.getPointActionNuit()>0)
				{
					Apocalypse.cEstLapocalypse();
					joueur.getMainDuJoueur().supprimerCarte(c);
					joueur.setPointActionNuit(joueur.getPointActionNuit()-1);
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" joue une carte Apocalypse.\n");
				}
				else if (c.getOrigine().equals("Jour") && joueur.getPointActionNeant()>0)
				{
					Apocalypse.cEstLapocalypse();
					joueur.getMainDuJoueur().supprimerCarte(c);
					joueur.setPointActionNeant(joueur.getPointActionNeant()-1);
					Pile.getInstance().ajouterCarteDansPile(c);
					apocalypseUtilisee=true;
					System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" joue une carte Apocalypse.\n");
				}
				
			}
		}
		
	}

}
