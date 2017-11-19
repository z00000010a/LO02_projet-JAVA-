package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Test.Test;
import modelDeroulementPartie.Tour;

public class ActionDemarrerJeu implements ActionListener
{	
	JFrame frame;

	public ActionDemarrerJeu(JFrame frame)
	{
		this.frame=frame;
	}
	public void actionPerformed(ActionEvent arg0) 
	{	
		this.frame.dispose();
		Test test = new Test();
		Thread thread = new Thread (test);
		thread.start();
	}

}
