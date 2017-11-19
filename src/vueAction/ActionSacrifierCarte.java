package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import controller.Controler;
import modelCarte.CarteAction;
/**
 * Action appelé par tout les boutons qui servent à utiliser/sacrifier une carte
 * @author Crowbar
 *
 */
public class ActionSacrifierCarte implements ActionListener 
{
	private CarteAction carte;
	public ActionSacrifierCarte(CarteAction carte) 
	{
		this.carte=carte;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try {
			Controler.getInstanceControler().UtiliserLeSacrifice(carte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
