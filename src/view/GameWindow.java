package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import controller.*;
import model.*;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

/**
 * Classe représentant la fenêtre de jeu
 * 
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */

public class GameWindow extends JFrame implements Observer {

	private JPanel contentPane;
	private JPanel boardPanel;
	private JScrollPane boardSP;
	private JTable boardTable;
	private JPanel playersPanel;
	private JPanel p1Panel;
	private JLabel p1ScoreLabel;
	private JLabel p1NameLabel;
	private JPanel p2Panel;
	private JLabel p2ScoreLabel;
	private JLabel p2NameLabel;
	private JPanel p3Panel;
	private JLabel p3ScoreLabel;
	private JLabel p3NameLabel;
	private JPanel handPanel;
	private JScrollPane handSP;
	private JTable handTable;
	private JLabel infoHandLabel;
	private JLabel infoVictoryCardLabel;
	private JLabel victoryCardLabel;
	private JButton endTurn;
	private JButton endRound;
	private GameController gameController;
	private Game game;
	private int xScale;
	private int yScale;
	public final JLabel f;
	public final JLabel e;
	public final JLabel cbt;
	public final JLabel cbf;
	public final JLabel sbt;
	public final JLabel sbf;
	public final JLabel tbt;
	public final JLabel tbf;
	public final JLabel cgt;
	public final JLabel cgf;
	public final JLabel sgt;
	public final JLabel sgf;
	public final JLabel tgt;
	public final JLabel tgf;
	public final JLabel crt;
	public final JLabel crf;
	public final JLabel srt;
	public final JLabel srf;
	public final JLabel trt;
	public final JLabel trf;
	public final JLabel cbth;
	public final JLabel cbfh;
	public final JLabel sbth;
	public final JLabel sbfh;
	public final JLabel tbth;
	public final JLabel tbfh;
	public final JLabel cgth;
	public final JLabel cgfh;
	public final JLabel sgth;
	public final JLabel sgfh;
	public final JLabel tgth;
	public final JLabel tgfh;
	public final JLabel crth;
	public final JLabel crfh;
	public final JLabel srth;
	public final JLabel srfh;
	public final JLabel trth;
	public final JLabel trfh;

	

	/**
	 * Converti l'attribut slots de la classe board en tableau utilisable pour créer
	 * un JTable de JLabel
	 * 
	 * @return le tableau de tableau de JLabel contenant les cartes à afficher
	 */
	public Object[][] convSlots() {
		Object[][] res = new Object[this.game.getCurrentRound().getBoard().getDimensions()[0]][this.game
				.getCurrentRound().getBoard().getDimensions()[1]];
		for (int i = 0; i < this.game.getCurrentRound().getBoard().getDimensions()[0]; i++) {
			for (int j = 0; j < this.game.getCurrentRound().getBoard().getDimensions()[1]; j++) {
				if (this.game.getCurrentRound().getBoard().getCard(i, j) == null) {
					res[i][j] = e;
				} else {
					switch (this.game.getCurrentRound().getBoard().getCard(i, j).getShape()) {
					case FORBIDDEN:
						res[i][j] = f;
						break;
					case CIRCLE:
						switch (this.game.getCurrentRound().getBoard().getCard(i, j).getColor()) {
						case BLUE:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = cbt;
							} else {
								res[i][j] = cbf;
							}
							break;
						case GREEN:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = cgt;
							} else {
								res[i][j] = cgf;
							}
							break;
						case RED:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = crt;
							} else {
								res[i][j] = crf;
							}
							break;
						}
						break;
					case SQUARE:
						switch (this.game.getCurrentRound().getBoard().getCard(i, j).getColor()) {
						case BLUE:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = sbt;
							} else {
								res[i][j] = sbf;
							}
							break;
						case GREEN:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = sgt;
							} else {
								res[i][j] = sgf;
							}
							break;
						case RED:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = srt;
							} else {
								res[i][j] = srf;
							}
							break;
						}
						break;
					case TRIANGLE:
						switch (this.game.getCurrentRound().getBoard().getCard(i, j).getColor()) {
						case BLUE:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = tbt;
							} else {
								res[i][j] = tbf;
							}
							break;
						case GREEN:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = tgt;
							} else {
								res[i][j] = tgf;
							}
							break;
						case RED:
							if (this.game.getCurrentRound().getBoard().getCard(i, j).isFull()) {
								res[i][j] = trt;
							} else {
								res[i][j] = trf;
							}
							break;
						}
						break;

					}
				}
			}
		}
		return res;
	}

	/**
	 * Créé le tableau de noms de colonne du JTable représentant le plateau de jeu
	 * 
	 * @return le tableau contenant les noms de colonne du JTable représentant le
	 *         plateau de jeu
	 */
	public Object[] header() {
		Object[] res = new Object[this.game.getCurrentRound().getBoard().getDimensions()[1]];
		for (int i = 0; i < res.length; i++) {
			res[i] = String.valueOf(i);
		}
		return res;
	}

	/**
	 * Converti l'attribut hand du joueur dont le tour est en cours en tableau
	 * utilisable pour créer un JTable de JLabel
	 * 
	 * @return le tableau de tableau de JLabel contenant les cartes à afficher
	 */
	public Object[][] convHand() {
		Object[][] res = new Object[1][this.game.getCurrentRound().getCurrentPlayer().getHand().size()];
		for (int i = 0; i < this.game.getCurrentRound().getCurrentPlayer().getHand().size(); i++) {
			res[0][i] = convCard(this.game.getCurrentRound().getCurrentPlayer().getHand().get(i));
		}
		return res;
	}

	/**
	 * Converti une Card en JLabel ayant l'icone correspondant
	 * 
	 * @param card carte à convertir
	 * @return le JLabel correspondant
	 */
	public JLabel convCard(Card card) {
		JLabel res = new JLabel();
		switch (card.getShape()) {
		case FORBIDDEN:
			break;
		case CIRCLE:
			switch (card.getColor()) {
			case BLUE:
				if (card.isFull()) {
					res = cbth;
				} else {
					res = cbfh;
				}
				break;
			case GREEN:
				if (card.isFull()) {
					res = cgth;
				} else {
					res = cgfh;
				}
				break;
			case RED:
				if (card.isFull()) {
					res = crth;
				} else {
					res = crfh;
				}
				break;
			}
			break;
		case SQUARE:
			switch (card.getColor()) {
			case BLUE:
				if (card.isFull()) {
					res = sbth;
				} else {
					res = sbfh;
				}
				break;
			case GREEN:
				if (card.isFull()) {
					res = sgth;
				} else {
					res = sgfh;
				}
				break;
			case RED:
				if (card.isFull()) {
					res = srth;
				} else {
					res = srfh;
				}
				break;
			}
			break;
		case TRIANGLE:
			switch (card.getColor()) {
			case BLUE:
				if (card.isFull()) {
					res = tbth;
				} else {
					res = tbfh;
				}
				break;
			case GREEN:
				if (card.isFull()) {
					res = tgth;
				} else {
					res = tgfh;
				}
				break;
			case RED:
				if (card.isFull()) {
					res = trth;
				} else {
					res = trfh;
				}
				break;
			}
			break;

		}

		return res;
	}

	/**
	 * Créé le tableau de noms de colonne du JTable représentant la main du joueur
	 * dont le tour est en cours
	 * 
	 * @return le tableau contenant les noms de colonne du JTable représentant la
	 *         main du joueur dont le tour est en cours
	 */
	public Object[] handHeader() {
		Object[] res = new Object[this.game.getCurrentRound().getCurrentPlayer().getHand().size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = String.valueOf(i);
		}
		return res;
	}

	/**
	 * Create the frame.
	 */
	public GameWindow(Game game) {
		setTitle("ShapeUp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-10, 0, 1940, 1050);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.game = game;

		endTurn = new JButton("Finir le tour");
		endTurn.setEnabled(true);
		endTurn.setBounds(1425, 910, 122, 23);
		contentPane.add(endTurn);

		endRound = new JButton("Finir le round");
		endRound.setBounds(1611, 910, 132, 23);
		endRound.setVisible(false);
		endRound.setEnabled(false);
		contentPane.add(endRound);

		this.gameController = new GameController(this.game, endTurn, endRound);

		// Ajout observer des plateaux de jeu
		for (int i = 0; i < this.game.getRounds().size(); i++) {
			this.game.getRounds().get(i).addObserver(this);
			this.game.getRounds().get(i).getBoard().addObserver(this);
		}

		// Ajout observer des joueurs
		for (int j = 0; j < this.game.getPlayers().size(); j++) {
			this.game.getPlayers().get(j).addObserver(this);
		}

		// Choix des dimension des cartes en fonction du plateau choisi
		switch (this.game.getCurrentRound().getBoard().getDimensions()[0]) {
		case 3:
			xScale = 166;
			yScale = 237;
			break;
		case 4:
			xScale = 124;
			yScale = 177;
			break;
		case 5:
			xScale = 96;
			yScale = 141;
			break;
		}

		// Création des imagees des cartes
		e = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/e.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		f = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/f.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		cbt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/cbt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		cbf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/cbf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		sbt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/sbt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		sbf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/sbf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		tbt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/tbt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		tbf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/tbf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		cgt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/cgt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		cgf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/cgf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		sgt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/sgt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		sgf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/sgf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		tgt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/tgt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		tgf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/tgf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		crt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/crt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		crf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/crf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		srt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/srt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		srf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/srf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		trt = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/trt.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		trf = new JLabel(new ImageIcon(new ImageIcon(GameWindow.class.getResource("/assets/trf.png")).getImage()
				.getScaledInstance(xScale, yScale, Image.SCALE_DEFAULT)));
		// Création des images des cartes pour la main
		cbth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/cbth.png")));
		cbfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/cbfh.png")));
		sbth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/sbth.png")));
		sbfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/sbfh.png")));
		tbth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/tbth.png")));
		tbfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/tbfh.png")));
		cgth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/cgth.png")));
		cgfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/cgfh.png")));
		sgth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/sgth.png")));
		sgfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/sgfh.png")));
		tgth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/tgth.png")));
		tgfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/tgfh.png")));
		crth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/crth.png")));
		crfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/crfh.png")));
		srth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/srth.png")));
		srfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/srfh.png")));
		trth = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/trth.png")));
		trfh = new JLabel(new ImageIcon(GameWindow.class.getResource("/assets/trfh.png")));

		playersPanel = new JPanel();
		playersPanel.setBounds(5, 5, 1154, 80);
		contentPane.add(playersPanel);
		playersPanel.setLayout(null);
		playersPanel.setOpaque(false);

		p1Panel = new JPanel();
		p1Panel.setBackground(Color.GRAY);
		p1Panel.setBounds(0, 0, 374, 80);
		playersPanel.add(p1Panel);
		p1Panel.setLayout(null);

		p1ScoreLabel = new JLabel(this.game.getPlayers().get(0).getScore() + " points");
		p1ScoreLabel.setBounds(10, 36, 100, 14);
		p1Panel.add(p1ScoreLabel);

		p1NameLabel = new JLabel(this.game.getPlayers().get(0).getName());
		p1NameLabel.setBounds(10, 11, 100, 14);
		p1Panel.add(p1NameLabel);

		p2Panel = new JPanel();
		p2Panel.setBackground(Color.GRAY);
		p2Panel.setBounds(384, 0, 384, 80);
		playersPanel.add(p2Panel);
		p2Panel.setLayout(null);

		p2NameLabel = new JLabel(this.game.getPlayers().get(1).getName());
		p2NameLabel.setBounds(10, 11, 100, 14);
		p2Panel.add(p2NameLabel);

		p2ScoreLabel = new JLabel(this.game.getPlayers().get(1).getScore() + " points");
		p2ScoreLabel.setBounds(10, 36, 100, 14);
		p2Panel.add(p2ScoreLabel);

		p3Panel = new JPanel();
		p3Panel.setBackground(Color.GRAY);
		p3Panel.setBounds(778, 0, 375, 80);
		playersPanel.add(p3Panel);
		p3Panel.setLayout(null);

		p3NameLabel = new JLabel("Player 3 name");
		p3NameLabel.setBounds(10, 11, 100, 14);
		p3Panel.add(p3NameLabel);

		p3ScoreLabel = new JLabel("Player 3 score");
		p3ScoreLabel.setBounds(10, 36, 100, 14);
		p3Panel.add(p3ScoreLabel);
		p3Panel.setVisible(false);
		if (this.game.getPlayers().size() == 3) {
			p3NameLabel.setText(this.game.getPlayers().get(2).getName());
			p3ScoreLabel.setText(this.game.getPlayers().get(2).getScore() + " points");
			p3Panel.setVisible(true);
		}

		handPanel = new JPanel();
		handPanel.setBounds(177, 843, 1182, 157);
		handPanel.setOpaque(true);
		contentPane.add(handPanel);

		boardPanel = new JPanel();
		boardPanel.setBounds(5, 96, 1663, 744);
		contentPane.add(boardPanel);
		boardPanel.setLayout(null);
		boardPanel.setOpaque(false);

		boardSP = new JScrollPane();
		boardSP.setBounds((1904 - this.game.getCurrentRound().getBoard().getDimensions()[1] * xScale) / 2, 11,
				this.game.getCurrentRound().getBoard().getDimensions()[1] * (xScale + 2), 722);
		boardPanel.add(boardSP);

		boardTable = new JTable(this.convSlots(), this.header());
		boardSP.setViewportView(boardTable);
		boardTable.setShowGrid(false);
		boardTable.setOpaque(false);
		boardTable.getTableHeader().setUI(null);
		boardTable.addMouseListener(new ClickListener(this, boardTable));
		// Ajout du cellRenderer spécifique au plateau permettant d'afficher les cartes
		for (int i = 0; i < this.header().length; i++) {
			boardTable.getColumn(this.header()[i])
					.setCellRenderer(new LabelRenderer(this.game.getCurrentRound().getBoard().getDimensions()));
		}

		handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));

		handSP = new JScrollPane();
		handPanel.add(handSP);

		handTable = new JTable(this.convHand(), this.handHeader());
		handSP.setViewportView(handTable);
		handTable.setShowGrid(true);
		handTable.setOpaque(true);
		handTable.getTableHeader().setUI(null);
		handTable.addMouseListener(new ClickListener(this, handTable));
		// Ajout du cellRenderer spécifique à la main permetant d'afficher les cartes
		for (int i = 0; i < this.handHeader().length; i++) {
			handTable.getColumn(this.handHeader()[i]).setCellRenderer(new HandRenderer());
		}

		infoHandLabel = new JLabel("main de " + this.game.getCurrentRound().getCurrentPlayer().getName() + ":");
		infoHandLabel.setBounds(19, 914, 132, 14);
		contentPane.add(infoHandLabel);

		infoVictoryCardLabel = new JLabel(
				"carte de victoire de " + this.game.getCurrentRound().getCurrentPlayer().getName() + ":");
		infoVictoryCardLabel.setBounds(1678, 259, 203, 14);
		contentPane.add(infoVictoryCardLabel);

		victoryCardLabel = new JLabel();
		victoryCardLabel.setBounds(1690, 300, 108, 153);
		contentPane.add(victoryCardLabel);

		this.game.start();
	}

	/**
	 * Mise à jour de l'affichage de la carte de victoire
	 */
	public void updateVictoryCard() {
		if (this.game.getCurrentRound().getCurrentPlayer().getVictoryCard() == null) {
			infoVictoryCardLabel.setVisible(false);
			victoryCardLabel.setVisible(false);
		} else {
			infoVictoryCardLabel
					.setText("carte de victoire de " + this.game.getCurrentRound().getCurrentPlayer().getName() + ":");
			infoVictoryCardLabel.setVisible(true);
			victoryCardLabel.setIcon(convCard(game.getCurrentRound().getCurrentPlayer().getVictoryCard()).getIcon());
			victoryCardLabel.setVisible(true);
		}
	}

	/**
	 * Mise à jour de l'affichage du plateau de jeu
	 */
	public void updateBoard() {
		boardTable = new JTable(this.convSlots(), this.header());
		boardSP.setViewportView(boardTable);
		boardTable.setShowGrid(false);
		boardTable.setOpaque(false);
		boardTable.getTableHeader().setUI(null);
		boardTable.addMouseListener(new ClickListener(this, boardTable));

		for (int i = 0; i < this.header().length; i++) {
			boardTable.getColumn(this.header()[i])
					.setCellRenderer(new LabelRenderer(this.game.getCurrentRound().getBoard().getDimensions()));
		}
	}

	/**
	 * Mise à jour de l'affichage de la main
	 */
	public void updateHand() {
		handTable = new JTable(this.convHand(), this.handHeader());
		handSP.setViewportView(handTable);
		handTable.setShowGrid(true);
		handTable.setOpaque(true);
		handTable.getTableHeader().setUI(null);
		handTable.addMouseListener(new ClickListener(this, handTable));

		for (int i = 0; i < this.handHeader().length; i++) {
			handTable.getColumn(this.handHeader()[i]).setCellRenderer(new HandRenderer());
		}
	}

	/**
	 * Accesseur de l'attribut gameController
	 * 
	 * @return l'instance de gameController liée à la fenêtre de jeu
	 */
	public GameController getGameController() {
		return gameController;
	}

	/**
	 * Mise à jour de l'affichage, déclenché lorsqu'un observable ayant la fenêtre
	 * de jeu pour observer notify
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Board) {
			this.updateBoard();
			if (game.getCurrentRound().roundEnded()) {
				endRound.setVisible(true);
				endRound.setEnabled(true);
				// endTurn.setVisible(false);
			} else {
				// endTurn.setVisible(true);
				endRound.setVisible(false);
				endRound.setEnabled(false);
			}
			p1ScoreLabel.setText(this.game.getPlayers().get(0).getScore() + " points");
			p2ScoreLabel.setText(this.game.getPlayers().get(1).getScore() + " points");
			if (this.game.getPlayers().size() > 2) {
				p3ScoreLabel.setText(this.game.getPlayers().get(2).getScore() + " points");
			}

		}
		if (arg0 instanceof Player) {
			this.updateHand();
			infoHandLabel.setText("main de " + this.game.getCurrentRound().getCurrentPlayer().getName() + ":");
			this.updateVictoryCard();
		}
		if (arg0 instanceof Round) {
			System.out.println("r update");
			if (game.getCurrentRound().hasCurrentPlayerPlayedACard()) {
				endTurn.setEnabled(true);
				System.out.println("coucoucoucouc");
			} else {
				endTurn.setEnabled(false);
			}
		}
	}
}
