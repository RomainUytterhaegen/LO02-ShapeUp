package model;

/**
 * Classe représentant un plateau circulaire
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see Board
 */
public class Board3 extends Board {
	
	/**
	 * Constructeur de la classe Board3 (Cercle)
	 */
	public Board3() {
		super(new Card[5][5]);
		this.setCard(0, 0, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(4, 0, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 4, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(4, 4, new Card(Shape.FORBIDDEN, Color.RED, true));
	}

}
