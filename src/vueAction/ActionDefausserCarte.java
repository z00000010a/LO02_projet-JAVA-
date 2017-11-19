package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Controler;
import modelCarte.CarteAction;

public class ActionDefausserCarte implements ActionListener
{
	private CarteAction carte;
	public ActionDefausserCarte (CarteAction carte)
	{
		this.carte=carte;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			Controler.getInstanceControler().defausserCarte(carte);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
