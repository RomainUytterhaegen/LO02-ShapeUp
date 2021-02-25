package view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Classe permettant d'afficher les cartes du plateau de jeu
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableCellRenderer.html">MouseAdapter</a>
 */

public class LabelRenderer implements TableCellRenderer {
	public int[] dimensions;

	/**
	 * Constructeur du LabelRenderer, prend les dimension en attribut pour choisir
	 * la dimension des cases du JTable
	 * 
	 * @param dim dimension de l'attribut slots de la classe Board
	 */
	public LabelRenderer(int[] dim) {
		super();
		dimensions = dim;
	}

	/**
	 * Méthode de l'interface TableCellRenderer réimplentée pour choisir les
	 * dimensions des cases du JTable et afficher l'icone d'un JLabel
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		int rowCount = dimensions[0];
		int colCount = dimensions[1];
		switch (rowCount) {
		case 3:
			table.setRowHeight(239);
			for (int i = 0; i < colCount; i++) {
				table.getColumn(String.valueOf(i)).setMaxWidth(168);
			}
			break;
		case 4:
			table.setRowHeight(179);
			for (int i = 0; i < colCount; i++) {
				table.getColumn(String.valueOf(i)).setMaxWidth(126);
			}
			break;
		case 5:
			table.setRowHeight(143);
			for (int i = 0; i < colCount; i++) {
				table.getColumn(String.valueOf(i)).setMaxWidth(98);
			}
			break;
		}

		return (Component) value;
	}

}
