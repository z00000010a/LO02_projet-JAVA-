package modelStrategy;

import java.util.Random;

import modelDeroulementPartie.Pile;
import modelJoueur.JoueurVirtual;
import vueLabel.VueLabelDefausser;
import vueLabel.VueLabelPiocher;

public class StrategieDefausser implements StrategyInterface 
{

	private JoueurVirtual joueur;
	
	public StrategieDefausser(JoueurVirtual j)
	{
		this.joueur=j;
	}
	@Override
	public void jouer()
	{
		boolean pasBon=true;
		int nbCarte=0;
		do {
			Random r=new Random();
			nbCarte=r.nextInt(6);
			if (nbCarte < 8 && nbCarte >0)
			{
				pasBon=false;
			}
		}while (pasBon);
		for (int q=1; q<=nbCarte;q++)
		{
			Pile.getInstance().ajouterCarteDansPile(joueur.getMainDuJoueur().getMainDuJoueur().getFirst());
			joueur.getMainDuJoueur().supprimerCarte(joueur.getMainDuJoueur().getMainDuJoueur().getFirst());				
		}
		
		try {
			VueLabelDefausser.avertirJoueur(joueur);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		joueur.mettreAJourGraphique();
	}
	
}
