package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Controler;
import modelApocalypse.Apocalypse;

public class ActionPoserApocalypse  implements ActionListener 
{
	Apocalypse carte;
	public ActionPoserApocalypse(Apocalypse carte)
	{
		this.carte = carte;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try {
			Controler.getInstanceControler().poserApocalypse(carte);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
