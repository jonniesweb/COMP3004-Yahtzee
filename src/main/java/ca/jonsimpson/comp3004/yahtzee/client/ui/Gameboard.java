package ca.jonsimpson.comp3004.yahtzee.client.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

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
import ca.jonsimpson.comp3004.yahtzee.client.ClientLogic;
import ca.jonsimpson.comp3004.yahtzee.main.IView;
import ca.jonsimpson.comp3004.yahtzee.net.NoMoreRollsException;

public class Gameboard extends JFrame implements IView {
	
	private JPanel contentPane;
	private final JPanel panelRolledDice = new JPanel();
	private final JPanel panelSelectedDice = new JPanel();
	private final JButton btnRoll = new JButton("Roll");
	private final JSeparator separator = new JSeparator();
	
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
//		component.repaint();
	}

	@Override
	public void updateScoreCard(ScoreCard scoreCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRollDiceObserver(Observer observer) {
		
		// on roll button press, notify the observers
		ButtonObserver observable = new ButtonObserver();
		observable.addObserver(observer);
		btnRoll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				observable.click();
				observable.notifyObservers();
			}
		});
	}

	@Override
	public void addDiceSwitchFromRollToChosenObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDiceSwitchFromChosenToRollObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChooseScoreCategoryObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonObserver extends Observable {
		public void click() {
			setChanged();
		}
	}
}
