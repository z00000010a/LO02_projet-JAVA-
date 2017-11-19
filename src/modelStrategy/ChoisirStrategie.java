package modelStrategy;

import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurVirtual;
import vueLabel.VueFin;

public class ChoisirStrategie 
{
	public static StrategyInterface selectStrat(JoueurVirtual j)
	{	
		if (Partie.compterLesJoueurs() < 4)//VERifie nb joueur
		{
			if (Partie.getMeilleurJoueur()==j && PeutJouer.PeutJouerApocalypse(j)) // met apoca si est meilleur
			{	/*
				if (j.jouerApocalypseAI())
				{
					System.out.println("Le joueur virtuel joue une carte Apocalypse.\n");
				}*/
				StrategieApocalypse strat = new StrategieApocalypse(j);
				System.out.println("Utiliser Apocalypse");
				return strat;
			}
			else { //sisnon (peut pas jouer apoca ou a pa point ou va pas gagner si le fait => joue normal


				if (j.getMainDuJoueur().getMainDuJoueur().size()<5)
				{
					StrategiePiocher strat = new StrategiePiocher(j);
					System.out.println("Pioche carte");
					return strat;
				}
				else if (PeutJouer.peutJouerCroyant(j) || PeutJouer.peutJouerGS(j))
				{	
					StrategiePoserCGS strat = new StrategiePoserCGS(j);
					System.out.println("Poser croyant/GS");
					return strat;
				}
				else if ( PeutJouer.peutJouerDeusEx(j) || PeutJouer.PeutSacrifierGuide(j) || PeutJouer.PeutSacrifierCroyant(j))
				{
					StrategieSacrifierEtJouerDeusEx strat = new StrategieSacrifierEtJouerDeusEx(j);
					System.out.println("Sacrifie / DeusEx");
					return strat;
				}
				else 
				{
					StrategieDefausser strat = new StrategieDefausser(j);
					System.out.println("Joueur défausse des cartes");
					return strat;
				}
			}
		}
		
		else 
		{
			if (!(Partie.getPlusMauvaisJoueur()==j) && PeutJouer.PeutJouerApocalypse(j)) // met apoca n'est pas le moins pire
			{	/*
				if (j.jouerApocalypseAI())
				{
					System.out.println("Le joueur virtuel joue une carte Apocalypse.\n");
				}*/
				StrategieApocalypse strat = new StrategieApocalypse(j);
				return strat;
			}
			else { //sisnon (peut pas jouer apoca ou a pa point ou va pas gagner si le fait => joue normal

				if (j.getMainDuJoueur().getMainDuJoueur().size()<5)
				{
					StrategiePiocher strat = new StrategiePiocher(j);
					System.out.println("Pioche carte");
					return strat;
				}
				else if (PeutJouer.peutJouerCroyant(j) || PeutJouer.peutJouerGS(j))
				{	
					StrategiePoserCGS strat = new StrategiePoserCGS(j);
					System.out.println("Poser croyant/GS");
					return strat;
				}
				else if ( PeutJouer.peutJouerDeusEx(j) || PeutJouer.PeutSacrifierGuide(j) || PeutJouer.PeutSacrifierCroyant(j))
				{
					StrategieSacrifierEtJouerDeusEx strat = new StrategieSacrifierEtJouerDeusEx(j);
					System.out.println("Sacrifie / DeusEx");
					return strat;
				}
				else 
				{
					StrategieDefausser strat = new StrategieDefausser(j);
					System.out.println("Joueur défausse des cartes");
					return strat;
				}
			}

	}
		

		
	}
	
}
