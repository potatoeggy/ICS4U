import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginAutentification extends JFrame implements ActionListener {
	JPanel holder = new JPanel();
	JPanel[] grid = {new JPanel(), new JPanel()};
	JButton butt = new JButton("Login");
	JLabel[] prompts = {new JLabel("Username:"), new JLabel("Password:")};
	JTextField[] tfs = {new JTextField(10), new JTextField(10)};
	JLabel[] greeting = {new JLabel("Welcome!"), new JLabel("You are logged in!")};
	
	public LoginAutentification() {
		holder.setLayout(new BoxLayout(holder, BoxLayout.PAGE_AXIS));
		holder.add(greeting[0]);
		holder.add(Box.createVerticalStrut(10));
		holder.add(grid[0]);
		holder.add(grid[1]);
		holder.setBackground(Color.WHITE);
		butt.setBackground(Color.WHITE);
		butt.addActionListener(this);
		holder.add(butt);
		holder.add(Box.createVerticalStrut(10));
		holder.add(greeting[1]);
		greeting[1].setForeground(Color.WHITE);
		
		for (int i = 0; i < tfs.length; i++) {
			grid[i].setLayout(new FlowLayout());
			grid[i].add(prompts[i]);
			grid[i].add(tfs[i]);
			grid[i].setBackground(Color.WHITE);
			tfs[i].addActionListener(this);
		}

		for (JComponent comp : new JComponent[] {butt, greeting[0], greeting[1]}) {
			comp.setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		add(holder);
		holder.setVisible(true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(420, 240);
		setTitle("Login Autentification");
		setBackground(Color.WHITE);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 30));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		LoginAutentification window = new LoginAutentification();
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == tfs[0]) {
			greeting[1].setForeground(Color.WHITE);
		} else {
			greeting[1].setForeground(Color.BLACK);
		}
	}
}
