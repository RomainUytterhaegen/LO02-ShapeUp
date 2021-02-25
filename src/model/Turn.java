package model;

/**
 * Classe représentant l'ensemble des actions d'un joueur lors d'un tour 
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class Turn {

	private int playedCard; // Indice de la carte jouée dans la main du Player
	private int x;
	private int y;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private boolean movedCard;
	private boolean movedFirst;
	
	/**
	 * Constructeur de Turn
	 */
	public Turn() {
		this.movedCard = false;
		this.movedFirst = false;
	}
	
	/**
	 * Définit la carte jouée pendant ce tour
	 * @param c Indice de la carte dans la main jouée
	 */
	public void setPlayedCard(int c) {
		this.playedCard = c;
		if(movedCard) {
			this.movedFirst = true;
		}
	}
	
	/**
	 * Retourne la carte jouée
	 * @return la carte jouée
	 */
	public int getPlayedCard() {
		return this.playedCard;
	}
	
	/**
	 * Définit la position de la carte jouée
	 * @param x abcisse
	 * @param y ordonnée
	 */
	public void setPlayedCardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Donne les position où la carte a été jouée
	 * @return positions de la carte jouée
	 */
	public int[] getPlayedCardPosition() {
		int[] res = new int[2];
		res[0] = this.x;
		res[1] =this.y;
		return res;
	}
	
	/**
	 * Définit qu'une carte a été déplacée durant ce tour
	 */
	public void hasMovedCard() {
		this.movedCard = true;
	}
	
	/**
	 * Accesseur de movedCard
	 * @return booléen
	 */
	public boolean getMovedCard() {
		return this.movedCard;
	}
	
	/**
	 * Permet de savoir si pendant ce tour la carte a été jouée avant d'être déplacée, et inversement
	 * @return booléen
	 */
	public boolean getMovedFirst() {
		return this.movedFirst;
	}
	
	/**
	 * Définit la position de la carte qui est déplacée
	 * @param x
	 * @param y
	 */
	public void setBeginningPoint(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}
	
	/**
	 * Accesseur de la position de la carte qui va être déplacée
	 * @return coordonnées
	 */
	public int[] getBeginningPoint() {
		int[] res = new int[2];
		res[0] = this.x1;
		res[1] =this.y1;
		return res;
	}
	
	/**
	 * Définit la position d'arrivée de la carte qui va être déplacée
	 * @param x
	 * @param y
	 */
	public void setEndingPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	
	/**
	 * Accesseur de la position d'arrivée de la carte
	 * @return coordonnées
	 */
	public int[] getEndingPoint() {
		int[] res = new int[2];
		res[0] = this.x2;
		res[1] =this.y2;
		return res;
	}
}
