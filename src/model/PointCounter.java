package model;

/**
 * Classe repr�sentant le compteur de point d'une partie de ShapeUp
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 * @see <a href="https://docs.oracle.com/middleware/1212/jdev/ESDKJ/oracle/javatools/patterns/Visitor.html">Visitor</a>
 */
public class PointCounter implements Visitor {

	int points;
	Card victoryCard;
	
	/**
	 * Constructeur de PointCounter. Par d�faut le score est nul et la carte de victoire est null (slot vide)
	 */
	public PointCounter() {
		this.points = 0;
		this.victoryCard = null;
	}
	
	/**
	 * D�clenche la m�thode accept du joueur pour lui incr�menter son score.
	 * 
	 * @param p joueur � visiter
	 * 
	 * @see Player
	 */
	public void visit(Player p) {
		p.accept();
	}

	/**
	 * Affecte � l'attribut points le score que marque le joueur ayant pour carte de victoire this.victoryCard en fonction du Board b
	 * 
	 *@param b Board � visiter
	 *
	 *@see Board
	 */
	public void visit(Board b) {
		int sTotal = 0;
		for (int i = 0; i < b.getDimensions()[0]; i++) {
			int sShape = 0;
			int sColor = 0;
			int sFill = 0;
			for (int j = 0; j < b.getDimensions()[1]; j++) {
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() == this.victoryCard.getShape()) {
					sShape += 1;
				} else {
					if (sShape >= 2) {
						sTotal += sShape - 1;
					}
					sShape = 0;
				}
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() != Shape.FORBIDDEN
						&& b.getCard(i, j).getColor() == this.victoryCard.getColor()) {
					sColor += 1;
				} else {
					if (sColor >= 3) {
						sTotal += sColor + 1;
					}
					sColor = 0;
				}
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() != Shape.FORBIDDEN
						&& b.getCard(i, j).isFull() == this.victoryCard.isFull()) {
					sFill += 1;
				} else {
					if (sFill >= 3) {
						sTotal += sFill;
					}
					sFill = 0;
				}
			}
			if (sShape >= 2) {
				sTotal += sShape - 1;
			}
			if (sColor >= 3) {
				sTotal += sColor + 1;
			}
			if (sFill >= 3) {
				sTotal += sFill;
			}
		}
		for (int j = 0; j < b.getDimensions()[1]; j++) {
			int sShape = 0;
			int sColor = 0;
			int sFill = 0;
			for (int i = 0; i < b.getDimensions()[0]; i++) {
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() == this.victoryCard.getShape()) {
					sShape += 1;
				} else {
					if (sShape >= 2) {
						sTotal += sShape - 1;
					}
					sShape = 0;
				}
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() != Shape.FORBIDDEN
						&& b.getCard(i, j).getColor() == this.victoryCard.getColor()) {
					sColor += 1;
				} else {
					if (sColor >= 3) {
						sTotal += sColor + 1;
					}
					sColor = 0;
				}
				if (b.getCard(i, j) != null && b.getCard(i, j).getShape() != Shape.FORBIDDEN
						&& b.getCard(i, j).isFull() == this.victoryCard.isFull()) {
					sFill += 1;
				} else {
					if (sFill >= 3) {
						sTotal += sFill;
					}
					sFill = 0;
				}
			}
			if (sShape >= 2) {
				sTotal += sShape - 1;
			}
			if (sColor >= 3) {
				sTotal += sColor + 1;
			}
			if (sFill >= 3) {
				sTotal += sFill;
			}
		}
		this.points = sTotal;
	}
	
	/**
	 * Accesseur aux points du compteur
	 * 
	 * @return points du compteur
	 */
	public int getScore() {
		return this.points;
	}
	
	/**
	 * d�finit la carte de victoire du joueur associ� � ce compteur
	 * 
	 * @param c carte � placer en carte de victoire
	 * 
	 * @see Card
	 */
	public void setVictoryCard(Card c) {
		this.victoryCard = c;
	}
	
	/**
	 * remet le score du compteur � 0
	 */
	public void resetCounter() {
		this.points = 0;
	}
	
	/**
	 * Accesseur de victoryCard
	 * 
	 * @return la victory card du joueur � qui ce compteur est associ�.
	 * 
	 * @see Card
	 */
	public Card getVictoryCard() {
		return this.victoryCard;
	}
	

}
