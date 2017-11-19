package vueLabel;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import modelCarte.CarteAction;
import vueAffichage.AfficheImage;

public class VueActionCarteUse extends JFrame{
	public VueActionCarteUse(CarteAction c) throws InterruptedException{
		 setSize(300,200);
	      setLocation(500, 200);
	      setUndecorated(true);
	      setVisible(true);
	      FlowLayout leFlux = new FlowLayout();
			this.getContentPane().setLayout(leFlux);
			AfficheImage afficheImage1 = new AfficheImage (c.getImage());
			this.getContentPane().add(afficheImage1, 0);
			this.update(getGraphics());
			this.pack();
			validate();
	     Thread.sleep(2000);
	     this.dispose();		
	}

}
