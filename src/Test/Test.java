package Test;

import modelApocalypse.Apocalypse;
import modelCarteDeusEx.Bouleversement;
import modelCarteDeusEx.ColereDivine1;
import modelCarteDeusEx.ColereDivine2;
import modelCarteDeusEx.Concentration;
import modelCarteDeusEx.Diversion;
import modelCarteDeusEx.Fourberie;
import modelCarteDeusEx.InfluenceJour;
import modelCarteDeusEx.InfluenceNeant;
import modelCarteDeusEx.InfluenceNuit;
import modelCarteDeusEx.InfluenceNulle1;
import modelCarteDeusEx.InfluenceNulle2;
import modelCarteDeusEx.Inquisition;
import modelCarteDeusEx.Miroir;
import modelCarteDeusEx.OrdreCeleste;
import modelCarteDeusEx.Phoenix;
import modelCarteDeusEx.Stase;
import modelCarteDeusEx.TrouNoir;
import modelCroyantJour.*;
import modelDeroulementPartie.Partie;
import modelDeroulementPartie.Pile;
import modelDeroulementPartie.Tour;
import modelGuideSpirituel.*;

public class Test implements Runnable{
	
	public static String verrouThread = "Cet objet est uniquement dédié à ce que les 2 threads s'échange son verrou";
	
	public static  void lancerJeu() throws InterruptedException 
	{
		Tour firstTour = new Tour();
		Tour t =firstTour;
		while (true)
		{
			Tour tour = new Tour(t);
			t=tour;
		}

	}
	
	@Override
	public void run()
	{
		synchronized (Test.getVerrouThread())
		{
			Tour firstTour = null;
			try {
				firstTour = new Tour();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tour t =firstTour;
			while (true)
			{
				Tour tour = null;
				try {
					tour = new Tour(t);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t=tour;
			}
		}
		
	}
	public static void main(String[] args) throws InterruptedException
	{
		Test test = new Test();
		Thread thread = new Thread (test);
		thread.start();
		/*
		//=============================================
		Tour firstTour = new Tour();
		Tour t =firstTour;
		while (true)
		{
			Tour tour = new Tour(t);
			t=tour;
		}

		*/
	}

	public static String getVerrouThread() {
		return verrouThread;
	}

	public static void setVerrouThread(String verrouThread) {
		Test.verrouThread = verrouThread;
	}
	
}

