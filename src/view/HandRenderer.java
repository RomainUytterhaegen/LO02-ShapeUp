package view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Classe permettant d'afficher les cartes dans la main
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableCellRenderer.html">MouseAdapter</a>
 */

public class HandRenderer implements TableCellRenderer {

	/**
	 * Méthode de l'interface TableCellRenderer réimplentée pour choisir les
	 * dimensions des cases du JTable et afficher l'icone d'un JLabel
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		table.setRowHeight(153);
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			table.getColumn(String.valueOf(i)).setMaxWidth(108);
		}

		return (Component) value;
	}

}
