package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Controler;
import modelCarte.Divinite;

public class ActionUtiliserCapaDiv implements ActionListener 
{
	private Divinite div;
	
	public  ActionUtiliserCapaDiv(Divinite di) 
	{
		this.div=di;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		try {
			Controler.getInstanceControler().utiliserCapaDiv(div);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
