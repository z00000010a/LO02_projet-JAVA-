package modelApocalypse;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelCarte.CarteAction;
import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
import modelJoueur.JoueurPhysique;
/**
 *Classe � partir de laquelle sont instanci�e toutes les cartes Apocalypse. H�rite de CarteAction.
 */
public class Apocalypse extends CarteAction
{
	private boolean dansPile;
	private static Partie partie;
	public String toString ()
	{
		System.out.println("Carte Apocalypse d'origine :"+this.origine);
		return "\n";
	}
	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	/**
	 * M�thode permettant de d�clencher l'apocalypse.
	 * Va v�rifier si les conditons pour jouer une apocalypse sont reunis (pas plus moins de deux tours apr�s la derni�re carte Apocalyse
	 * et pas le 1er tour.
	 *  Si c'est le cas v�rifie le nombre de Joueur et suit les r�gles de Pandocr�on. Si le joueur est supprim� de la partie, 
	 *  la m�thode arr�te le programme. 
	 *  Cependant, une exception avec les r�gles. L'apocalypse ne reset pas le tour. Et le joueur qui l'a pos� ne commence pas le prochain tour.
	 *  Le boolen ApoJoue permet � l'IA de savoir que l'apocalypse n'a pas �t� effective et donc de rejouer quelquechose d'autre. Le joueur humain 
	 *  se contente d'un message sur la console pour savoir  si l'utilisation de cette carte � �t� effective.
	 *  
	 */
	public static void cEstLapocalypse () 
	{	if (!Partie.isApoJoue())
	{
		if (Partie.compterLesJoueurs()>3)
		{	
			if (Partie.getPlusMauvaisJoueur()==null)
			{ 
				JFrame frameErreur = new JFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				JOptionPane.showMessageDialog(frameErreur , 
						"Apocalypse"
						  ,
						  "Il y � �galit� entre deux joueurs, la carte est donc d�fauss�e et sans effet.\n",
				         JOptionPane.INFORMATION_MESSAGE);
				frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			}
			else {
				if (Partie.getPlusMauvaisJoueur() instanceof JoueurPhysique)
				{
					JFrame frameErreur = new JFrame();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					
					JOptionPane.showMessageDialog(frameErreur , 
							"Echec"
							  ,
					         "Dommage que vous ayez perdu : ^ )",
					         JOptionPane.INFORMATION_MESSAGE);
					frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
				}
				else{
					System.out.println("Le joueur virtuel n�"+Partie.getPlusMauvaisJoueur().getNumJoueur()+" n'a pas reussi � se maintenir dans la course pour le tr�ne du Haut-Dieu.\n ");
					System.out.println("Il � donc �t� �limin� de la partie.");
					Partie.getJoueurs().remove(Partie.getPlusMauvaisJoueur());
					System.out.println("Il reste "+Partie.compterLesJoueurs()+" joueur dans cette partie.\n\n");
					Partie.setApoJoue(true);
				}
				
			}
		}
		else 
		{
			if (Partie.getMeilleurJoueur()==null)
			{ 
				JFrame frameErreur = new JFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				JOptionPane.showMessageDialog(frameErreur , 
						"Apocalypse"
						  ,
						  "Il y � �galit� entre deux joueurs, la carte est donc d�fauss�e et sans effet.\n",
				         JOptionPane.INFORMATION_MESSAGE);
				frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			}
			else {
			Partie.setApoJoue(true);
			if (Partie.getMeilleurJoueur() instanceof JoueurPhysique)
			{
				JFrame frameErreur = new JFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				JOptionPane.showMessageDialog(frameErreur , 
						"Victoire !"
						  ,
				         " Bravo, vous avez gagné",
				         JOptionPane.INFORMATION_MESSAGE);
				frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			}
			else
			{
				//System.out.println("Le joueur virtuel n�"+Partie.getMeilleurJoueur().getNumJoueur()+" � gagn�, bravo � lui.\n");
				JFrame frameErreur = new JFrame();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				JOptionPane.showMessageDialog(frameErreur , 
						"ECHEC !"
						  ,
				         " Quelle dommage, c'est le joueur n°"+Partie.getMeilleurJoueur().getNumJoueur()+" qui à gagné",
				         JOptionPane.INFORMATION_MESSAGE);
				frameErreur.setLocation(dim.width/2-frameErreur.getSize().width/2, dim.height/2-frameErreur.getSize().height/2);
			}
			/*
			System.out.println("Appuyer sur 1 pour avoir les statistique de la partie.\n");
			Scanner sc = new Scanner(System.in);
			int stat = sc.nextInt();
			switch (stat)
			{
			case 1:
			{
				int [] nombrePriereChaqueJoueur = null;
				int i=0;
				Iterator <Joueur> iterateur = Partie.getJoueurs().iterator();
				while (iterateur.hasNext())
				{
					Joueur j = iterateur.next();
					System.out.println("Le joueur n�"+j.getNumJoueur()+" a fini le jeu avec"+j.compterLesPrieres ()+" pri�res.\n C'est donc bien jou� � lui.\n");
					i+=1;
				}
				break;
			}
			default :
				System.out.println("C'est dommage, ce sont pourtant de tr�s bonnes statistiques.\n Dommage pour vous.\n");
			}
			System.out.println("Nous esp�rons que vous avez pass� un agr�able moment sur ce programme.\n");
			*/
			System.out.println("Appuyer sur n'importe quelle lettre pour quitter le jeu.\n");
			Scanner fin = new Scanner(System.in);
			String finJeu = fin.nextLine();
			if (finJeu==finJeu)
			{
				System.exit (0); //instruction pour terminer le programme
			}
			}
		}
	}
	else 
	{
		System.out.println("Une carte apocalypse ne peut pas �tre jou�e � ce moment l� de la partie.\n");
	}
	}
	
	public Apocalypse ( String origine) //en fonction de l'origine on donne l'image appropri�e
	{	
			this.origine=origine;
			Image image = null;
			if (origine == "Jour")
			{
				try {
					image = ImageIO.read(new File ("apocalypse1.jpg"));
				}catch (IOException ie) {
					ie.printStackTrace();
					System.out.println("erreur sur le chargement de l'image");
				}
			}
			else if (origine =="Nuit")
			{
				try {
					image = ImageIO.read(new File ("apocalypse2.jpg"));
				}catch (IOException ie) {
					ie.printStackTrace();
					System.out.println("erreur sur le chargement de l'image");
				}
			}
			else if (origine =="Neant")
			{
				try {
					image = ImageIO.read(new File ("apocalypse3.jpg"));
				}catch (IOException ie) {
					ie.printStackTrace();
					System.out.println("erreur sur le chargement de l'image");
				}
			}
			else if (origine==null)
			{
				try {
					image = ImageIO.read(new File ("apocalypse4.jpg"));
				}catch (IOException ie) {
					ie.printStackTrace();
					System.out.println("erreur sur le chargement de l'image");
				}
			}
			this.setImage(image);
	}
}
