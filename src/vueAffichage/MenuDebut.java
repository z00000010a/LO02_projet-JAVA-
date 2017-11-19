package vueAffichage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vueAction.ActionFermeFenetre;
import vueAction.ActionOuvrirPageWeb;
import vuePopUpInterrogerJoueur.DemanderDebutPartie;
/**
 * Une classe qui permet de démarrer le jeu sur une image et 3 boutons. C'est elle qui contient la 
 * méthode main nécessaire au lancement du programme.
 */
public class MenuDebut extends JFrame
{
	/**
	 * Le contructeur de cette classe, c'est une fenetre avec un JLabel pour afficher l'image de fond et 3 boutons.
	 * Un permettant de lancer le jeu à proprement parler et les deux autres ouvrant un lien respectivement vers les 
	 * règles et vers le site web du créateur du jeu.
	 */
	public MenuDebut()
	{
		super("Pandocréon Divinae : le jeu des luttes divines");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim);//donne les dim de l'écran
		this.setLayout(null);
		JLabel background = null;
		ImageIcon imageJ=null;
		ImageIcon imageR=null;
		ImageIcon imageAP=null;
		
	      try {
			background = new JLabel(new ImageIcon(ImageIO.read(new File("backgroundMenu.jpg"))));
			this.setContentPane(background);
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	      try {
				imageJ = new ImageIcon(ImageIO.read(new File("boutonJ.png")));
		      } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
	      try {
				imageR = new ImageIcon(ImageIO.read(new File("boutonR.png")));
		      } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
	      try {
				imageAP = new ImageIcon(ImageIO.read(new File("boutonAP.png")));
		      } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }

		 JButton jouer = new JButton("Jouer",imageJ);
		 jouer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {	DemanderDebutPartie dem = new DemanderDebutPartie();}});
		 jouer.addActionListener(new ActionFermeFenetre(this));
		 JButton regles = new JButton("Jouer",imageR);
		 regles.addActionListener(new ActionOuvrirPageWeb("http://www.pandocreon.com/jeux/divinae/pandocreon_divinae-regles-1.0.0.pdf"));
		 JButton apropos = new JButton("Jouer",imageAP);
		 apropos.addActionListener(new ActionOuvrirPageWeb("http://www.pandocreon.com/jeux/divinae/index.php?sess=&l=fr")); 

		 this.add(jouer);
		 this.add(regles);
		 this.add(apropos);
		 
		
		 jouer.setLocation(dim.width/2-125, dim.height/4);
		 regles.setLocation(dim.width/2-125, dim.height/4+150);
		 apropos.setLocation(dim.width/2-125, dim.height/4+300);
		 
		 jouer.setSize(287, 67);
		 regles.setSize(287, 67);
		 apropos.setSize(287, 67);

		 this.setVisible(true);
	}
	
	
	
	
	public static void main(String[] args)
	{
		new MenuDebut();
	}

}
