package model;

import java.util.*;

/**
 * 
 * Classe représentant le plateau de jeu.
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
	 * @param slots forme du plateau qui dépend du constructeur fils utilisé
	 * 
	 * @see Card
	 */
	public Board(Card[][] slots) {
		this.slots = slots;
	}

	/**
	 * Constructeur de Board permettant de copier un objet de type Board.
	 * 
	 * @param b l'instance de Board à copier
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
	 * Permet de notifier aux Observer de Board que l'objet a changé.
	 */
	public void notifyBoard() {
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de connaître la carte de coordonées x,y dans le Board.
	 * 
	 * @param x abscisse
	 * @param y ordonnée
	 * @return la carte de coordonnées x,y dans le Board
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
	 * Permet de poser une carte dans le Board aux coordonnées x,y.
	 * 
	 * @param x abscisse
	 * @param y ordonnée
	 * @param c carte à poser
	 * 
	 * @see Card
	 */
	public void setCard(int x, int y, Card c) {
		this.slots[x][y] = c;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de déplacer la carte située aux coordonées x1,y1 aux coordonées x2,y2.
	 * 
	 * @param x1 Première abscisse
	 * @param y1 Première ordonnée
	 * @param x2 Deuxième abscisse
	 * @param y2 Deuxième ordonnée
	 */
	public void moveCard(int x1, int y1, int x2, int y2) {
		this.slots[x2][y2] = this.slots[x1][y1];
		this.slots[x1][y1] = null;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Permet de vérifier si le Board est complet.
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
	 * Détermine les slots du Board où l'on peut poser une carte. Si le plateau est
	 * vide ou si on joue en règle freeMove, tout les slots vides sont jouables.
	 * 
	 * @param gm Instance de Gamerule, représentant le mode de jeu
	 * @return Tous les endroits sur le Board où l'on peut poser
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
						int[] coordonées = new int[2];
						coordonées[0] = i;
						coordonées[1] = j;
						res.add(coordonées);
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
							int[] coordonées = new int[2];
							coordonées[0] = i;
							coordonées[1] = j;
							res.add(coordonées);
						}
					}
				}
			}
			return res;
		}
	}

	/**
	 * Détermine tout les emplacements de cartes qui peuvent être déplacées.
	 * 
	 * @return Les slots des cartes pouvant être déplacées
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
						int[] coordonées = new int[2];
						coordonées[0] = i;
						coordonées[1] = j;
						res.add(coordonées);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Permet de savoir les emplacements où l'on peut déplacer une carte.
	 * @param gm Gamerule actuel
	 * @param x Position x de la carte qui va être déplacée
	 * @param y	Position y de la carte qui va être déplacée
	 * @return La liste des coordonnées où l'on peut déplacer la carte
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
						int[] coordonées = new int[2];
						coordonées[0] = i;
						coordonées[1] = j;
						res.add(coordonées);
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
							int[] coordonées = new int[2];
							coordonées[0] = i;
							coordonées[1] = j;
							res.add(coordonées);
						}
					}
				}
			}
		return res;
		}
	}

	/**
	 * Surcharge de l'opérateur toString
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
