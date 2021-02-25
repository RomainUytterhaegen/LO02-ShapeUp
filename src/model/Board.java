package model;

import java.util.*;

/**
 * 
 * Classe repr�sentant le plateau de jeu.
 *
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * 
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html">Observable</a>
 */
public class Board extends Observable{

	private Card[][] slots;

	/**
	 * Constructeur de Board.
	 * 
	 * @param slots forme du plateau qui d�pend du constructeur fils utilis�
	 * 
	 * @see Card
	 */
	public Board(Card[][] slots) {
		this.slots = slots;
	}

	/**
	 * Constructeur de Board permettant de copier un objet de type Board.
	 * 
	 * @param b l'instance de Board � copier
	 */
	public Board(Board b) {
		this.slots = new Card[b.slots.length][b.slots[0].length];
		for(int x = 0 ; x < b.slots.length ; x++) {
			for(int y = 0 ; y < b.slots[0].length; y++) {
				this.slots[x][y] = b.slots[x][y];
			}
		}
		
	}
	
	/**
	 * Permet de notifier aux Observer de Board que l'objet a chang�.
	 */
	public void notifyBoard() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de conna�tre la carte de coordon�es x,y dans le Board.
	 * 
	 * @param x abscisse
	 * @param y ordonn�e
	 * @return la carte de coordonn�es x,y dans le Board
	 * 
	 * @see Card
	 */
	public Card getCard(int x, int y) {
		return this.slots[x][y];
	}
	
	/**
	 * Accesseur de l'attribut slots.
	 * @return les slots
	 * 
	 * @see Card
	 */
	public Card[][] getSlots(){
		return this.slots;
	}



	/**
	 * Retourne les dimensions du plateau.
	 * @return tableau des dimensions
	 */
	public int[] getDimensions() {
		int[] res = new int[2];
		res[0] = this.slots.length;
		res[1] = this.slots[0].length;
		return res;
	}

	/**
	 * Permet de poser une carte dans le Board aux coordonn�es x,y.
	 * 
	 * @param x abscisse
	 * @param y ordonn�e
	 * @param c carte � poser
	 * 
	 * @see Card
	 */
	public void setCard(int x, int y, Card c) {
		this.slots[x][y] = c;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de d�placer la carte situ�e aux coordon�es x1,y1 aux coordon�es x2,y2.
	 * 
	 * @param x1 Premi�re abscisse
	 * @param y1 Premi�re ordonn�e
	 * @param x2 Deuxi�me abscisse
	 * @param y2 Deuxi�me ordonn�e
	 */
	public void moveCard(int x1, int y1, int x2, int y2) {
		this.slots[x2][y2] = this.slots[x1][y1];
		this.slots[x1][y1] = null;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de v�rifier si le Board est complet.
	 * 
	 * @return true si le tableau est complet, false sinon
	 */
	public boolean isFull() {
		int compteur = 0;
		for (int i = 0; i < this.slots.length; i++) {
			for (int j = 0; j < this.slots[0].length; j++) {
				if (this.slots[i][j] != null && this.slots[i][j].getShape() != Shape.FORBIDDEN) {
					compteur += 1;
				}
			}
		}
		return compteur == 15;
	}

	/**
	 * D�termine les slots du Board o� l'on peut poser une carte. Si le plateau est
	 * vide ou si on joue en r�gle freeMove, tout les slots vides sont jouables.
	 * 
	 * @param gm Instance de Gamerule, repr�sentant le mode de jeu
	 * @return Tous les endroits sur le Board o� l'on peut poser
	 *         une carte
	 *         
	 * @see Gamerule
	 */
	public ArrayList<int[]> playableSlots(Gamerule gm) {
		ArrayList<int[]> res = new ArrayList<>();
		boolean isEmpty = true;
		for (int i = 0; i < this.slots.length; i++) {
			for (int j = 0; j < this.slots[0].length; j++) {
				if (this.getCard(i, j) != null && this.getCard(i, j).getShape() != Shape.FORBIDDEN) {
					isEmpty = false;
				}
			}
		}
		if (gm.getFreeMove() || isEmpty) {
			for (int i = 0; i < this.slots.length; i++) {
				for (int j = 0; j < this.slots[0].length; j++) {
					if (this.getCard(i, j) == null) {
						int[] coordon�es = new int[2];
						coordon�es[0] = i;
						coordon�es[1] = j;
						res.add(coordon�es);
					}
				}
			}
			return res;
		} else {
			for (int i = 0; i < this.slots.length; i++) {
				for (int j = 0; j < this.slots[0].length; j++) {
					if (this.getCard(i, j) == null) {
						int count = 0;
						if (i > 0) {
							if (this.getCard(i - 1, j) != null
									&& this.getCard(i - 1, j).getShape() != Shape.FORBIDDEN) {
								count += 1;
							}
						}
						if (i < this.slots.length - 1) {
							if (this.getCard(i + 1, j) != null
									&& this.getCard(i + 1, j).getShape() != Shape.FORBIDDEN) {
								count += 1;
							}
						}
						if (j > 0) {
							if (this.getCard(i, j - 1) != null
									&& this.getCard(i, j - 1).getShape() != Shape.FORBIDDEN) {
								count += 1;
							}
						}
						if (j < this.slots[0].length - 1) {
							if (this.getCard(i, j + 1) != null
									&& this.getCard(i, j + 1).getShape() != Shape.FORBIDDEN) {
								count += 1;
							}
						}
						if (count > 0) {
							int[] coordon�es = new int[2];
							coordon�es[0] = i;
							coordon�es[1] = j;
							res.add(coordon�es);
						}
					}
				}
			}
			return res;
		}
	}

	/**
	 * D�termine tout les emplacements de cartes qui peuvent �tre d�plac�es.
	 * 
	 * @return Les slots des cartes pouvant �tre d�plac�es
	 */
	public ArrayList<int[]> moveableSlots() {
		ArrayList<int[]> res = new ArrayList<>();
		for (int i = 0; i < this.slots.length; i++) {
			for (int j = 0; j < this.slots[0].length; j++) {
				int count = 0;
				if (this.getCard(i, j) != null && this.getCard(i, j).getShape() != Shape.FORBIDDEN) {
					if (i > 0) {
						if (this.getCard(i - 1, j) == null) {
							count += 1;
						}
					}
					if (i < this.slots.length - 1) {
						if (this.getCard(i + 1, j) == null) {
							count += 1;
						}

					}
					if (j > 0) {
						if (this.getCard(i, j - 1) == null) {
							count += 1;
						}
					}
					if (j < this.slots[0].length - 1) {
						if (this.getCard(i, j + 1) == null) {
							count += 1;
						}
					}
					if (count > 0) {
						int[] coordon�es = new int[2];
						coordon�es[0] = i;
						coordon�es[1] = j;
						res.add(coordon�es);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Permet de savoir les emplacements o� l'on peut d�placer une carte.
	 * @param gm Gamerule actuel
	 * @param x Position x de la carte qui va �tre d�plac�e
	 * @param y	Position y de la carte qui va �tre d�plac�e
	 * @return La liste des coordonn�es o� l'on peut d�placer la carte
	 * 
	 * @see Gamerule
	 */
	public ArrayList<int[]> slotsToMoveCardsTo(Gamerule gm, int x, int y) {
		ArrayList<int[]> res = new ArrayList<>();
		boolean isEmpty = true;
		for (int i = 0; i < this.slots.length; i++) {
			for (int j = 0; j < this.slots[0].length; j++) {
				if (this.getCard(i, j) != null && this.getCard(i, j).getShape() != Shape.FORBIDDEN) {
					isEmpty = false;
				}
			}
		}
		if (gm.getFreeMove() || isEmpty) {
			for (int i = 0; i < this.slots.length; i++) {
				for (int j = 0; j < this.slots[0].length; j++) {
					if (this.getCard(i, j) == null) {
						int[] coordon�es = new int[2];
						coordon�es[0] = i;
						coordon�es[1] = j;
						res.add(coordon�es);
					}
				}
			}
			return res;
		} else {
			for (int i = 0; i < this.slots.length; i++) {
				for (int j = 0; j < this.slots[0].length; j++) {
					if (this.getCard(i, j) == null) {
						int count = 0;
						if (i > 0) {
							if (this.getCard(i - 1, j) != null
									&& this.getCard(i - 1, j).getShape() != Shape.FORBIDDEN && (x!=i-1 || y!=j)) {
								count += 1;
							}
						}
						if (i < this.slots.length - 1) {
							if (this.getCard(i + 1, j) != null
									&& this.getCard(i + 1, j).getShape() != Shape.FORBIDDEN && (x!=i+1 || y!=j)) {
								count += 1;
							}
						}
						if (j > 0) {
							if (this.getCard(i, j - 1) != null
									&& this.getCard(i, j - 1).getShape() != Shape.FORBIDDEN && (x!=i || y!=j-1)) {
								count += 1;
							}
						}
						if (j < this.slots[0].length - 1) {
							if (this.getCard(i, j + 1) != null
									&& this.getCard(i, j + 1).getShape() != Shape.FORBIDDEN && (x!=i || y!=j+1)) {
								count += 1;
							}
						}
						if (count > 0) {
							int[] coordon�es = new int[2];
							coordon�es[0] = i;
							coordon�es[1] = j;
							res.add(coordon�es);
						}
					}
				}
			}
		return res;
		}
	}

	/**
	 * Surcharge de l'op�rateur toString
	 */
	public String toString() {
		String str = "   ";
		String newLine = System.getProperty("line.separator");
		for (int i = 0; i < this.slots[0].length; i++) {
			str+="            "+i+"          ";
		}
		str+=newLine;
		for (int i = 0; i < this.slots.length; i++) {
			str = str + i+": [";
			for (int j = 0; j < this.slots[0].length; j++) {
				if (this.slots[i][j] == null) {
					str += "[-----EMPTY  SLOT-----]";
				} else {
					str = str + this.slots[i][j] + "";
				}
			}
			str = str + "]" + newLine;
		}
		return str;
	}
	

}
