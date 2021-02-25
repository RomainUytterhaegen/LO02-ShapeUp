package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interface des diff�rentes strat�gies de jeu d'un joueur robot
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public interface PlayingStrategy {
	
	/**
	 * Permet de d�finir le tour � jouer pour un joueur robot
	 * @param b le plateau de jeu actuel
	 * @param gamerule le mode de jeu
	 * @param playerHand la main du joueur robot
	 * @return le tour du joueur robot
	 * 
	 * @see Turn
	 * @see Board
	 * @see Gamerule
	 * @see Card
	 */
	public Turn playCard(Board b,Gamerule gamerule, ArrayList<Card> playerHand);
	
}
