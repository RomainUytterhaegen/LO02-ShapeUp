package model;

/**
 * Classe repr�sentant l'ensemble des actions d'un joueur lors d'un tour 
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class Turn {

	private int playedCard; // Indice de la carte jou�e dans la main du Player
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
	 * D�finit la carte jou�e pendant ce tour
	 * @param c Indice de la carte dans la main jou�e
	 */
	public void setPlayedCard(int c) {
		this.playedCard = c;
		if(movedCard) {
			this.movedFirst = true;
		}
	}
	
	/**
	 * Retourne la carte jou�e
	 * @return la carte jou�e
	 */
	public int getPlayedCard() {
		return this.playedCard;
	}
	
	/**
	 * D�finit la position de la carte jou�e
	 * @param x abcisse
	 * @param y ordonn�e
	 */
	public void setPlayedCardPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Donne les position o� la carte a �t� jou�e
	 * @return positions de la carte jou�e
	 */
	public int[] getPlayedCardPosition() {
		int[] res = new int[2];
		res[0] = this.x;
		res[1] =this.y;
		return res;
	}
	
	/**
	 * D�finit qu'une carte a �t� d�plac�e durant ce tour
	 */
	public void hasMovedCard() {
		this.movedCard = true;
	}
	
	/**
	 * Accesseur de movedCard
	 * @return bool�en
	 */
	public boolean getMovedCard() {
		return this.movedCard;
	}
	
	/**
	 * Permet de savoir si pendant ce tour la carte a �t� jou�e avant d'�tre d�plac�e, et inversement
	 * @return bool�en
	 */
	public boolean getMovedFirst() {
		return this.movedFirst;
	}
	
	/**
	 * D�finit la position de la carte qui est d�plac�e
	 * @param x
	 * @param y
	 */
	public void setBeginningPoint(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}
	
	/**
	 * Accesseur de la position de la carte qui va �tre d�plac�e
	 * @return coordonn�es
	 */
	public int[] getBeginningPoint() {
		int[] res = new int[2];
		res[0] = this.x1;
		res[1] =this.y1;
		return res;
	}
	
	/**
	 * D�finit la position d'arriv�e de la carte qui va �tre d�plac�e
	 * @param x
	 * @param y
	 */
	public void setEndingPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	
	/**
	 * Accesseur de la position d'arriv�e de la carte
	 * @return coordonn�es
	 */
	public int[] getEndingPoint() {
		int[] res = new int[2];
		res[0] = this.x2;
		res[1] =this.y2;
		return res;
	}
}
