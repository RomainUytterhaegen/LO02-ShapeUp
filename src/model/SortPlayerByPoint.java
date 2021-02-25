package model;

import java.util.Comparator;

/**
 * Classe utilitaire permettant de trier une liste de Player selon leur nombre de points
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class SortPlayerByPoint  implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		
		return p1.getScore() - p2.getScore();
	}

}
