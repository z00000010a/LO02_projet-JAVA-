package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Controler;
import modelCarte.GuideSpirituel;

public class ActionPoserGS  implements ActionListener {
	GuideSpirituel guide;
	public ActionPoserGS(GuideSpirituel guide)
	{
		this.guide = guide;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		try {
			Controler.getInstanceControler().poserGuideSpirituel(guide);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
