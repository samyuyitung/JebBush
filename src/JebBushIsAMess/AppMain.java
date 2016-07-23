package JebBushIsAMess;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain implements KeyListener, ActionListener {

	Drawing draw = new Drawing();
	JFrame frame = new JFrame("Jeb Bush is a Mess");
	int height = 685; // frame height
	int width = 800; // frame width
	int gameState = 1; // Start screen
	JButton startButton = new JButton("start");

	ImageIcon startScreen = new ImageIcon("start.jpg");

	public AppMain() {
		frame.addKeyListener(this);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(draw);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	class Drawing extends JComponent {
		public void paint(Graphics g) {
			if (gameState == 1){
				startScreen();
				g.drawImage(startScreen.getImage(), 0, 0, this);
			}else if(gameState == 2){
				
			}
 				
		}	
	}

	void startScreen() {
		JPanel jpane = new JPanel();
		jpane.setLayout(new GridLayout(1,1));
		startButton.setOpaque(true);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		jpane.add(startButton);
		frame.add(jpane);
		frame.revalidate();
		frame.repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		AppMain a = new AppMain();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == startButton){
			gameState = 2; 
		}
		frame.repaint();
	}
}
