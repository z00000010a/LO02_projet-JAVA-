package vueLabel;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelDeroulementPartie.Tour;
import modelJoueur.Joueur;

public class NewTour extends JFrame{
	public NewTour(Tour tour) throws InterruptedException{
		setSize(600,100);
	    setLocation(300, 200);
	    setUndecorated(true);
	    setVisible(true);
	    this.setBackground(Color.BLACK);
	    JPanel p=new JPanel();
	    this.setContentPane(p);
		Label lb=new Label("Tour :"+tour.getNumeroDeTour()+"        commence ");
		lb.setFont(new   java.awt.Font("Dialog",   2,   50));
		lb.setForeground(Color.magenta);
		p.add(lb);
		this.pack();
		 Thread.sleep(2000);
	     this.dispose();
	}

}
