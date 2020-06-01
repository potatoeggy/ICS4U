// Daniel Chen
// 9 February 2020
// random gooey labels on 2d arrays

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

class Gooey2D extends JFrame implements ActionListener {
	static List<String> wordlist = new ArrayList<String>(); // holds list of words that are allowed
	
	static JPanel labPan = new JPanel(); // label array panel
	static JLabel[][] labels = new JLabel[6][6]; // label array for word search
	static char[][] charArray = new char[labels.length][labels.length]; // corresponding string array for label array
	
	static JPanel addPan = new JPanel(); // panel for prompts
	static JLabel prompt; // "please"
	static JTextField promptTf; // textfield for user to enter something
	static JButton promptButt; // button to confirm
	
	static JPanel notiPan = new JPanel(); // notification panel
	static JLabel noti; // notification text
	
	static JPanel wrapperPan = new JPanel(); // wraps everything in a panel to make pretty
	
	public Gooey2D() { // constructor
		prompt = new JLabel("Please enter a word to search for:");
		prompt.setFont(new Font("sans-serif", Font.PLAIN, 14)); // look pretty
		promptButt = new JButton("OK");
		promptTf = new JTextField(10);
		addPan.add(prompt); // add everything to panel
		addPan.add(promptTf);
		addPan.add(promptButt);
		addPan.setLayout(new FlowLayout()); // left to right
		promptButt.setBackground(Color.WHITE);
		promptButt.addActionListener(this); // check on click
		promptTf.addActionListener(this); // check on hitting enter too

		char[] vowels = {'A', 'E', 'I', 'O', 'U'}; // vowel array
		
		for (int i = 0; i < labels.length; i++) { // for every element in grid
			for (int j = 0; j < labels[i].length; j++) {
				if ((int) (Math.random() * 4) == 0) { // one in three chance to always make vowels so words are easier to make
					charArray[i][j] = vowels[(int)(Math.random() * vowels.length)];
				} else {
					charArray[i][j] = (char)(int)(Math.random()*26+65); // random letter
				}
				
				labels[i][j] = new JLabel("" + charArray[i][j], JLabel.CENTER); // add to labels
				labPan.add(labels[i][j]);
			}
		}
		
		labPan.setLayout(new GridLayout(charArray.length, charArray.length, 10, 10)); // 10 pixels of spacing

		noti = new JLabel();
		noti.setFont(new Font("sans-serif", Font.BOLD, 14)); // make pretty
		notiPan.add(noti);
		notiPan.setLayout(new FlowLayout());
		
		wrapperPan.setLayout(new BoxLayout(wrapperPan, BoxLayout.PAGE_AXIS)); // arrange up to down
		wrapperPan.add(addPan); // add stuffs to panel
		wrapperPan.add(notiPan);
		
		for (JPanel panel : new JPanel[] {wrapperPan, addPan, labPan, notiPan}) {
			panel.setBackground(Color.WHITE); // make all panels white and add them all to the frame
			add(panel);
		}
		
		setSize(480, 400);
		setTitle("Word Search");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 10)); // add margins to frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} // end constructor
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == promptTf || event.getSource() == promptButt) { // if enter or button is pressed
			resetText(); // change all text colours to black
			String user = promptTf.getText(); // store user input
			
			boolean gridFind = findWord(user);
			boolean listFind = wordlistContains(user.toLowerCase());
			if (gridFind) {
				if (listFind) {
					noti.setText("\"" + user + "\" was found in the grid and the word list."); // tell user
					noti.setForeground(Color.GREEN); // make pretty
				} else {
					noti.setText("\"" + user + "\" was found in the grid but not the word list.");
					noti.setForeground(Color.RED); // make pretty
				}
			} else if (listFind) {
				noti.setText("\"" + user + "\" was found in the word list but not the grid.");
				noti.setForeground(Color.RED);
			} else {
				noti.setText("\"" + user + "\" was not found in the grid nor in the word list."); // not found in grid
				noti.setForeground(Color.RED); // make pretty
			}
		} // end if
	} // end actionPerformed

	static boolean findWord(String user) { // finds if word is in array
		char[] userChar = user.toUpperCase().toCharArray();
		if (userChar.length == 0) {
			return false;
		}

		boolean found = false;

		for (int i = 0; i < charArray.length; i++) { // go through entire array
			for (int j = 0; j < charArray[i].length; j++) {
				if (charArray[i][j] == userChar[0]) {
					if (!found) { // we don't want to reassign found once we found something
						found = searchWord(userChar, 1, i, j, 0, 0); // dfs through everything, james says bfs works better :(
						if (found) {
							labels[i][j].setForeground(Color.GREEN); // make pretty
						}
					} else {
						boolean unused = searchWord(userChar, 1, i, j, 0, 0);
						if (unused) {
							labels[i][j].setForeground(Color.GREEN);
						}
					} // end else
				} // end if
			} // end for
		} // end for
		return found;
	} // end findWords

	static boolean searchWord(char[] word, int index, int x, int y, int dirX, int dirY) { // ... this is DFS
		if (index >= word.length) { // so we don't get out of bounds
			return true;
		}
		if (dirX == 0 && dirY == 0) { // search in all directions if no direction given
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i != 0 || j != 0) { // don't check itself
						if (x+i >= 0 && x+i < charArray.length && y+j >= 0 && y+j < charArray[0].length) { // check for bounds
							if (charArray[x+i][y+j] == word[index]) { // if character found
								if (index == word.length-1) { // if we found all of them
									labels[x+i][y+j].setForeground(Color.GREEN); // make pretty
									return true;
								} else {
									if (searchWord(word, index+1, x+i, y+j, i, j)) { // if found eventually
										labels[x+i][y+j].setForeground(Color.GREEN);
										return true;
									}
								} // end else
							} // end if
						} // end if
					} // end if
				} // end for
			} // end for
		} else { // we have a direction
			if (x+dirX >= 0 && x+dirX < charArray.length && y+dirY >= 0 && y+dirY < charArray.length) { // check for bounds
				if (charArray[x+dirX][y+dirY] == word[index]) { // check if character found
					if (index == word.length-1) { // if we found all characters
						labels[x+dirX][y+dirY].setForeground(Color.GREEN);
						return true;
					} else { // otherwise continue recursing
						if (searchWord(word, index+1, x+dirX, y+dirY, dirX, dirY)) {
							labels[x+dirX][y+dirY].setForeground(Color.GREEN);
							return true;
						}
					} // end else
				} // end if
			} // end if
		} // end else
		return false; // we didn't find it
	} // end searchWords

	static boolean wordlistContains(String user) { // binary search
		int min = 0;
		int max = wordlist.size()-1; // set highest to end of arraylist

		while (min <= max) { // iterative mode
			int mid = (min + max) / 2; // set middle
			if (user.compareToIgnoreCase(wordlist.get(mid)) == 0) { // if equal we found it
				return true;
			} else if (user.compareToIgnoreCase(wordlist.get(mid)) > 0) { // if not equal do something but I forget how compareTo works
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		return false;
	} // end wordlistContains

	static void resetText() { // set all grid labels to black
		for (JLabel[] list : labels) {
			for (JLabel label : list) {
				label.setForeground(Color.BLACK);
			}
		}
		noti.setText(""); // reset notification text too
	} // end resetText
		
	static void readWords() { // read from file
		try {
			File file = new File("wordlist.txt");
			Scanner finput = new Scanner(file);
			while (finput.hasNextLine()) { // read everything from file
				wordlist.add(finput.nextLine());
			} // end while
			finput.close();
		} catch (Exception e) {
			System.out.println("IO broke");
		}
	} // end readWords
	
	public static void main(String[] args) {
		readWords();
		UIManager.getLookAndFeelDefaults().put("Label.font", new Font("sans-serif", Font.BOLD, 24)); // make pretty
		UIManager.getLookAndFeelDefaults().put("Button.font", new Font("sans-serif", Font.BOLD, 14));
		UIManager.getLookAndFeelDefaults().put("TextField.font", new Font("sans-serif", Font.PLAIN, 14));
		System.setProperty("awt.useSystemAAFontSettings","on"); // make pretty just in case it's not on by default
		System.setProperty("swing.aatext", "true"); // really double check that antialiasing is on
		Gooey2D window = new Gooey2D();
	} // end main
} // end class
