package vueLabel;

import java.awt.Color;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelCarte.CarteAction;
import modelJoueur.Joueur;

public class VueLabelPoserCro extends JFrame{
	public VueLabelPoserCro(Joueur j) throws InterruptedException{
	setSize(600,100);
    setLocation(300, 200);
    setUndecorated(true);
    setVisible(true);
    this.setBackground(Color.BLACK);
    JPanel p=new JPanel();
    this.setContentPane(p);
	Label lb=new Label("Le joueur virtuel n°"+j.getNumJoueur()+" pose un croyant");
	lb.setFont(new   java.awt.Font("Dialog",   2,   50));
	lb.setForeground(Color.BLUE);
	p.add(lb);
	this.pack();
	 Thread.sleep(2000);
     this.dispose();
}
	public static void avertirJoueur(Joueur j, CarteAction carte) throws InterruptedException
	{
		VueLabelPoserCro alerte = new VueLabelPoserCro(j);
		VueActionCarteUse dispCarte = new VueActionCarteUse(carte);
	}

}
