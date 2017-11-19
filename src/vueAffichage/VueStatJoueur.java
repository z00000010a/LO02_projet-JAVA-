package vueAffichage;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelDeroulementPartie.Partie;
import modelJoueur.Joueur;
/**
 * 
 *
 */
public class VueStatJoueur extends JPanel implements Observer
{
	private Joueur joueur;
	private JLabel texteNom;
	private JLabel textePointJour;
	private JLabel textePointNuit;
	private JLabel textePointNeant;
	private JLabel texteNombrePrieres;
	private AfficheImage imageCarteDivinite;

	public VueStatJoueur (Joueur joueur)
	{
		
		this.joueur = joueur;
		this.texteNom = new JLabel (joueur.getNomJoueur());
		this.textePointJour = new JLabel ("Point d'action Jour : "+joueur.getPointActionJour());
		this.textePointNeant = new JLabel ("Point d'action Neant : "+joueur.getPointActionNeant());
		this.textePointNuit = new JLabel ("Point d'action Nuit : "+joueur.getPointActionNuit());
		if (Partie.getMeilleurJoueur()==null)
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Il n'y a pas de meilleur joueur actuellement.");
		}
		else if (Partie.verifieSiJoueurDansMoyenneSup(joueur) || Partie.getMeilleurJoueur().equals(joueur))
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Si vous placez une apocalypse maintenant cela vous sera bénéfique.");
		}
		else if (Partie.verifieSiJoueurDansMoyenneSup(joueur)==false ||Partie.getMeilleurJoueur()!= joueur)
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Si vous placez une apocalypse maintenant cela vous fera perdre le jeu.");
		}

		//ImageIcon icon = new ImageIcon(joueur.getDivinite().getImage());
		this.imageCarteDivinite = new AfficheImage(joueur.getDivinite().getImage());
		
		texteNom.setFont(new Font("Serif", Font.PLAIN, 40));
		textePointJour.setFont(new Font("Serif", Font.PLAIN, 30));
		textePointNuit.setFont(new Font("Serif", Font.PLAIN, 30));
		textePointNeant.setFont(new Font("Serif", Font.PLAIN, 30));
		texteNombrePrieres.setFont(new Font("Serif", Font.PLAIN, 30));
		BoxLayout bLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(bLayout);
		
		this.add(texteNom, 0);
		this.add(textePointJour, 1);
		this.add(textePointNeant, 2);
		this.add(textePointNuit, 3);
		this.add(texteNombrePrieres, 4);
		this.add(imageCarteDivinite, 5);
		
		this.setVisible(true);
	}
	
	public void rafraichir ()
	{
		removeAll();
		
		this.texteNom = new JLabel (joueur.getNomJoueur());
		this.textePointJour = new JLabel ("Point d'action Jour : "+joueur.getPointActionJour());
		this.textePointNeant = new JLabel ("Point d'action Neant : "+joueur.getPointActionNeant());
		this.textePointNuit = new JLabel ("Point d'action Nuit : "+joueur.getPointActionNuit());
		if (Partie.getMeilleurJoueur()==null)
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Il n'y a pas de meilleur joueur actuellement.");
		}
		else if (Partie.verifieSiJoueurDansMoyenneSup(joueur) || Partie.getMeilleurJoueur().equals(joueur))
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Si vous placer une apocalypse maintenant cela vous sera bénéfique.");
		}
		else if (Partie.verifieSiJoueurDansMoyenneSup(joueur)==false ||Partie.getMeilleurJoueur()!= joueur)
		{
			this.texteNombrePrieres = new JLabel ("Vous avez actuellement "+joueur.compterLesPrieres()+" prières.\n"
					+ " Si vous placer une apocalypse maintenant cela vous fera perdre le jeu.");
		}
		//ImageIcon icon = new ImageIcon(joueur.getDivinite().getImage());
		this.imageCarteDivinite = new AfficheImage(joueur.getDivinite().getImage());
		texteNom.setFont(new Font("Serif", Font.PLAIN, 40));
		textePointJour.setFont(new Font("Serif", Font.PLAIN, 30));
		textePointNuit.setFont(new Font("Serif", Font.PLAIN, 30));
		textePointNeant.setFont(new Font("Serif", Font.PLAIN, 30));
		texteNombrePrieres.setFont(new Font("Serif", Font.PLAIN, 30));
		this.add(texteNom, 0);
		this.add(textePointJour, 1);
		this.add(textePointNeant, 2);
		this.add(textePointNuit, 3);
		this.add(texteNombrePrieres, 4);
		this.add(imageCarteDivinite, 5);
	}
	/*
	public void ajouterBoutonFinTour(Thread t)
	{
		this.add(new BoutonFinTour(t));
	}
	*/
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		rafraichir();
	}
	
	
	
	
	
}
