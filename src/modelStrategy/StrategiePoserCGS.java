package modelStrategy;

import java.util.Iterator;
import java.util.LinkedList;

import modelCarte.CarteAction;
import modelCarte.Croyant;
import modelCarte.GuideSpirituel;
import modelJoueur.CarteCentreTable;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
import modelJoueur.JoueurVirtual;
import vueLabel.VueLabelPoserCro;
import vueLabel.VueLabelPoserGS;

public class StrategiePoserCGS implements StrategyInterface
{

	JoueurVirtual joueur;
	
	public StrategiePoserCGS(JoueurVirtual j) 
	{
		this.joueur=j;
	}
	@Override
	public void jouer() 
	{
		boolean aJouer = false;
//		//Thread.sleep(2000);
//		System.out.println();
//		System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" pose un guide spirituel.\n");
//		System.out.println();
//		//Thread.sleep(2000);
		if (PeutJouer.peutJouerGS(joueur))
		{
			LinkedList <CarteAction> mainJ = new LinkedList();
			mainJ.addAll(joueur.getMainDuJoueur().getMainDuJoueur());
			Iterator <CarteAction> iterateur1= mainJ.iterator();
			
			int n1=0; //mis a 1 si un GS guide des croyants
			//GuideSpirituel[] tabGSSelec = new GuideSpirituel[7]; //la tableau dans lequel
			while ( iterateur1.hasNext() && n1==0 ) // itére tout la main du JV
			{
				CarteAction objettest=iterateur1.next(); 
				if (objettest instanceof GuideSpirituel)
				{
						GuideSpirituel leGuide = (GuideSpirituel) objettest;
	
						//this.pointActionJour-=1;								//retranche les points
						
	
						Croyant[] tabCroyant1=new Croyant[CarteCentreTable.getCentreTable().compterCroyant()];
						for (int i = 0; i < tabCroyant1.length; i++) {
							for (int k=0; k<CarteCentreTable.getCentreTable().compterCroyant(); k++)
							{
								if (joueur.dogmeCommun(leGuide, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k)) && !(JoueurPhysique.tableauContient(tabCroyant1, CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k))))
								{ //vérifie que le Cro n'a pas déja été selectionné et que il y a un dogmes commun
									tabCroyant1[i]=CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k);
									CarteCentreTable.getCentreTable().supprimerCroyant(CarteCentreTable.getCentreTable().getEspaceCentreTable().get(k));
									n1++;
									//le 1er For itère sur chaque case de tabCroyant1 (ou on va stocker les futurs croyant guidé)
									//le second For itére sur la linkedList MAIS on réapelle la méthode pour réajuster la taille à chaque boucle
								}
	
							}
	
						}
						if (n1!=0) 	//si n1 != 0 c'est  que toutes les conditions pour ajouter le Guide et ses croyant sont reunis, 
									//on va donc le poser et retrancher les points
							
						{
							joueur.getEspaceDuJoueur().ajouterGuideSpirituel(tabCroyant1, leGuide);
							System.out.println("L'I.A pose"+leGuide+"\n");
							try {
								VueLabelPoserGS.avertirJoueur(joueur, leGuide);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//retranchons les points correspondant
							 if (leGuide.getOrigine().equals("Jour"))
							{
								joueur.setPointActionJour(joueur.getPointActionJour()-1);
							}
							else if (leGuide.getOrigine().equals("Nuit"))
							{
								joueur.setPointActionNuit(joueur.getPointActionNuit()-1);
							}
							else if (leGuide.getOrigine().equals("Neant"))
							{
								joueur.setPointActionNeant(joueur.getPointActionNeant()-1);
							}
							//on enlève le Guide de la main du JV
							joueur.getMainDuJoueur().supprimerCarte(leGuide);
							//iterateur1.remove(); 									//retire le guide de la liste temporaire
							aJouer=true;
						}
					}
			}
			joueur.mettreAJourGraphique();
			CarteCentreTable.getCentreTable().notifier(); //update aussi l'espace central
		}
		

//=====================================================================================================Partie ou le joueur pose des croyant		
//		System.out.println();
//		System.out.println("Le joueur virtuel n°"+joueur.getNumJoueur()+" pose un croyant.\n");
//		System.out.println();
//		//Thread.sleep(2000);		
		if (PeutJouer.peutJouerCroyant(joueur))
		{
		LinkedList<CarteAction> listCj = new LinkedList();
		listCj.addAll(joueur.getMainDuJoueur().getMainDuJoueur());	

		Iterator <CarteAction> iterateur4= listCj.iterator();
		 aJouer = false; //boolen qui définira aJoue (boolen utile dans utiliser) pour renvoyer si la méthode a été éfficace ou pas
		while (iterateur4.hasNext() && aJouer==false)
		{
			CarteAction objettest=iterateur4.next(); 
			if (objettest instanceof Croyant && objettest.getOrigine()=="Jour" && joueur.getPointActionJour()>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				joueur.setPointActionJour(joueur.getPointActionJour()-1);
				joueur.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				System.out.println("AI "+joueur.getNumJoueur()+" pose \n\n"+objettest+" au centre de la table.");
				aJouer=true;
				try {
					VueLabelPoserCro.avertirJoueur(joueur, objettest);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objettest instanceof Croyant && objettest.getOrigine()=="Neant" && joueur.getPointActionNeant()>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				joueur.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				joueur.setPointActionNeant(joueur.getPointActionNeant()-1);
				System.out.println("AI "+joueur.getNumJoueur()+" pose "+(objettest)+" au centre de la table.");
				aJouer=true;
				try {
					VueLabelPoserCro.avertirJoueur(joueur, objettest);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (objettest instanceof Croyant && objettest.getOrigine()=="Nuit" && joueur.getPointActionNuit()>0)
			{
				CarteCentreTable.getCentreTable().ajouterCroyant((Croyant)objettest);
				joueur.getMainDuJoueur().supprimerCarte(objettest);		//on l'enlève de la main du Joueur
				//iterateur4.remove();									//on l'enlève de la liste qu'on itère, pour ne pas retomber sur elle
				joueur.setPointActionNuit(joueur.getPointActionNuit()-1);
				System.out.println("AI "+joueur.getNumJoueur()+" pose "+(objettest)+" au centre de la table.");
				aJouer=true;
				try {
					VueLabelPoserCro.avertirJoueur(joueur, objettest);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		joueur.mettreAJourGraphique();
	
	 }
		if (PeutJouer.PeutSacrifierGuide(joueur))
		{
			joueur.getEspaceDuJoueur().getListeDesGuides().get(0).sacrifierAI(joueur);
		}
		
}
}