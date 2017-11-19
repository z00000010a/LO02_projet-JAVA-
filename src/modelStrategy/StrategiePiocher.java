package modelStrategy;

import modelJoueur.JoueurVirtual;
import vueLabel.VueLabelPiocher;

public class StrategiePiocher implements StrategyInterface 
{
	private JoueurVirtual joueur;
	
	public StrategiePiocher(JoueurVirtual j)
	{
		this.joueur=j;
	}
	@Override
	public void jouer()
	{
		joueur.getMainDuJoueur().piocherCarte();
		
		try {
			VueLabelPiocher.avertirJoueur(joueur);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		joueur.mettreAJourGraphique();
	}
}
