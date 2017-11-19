package vueBouton;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import modelCarte.CarteAction;
import modelCarte.Croyant;
/**
 * Cette classe est un JButton qui contient une carte, ce qui est plus pratique pour l'ajout de certain ActionListener.
 *
 */
public class BoutonCarte extends JButton
{
	CarteAction carte;
	/**
	 * Le premier paramètre est le nom, le second est la carte qui sera contenu dans le bouton.
	 * @param nom Un String qui donne le nom du bouton (utiliser par le constructeur de JButton)
	 * @param carte La carte qui sera mise dans le bouton
	 */
	public BoutonCarte (String nom, CarteAction carte)
	{
		super(nom);
		this.carte = carte;
	}
}
