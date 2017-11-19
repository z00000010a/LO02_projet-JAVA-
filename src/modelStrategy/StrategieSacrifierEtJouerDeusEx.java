package modelStrategy;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import controller.Controler;
import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.DeusEx;
import modelCarte.GuideSpirituel;
import modelDeroulementPartie.Partie;
import modelJoueur.JoueurVirtual;
import vueLabel.VueLabelDeusEx;
import vueLabel.VueLabelSacrifierCry;

public class StrategieSacrifierEtJouerDeusEx implements StrategyInterface
{

	private JoueurVirtual joueur;
	public StrategieSacrifierEtJouerDeusEx(JoueurVirtual j) 
	{
		this.joueur=j;
	}
	
	@Override
	public void jouer() 
	{	boolean AJouer=false; //mis a vrai si le joueur a fait déja utilisé une DeusEx ou sacrifier un croyant (pour qu'il ne sacrifie pas tout directement)
	
		if (PeutJouer.peutJouerDeusEx(joueur))
		{
			AJouer=true;

				LinkedList<CarteAction> listeC = new LinkedList<CarteAction>();
				listeC.addAll(joueur.getMainDuJoueur().getMainDuJoueur());
				Iterator<CarteAction> it = listeC.iterator();
				out: while (it.hasNext()) 
				{
					CarteAction c = it.next();
					if (c instanceof DeusEx) 
					{   
						DeusEx deusEx = (DeusEx) c;
						boolean beUsed = false;
						boolean bePrevented = false;

						if (deusEx.getOrigine() == "Jour" && deusEx.isPeutJouerParAI()) 
						{	
							if (joueur.getPointActionJour() >= 1) 
							{
								joueur.setPointActionJour(joueur.getPointActionJour()-1);
								try {
									VueLabelDeusEx.avertirJoueur(joueur, deusEx);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								beUsed = true;
								try {
									bePrevented = Controler.getInstanceControler().prevent(deusEx, joueur);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (bePrevented == false) {
									deusEx.sacrifierAI();
								}
								if (beUsed) {
								
									joueur.getMainDuJoueur().supprimerCarte(deusEx);;
								}
								joueur.mettreAJourGraphique();
								break out;
							}
						}

						if (deusEx.getOrigine() == "Nuit" && deusEx.isPeutJouerParAI()) {
							if (joueur.getPointActionNuit() >= 1) {
								joueur.setPointActionNuit(joueur.getPointActionNuit()-1);
								try {
									VueLabelDeusEx.avertirJoueur(joueur, deusEx);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								beUsed = true;
								try {
									try {
										bePrevented = Controler.getInstanceControler().prevent(deusEx, joueur);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (bePrevented == false) {
									deusEx.sacrifierAI();
								}
								if (beUsed) {
									
									joueur.getMainDuJoueur().supprimerCarte(deusEx);;
								}
								joueur.mettreAJourGraphique();
								break out;

							}
						}

						if (deusEx.getOrigine() == "Neant" && deusEx.isPeutJouerParAI()) {
							if (joueur.getPointActionNeant() >= 1) {
								joueur.setPointActionNeant(joueur.getPointActionNeant()-1);
								try {
									VueLabelDeusEx.avertirJoueur(joueur, deusEx);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								beUsed = true;
								
								try {
									try {
										bePrevented = Controler.getInstanceControler().prevent(deusEx, joueur);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (bePrevented == false) {
									deusEx.sacrifierAI();
								}
								if (beUsed) {
								
									joueur.getMainDuJoueur().supprimerCarte(deusEx);;
								}
								joueur.mettreAJourGraphique();
								break out;
							}
						}
					}
				}	
		}
		//===========================================================================================
		if (PeutJouer.PeutSacrifierCroyant(joueur) && AJouer==false)
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
			
			LinkedList <Croyant> listeCro = new LinkedList<Croyant>();
			
					for (int i = 0; i < listeGuideAvecPlusDe2Cro.size(); i++) 
					{
						
						int tailleReelTabCro=0;//on va enlever les cases vides de tabCro
						for (int j = 0; j < listeGuideAvecPlusDe2Cro.get(i).getCroyants().length-1; j++) //-1 pour laisser au moins un croyant
						{
							if (listeGuideAvecPlusDe2Cro.get(i).getCroyants()[j] != null)
							{
								tailleReelTabCro++;
							}
						}
						
						Croyant[] tabCro2 = new Croyant [tailleReelTabCro];
						int h=0;
						for (int j = 0; j < listeGuideAvecPlusDe2Cro.get(i).getCroyants().length-1; j++) //-1 pour laisser au moins un croyant
						{
							if (h<tabCro2.length)
							{
								if (listeGuideAvecPlusDe2Cro.get(i).getCroyants()[j] != null)
								{
									tabCro2[h]=listeGuideAvecPlusDe2Cro.get(i).getCroyants()[j];
									h++;
								}
							}	
						} 
						for (int g = 0; g < tabCro2.length; g++) 
						{
							listeCro.add(tabCro2[g]);
						}
					}
					//à ce stade on mis tout les croyant éligible dans la linkedList et on a laissé au moins un croyant
					
			
			Iterator<Croyant> it = listeCro.iterator();
			out:while (it.hasNext()) 
			{
					Croyant croyant = it.next();
					if (croyant.isPeutJouerParAI()	&& AJouer==false) 
					{
						try {
							VueLabelSacrifierCry.avertirJoueur(joueur, croyant);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						boolean prevent = false;
						try {
							prevent = Controler.getInstanceControler().prevent(croyant, joueur);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (prevent == false) 
						{
							croyant.sacrifierAI(joueur);
							AJouer=true;
						}
						joueur.getEspaceDuJoueur().supprimerCroyant(croyant);
						
					}
			}

			joueur.mettreAJourGraphique();

		}
		//==========================================================================================
		
		if (PeutJouer.PeutSacrifierGuide(joueur) && AJouer==false)
		{
			
		}
		
		
	}

}
