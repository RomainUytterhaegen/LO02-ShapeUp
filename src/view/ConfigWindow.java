package view;

import java.awt.BorderLayout;

import model.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Classe représentant l'interface graphique pour choisir la configuration de la partie de ShapeUp
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class ConfigWindow extends JFrame {

	private JPanel contentPane;
	// Noms des joueurs
	private JTextField txtPlayer1Name;
	private JTextField txtPlayer2Name;
	private JTextField txtPlayer3Name;
	//Choix du gamerule
	private JRadioButton radioBtnGamerule1;
	private JRadioButton radioBtnGamerule2;
	private JRadioButton radioBtnGamerule3;
	//Choix du board
	private JRadioButton radioBtnBoard1;
	private JRadioButton radioBtnBoard2;
	private JRadioButton radioBtnBoard3;
	// Choix humain ou robot
	private JRadioButton radioBtnHuman1;
	private JRadioButton radioBtnHuman2;
	private JRadioButton radioBtnHuman3;
	private JRadioButton radioBtnRobot1;
	private JRadioButton radioBtnRobot2;
	private JRadioButton radioBtnRobot3;
	// Nombre de joueurs
	private JRadioButton radioBtn2players;
	private JRadioButton radioBtn3players;
	//Groupes de boutons
	private final ButtonGroup btnGroupGamerule = new ButtonGroup();
	private final ButtonGroup btnGroupBoard = new ButtonGroup();
	private final ButtonGroup btnGroupPlayerNumber = new ButtonGroup();
	private final ButtonGroup btnGroupPlayer1 = new ButtonGroup();
	private final ButtonGroup btnGroupPlayer2 = new ButtonGroup();
	private final ButtonGroup btnGroupPlayer3 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigWindow frame = new ConfigWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Constructeur de la classe ConfigWindow
	 */
	public ConfigWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1340, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblGamerule = new JLabel("Choisir le mode de jeu");
		contentPane.add(lblGamerule);
		lblGamerule.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelGameruleChoice = new JPanel();
		contentPane.add(panelGameruleChoice);
		FlowLayout fl_panelGameruleChoice = (FlowLayout) panelGameruleChoice.getLayout();
		
		this.radioBtnGamerule1 = new JRadioButton("R\u00E8gles du jeu classique");
		btnGroupGamerule.add(radioBtnGamerule1);
		panelGameruleChoice.add(radioBtnGamerule1);
		
		this.radioBtnGamerule2 = new JRadioButton("R\u00E8gles du jeu avanc\u00E9");
		btnGroupGamerule.add(radioBtnGamerule2);
		radioBtnGamerule2.setHorizontalAlignment(SwingConstants.CENTER);
		panelGameruleChoice.add(radioBtnGamerule2);
		
		this.radioBtnGamerule3 = new JRadioButton("freeMove");
		btnGroupGamerule.add(radioBtnGamerule3);
		panelGameruleChoice.add(radioBtnGamerule3);
		
		JLabel lblBoard = new JLabel("Choisir le plateau");
		contentPane.add(lblBoard);
		
		JPanel panelBoardChoice = new JPanel();
		contentPane.add(panelBoardChoice);
		
		this.radioBtnBoard1 = new JRadioButton("Plateau rectangulaire");
		btnGroupBoard.add(radioBtnBoard1);
		panelBoardChoice.add(radioBtnBoard1);
		
		this.radioBtnBoard2 = new JRadioButton("Plateau triangulaire");
		btnGroupBoard.add(radioBtnBoard2);
		panelBoardChoice.add(radioBtnBoard2);
		
		this.radioBtnBoard3 = new JRadioButton("Plateau circulaire");
		btnGroupBoard.add(radioBtnBoard3);
		panelBoardChoice.add(radioBtnBoard3);
		
		JPanel playersConfig = new JPanel();
		contentPane.add(playersConfig);
		playersConfig.setLayout(null);
		
		JLabel lblNumberOfPlayers = new JLabel("Nombre de joueurs");
		lblNumberOfPlayers.setBounds(526, 8, 109, 14);
		playersConfig.add(lblNumberOfPlayers);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(675, 14, 0, 2);
		playersConfig.add(separator);
		
		JPanel player1Config = new JPanel();
		player1Config.setBounds(39, 38, 177, 111);
		playersConfig.add(player1Config);
		
		JLabel lblPlayer1 = new JLabel("Joueur 1");
		player1Config.add(lblPlayer1);
		
		txtPlayer1Name = new JTextField();
		player1Config.add(txtPlayer1Name);
		txtPlayer1Name.setText("Player Name 1");
		txtPlayer1Name.setToolTipText("");
		txtPlayer1Name.setColumns(10);
		
		this.radioBtnHuman1 = new JRadioButton("Humain");
		btnGroupPlayer1.add(radioBtnHuman1);
		player1Config.add(radioBtnHuman1);
		
		this.radioBtnRobot1 = new JRadioButton("Robot");
		btnGroupPlayer1.add(radioBtnRobot1);
		player1Config.add(radioBtnRobot1);
		
		JPanel player2Config = new JPanel();
		player2Config.setBounds(581, 38, 177, 111);
		playersConfig.add(player2Config);
		
		JLabel lblPlayer2 = new JLabel("Joueur 2");
		player2Config.add(lblPlayer2);
		
		txtPlayer2Name = new JTextField();
		txtPlayer2Name.setToolTipText("");
		txtPlayer2Name.setText("Player Name 2");
		txtPlayer2Name.setColumns(10);
		player2Config.add(txtPlayer2Name);
		
		this.radioBtnHuman2 = new JRadioButton("Humain");
		btnGroupPlayer2.add(radioBtnHuman2);
		player2Config.add(radioBtnHuman2);
		
		this.radioBtnRobot2 = new JRadioButton("Robot");
		btnGroupPlayer2.add(radioBtnRobot2);
		player2Config.add(radioBtnRobot2);
		
		final JPanel player3Config = new JPanel();
		player3Config.setBounds(985, 38, 177, 111);
		playersConfig.add(player3Config);
		
		JLabel lblPlayer3 = new JLabel("Joueur 3");
		player3Config.add(lblPlayer3);
		
		txtPlayer3Name = new JTextField();
		txtPlayer3Name.setToolTipText("");
		txtPlayer3Name.setText("Player Name 3");
		txtPlayer3Name.setColumns(10);
		player3Config.add(txtPlayer3Name);
		
		this.radioBtnHuman3 = new JRadioButton("Humain");
		btnGroupPlayer3.add(radioBtnHuman3);
		player3Config.add(radioBtnHuman3);
		
		this.radioBtnRobot3 = new JRadioButton("Robot");
		btnGroupPlayer3.add(radioBtnRobot3);
		player3Config.add(radioBtnRobot3);
		
		this.radioBtn2players = new JRadioButton("2");
		
		btnGroupPlayerNumber.add(radioBtn2players);
		radioBtn2players.setBounds(646, 4, 109, 23);
		playersConfig.add(radioBtn2players);
		
		this.radioBtn3players = new JRadioButton("3");
		btnGroupPlayerNumber.add(radioBtn3players);
		radioBtn3players.setBounds(754, 4, 109, 23);
		playersConfig.add(radioBtn3players);
		
		JPanel validationPanel = new JPanel();
		contentPane.add(validationPanel);
		validationPanel.setLayout(null);
		
		JButton btnValidate = new JButton("Valider");
		// Quand on appuie sur valider, ça lance le code suivant
		btnValidate.addActionListener(new ActionListener() {
			Game jeu;
			public void actionPerformed(ActionEvent e) {
				
				//Instanciation des joueurs
				
				ArrayList<Player> players = new ArrayList<Player>();
				players.add(new Player(txtPlayer1Name.getText(),radioBtnHuman1.isSelected()));
				System.out.println("DEGUGGGGG");
				System.out.println(players.get(0).isHuman());
				players.add(new Player(txtPlayer2Name.getText(),radioBtnHuman2.isSelected()));
				if(radioBtn3players.isSelected()) {
					players.add(new Player(txtPlayer3Name.getText(),radioBtnHuman3.isSelected()));
				}
				
				// Instanciation du gamerule
				
				Gamerule gm;
				if(radioBtnGamerule1.isSelected()) {
					gm = new Gamerule(1);
				}else if(radioBtnGamerule2.isSelected()) {
					gm = new Gamerule(2);
				}else {
					gm = new Gamerule(3);
				}
				
				// Instanciation du plateau de jeu
				
				Board b;
				if(radioBtnBoard1.isSelected()) {
					b = new Board1();
				}else if(radioBtnBoard2.isSelected()) {
					b = new Board2();
				}else {
					b = new Board3();
				}
				
				// Instanciation du jeu
				jeu = new Game(players,gm,b);
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GameWindow frame = new GameWindow(jeu);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				VueTexte maConsoleText = new VueTexte(jeu);
				
			}
		});
		btnValidate.setBounds(1166, 101, 125, 43);
		validationPanel.add(btnValidate);
		
		// Boutons prédéfinis et presets
		radioBtnBoard1.setSelected(true);
		radioBtnGamerule1.setSelected(true);
		radioBtnHuman1.setSelected(true);
		radioBtnRobot2.setSelected(true);
		radioBtnRobot3.setSelected(true);
		radioBtn2players.setSelected(true);
		player3Config.setVisible(false);
		
		// Event Handlers liés à l'interface graphique
		
		radioBtn2players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player3Config.setVisible(false);
			}
		});
		
		radioBtn3players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player3Config.setVisible(true);
			}
		});
		
	}
}
