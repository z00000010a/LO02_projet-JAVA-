package vueLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelJoueur.Joueur;

public class VueFin extends JFrame{
	public VueFin(Joueur j) throws InterruptedException{
		setSize(600,100);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/3, dim.height/2);
	    setUndecorated(true);
	    setVisible(true);
	    this.setBackground(Color.BLACK);
	    JPanel p=new JPanel();
	    this.setContentPane(p);
		Label lb=new Label("Fin de la phase du joueur virtuel n°"+j.getNumJoueur());
		lb.setFont(new   java.awt.Font("Dialog",3,   50));
		lb.setForeground(Color.GRAY);
		p.add(lb);
		this.pack();
		 Thread.sleep(2000);
	     this.dispose();
	}
	public static void avertirJoueur(Joueur j)
	{
		try {
			VueFin v = new VueFin(j);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
