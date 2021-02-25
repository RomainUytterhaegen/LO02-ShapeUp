package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import model.Game;

/**
 * Classe représentant la vue texte du jeu ShapeUp
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class VueTexte implements Runnable {

    public static String QUITTER = "quit";
    public static String FINTOUR = "end";
    public static String JOUERCARTE = "play";
    public static String DEPLACERCARTE = "move";
    public static String AFFICHERPLATEAU = "board";
    public static String AFFICHERMAIN = "hand";
    public static String MANCHESUIVANTE = "nextRound";
    public static String AIDE = "help";
    public static String PROMPT = ">";

    private Game game;

    /**
     * Constructeur de VueTexte
     * @param game jeu en cours
     */
	public VueTexte(Game game) {
	this.game = game;
	
	Thread t = new Thread(this);
	t.start();
    }

	/**
	 * Appelée lors du lancement du Thread
	 */
    public void run() {

	String saisie = null;
	String[] parsedSaisie = null;
	boolean quitter = false;

	printHelp();
	
	do {
	    saisie = this.lireChaine();
	    parsedSaisie = saisie.split(" ");
	    
	    if (saisie != null) {
		if (saisie.equals(VueTexte.FINTOUR) == true) {
			
		    this.game.getCurrentRound().endTurn();
		    this.game.getCurrentRound().getCurrentPlayer().notifyPlayer();
		    
		} else if (saisie.equals(VueTexte.QUITTER) == true) {
			
		    quitter = true;	
		    
		}else if(parsedSaisie[0].equals(VueTexte.DEPLACERCARTE) == true && parsedSaisie.length == 5) {
			
			this.game.getCurrentRound().currentPlayerMoveCard(Integer.parseInt(parsedSaisie[1]),Integer.parseInt(parsedSaisie[2]),Integer.parseInt(parsedSaisie[3]), Integer.parseInt(parsedSaisie[4]));
			
		}else if(parsedSaisie[0].equals(VueTexte.JOUERCARTE) == true && parsedSaisie.length == 4) {
			
			this.game.getCurrentRound().currentPlayerPlayCard(Integer.parseInt(parsedSaisie[1]),Integer.parseInt(parsedSaisie[2]),Integer.parseInt(parsedSaisie[3]));
			
		}else if(saisie.equals(VueTexte.AIDE) == true) {
			
			printHelp();
			
		}else if(saisie.equals(VueTexte.AFFICHERPLATEAU) == true) {
			
			System.out.println(this.game.getCurrentRound().getBoard());
			
		}else if(saisie.equals(VueTexte.AFFICHERMAIN) == true) {
			
			System.out.println(this.game.getCurrentRound().getCurrentPlayer().displayHand());
			
		}else if(saisie.equals(VueTexte.MANCHESUIVANTE) == true){
			this.game.nextRound();
			this.game.getCurrentRound().getBoard().notifyBoard();
		}else {
			
		    System.out.println("Commande non reconnue...");
		    
		}		
	    }
	} while (quitter == false);
	System.exit(0);
    }

    /**
     * Lis une entrée utilisateur
     * @return l'entrée utilisateur
     */
    private String lireChaine() {
	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	String resultat = null;
	try {
	    System.out.print(VueTexte.PROMPT);
	    resultat = br.readLine();
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	}
	return resultat;	
    }

    /**
     * Affiche les commandes utilisables
     */
    public void printHelp() {
    	System.out.println("Jouer une carte : "+VueTexte.JOUERCARTE+" <indiceCarte> <x> <y>");
		System.out.println("Déplacer une carte : "+VueTexte.DEPLACERCARTE+" <x1> <y1> <x2> <y2>");
		System.out.println("Fin du tour : "+VueTexte.FINTOUR);
		System.out.println("Pour quitter le programme : "+VueTexte.QUITTER);
		System.out.println("Pour un rappel des commandes : "+VueTexte.AIDE);
		System.out.println("Pour afficher votre main : "+VueTexte.AFFICHERMAIN);
		System.out.println("Pour afficher le plateau : "+VueTexte.AFFICHERPLATEAU);
    }
    

}

