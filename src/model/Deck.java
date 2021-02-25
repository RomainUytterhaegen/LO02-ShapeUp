package model;
import java.util.*;

/**
 * Classe repr�sentant un paquet de cartes du jeu ShapeUp
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 */
public class Deck {

	private ArrayList<Card> cards;
	
	/**
	 * Constructeur de la classe Deck
	 */
	public Deck() {
		this.cards =  new ArrayList<>();
		Color[] colors = {Color.BLUE, Color.GREEN, Color.RED};
		Shape[] shapes = {Shape.CIRCLE,Shape.SQUARE, Shape.TRIANGLE};
		
		for(int i = 0 ; i < shapes.length ; i ++) {
			for(int j = 0 ; j < colors.length ; j++) {
				this.cards.add(new Card(shapes[i],colors[j],true));
				this.cards.add(new Card(shapes[i],colors[j],false));
			}
		}
	}
	
	/**
	 * Simule la pioche d'un deck m�lang�. 
	 * @return La carte pioch�e
	 * 
	 * @see Card
	 */
	public Card drawTopCard() {
		Random random = new Random();
	    return this.cards.remove(random.nextInt(cards.size() - 0) + 0);
	}
	
	/**
	 * D�termine si le paquet est vide.
	 * @return true si le paquet est vide, false sinon
	 */
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	/**
	 * D�termine le nombre de cartes restantes dans le paquet.
	 * @return taille du paquet
	 */
	public int getSize() {
		return this.cards.size();
	}
	
	
}