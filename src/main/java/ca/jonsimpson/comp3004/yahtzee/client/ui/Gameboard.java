package ca.jonsimpson.comp3004.yahtzee.client.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class Gameboard extends JFrame {
	
	private JPanel contentPane;
	private final JPanel panelRolledDice = new JPanel();
	private final JPanel panelSelectedDice = new JPanel();
	private final JButton btnRoll = new JButton("Roll");
	private final JSeparator separator = new JSeparator();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gameboard frame = new Gameboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Gameboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow]", "[grow][grow][]"));
		
		contentPane.add(panelRolledDice, "cell 0 0,grow");
		separator.setOrientation(SwingConstants.VERTICAL);
		
		contentPane.add(separator, "cell 1 0 1 3");
		
		contentPane.add(panelSelectedDice, "cell 0 1,grow");
		
		contentPane.add(btnRoll, "cell 0 2");
		
		// style dice areas
		Color black = new Color(0);
		panelRolledDice.setBorder(new BevelBorder(1, black, black));
		panelSelectedDice.setBorder(new BevelBorder(1, black, black));
		
		
		// add demo dice
		addDieToSelectedDice(getDie(1));
		addDieToSelectedDice(getDie(2));
		addDieToSelectedDice(getDie(3));
		addDieToSelectedDice(getDie(4));
		addDieToSelectedDice(getDie(5));
		
		addDieToRolledDice(getDie(1));
		addDieToRolledDice(getDie(2));
		addDieToRolledDice(getDie(3));
		addDieToRolledDice(getDie(4));
		addDieToRolledDice(getDie(5));
	}
	
	private JButton getDie(Integer number) {
		JButton button = new JButton(number.toString());
		button.setSize(50, 50);
		button.setBackground(new Color(255, 255, 255));
		return button;
	}
	
	private void addDieToSelectedDice(JButton die) {
		panelSelectedDice.add(die);
	}
	
	private void addDieToRolledDice(JButton die) {
		panelRolledDice.add(die);
	}
}
