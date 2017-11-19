package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Controler;
import modelCarte.Croyant;

public class ActionPoserCroyant implements ActionListener
{
	private Croyant cro = null;
	
	public ActionPoserCroyant (Croyant cro)
	{
		this.cro=cro;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		try {
			Controler.getInstanceControler().getInstanceControler().poserCroyant(cro);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("bobobobobo");
	}

}
