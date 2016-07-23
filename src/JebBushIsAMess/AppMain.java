package JebBushIsAMess;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain implements KeyListener, ActionListener {

	Drawing draw = new Drawing();
	JFrame frame = new JFrame("Jeb Bush is a Mess");
	int height = 685; // frame height
	static int width = 800; // frame width
	JButton startButton = new JButton("start");
	JPanel jpane = new JPanel();
	ImageIcon startScreen = new ImageIcon("start.jpg");
	ImageIcon background = new ImageIcon("background.jpg");
	ImageIcon gameOver = new ImageIcon("start.jpg");

	static List<Enemy> enemies = new CopyOnWriteArrayList<>();
	static Player player = new Player(10, 450, width);
	static finalBosos finalBoss = new finalBosos(300, 500);
	static Hillary hillaryPlane = new Hillary(100, 300, 0); 

	TTimer timer;

	int gameState = 1; // Start screen
	int level = 5;

	public AppMain() {
		frame.addKeyListener(this);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(draw);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.requestFocus();
	}

	class Drawing extends JComponent {
		public void paint(Graphics g) {
			if (gameState == 1) {
				drawStartScreen();
				g.drawImage(startScreen.getImage(), 0, 0, this);
			} else if (gameState == 2) {
				g.drawImage(background.getImage(), 0, 0, this);
				player.drawPlayer(g, this);
				for (Enemy e : enemies)
					e.drawEnemy(g, this);
				
				hillaryPlane.drawHillary(g, this); 
				finalBoss.drawBoss(g, this);
			} else if (gameState == 3) {
				g.drawImage(gameOver.getImage(), 0, 0, this);
			}
		}

	}

	public void start() {
		timer = new TTimer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (timer) {
					player.move();
					for (Enemy s : enemies)
						if (s.doSomething(player.x_pos, player.y_pos))
							player.decrementHealth();

					for (Bullet b : Player.getBullets()) {
						for (Enemy s : enemies) {
							if (b.checkHit(s.x, s.y, s.width, s.height)) {
								System.out.println("GIT");
								enemies.remove(s);
								Player.getBullets().remove(b);
							}
						}
						if (b.fly(width))
							Player.getBullets().remove(b);
					}
					
					hillaryPlane.doSomething();
					finalBoss.doSomething(player.x_pos);

				}

				draw.repaint();
			}
		});
		timer.start(); // pauses immediately

	}

	// Timer Class
	// handles the falling of the block timer and in game mechanic thread
	public class TTimer extends Thread {
		long delay;
		boolean pause = false;
		boolean fast = false;
		ActionListener a;

		public TTimer(long adelay, ActionListener aa) {
			delay = adelay;
			a = aa;
		}

		public void run() {

			int i = 0;
			while (!player.getIsDead()) {
				try {
					sleep(100);
				} catch (Exception e) {

				}
				if (!pause)
					a.actionPerformed(null);

			}
			drawGameOver();
		}
	}

	// Counter class (in game time)
	// used to track the time ingame

	void startGame() {
		start();
		drawGameBoard();
		makeEnemies(level);
	}

	void drawStartScreen() {
		jpane.setLayout(new GridLayout(1, 1));
		startButton.setOpaque(true);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		jpane.add(startButton);
		frame.add(jpane);
		frame.revalidate();
		frame.repaint();
	}

	void drawGameBoard() {
		frame.remove(jpane);
		frame.requestFocus();
	}

	void drawGameOver() {

	}

	void makeEnemies(int level) {
		enemies.clear();
		for (int i = 0; i < level; i++) {
			System.out.println("made " + i);
			int xLoc = (int) (Math.random() * (width / 2 - 20)) + (width / 2);
			enemies.add(new Enemy(xLoc, 1, width));
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.setXSpeed(-10);
			break;
		case KeyEvent.VK_RIGHT:
			player.setXSpeed(10);
			break;
		case KeyEvent.VK_UP:
			if (!player.jumping)
				player.setYSpeed(20);
			break;

		case KeyEvent.VK_SPACE:
			player.shootABullet();
			break;

		}

		frame.revalidate();
		frame.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton && gameState != 2) {
			gameState = 2;
			startGame();
		}
		frame.revalidate();
		frame.repaint();

	}

	public static void main(String[] args) {
		AppMain a = new AppMain();
	}

}
