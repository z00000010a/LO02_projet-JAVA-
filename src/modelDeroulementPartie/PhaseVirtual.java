package modelDeroulementPartie;

import modelJoueur.JoueurVirtual;
import modelStrategy.ChoisirStrategie;
import vueLabel.VueFin;

/**
 * Cette classe guide les décisions de l'I.A.
 *
 */


public class PhaseVirtual {
	private JoueurVirtual joueur;
	private int choisir;
	public PhaseVirtual(JoueurVirtual joueur){
		this.joueur= joueur;
	}
	/**
	 * Fait agir l'I.A comme suit :
	 * Si poser une carte Apocalypse est à l'avantage du joueur alors le joueur pose une carte Apocalypse
	 * Sinon le joueur vérifie si il a moins de 5 cartes, si c'est le cas il complète sa main
	 * Sinon il vérifie ses point d'action, si il en a les capacité, il va appeller la méthode utiliser() de JoueurVirtual.
	 * Sinon il défausses ses cartes.
	 * @see modelJoueur.JoueurVirtual#jouerApocalypseAI()
	 * @see modelJoueur.JoueurVirtual#utiliser()
	 * @see modelJoueur.JoueurVirtual#defausserCarte()
	 * @see modelJoueur.JoueurVirtual#piocherCarte()
	 * @throws InterruptedException
	 */
	public void joueDePhase() throws InterruptedException{
		System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" réfléchit à la manière dont il va tirer avantage de la situation.\n");
		
		joueur.setStrategie(ChoisirStrategie.selectStrat(joueur));
		if (ChoisirStrategie.selectStrat(joueur)==null)
		{
			System.out.println("PROBLEME DANS LA SELECTION DE LA STRATEGIE");
		}
		joueur.getStrategie().jouer();
		joueur.setaJoue(true);
		VueFin.avertirJoueur(joueur);
		//Thread.sleep(3000);
				/*
				if (Partie.compterLesJoueurs() < 4)//VERifie nb joueur
				{
					if (Partie.getMeilleurJoueur()==this.joueur) // met apoca si est meilleur
					{
						if (joueur.jouerApocalypseAI())
						{
							System.out.println("Le joueur virtuel joue une carte Apocalypse.\n");
						}
					}
					else { //sisnon (peut pas jouer apoca ou a pa point ou va pas gagner si le fait => joue normal


						if (joueur.getMainDuJoueur().getMainDuJoueur().size()<5) {
							choisir=2;
						}else if (joueur.getMainDuJoueur().peutJouerCroyant() || joueur.getMainDuJoueur().peutJouerDeusEx() || joueur.getMainDuJoueur().peutJouerGS())
						{	//avec ce test on vérifie que le joueur ne va pas arriver dans la méthode utiliser avec aucune option
							choisir=3;
						}else {
							choisir=1;
						}

						switch (choisir){ 
						case 1:
							this.joueur.defausserCarte();
							break;
						case 2:
							this.joueur.piocherCarte();
							break;
						case 3:
							this.joueur.utiliser();
							break;
						}
					}

				}
				else if (Partie.compterLesJoueurs() > 3)
				{
					if (Partie.getPlusMauvaisJoueur()==this.joueur) // met pas apoca si pire joueur
					{
						if (joueur.jouerApocalypseAI())
						{

						}
					}



					else { //sisnon (peut pas jouer apoca ou a pa point ou va pas gagner si le fait => joue normal


						if (joueur.getMainDuJoueur().getMainDuJoueur().size()<5) {
							choisir=2;
						}else if (joueur.getPointActionJour()>1||joueur.getPointActionNeant()>1||joueur.getPointActionNuit()>1) {
							choisir=3;
						}else {
							choisir=1;
						}

						switch (choisir){ 
						case 1:
							this.joueur.defausserCarte();
							break;
						case 2:
							this.joueur.piocherCarte();
							break;
						case 3:
							this.joueur.utiliser();
							break;
						}
					}
				}
				*/
	}
}



