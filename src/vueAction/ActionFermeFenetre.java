package vueAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ActionFermeFenetre implements ActionListener 
{
	private JFrame frame;
	public ActionFermeFenetre(JFrame frame)
	{
		this.frame=frame;
	}
	public void actionPerformed(ActionEvent e) 
	{
		frame.dispose();
	}

}
