package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Player;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Classe représentant l'interface graphique de la fenêtre de fin de jeu de ShapeUp
 * @author Tristan GODEC
 * @author Romain UYTTERHAEGEN
 *
 */
public class EndGameWindow extends JFrame {

	private JPanel contentPane;
	private ArrayList<Player> players;
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	private JLabel lblPlayer3;

	/**
	 * Constructeur de la classe EndGameWindow
	 */
	public EndGameWindow(ArrayList<Player> players) {
		this.players = players;
		setTitle("Classement");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panelPlayer1 = new JPanel();
		contentPane.add(panelPlayer1);
		
		lblPlayer1 = new JLabel("New label");
		lblPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelPlayer1.add(lblPlayer1);
		
		JPanel panelPlayer2 = new JPanel();
		contentPane.add(panelPlayer2);
		
		lblPlayer2 = new JLabel("New label");
		lblPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelPlayer2.add(lblPlayer2);
		
		JPanel panelPlayer3 = new JPanel();
		contentPane.add(panelPlayer3);
		
		lblPlayer3 = new JLabel("New label");
		lblPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelPlayer3.add(lblPlayer3);
		
		JPanel panelQuit = new JPanel();
		contentPane.add(panelQuit);
		
		JButton btnQuit = new JButton("Quitter");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelQuit.add(btnQuit);
		
		this.lblPlayer1.setText("1er : "+this.players.get(0).getName()+" ->"+this.players.get(0).getScore());
		this.lblPlayer2.setText("2ème : "+this.players.get(1).getName()+" ->"+this.players.get(1).getScore());
		
		if(this.players.size() > 2) {
			this.lblPlayer3.setText("3ème : "+this.players.get(2).getName()+" ->"+this.players.get(2).getScore());
		}else {
			this.lblPlayer3.setVisible(false);
		}
	}
}
