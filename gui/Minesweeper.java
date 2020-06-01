// Daniel Chen
// 26 February 2020
// Minesweeper part 2

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

// TODO: Wincon

class Minesweeper extends JFrame implements ActionListener {
	static int[][] bombs = new int[10][10]; // holds bombs
	static int[][] prettyBombs = new int[10][10]; // holds numbers but doesn't store x's
	static JButton[][] buttons = new JButton[bombs.length][bombs.length]; // holds buttons to press
	static JPanel gridPan = new JPanel(); // main clicky panel
	static JPanel topPan = new JPanel(); // prompt panel
	static JLabel stats; // tell user what to do and if they lose
	static boolean started = false; // reassign numbers?
	
	public Minesweeper() {
		for (int i = 0; i < (bombs.length * bombs.length * 0.2); i++) {
			int x = (int) (Math.random() * bombs.length); // randomly generate square
			int y = (int) (Math.random() * bombs.length);
			
			if (bombs[x][y] == 1) {
				i--; // it's already a bomb try again
			} else {
				bombs[x][y] = 1;
			}
		}

		stats = new JLabel("Press a square!"); // TODO: Fix gray spot I don't know why it's gray
		stats.setFont(new Font("sans-serif", Font.BOLD, 18)); // make pretty
		topPan.add(stats);
		topPan.setLayout(new BoxLayout(topPan, BoxLayout.X_AXIS)); // probably should change to fix that's why it's gray
		topPan.setBackground(Color.WHITE);
			
		for (int i = 0; i < bombs.length; i++) {
			for (int j = 0; j < bombs.length; j++) { // make all buttons equal
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(Color.LIGHT_GRAY); // make it pretty
				buttons[i][j].putClientProperty("x", new Integer(i)); // so we can get index in actionPerformed
				buttons[i][j].putClientProperty("y", new Integer(j));
				buttons[i][j].setFocusPainted(false); // no highlight
				buttons[i][j].setMargin(new Insets(0, 0, 0, 0)); // more text in smaller button
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].addActionListener(this);
				buttons[i][j].addMouseListener(new MouseAdapter() { // pretty hover effect
					public void mouseEntered(MouseEvent event) {
						((JButton) event.getSource()).setBackground(Color.GRAY);
					}

					public void mouseExited(MouseEvent event) {
						((JButton) event.getSource()).setBackground(Color.LIGHT_GRAY);
					}
					/*
					public void mouseClicked(MouseEvent event) {
						if (SwingUtilities.isRightMouseButton(e)) {
							(JButt) // you have dumb handling for colours do it later
						} else {

						}
					}
					*/
				});
				
				gridPan.add(buttons[i][j]);
			}
		} // end for
		gridPan.setLayout(new GridLayout(bombs.length, bombs.length, 5, 5));
		gridPan.setBackground(Color.WHITE);

		add(topPan);
		add(gridPan);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)); // make frame pretty
		setTitle("Minesweeper");
		setBackground(Color.WHITE);
		setSize(500, 500);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5)); // margins
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		for (int i = 0; i < bombs.length; i++) {
			for (int j = 0; j < bombs.length; j++) {
				System.out.print(bombs[i][j] + " "); // used for debugging and for help if user needs it
			}
			System.out.println();
		}
	} // end constructor

	public void actionPerformed(ActionEvent event) {
		JButton butt = (JButton) event.getSource(); // only possible source so this should be safe
		int x = (Integer) butt.getClientProperty("x"); // so we know indices
		int y = (Integer) butt.getClientProperty("y");
		
		if (! started) { // if first button clicked
			started = true;
			if (bombs[x][y] == 1) { // move bomb if current one is bomb
				boolean rearranged = false;
				while (! rearranged) {
					int tempX = (int) (Math.random() * bombs.length); // try to randomise location of bomb
					int tempY = (int) (Math.random() * bombs.length);
					if (bombs[tempX][tempY] == 0) {
						rearranged = true;
						bombs[tempX][tempY] = 1;
						bombs[x][y] = 0;
					}
				}
			} // end if

			for (int g = 0; g < bombs.length; g++) {
				for (int h = 0; h < bombs.length; h++) { // iterate through every square
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) { // iterate through every adjacent square
							if (g + i >= 0 && g + i < bombs.length && h + j >= 0 && h + j < bombs.length) { // check for bounds
								if (bombs[g+i][h+j] == 1) { // get total bombs beside it
									prettyBombs[g][h]++;
								}
							}
						}
					}
				}
			} // end for
		} // end if

		if (revealSquare(x, y)) { // if game is over
			for (int i = 0; i < bombs.length; i++) { // reveal all squares
				for (int j = 0; j < bombs.length; j++) {
					boolean whoCaresAboutThis = revealSquare(i, j); // used to just hold boolean return
				}
			}
		}
	} // end actionPerformed

	static boolean revealSquare(int x, int y) { // reveals squares
		buttons[x][y].setBackground(Color.WHITE); // replacement for setEnabled because setEnabled doesn't show my pretty colours
		buttons[x][y].setRolloverEnabled(false);
		buttons[x][y].setContentAreaFilled(false);
		if (bombs[x][y] == 1) { // if there's a bomb end game
			buttons[x][y].setText("X");
			buttons[x][y].setForeground(Color.RED);
			stats.setText("Game over!");
			return true;
		} else {
			int counter = prettyBombs[x][y]; // working around old code
			if (counter != 0) { // if we don't need to search adjacent squares
				JButton butt = buttons[x][y];
				butt.setText(Integer.toString(counter)); // tell user and make it pretty
				if (counter == 1) {
					butt.setForeground(Color.BLUE);
				} else if (counter == 2) {
					butt.setForeground(Color.GREEN);
				} else if (counter == 3) {
					butt.setForeground(Color.RED);
				} else if (counter == 4) {
					butt.setForeground(Color.MAGENTA);
				} else if (counter == 5) {
					butt.setForeground(Color.ORANGE);
				} else if (counter == 6) {
					butt.setForeground(Color.CYAN);
				} else if (counter == 7) {
					butt.setForeground(Color.BLACK);
				} else {
					butt.setForeground(Color.GRAY);
				}
			} else {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) { // check all adjacent squares
						if (x+i >= 0 && x+i < buttons.length && y+j >= 0 && y+j < buttons.length) { // check for bounds
							if (buttons[x+i][y+j].getBackground() != Color.WHITE) {
								revealSquare(x+i, y+j); // recursion! to expand all empty squares
							}
						}
					}
				}
			} // end else
			return false; // TODO: handle winning, return true if you win to clear all squares
		}
	} // end revealSquare
	
	public static void main(String[] args) {
		UIManager.getLookAndFeelDefaults().put("Button.font", new Font("sans-serif", Font.BOLD, 20)); // make pretty
		Minesweeper window = new Minesweeper();
	} // end main
}
