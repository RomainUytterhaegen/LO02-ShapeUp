package model;

/**
 * Classe représentant une carte de jeu ShapeUp
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 */
public class Card {
	
	private Shape shape;
	private Color color;
	private boolean full;
	
	/**
	 * Constructeur de la classe Card.
	 * @param shape Forme de la carte
	 * @param color Couleur de la carte
	 * @param full true si la forme est pleine, false sinon
	 * 
	 * @see Color
	 * @see Shape
	 */
	public Card(Shape shape,Color color,boolean full) {
		this.shape = shape;
		this.color = color;
		this.full = full;
	}
	
	/**
	 * Accesseur de l'attribut shape
	 * @return La forme de la carte
	 * 
	 * @see Shape
	 */
	public Shape getShape() {
		return this.shape;
	}
	
	/**
	 * Accesseur de l'attribut color
	 * @return Couleur de la carte
	 * 
	 * @see Color
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Accesseur de l'attribut full
	 * @return La carte est pleine ou non
	 */
	public boolean isFull() {
		return this.full;
	}
	
	/**
	 * Surcharge de l'opérateur toString
	 */
	public String toString() {
		String res = "";
		if(this.getShape()==Shape.FORBIDDEN) {
			return "[-------NO SLOT-------]";
		}
		res +="[";
		Color c = this.getColor();
		switch(c) {
		case RED:
			res+= " RED ";
			break;
		case GREEN:
			res+="GREEN";
			break;
		case BLUE:
			res+="BLUE ";
			break;
		}
		res+="/";
		Shape s = this.getShape();
		switch(s) {
		case CIRCLE:
			res+= " CIRCLE ";
			break;
		case SQUARE:
			res+=" SQUARE ";
			break;
		case TRIANGLE:
			res+="TRIANGLE";
			break;
		case FORBIDDEN:
			break;
		}
		res+="/";
		if(this.isFull()) {
			res+="Filled";
		}
		else {
			res+="Hollow";
		}
		res+="]";
		return res;
	}
	
	/**
	 * Détermine si deux cartes sont égales
	 * 
	 * @param c Carte à comparer avec la carte de référence
	 * @return déterminant si les cartes sont identiques
	 */
	public boolean equals(Card c) {
		boolean res = this.getColor() == c.getColor() && this.getShape() == c.getShape() && this.isFull() == c.isFull();
		return res;
	}
}
