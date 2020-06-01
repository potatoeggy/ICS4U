// Daniel Chen
// 26 February 2020
// Minesweeper part 1

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gooey2DButts extends JFrame implements ActionListener {
	JButton[][] buttons = new JButton[5][5]; // make button array
	JPanel panel = new JPanel(); // make panel for buttons
	public Gooey2DButts() {
		panel.setLayout(new GridLayout(5, 5, 5, 5)); // spacing of 5 for array
		panel.setBackground(Color.WHITE); // make pretty

		for (JButton[] list : buttons) { // add all buttons
			for (int i = 0; i < list.length; i++) {
				list[i] = new JButton();
				list[i].setText(Integer.toString((int) (Math.random() * 6 + 1))); // random int from 1-6
				list[i].addActionListener(this); // make it do something
				list[i].setBackground(Color.WHITE); // make pretty
				panel.add(list[i]); // add to pan
			}
		} // add for
		
		add(panel); // add to frame
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)); // make big
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5)); // make margins for pretty
		setTitle("Please Press Buttons!"); // nicely tell user
		setSize(320, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setVisible(true);
	} // end constructor

	public void actionPerformed(ActionEvent event) {
		JButton butt = (JButton) event.getSource();
		butt.setText(Integer.toString(Integer.parseInt(butt.getText())-1)); // reduce text by 1
	} // end actionPerformed
	
	public static void main(String[] args) {
		UIManager.getLookAndFeelDefaults().put("Button.font", new Font("sans-serif", Font.BOLD, 14)); // make pretty
		Gooey2DButts window = new Gooey2DButts();
	} // end main
} // end class
