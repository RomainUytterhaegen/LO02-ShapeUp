package model;

import java.util.*;

/**
 * Classe repr�sentant un joueur
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html">Observable</a>
 */
public class Player extends Observable{

	private String name;
	private boolean isHuman;
	private ArrayList<Card> hand;
	private int points;
	private PlayingStrategy strategy;
	private PointCounter counter;

	/**
	 * Constructeur de la classe Player
	 * 
	 * @param name    Nom du joueur
	 * @param isHuman Bool�en pour savoir si le joueur est un humain ou non
	 */
	public Player(String name, boolean isHuman) {
		this.name = name;
		this.isHuman = isHuman;
		if (!this.isHuman) {
			this.strategy = new RobotPlayerStrategy();
		} 
			
		
		this.points = 0;
		this.counter = new PointCounter();
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Notifie � l'Observer un changement de l'objet
	 */
	public void notifyPlayer() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Ajoute une carte dans la main du Player
	 * 
	 * @param c Carte � ajouter
	 * 
	 * @see Card
	 */
	public void drawCard(Card c) {
		this.hand.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * D�finit la carte de victoire du Player (et du PointCounter qui lui est
	 * associ�)
	 * 
	 * @param c Carte de victoire du Player
	 * 
	 * @see Card
	 */
	public void setVictoryCard(Card c) {
		this.counter.setVictoryCard(c);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Incr�mente le score du joueur par le score du PointCounter. Appel� par
	 * visit(player)
	 */
	public void accept() {
		this.points += this.counter.getScore();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Appel� � la fin d'un round. Compte les points du Round pass�, remet le score
	 * du counter � 0, Vide la main du joueur, enl�ve la carte de victoire.
	 */
	public void roundFinished() {
		this.counter.visit(this);
		this.counter.resetCounter();
		this.hand = new ArrayList<Card>();
		this.setVictoryCard(null);

	}

	/**
	 * Accesseur de la strat�gie du joueur
	 * @return La strat�gie du joueur
	 * 
	 * @see PlayingStrategy
	 */
	public PlayingStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Accesseur du score du joueur
	 * @return le score du joueur
	 */
	public int getScore() {
		return this.points;
	}

	/**
	 * Accesseur du compteur de point du joueur
	 * @return Le compteur de point du joueur
	 * 
	 * @see PointCounter
	 */
	public PointCounter getCounter() {
		return this.counter;
	}

	/**
	 * Joue la carte de l'indice donn�.
	 * 
	 * @param index de la carte � jouer
	 * @return La carte jou�e
	 * 
	 * @see Card
	 */
	public Card playCard(int index) {
		Card res = this.hand.remove(index);
		this.setChanged();
		this.notifyObservers();
		return res;
	}

	/**
	 * Permet d'avoir la main du joueur
	 * 
	 * @return Liste des cartes repr�sentant la main du joueur
	 * 
	 * @see Card
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}

	/**
	 * Permet d'afficher la main d'un joueur
	 * 
	 * @return un string contenant la main du joueur
	 */
	public String displayHand() {
		String res = "La main de " + this.name + " est  : [ ";
		for (int i = 0; i < this.hand.size(); i++) {
			res += i+". "+this.hand.get(i);
			res += " ";
		}
		res += " ]";
		return res;
	}
	
	/**
	 * Accesseur du nom du joueur
	 * @return Le nom du joueur
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Accesseur de l'attribut isHuman
	 * @return bool�en 
	 */
	public boolean isHuman() {
		return this.isHuman;
	}
	
	/**
	 * Accesseur de la carte de victoire du joueur
	 * @return La carte de victoire
	 * 
	 * @see Card
	 */
	public Card getVictoryCard() {
		return this.counter.getVictoryCard();
	}

}
