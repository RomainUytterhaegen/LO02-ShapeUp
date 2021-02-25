package model;

/**
 * Interface nous permettant d'impl�menter le compteur de points
 *
 */
public interface Visitor {

	public void visit(Player p);
	public void visit(Board b);
	public int getScore();
}
