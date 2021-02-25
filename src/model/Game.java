package model;
import java.awt.EventQueue;
import java.util.*;

import view.EndGameWindow;

/**
 * Classe représentant une partie du jeu ShapeUp
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html">Observable</a>
 */
public class Game extends Observable{
	
	private ArrayList<Round> rounds;
	private ArrayList<Player> players;
	private Gamerule gamerule;
	private Board board;
	private int currentRound;
	
	/**
	 * Constructeur de la classe Game
	 * @param players Liste des joueurs
	 * @param gamerule Mode de jeu
	 * @param board Plateau utilisé
	 * 
	 * @see Player
	 * @see Gamerule
	 * @see Board
	 */
	public Game(ArrayList<Player> players,Gamerule gamerule, Board board) {
		this.players = players;
		this.board = board;
		this.gamerule = gamerule;
		this.rounds = new ArrayList<>();
		this.currentRound = 0;
		for(int i = 1 ; i < 5 ; i++) {
			switch(this.board.getClass().getSimpleName()) {
			case "Board1":
				this.rounds.add(new Round(this.players,new Board1(),gamerule,i));
				break;
			case "Board2":
				this.rounds.add(new Round(this.players,new Board2(),gamerule,i));
				break;
			case "Board3":
				this.rounds.add(new Round(this.players,new Board3(),gamerule,i));
				break;
			}
			
		}
	}
	
	/**
	 * Permet de lancer une partie
	 */
	public void start() {
		// Initialisation
		this.rounds.get(this.currentRound).initialize();
	}
	
	/**
	 * Permet de passer au Round suivant, lance la fin de la partie si c'est le dernier Round.
	 */
	public void nextRound() {
		if(this.getCurrentRound().isRoundFinished()) { // Si le Round est terminé
			if(this.currentRound == 3) { // Si la partie est terminée
				this.end();
			}else {
				this.currentRound++;
				this.rounds.get(this.currentRound).initialize();
			}
		}else {
			System.out.println("La manche n'est pas terminée !");
		}
		
	}
	
	/**
	 * Permet de terminer la partie, compte les points de chaque joueur pour déterminer le classement.
	 */
	public void end() {
		System.out.println("Fin de la partie !");
		System.out.println("Classement : ");
		// Tri et affichage du classement des joueurs
		Collections.sort(this.players, new SortPlayerByPoint());
		Collections.reverse(this.players);
		for(int j = 0 ; j < this.players.size() ; j++) {
			System.out.println((j+1)+" "+this.players.get(j).getName()+" : "+this.players.get(j).getScore());
		}
		
		//Instanciation de la fenêtre de fin de partie
		final ArrayList<Player> playerList = this.players;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndGameWindow frame = new EndGameWindow(playerList);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Accesseur des rounds de la partie.
	 * @return liste des Round
	 * 
	 * @see Round
	 */
	public ArrayList<Round> getRounds(){
		return this.rounds;
	}
	
	/**
	 * Acesseur des joueurs de la partie.
	 * @return liste des Player
	 * 
	 * @see Player
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Acesseur du plateau de référence de la partie
	 * @return le plateau de référence
	 * 
	 * @see Board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Acesseur au Round en train d'être joué
	 * @return le Round actuel
	 * 
	 * @see Round
	 */
	public Round getCurrentRound() {
		return this.rounds.get(this.currentRound);
	}
	
	/**
	 * Accesseur de gamerule
	 * @return le gamerule
	 * 
	 * @see Gamerule
	 */
	public Gamerule getGamerule() {
		return this.gamerule;
	}
	
	
	

}
