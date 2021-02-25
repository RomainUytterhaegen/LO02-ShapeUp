package model;

/**
 * Classe représentant le mode jeu d'une partie de ShapeUp
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
	 * 2. 3 cartes dans la main, carte de victoire = celle restante à la fin du round dans la main du joueur
	 * 3. Contraintes de placement / déplacement de carte enlevées
	 * On peut mettre les variantes comme ceci. A voir si on peut créer une personnalisation plus poussée si le temps nous le permet
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
	 * Accesseur de l'attribut startingVictoryCard, si la carte de victoire est désignée au début ou non.
	 * @return true si la carte est prédéfinie, false sinon.
	 */
	public boolean getStartingVictoryCard() {
		return this.startingVictoryCard;
	}
	
	/**
	 * Accesseur de l'attribut freeMove, si les contraintes de poses / déplacements de cartes sont appliqués.
	 * @return true si l'on a déplacement libre, false sinon.
	 */
	public boolean getFreeMove() {
		return this.freeMove;
	}
}
