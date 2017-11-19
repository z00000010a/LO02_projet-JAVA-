package vueLabel;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelJoueur.Joueur;

public class VueCommence extends JFrame{
	public VueCommence(Joueur j) throws InterruptedException{
	setSize(600,100);
    setLocation(300, 200);
    setUndecorated(true);
    setVisible(true);
    this.setBackground(Color.BLACK);
    JPanel p=new JPanel();
    this.setContentPane(p);
	Label lb=new Label("Le joueur virtuel n°"+j.getNumJoueur()+"        commence sa phase");
	lb.setFont(new   java.awt.Font("Dialog",   2,   50));
	lb.setForeground(Color.ORANGE);
	p.add(lb);
	this.pack();
	 Thread.sleep(2000);
     this.dispose();
}

}
