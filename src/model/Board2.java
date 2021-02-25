package model;


/**
 * Classe représentant un plateau de jeu triangulaire base de 7 cartes, hauteur de 4 cartes) 
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see Board
 */
public class Board2 extends Board {

	/**
	 * Constructeur de la classe Board2 (Triangle avec base de 7 cartes et hauteur de 4 cartes)
	 */
	public Board2() {
		super(new Card[4][7]);
		this.setCard(0, 0, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 1, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 2, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 4, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 5, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(0, 6, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(1, 0, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(1, 1, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(1, 5, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(1, 6, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(2, 0, new Card(Shape.FORBIDDEN, Color.RED, true));
		this.setCard(2, 6, new Card(Shape.FORBIDDEN, Color.RED, true));
	}

}
