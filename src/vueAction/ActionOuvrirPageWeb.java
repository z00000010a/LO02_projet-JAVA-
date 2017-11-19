package vueAction;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Action;

public class ActionOuvrirPageWeb implements ActionListener {
	private String url;
	public ActionOuvrirPageWeb(String url)
	{
		this.url=url;
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	}


