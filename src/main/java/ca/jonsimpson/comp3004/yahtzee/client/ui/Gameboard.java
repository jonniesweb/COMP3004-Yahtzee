package ca.jonsimpson.comp3004.yahtzee.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import ca.jonsimpson.comp3004.yahtzee.DiceSet;
import ca.jonsimpson.comp3004.yahtzee.ScoreCard;
import ca.jonsimpson.comp3004.yahtzee.main.IView;

import javax.swing.JLabel;

import java.awt.Component;

public class Gameboard extends JFrame implements IView {
	
	private JPanel contentPane;
	private final JPanel panelRolledDice = new JPanel();
	private final JPanel panelSelectedDice = new JPanel();
	private final JButton btnRoll = new JButton("Roll");
	private ActionListener savedToRolledActionListener;
	private ActionListener rolledToSavedActionListener;
	private final JPanel panel = new JPanel();
	private final JLabel lblOnes = new JLabel("Ones");
	private final JLabel lblTwos = new JLabel("Twos");
	private final JLabel lblThrees = new JLabel("Threes");
	private final JLabel lblFours = new JLabel("Fours");
	private final JLabel lblFives = new JLabel("Fives");
	private final JLabel lblSixes = new JLabel("Sixes");
	private final JLabel lblOfA = new JLabel("3 of a kind");
	private final JLabel lblOfA_1 = new JLabel("4 of a kind");
	private final JLabel lblFullHouse = new JLabel("Full House");
	private final JLabel lblSmallStraight = new JLabel("Small Straight");
	private final JLabel lblLargeStraight = new JLabel("Large Straight");
	private final JLabel lblYahtzee = new JLabel("YAHTZEE");
	private final JLabel lblChance = new JLabel("Chance");
	private final JLabel lblYahtzeeBonus = new JLabel("YAHTZEE Bonus");
	private final JLabel lblNewLabel = new JLabel("Rolling");
	private final JLabel lblChosen = new JLabel("Chosen");
	private final JButton btn_ones = createScoreCardButton("s");
	private final JButton btn_twos = createScoreCardButton("");
	private final JButton button_1 = createScoreCardButton("");
	private final JButton btnFours = createScoreCardButton("3");
	private final JButton btnFives = createScoreCardButton("");
	private final JButton btnSixes = createScoreCardButton("");
	private final JButton btn3OfAKind = createScoreCardButton("");
	private final JButton btn4OfAKind = createScoreCardButton("");
	private final JButton btnFullHouse = createScoreCardButton("");
	private final JButton btnSmallStraight = createScoreCardButton("");
	private final JButton btnLargeStraight = createScoreCardButton("");
	private final JButton btnYahtzee = createScoreCardButton("");
	private final JButton btnChance = createScoreCardButton("");
	private final JButton btnYahtzeeBonus1 = createScoreCardButton("\u2713");
	private final JButton btnYahtzeeBonus2 = createScoreCardButton("✓");
	private final JButton btnYahtzeeBonus3 = createScoreCardButton("✓");
	
	/**
	 * Create the frame.
	 */
	public Gameboard() {
		init();
		setVisible(true);
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][2px][][][][grow]"));
		
		contentPane.add(lblNewLabel, "cell 0 0");
		panelRolledDice.setPreferredSize(new Dimension(200, 50));
		
		contentPane.add(panelRolledDice, "cell 0 1,grow");
		
		contentPane.add(panel, "cell 2 0 1 7,grow");
		panel.setLayout(new MigLayout("", "[][14.00][14.00][14.00]", "[][][][][][][2px][][][][][][][][]"));
		
		panel.add(lblOnes, "cell 0 0");
		
		panel.add(btn_ones, "cell 1 0 3 1,growx");
		
		panel.add(lblTwos, "cell 0 1");
		
		panel.add(btn_twos, "cell 1 1 3 1,growx");
		
		panel.add(lblThrees, "cell 0 2");
		
		panel.add(button_1, "cell 1 2 3 1,growx");
		
		panel.add(lblFours, "cell 0 3");
		
		panel.add(btnFours, "cell 1 3 3 1,growx");
		
		panel.add(lblFives, "cell 0 4");
		
		panel.add(btnFives, "cell 1 4 3 1,growx");
		
		panel.add(lblSixes, "cell 0 5");
		
		panel.add(btnSixes, "cell 1 5 3 1,growx");
		
		panel.add(lblOfA, "cell 0 7");
		
		panel.add(btn3OfAKind, "cell 1 7 3 1,growx");
		
		panel.add(lblOfA_1, "cell 0 8");
		
		panel.add(btn4OfAKind, "cell 1 8 3 1,growx");
		
		panel.add(lblFullHouse, "cell 0 9");
		
		panel.add(btnFullHouse, "cell 1 9 3 1,growx");
		
		panel.add(lblSmallStraight, "cell 0 10");
		
		panel.add(btnSmallStraight, "cell 1 10 3 1,growx");
		
		panel.add(lblLargeStraight, "cell 0 11");
		
		panel.add(btnLargeStraight, "cell 1 11 3 1,growx");
		
		panel.add(lblYahtzee, "cell 0 12");
		
		panel.add(btnYahtzee, "cell 1 12 3 1,growx");
		lblChance.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(lblChance, "cell 0 13");
		
		panel.add(btnChance, "cell 1 13 3 1,growx");
		
		panel.add(lblYahtzeeBonus, "cell 0 14");
		
		panel.add(btnYahtzeeBonus1, "cell 1 14");
		
		panel.add(btnYahtzeeBonus2, "cell 2 14");
		
		panel.add(btnYahtzeeBonus3, "cell 3 14");
		
		contentPane.add(lblChosen, "cell 0 3");
		panelSelectedDice.setPreferredSize(new Dimension(200, 50));
		
		contentPane.add(panelSelectedDice, "cell 0 4,grow");
		
		// style dice areas
		Color black = new Color(0);
		panelRolledDice.setBorder(new BevelBorder(1, black, black));
		panelSelectedDice.setBorder(new BevelBorder(1, black, black));
		
		contentPane.add(btnRoll, "cell 0 5,growx");
		
	}
	
	@Override
	public void updateScoreCard(ScoreCard scoreCard) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Update the view with the given {@link DiceSet}
	 * @param dice
	 */
	public void updateDice(DiceSet dice) {
		panelRolledDice.removeAll();
		for (Integer die : dice.getRolledDice()) {
			addDieToRolledDice(getDie(die));
			
		}
		
		
		panelSelectedDice.removeAll();
		for (Integer die : dice.getSavedDice()) {
			addDieToSelectedDice(getDie(die));
		}
		update(panelRolledDice);
		update(panelSelectedDice);
	}

	private void update(JComponent component) {
			component.revalidate();
			component.repaint();
		}

	private JButton getDie(Integer number) {
		JButton button = new JButton(number.toString());
		button.setFocusable(false);
		int xy = 30;
		button.setPreferredSize(new Dimension(xy, xy));
		button.setBackground(new Color(255, 255, 255));
		button.setMargin(new Insets(2, 2, 2, 2));
		return button;
	}
	
	private void addObserverToButton(Observer observer, JButton button) {
		// on roll button press, notify the observers
		DoWhatISayObservable observable = new DoWhatISayObservable();
		observable.addObserver(observer);
		button.addActionListener(e -> observable.doIt());
	}

	@Override
	public void addRollDiceObserver(Observer observer) {
		
		addObserverToButton(observer, btnRoll);
	}

	@Override
		public void addDiceSwitchFromRolledToSavedObserver(Observer observer) {
			DoWhatISayObservable observable = new DoWhatISayObservable();
			observable.addObserver(observer);
			
			/*
			 * Create an ActionListener that will notify the observer of the
			 * die that the user clicked. No fancy lambdas here.
			 */
			rolledToSavedActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() instanceof JButton) {
						JButton button = (JButton) e.getSource();
						String die = button.getText();
						observable.doIt(die);
					}
				}
			};
		}

	@Override
		public void addDiceSwitchFromSavedToRolledObserver(Observer observer) {
			DoWhatISayObservable observable = new DoWhatISayObservable();
			observable.addObserver(observer);

			/*
			 * Create an ActionListener that will notify the observer of the
			 * die that the user clicked. No fancy lambdas here.
			 */
			savedToRolledActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() instanceof JButton) {
						JButton button = (JButton) e.getSource();
						String die = button.getText();
						observable.doIt(die);
					}
				}
			};
		}

	@Override
	public void addChooseScoreCategoryObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	private void addDieToSelectedDice(JButton die) {
		panelSelectedDice.add(die);
		die.addActionListener(savedToRolledActionListener);
	}
	
	private void addDieToRolledDice(JButton die) {
		panelRolledDice.add(die);
		
		// add an action listener to move the die to rolled dice
		// notify the server of the new DiceSet
		die.addActionListener(rolledToSavedActionListener);
	}


	class DoWhatISayObservable extends Observable {
		public void doIt() {
			setChanged();
			notifyObservers();
		}
		public void doIt(Object o) {
			setChanged();
			notifyObservers(o);
		}
	}

	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source text ""
	 */
	public static JButton createScoreCardButton(String text) {
		JButton button = new JButton(text);
		button.setFocusable(false);
		int xy = 30;
		button.setPreferredSize(new Dimension(xy, xy));
		button.setBackground(new Color(255, 255, 255));
		button.setMargin(new Insets(2, 2, 2, 2));
		return button;
	}
	
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source text ""
	 */
	public static JButton createScoreCardBonusButton(String text) {
		JButton button = new JButton(text);
		button.setFocusable(false);
		int xy = 30;
		button.setPreferredSize(new Dimension(40, xy));
		button.setBackground(new Color(255, 255, 255, 0));
		button.setMargin(new Insets(2, 2, 2, 2));
		return button;
	}

}
