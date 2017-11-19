package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Test.Test;

public class ActionKillThreadFenetreInterrogeJoueur implements ActionListener
{
	private Thread t;
	private JFrame frame;
	
	public ActionKillThreadFenetreInterrogeJoueur (Thread t, JFrame frame)
	{
		this.t=t;
		this.frame=frame;
	}
	
	public void actionPerformed(ActionEvent arg0)
	{ synchronized (Test.getVerrouThread())
		{
			frame.dispose();
			Test.getVerrouThread().notifyAll();
			t.interrupt();
		}
	}
}
