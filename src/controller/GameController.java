package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.*;
import view.*;

/**
 * Classe représentant le contrôleur entre l'interface graphique de
 * l'application et le modèle.
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 */
public class GameController {

	private Game game;
	private JButton endTurn;
	private JButton endRound;
	private int[] lastClick;
	private boolean lastClickTable;
	private boolean hasClicked;

	/**
	 * Constructeur de la classe GameController.
	 * 
	 * @param g        Instance du jeu en cours
	 * @param endTurn  Bouton marquant la fin du tour
	 * @param endRound Bouton marquant la fin de la manche
	 */
	public GameController(Game g, JButton endTurn, JButton endRound) {
		this.game = g;
		this.endTurn = endTurn;
		this.endRound = endRound;
		this.lastClick = new int[2];
		this.hasClicked = false;

		this.endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getCurrentRound().endTurn();
				game.getCurrentRound().getCurrentPlayer().notifyPlayer();
			}
		});

		this.endRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.nextRound();
				game.getCurrentRound().getBoard().notifyBoard();
			}
		});
	}

	/**
	 * Analyse la position du curseur lors d'un clic, si un clic précédent est deja
	 * enregistré, joue ou pose en carte
	 * 
	 * @param row   ligne dans laquelle le curseur se trouve lors du clic
	 * @param col   colonne dans laquelle le curseur se trouve lors du clic
	 * @param table table dans laquelle le curseur se trouve lors du clic (true pour
	 *              le plateau, false pour la main)
	 */
	public void hasClicked(int row, int col, boolean table) {
		if (!hasClicked) {
			if (table) {
				if (game.getCurrentRound().getBoard().getCard(row, col) == null
						|| game.getCurrentRound().getBoard().getCard(row, col).getShape() != Shape.FORBIDDEN) {
					hasClicked = true;
					lastClick[0] = row;
					lastClick[1] = col;
					lastClickTable = table;
				}
			} else {
				if (col >= 0) {
					hasClicked = true;
					lastClick[0] = row;
					lastClick[1] = col;
					lastClickTable = table;
				}
			}
		} else {
			if (table) {
				if (!lastClickTable) {
					game.getCurrentRound().currentPlayerPlayCard(lastClick[1], row, col);
					hasClicked = false;
				} else {
					game.getCurrentRound().currentPlayerMoveCard(lastClick[0], lastClick[1], row, col);
					hasClicked = false;
				}
			}
		}
	}
}
