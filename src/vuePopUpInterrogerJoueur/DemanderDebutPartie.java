package vuePopUpInterrogerJoueur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import vueAction.ActionDemarrerJeu;
/**
 * Cette classe g閚鑢e la seconde fen鑤re du jeu, celle qui va r閏uper le nom du joueur et le nombre de joueur virtuel qu'il veut affronter
 *
 */
public class DemanderDebutPartie
{
	/**
	 * Le nom du joueur, sous forme de String
	 */
	private static String nomJ;
	/**
	 * Le nombre de joueur virtuel que le joueur humain va affronter, sous forme de int
	 */
	private static int nbJV;
	/**
	 *  Le constructeur de la classe, elle est appeler par la classe menuDebut lorsque l'on appuie sur le bouton jouer. Petite sp閏ificit� :
	 *  ce n'est pas un text field mais un NumberFormatter qui est utilis� pour le nombre de joueur, ce qui permet de forcer le joueur 
	 *  a ne rentrer que des entier compris entre 1 et 8. 
	 */
	public DemanderDebutPartie()
	{
		JFrame frame = new JFrame("Entrer ces informations pour d閎uter la partie");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		final JTextField nomJoueur = new JTextField("Entrer votre nom", 25);
	    NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(1);
	    formatter.setMaximum(8);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    final JFormattedTextField nbJoueurVirtual = new JFormattedTextField(formatter);
		JLabel label = new JLabel("Entrer le nombre d'adversaires que vous d閟irez");
		JButton valider = new JButton("valider");
		
		valider.addActionListener(new ActionListener () {public void actionPerformed(ActionEvent arg0) 
		{ 	DemanderDebutPartie.setNomJ(nomJoueur.getText());
			int jml;
			DemanderDebutPartie.setNbJV( jml = Integer.parseInt(nbJoueurVirtual.getText()));
		}});	
		valider.addActionListener(new ActionDemarrerJeu(frame));
			
		
		frame.getContentPane().add(nomJoueur);
		frame.getContentPane().add(label);
		frame.getContentPane().add(nbJoueurVirtual);
		frame.getContentPane().add(valider);
		frame.pack();
		frame.setDefaultCloseOperation(0);
		frame.setLocation(dim.width/2-frame.getWidth()/2, dim.height/2-frame.getHeight()/2);
		//((JComponent) frame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.setVisible(true);
		
	}
	public static String getNomJ()
	{
		return nomJ;
	}
	public static void setNomJ(String nomJ) 
	{
		DemanderDebutPartie.nomJ = nomJ;
	}
	public static int getNbJV() {
		return nbJV;
	}
	public static void setNbJV(int i) 
	{
		DemanderDebutPartie.nbJV = i;
	}
}
