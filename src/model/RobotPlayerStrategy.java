package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe représentant la stratégie de jeu d'un joueur Robot
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 * @see PlayingStrategy
 */
public class RobotPlayerStrategy implements PlayingStrategy {

	/**
	 * Constructeur de RobotPlayerStrategy, vide car inutilisé.
	 */
	public RobotPlayerStrategy() {

	}

	/**
	 * Permet de joueur un tour d'un joueur Robot
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
	public Turn playCard(Board b, Gamerule gamerule, ArrayList<Card> playerHand) {
		Board ref = new Board(b);
		Turn res = new Turn();
		Random random = new Random();
		int truc = random.nextInt(playerHand.size());
		res.setPlayedCard(random.nextInt(playerHand.size()));
		int[] playedCardPosition = ref.playableSlots(gamerule).get(random.nextInt(ref.playableSlots(gamerule).size()));
		ref.setCard(playedCardPosition[0], playedCardPosition[1], new Card(Shape.CIRCLE, Color.BLUE, false));
		res.setPlayedCardPosition(playedCardPosition[0], playedCardPosition[1]);
		int nbDeCartesPosées = 0;
		boolean moveCard = false;
		for (int i = 0; i < ref.getDimensions()[0]; i++) {
			for (int j = 0; j < ref.getDimensions()[1]; j++) {
				if (ref.getCard(i, j) != null && ref.getCard(i, j).getShape() != Shape.FORBIDDEN) {
					nbDeCartesPosées++;
				}
			}
		}
		if (nbDeCartesPosées > 1) {
			if( (playerHand.size()>2 && gamerule.getStartingCards()>1) || gamerule.getStartingCards()==1){
				moveCard = random.nextInt(10) < 5;
			}else {
				moveCard = false;
			}
		}
		if (moveCard && (!ref.moveableSlots().isEmpty())) {
			res.hasMovedCard();
			int[] beginningPoint;
			beginningPoint = ref.moveableSlots().get(random.nextInt(ref.moveableSlots().size()));
			res.setBeginningPoint(beginningPoint[0], beginningPoint[1]);
				int[] endingPoint = ref.slotsToMoveCardsTo(gamerule, beginningPoint[0], beginningPoint[1]).remove(
						random.nextInt(ref.slotsToMoveCardsTo(gamerule, beginningPoint[0], beginningPoint[1]).size()));
				res.setEndingPoint(endingPoint[0], endingPoint[1]);
			
			
		}
		System.out.println("Robot a joué carte  " + res.getPlayedCard() + " à la position "
				+ res.getPlayedCardPosition()[0] + " " + res.getPlayedCardPosition()[1]);
		if (res.getMovedCard()) {
			System.out.println("Robot a bougé une carte");
		}
		return res;
	}

}
