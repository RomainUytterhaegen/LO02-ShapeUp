package model;

/**
 * Classe repr�sentant le mode jeu d'une partie de ShapeUp
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 */
public class Gamerule {

	private int startingCards;
	private boolean startingVictoryCard;
	private boolean freeMove;
	
	/**
	 * Constructeur de la classe Gamerule
	 * @param variante, choisis la variante de jeu choisie.
	 * 1. Jeu classique
	 * 2. 3 cartes dans la main, carte de victoire = celle restante � la fin du round dans la main du joueur
	 * 3. Contraintes de placement / d�placement de carte enlev�es
	 * On peut mettre les variantes comme ceci. A voir si on peut cr�er une personnalisation plus pouss�e si le temps nous le permet
	 */
	public Gamerule(int variante) {
		switch(variante) {
		case 1:
			this.startingCards=1;
			this.startingVictoryCard = true;
			this.freeMove = false;
			break;
		case 2:
			this.startingCards=3;
			this.startingVictoryCard = false;
			this.freeMove = false;
			break;
		case 3:
			this.startingCards=1;
			this.startingVictoryCard = true;
			this.freeMove = true;
			break;
		}
	}
	
	
	/**
	 * Accesseur de l'attribut startingCard, nombre de carte dans la main du joueur.
	 * @return Nombre de cartes dans la main d'un joueur
	 */
	public int getStartingCards() {
		return this.startingCards;
	}
	
	/**
	 * Accesseur de l'attribut startingVictoryCard, si la carte de victoire est d�sign�e au d�but ou non.
	 * @return true si la carte est pr�d�finie, false sinon.
	 */
	public boolean getStartingVictoryCard() {
		return this.startingVictoryCard;
	}
	
	/**
	 * Accesseur de l'attribut freeMove, si les contraintes de poses / d�placements de cartes sont appliqu�s.
	 * @return true si l'on a d�placement libre, false sinon.
	 */
	public boolean getFreeMove() {
		return this.freeMove;
	}
}
