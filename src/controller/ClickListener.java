package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import view.*;

/**
 * Classe permettant d'écouter les évènements liés au clic de la souris sur un
 * élément
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseAdapter.html">MouseAdapter</a>
 */
public class ClickListener extends MouseAdapter {

	private JTable table;
	private GameWindow gamewindow;

	/**
	 * Constructeur de la classe ClickListener
	 * 
	 * @param gw Instance de GameWindow, interface du jeu
	 * @param t  Instance du JTable auquel le ClickListener est assiocié
	 */
	public ClickListener(GameWindow gw, JTable t) {
		super();
		gamewindow = gw;
		table = t;
	}

	/**
	 * Envoie les coordonées du curseur au GameController lors d'un clic 
	 */
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		int row = table.rowAtPoint(p);
		int col = table.columnAtPoint(p);
		gamewindow.getGameController().hasClicked(row, col, table.getRowCount() > 1);
	}

}
