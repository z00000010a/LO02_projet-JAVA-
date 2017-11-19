package modelDeroulementPartie;
import java.util.*;

import modelApocalypse.Apocalypse;
import modelCarte.CarteAction;
import modelCarteDeusEx.*;
import modelCroyantJour.*;
import modelCroyantNeant.*;
import modelCroyantNuit.*;
import modelGuideSpirituel.*;
/**
 * Represente la pile de la partie. Implémenter en singleton. Une LinkedList de carteAction avec des methodes
 *  permettant de piocher et de remettre des cartes dans la pile. Contient aussi une méthode permettant à la création de la pile d'y
 *  ajouter toutes les cartes du jeu. ATTENTTION PLUSIEURS CARTES NE SONT PAS FINIS. LEUR CAPACITE N'ETANT PAS IMPLEMENTE ELLES SONT 
 *  AJOUTE DANS LE JEU MAIS LEUR CAPACITE EST INEFFECTIVE. C'est aussi le cas de certaines divinités.
 *
 */
public class Pile 
{	/**
	*La LinkedList contenant les cartes de la pile.
	*/

		private LinkedList <CarteAction> pile;
	//patron du singleton
		private static Pile INSTANCE =null;
		/**
		 * Contructeur de Pile en privé (patron du singleton). Il utilise la méthode ajouterTouteslesCartes() où l'on créer toutes les 
		 * cartes de la partie.
		 */
		private Pile() {	pile = new LinkedList <CarteAction>();
				 //initialise la List, avant elle n'était que déclarée
		 ajouterTouteslesCartes();
		}
		
		public static Pile getInstance ()
		{	
			if (INSTANCE == null)
			{
				INSTANCE = new Pile();
			}
			return INSTANCE;
		}
		
		/**
		 * Créé et ajoute toutes les cartes présentes dans le jeu.
		 */
		
		public void ajouterTouteslesCartes()
		{
			Anarchiste anarchiste = new Anarchiste ();
			Ascete ascete = new Ascete();
			Clerc clerc1 = new Clerc("Humain","Chaos","Jour",1);
			Clerc clerc2 = new Clerc("Nature","Symboles","Nuit",2);
			Clerc clerc3 = new Clerc("Mystique","Nature","Neant",3);
			Clerc clerc4 = new Clerc("Chaos","Nature","Jour",4);
			Clerc clerc5 = new Clerc("Mystique","Symboles","Nuit",5);
			Clerc clerc6 = new Clerc("Chaos","Symboles","Neant",6);
			Clerc clerc7 = new Clerc("Chaos","Mystique","Jour",7);
			Clerc clerc8 = new Clerc("Humain","Nature","Nuit",8);
			Clerc clerc11 = new Clerc("Humain","Chaos","Jour",1);
			Clerc clerc12 = new Clerc("Nature","Symboles","Nuit",2);
			Clerc clerc13 = new Clerc("Mystique","Nature","Neant",3);
			Clerc clerc14 = new Clerc("Chaos","Nature","Jour",4);
			Clerc clerc15 = new Clerc("Mystique","Symboles","Nuit",5);
			Clerc clerc16 = new Clerc("Chaos","Symboles","Neant",6);
			Clerc clerc17 = new Clerc("Chaos","Mystique","Jour",7);
			Clerc clerc18 = new Clerc("Humain","Nature","Nuit",8);
			Devin devin = new Devin();
			Exorciste exorciste = new Exorciste();
			Martyr martyr1 = new Martyr("Nature","Humain","Jour");
			Martyr martyr2 = new Martyr("Symboles","Humain","Nuit");
			Martyr martyr3 = new Martyr("Nature","Chaos","Neant");
			Messie messie = new Messie ();
			Paladin palouf = new Paladin();
			Shaman shaman = new Shaman();
			Sorcier sorcier = new Sorcier();
			Tyran tyran = new Tyran();
			pile.add(tyran);
			pile.add(sorcier);
			pile.add(shaman);
			pile.add(palouf);
			pile.add(messie); // pas fini
			pile.add(exorciste);
			pile.add(devin);
			pile.add(clerc8);
			pile.add(clerc7);
			pile.add(clerc6);
			pile.add(clerc5);
			pile.add(clerc4);
			pile.add(clerc3);
			pile.add(clerc2);
			pile.add(clerc1);
			pile.add(ascete);
			pile.add(anarchiste);
//			pile.add(martyr3);
//			pile.add(martyr2);
//			pile.add(martyr1);
			pile.add(clerc18);
			pile.add(clerc17);
			pile.add(clerc16);
			pile.add(clerc15);
			pile.add(clerc14);
			pile.add(clerc13);
			pile.add(clerc12);
			pile.add(clerc11);
			//============================================= fin ajout des GS
			Diplomates diplomates = new Diplomates ();
			Ermite1 ermite1 = new Ermite1 ();
			Ermite2 ermite2 = new Ermite2 ();
			Integristes integristes = new Integristes ();
			Moines1 moines1 = new Moines1();
			Moines2 moines2 = new Moines2();
			Moines3 moines3 = new Moines3();
			Moines4 moines4 = new Moines4();
			Moines5 moines5 = new Moines5();
			Moines1 moines11 = new Moines1();
			Moines2 moines12 = new Moines2();
			Moines3 moines13 = new Moines3();
			Moines4 moines14 = new Moines4();
			Moines5 moines15 = new Moines5();
			Travailleurs1 travailleur1 = new Travailleurs1();
			Travailleurs2 travailleur2 = new Travailleurs2();
			Travailleurs3 travailleur3 = new Travailleurs3();
			pile.add(travailleur3);
			pile.add(travailleur2);
			pile.add(travailleur1);
			pile.add(moines5);
			pile.add(moines4);
			pile.add(moines3);
			pile.add(moines2);
			pile.add(moines1);
			pile.add(moines15);
			pile.add(moines14);
			pile.add(moines13);
			pile.add(moines12);
			pile.add(moines11);
			pile.add(integristes);
			pile.add(ermite2);
			pile.add(ermite1);
			pile.add(diplomates);
			//===========================================fin ajout croyant jour SAUF guerrier saint
			Alienes1 alienes1 = new Alienes1();
			Alienes2 alienes2 = new Alienes2();
			Alienes3 alienes3 = new Alienes3();
			Esprits1 esprit1 = new Esprits1();
			Esprits2 esprit2 = new Esprits2();
			Esprits3 esprit3 = new Esprits3();
			Esprits4 esprit4 = new Esprits4();
			Esprits5 esprit5 = new Esprits5();
			Esprits1 esprit11 = new Esprits1();
			Esprits2 esprit12 = new Esprits2();
			Esprits3 esprit13 = new Esprits3();
			Esprits4 esprit14 = new Esprits4();
			Esprits5 esprit15 = new Esprits5();
			Nihillistes nihilistes = new Nihillistes();
			Revenant revenant = new Revenant();
			Revolutionnaires revolutionnaires = new Revolutionnaires();
			pile.add(revolutionnaires);
//			pile.add(nihilistes);
			pile.add(revenant);
			pile.add(esprit5);
			pile.add(esprit4);
			pile.add(esprit3);
			pile.add(esprit2);
			pile.add(esprit1);
			pile.add(esprit15);
			pile.add(esprit14);
			pile.add(esprit13);
			pile.add(esprit12);
			pile.add(esprit11);
			pile.add(alienes3);
			pile.add(alienes2);
			pile.add(alienes1);
			pile.add(revolutionnaires);
			//=========================================fin ajout croyant Neant
			Alchimistes1 alchimistes1 = new Alchimistes1();
			Alchimistes2 alchimistes2 = new Alchimistes2();
			Alchimistes3 alchimistes3 = new Alchimistes3();
			Demons1 demons1 = new Demons1();
			Demons2 demons2 = new Demons2();
			Demons3 demons3 = new Demons3();
			Demons4 demons4 = new Demons4();
			Demons5 demons5 = new Demons5();
			Demons1 demons11 = new Demons1();
			Demons2 demons12 = new Demons2();
			Demons3 demons13 = new Demons3();
			Demons4 demons14 = new Demons4();
			Demons5 demons15 = new Demons5();
			Lycanthropes lycanthropes = new Lycanthropes();
			Pillards pillards = new Pillards();
			Vampire1 vampire1 = new Vampire1();
			Vampire2 vampire2 = new Vampire2();
			GuerriersSaints guerriersSaints= new GuerriersSaints();
			pile.add(vampire2);
			pile.add(vampire1);
//			pile.add(pillards);
			pile.add(lycanthropes);
			pile.add(demons5);
			pile.add(demons4);
			pile.add(demons3);
			pile.add(demons2);
			pile.add(demons1);
			pile.add(demons15);
			pile.add(demons14);
			pile.add(demons13);
			pile.add(demons12);
			pile.add(demons11);
			pile.add(alchimistes3);
			pile.add(alchimistes2);
			pile.add(alchimistes1);
			pile.add(guerriersSaints);
			//=========================================fin ajout croyant Nuit SAUF ILLUSIONNISTES
			Bouleversement bouleversement=new Bouleversement();
			ColereDivine1 ColereDivine1=new ColereDivine1();
			ColereDivine2 colereDivine2=new ColereDivine2();
			Concentration concentration=new Concentration();
			Diversion diversion=new Diversion();
			Fourberie fourberie=new Fourberie();
			InfluenceJour influenceJour=new InfluenceJour();
			InfluenceNeant influenceNeant=new InfluenceNeant();
			InfluenceNuit InfluenceNuit=new InfluenceNuit();
			InfluenceNulle1 influenceNulle1=new InfluenceNulle1();
			InfluenceNulle2 influenceNulle2=new InfluenceNulle2();
			Inquisition inquisition=new Inquisition();
			Miroir miroir=new Miroir();
			OrdreCeleste ordreCeleste=new OrdreCeleste();
			Phoenix phoenix=new Phoenix();
			Stase stase=new Stase();
			TrouNoir trouNoir=new TrouNoir();
			pile.add(bouleversement);
			pile.add(ColereDivine1);
			pile.add(colereDivine2);
			pile.add(concentration);
//			pile.add(diversion);
			pile.add(fourberie);
			pile.add(influenceJour);
			pile.add(influenceNeant);
			pile.add(InfluenceNuit);
			pile.add(influenceNulle1);
			pile.add(influenceNulle2);
//			pile.add(inquisition);
			pile.add(ordreCeleste);
//			pile.add(phoenix);
			pile.add(stase);
//			pile.add(trouNoir);
			//==================================================== Fin ajout DEUS EX | PHENIX non ajoutï¿½
			Apocalypse apocalypseJ = new Apocalypse("Jour");
			Apocalypse apocalypseN = new Apocalypse("Nuit");
			Apocalypse apocalypseNe = new Apocalypse("Neant");
			Apocalypse apocalypseSO1 = new Apocalypse(null);
			Apocalypse apocalypseSO2 = new Apocalypse(null);
			pile.add(apocalypseSO2);
			pile.add(apocalypseSO1);
			pile.add(apocalypseNe);
			pile.add(apocalypseN);
			pile.add(apocalypseJ);
			//===================================================Fin ajout carte APOCALYPSE
			System.out.println("Jeu de carte créé.\n");
		}
/**
 * Permet de replacer un objet en mettant à jour le booléen dansPile. Utile après l'utilisation d'une capacité sacrifier par exemple.
 * @param objet
 * La carteAction que l'on souhaite replacer dans la pile.
 */
	public void ajouterCarteDansPile(CarteAction objet)
	{
		pile.add(objet);
		objet.setDansPile(true);
	}
	/**
	 * Permet de piocher une carte dans la pile. Mélange cette dernière avant la pioche.
	 * @return
	 * Retourne la carteAction qui était à l'index 0 de la LinkedList après le shuffle de cette dernière.
	 */
	public CarteAction piocher()
	{
		Collections.shuffle(pile);
		CarteAction carte = pile.pop();
		carte.setDansPile(false);
		return carte;
	}
	/**
	 * Permet d'afficher l'ensemble de la pile. Surtout utiliser à des fins de developement.
	 */
	public static void afficherPile()
	{
		Iterator <CarteAction> iterateur= INSTANCE.pile.iterator();
		
		while (iterateur.hasNext())
		{
			CarteAction objettest=iterateur.next(); 
			System.out.println("ID de la carte : "+objettest.getIdCarte());
			System.out.println("Nom de la carte : "+objettest.getNom());
		}
	}
}
