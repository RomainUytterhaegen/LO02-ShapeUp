package model;

import java.util.*;

/**
 * Classe représentant une manche de partie de ShapeUp
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html">Observable</a>
 */
public class Round extends Observable{

	private Deck deck;
	private Card hiddenCard;
	private Board board;
	private List<Player> players;
	private Gamerule gamerule;
	private int roundNumber;
	private int currentTurn;
	private boolean currentPlayerMovedACard;
	private boolean currentPlayerPlayedACard;
	private boolean roundFinished;

	/**
	 * Constructeur de Round
	 * @param players La liste des joueurs
	 * @param board Le plateau de jeu
	 * @param gamerule Le mode de jeu
	 * @param roundNumber le numéro de manche
	 * 
	 * @see Player
	 * @see Board
	 * @see Gamerule
	 */
	public Round(List<Player> players, Board board, Gamerule gamerule,int roundNumber) {
		this.players = players;
		this.board = board;
		this.deck = new Deck();
		this.currentTurn=0;
		this.currentPlayerMovedACard = false;
		this.currentPlayerPlayedACard = false;
		this.roundFinished = false;
		if (players.size() == 2) {
			this.hiddenCard = this.deck.drawTopCard();
		} else {
			this.hiddenCard = null;
		}
		this.gamerule = gamerule;
		this.roundNumber = roundNumber;
	}

	/**
	 * Initialise une manche du jeu
	 */
	public void initialize() {
		
		// Distribution des cartes de victoire
		if (this.gamerule.getStartingVictoryCard()) {
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setVictoryCard(this.deck.drawTopCard());
			}
		}
		
		// Distribution des cartes initiales dans la main
		for(int j = 0 ; j < this.gamerule.getStartingCards(); j++) {
			this.getCurrentPlayer().drawCard(this.deck.drawTopCard());
		}
		
		for(int j = 0 ; j < this.gamerule.getStartingCards()-1; j++) {
			if(this.players.size() == 3) {
				this.players.get((this.currentTurn+this.roundNumber+2) % players.size()).drawCard(this.deck.drawTopCard());
			}
			this.players.get((this.currentTurn+this.roundNumber+1) % players.size()).drawCard(this.deck.drawTopCard());
		}
		
		System.out.println("Tour 1:");
		if(!this.players.get((this.currentTurn+this.roundNumber) % players.size()).isHuman()){
			this.robotPlays();
			this.endTurn();
		}
		
		System.out.println("Manche "+this.roundNumber);
		this.hasChanged();
		this.notifyObservers();

	}
	
	/**
	 * Accesseur à numéro de tour actuel
	 * @return le numéro de tour
	 */
	public int getCurrentTurn() {
		return this.currentTurn;
	}
	
	/**
	 * Permet de terminer un tour
	 */
	public void endTurn() {
		if(this.roundEnded()) {
			this.roundFinished = true;
			this.endRound();
		}else if(!this.currentPlayerPlayedACard) {
			System.out.println("Vous n'avez pas joué de carte, vous ne pouvez pas terminer le tour.");
		}else {
			System.out.println("Fin du tour.");
			this.nextTurn();
		}
		this.hasChanged();
		this.notifyObservers();
	}
	
	/**
	 * Récupère le joueur qui doit jouer
	 * @return joueur qui doit jouer
	 * 
	 * @see Player
	 */
	public Player getCurrentPlayer() {
		return this.players.get((this.currentTurn+this.roundNumber) % players.size());
	}
	
	/**
	 * Accesseur de l'attribut hasCurrentPlayerPlayedACard
	 * @return true si le joueur a déjà joué une carte, false sinon
	 */
	public boolean hasCurrentPlayerPlayedACard() {
		return currentPlayerPlayedACard;
	}
	
	/**
	 * Permet de passer au tour suivant
	 */
	public void nextTurn() {
		// Reinitilisation du tour
		this.currentTurn++;
		this.currentPlayerMovedACard = false;
		this.currentPlayerPlayedACard = false;
		
		// Annoncer le joueur qui va jouer
		System.out.println(this.getCurrentPlayer().getName()+", à toi de joueur");
		
		// Faire piocher une carte au joueur
			if(!this.deck.isEmpty()) {
					this.getCurrentPlayer().drawCard(this.deck.drawTopCard());
			}
		
		
		
		
		
		// Cas où le joueur est un robot
		if(!this.getCurrentPlayer().isHuman()){
			if(!this.roundEnded()) {
				this.robotPlays();
			}
			this.endTurn();
		}
		
		
		
		
	}
	
	/**
	 * Permet de terminer le round actuel
	 */
	public void endRound() {
		this.roundFinished = true;
			// COMPTAGE DES POINTS POUR CHAQUE JOUEUR , PRENDRE EN COMPTE
			// SI LA VICTORYCARD EST PAS DEFINIE, ON LA SET POUR CHAQUE CARTE RESTANT DANS
			// LA MAIN DE CHAQUE JOUEUR
			if (!this.gamerule.getStartingVictoryCard()) {
				for (int i = 0; i < players.size(); i++) {
					players.get(i).setVictoryCard(players.get(i).getHand().get(0));
				}
			}
			
			// ON LANCE LE COMPTAGE DE POINT
			for (int i = 0; i < players.size(); i++) {
				players.get(i).getCounter().visit(this.board);
				players.get(i).roundFinished();
			}
			System.out.println("Manche terminée.");
			System.out.println("Tapez nextRound pour passer à la manche suivante.");
		
		
	}
	
	/**
	 * Détermine si le Round est terminé
	 * @return true si le Round est terminé, false sinon
	 */
	public boolean roundEnded() {
		boolean res = this.board.isFull();
		if(this.gamerule.getStartingVictoryCard()) {
			for(int i = 0 ; i < this.players.size() ; i++) {
				int compteur = 0;
				if(this.players.get(i).getHand().isEmpty()) {
					compteur++;
				}
				if(compteur == this.players.size()) {
					res = true;
				}
			}
		}else {
			for(int i = 0 ; i < this.players.size() ; i++) {
				int compteur = 0;
				if(this.players.get(i).getHand().size() == 1) {
					compteur++;
				}
				if(compteur == this.players.size()) {
					res = true;
				}
			}
		}
		return res;
	}
	
	/**
	 * Accesseur de l'attribut isRoundFinished
	 * @return true si le Round est terminé, false sinon
	 */
	public boolean isRoundFinished() {
		return this.roundFinished;
	}
	
	/**
	 * Permet au joueur qui doit jouer de déplacer une carte
	 * @param x1 abcisse de la position de départ
	 * @param y1 ordonnée de la position de départ
	 * @param x2 abcisse de la position d'arrivée
	 * @param y2 ordonnée de la position d'arrivée
	 */
	public void currentPlayerMoveCard(int x1, int y1, int x2, int y2) {
		boolean departCorrect = false;
		boolean arriveeCorrecte = false;
		if(this.currentPlayerMovedACard) {
			System.out.println("Vous avez déjà déplacé une carte durant ce tour.");
		}else {
			// Le coup joué est-il dans les coups permis ?
			
			for(int [] i : this.board.moveableSlots()) {
				if(i[0] == x1 && i[1] == y1) {
					departCorrect = true;
				}
			}
			
			for(int [] j : this.board.slotsToMoveCardsTo(this.gamerule, x1, y1)) {
				if(j[0] == x2 && j[1] == y2) {
					arriveeCorrecte = true;
				}
			}
			
			if(departCorrect && arriveeCorrecte) {
				this.board.moveCard(x1, y1, x2, y2);
				System.out.println(this.getCurrentPlayer().getName()+" a déplacé la carte de ("+x1+" "+y1+") à ("+x2+" "+y2+")");
				this.currentPlayerMovedACard = true;
			}else {
				System.out.println("Saisie incorrecte.");
			}
		}
	}
	
	
	
	/**
	 * Méthode permettant à un joueur de jouer une carte d'un joueur 
	 * @param indexCard index de la carte à jouer dans la main du joueur
	 * @param x abcisse de l'endroit où poser la carte
	 * @param y ordonnée de l'endroit où poser la carte
	 */
	public void currentPlayerPlayCard(int indexCard, int x, int y) {
		boolean saisieCorrecte = false;
		if(this.currentPlayerPlayedACard) {
			System.out.println("Vous avez déjà joué une carte durant ce tour.");
		}else {
			// Le coup joué est-il dans les coups permis ?
			for(int [] i : this.board.playableSlots(this.gamerule)) {
				if(i[0] == x && i[1] == y) {
					saisieCorrecte = true;
				}
			}
			if(saisieCorrecte) {
				this.board.setCard(x, y, this.getCurrentPlayer().playCard(indexCard));
				System.out.println(this.getCurrentPlayer().getName()+" a joué une carte en ("+x+" "+y+").");
				this.currentPlayerPlayedACard = true;
			}else {
				System.out.println("Saisie incorrecte.");
			}
		}
		this.hasChanged();
		this.notifyObservers();
	}
	
	/**
	 * Utilisée lorsque le joueur est un robot, permet de lancer la stratégie de jeu du robot et de faire jouer son tour
	 */
	public void robotPlays() {
		Turn pTurn = this.getCurrentPlayer().getStrategy().playCard(this.board, this.gamerule, this.getCurrentPlayer().getHand());
		
		this.currentPlayerPlayedACard = true;
		if (pTurn.getMovedFirst()) { // Détermine si le joueur a déplacé sa carte avant d'en jouer une
			this.currentPlayerMovedACard = true;
			this.board.moveCard(pTurn.getBeginningPoint()[0], pTurn.getBeginningPoint()[1],
					pTurn.getEndingPoint()[0], pTurn.getEndingPoint()[1]);
			this.board.setCard(pTurn.getPlayedCardPosition()[0], pTurn.getPlayedCardPosition()[1],
					this.getCurrentPlayer().playCard(pTurn.getPlayedCard()));
		} else {
			if (pTurn.getMovedCard()) { // Si le joueur a choisi de déplacer une carte durant son tour
				this.currentPlayerMovedACard = true;
				this.board.setCard(pTurn.getPlayedCardPosition()[0], pTurn.getPlayedCardPosition()[1],
						this.getCurrentPlayer().playCard(pTurn.getPlayedCard()));
				this.board.moveCard(pTurn.getBeginningPoint()[0], pTurn.getBeginningPoint()[1],
						pTurn.getEndingPoint()[0], pTurn.getEndingPoint()[1]);
			} else {
				this.board.setCard(pTurn.getPlayedCardPosition()[0], pTurn.getPlayedCardPosition()[1],
						this.getCurrentPlayer().playCard(pTurn.getPlayedCard()));
			}
		}
	}
	
	/**
	 * Accesseur de l'attribut board
	 * @return l'attribut Board
	 * 
	 * @see Board
	 */
	public Board getBoard() {
		return this.board;
	}


}
