package JebBushIsAMess;

import java.awt.Color;
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
	JButton startButton = new JButton("");
	JPanel jpane = new JPanel();
	ImageIcon startScreen = new ImageIcon("start.jpg");
	ImageIcon background = new ImageIcon("background.jpg");
	ImageIcon gameOver = new ImageIcon("gameover.jpg");

<<<<<<< 257d218e8886fee1b90ecf1b48d5008b7d602e92
	static List<Enemy> enemies = new CopyOnWriteArrayList<>();
	static Player player = new Player(10, 450, width);
	static finalBosos finalBoss = new finalBosos(300, 500);
=======
	static List<Enemy> enemies;
	static Player player;
>>>>>>> added hillary and shit

	TTimer timer;

	int gameState = 1; // Start screen
	int level;

	void reset(){
		enemies = new CopyOnWriteArrayList<>();
		player = new Player(10, 450, width);
		level = 1;
		
	}
	
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
				g.setColor(Color.black);
				g.drawString("Level: " + level, 10, 700);
				player.drawPlayer(g, this);
				for (Enemy e : enemies)
					e.drawEnemy(g, this);
				finalBoss.drawBoss(g, this);
			} else if (gameState == 3) {
				g.drawImage(gameOver.getImage(), 0, 0, this);
			}
		}

	}
	
	
	public void nextLevel(){
		makeEnemies(++level);
	}
	public void start() {
		timer = new TTimer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (timer) {
					if(enemies.size() == 0){
						nextLevel();
					}
					player.move();
					for (Enemy s : enemies)
						if (s.doSomething(player.x_pos, player.y_pos))
							player.decrementHealth();

					for (Bullet b : Player.getBullets()) {
						for (Enemy s : enemies) {
							if (b.checkHit(s.x, s.y, s.width, s.height)) {
								enemies.remove(s);
								Player.getBullets().remove(b);
							}
						}
						if (b.fly(width))
							Player.getBullets().remove(b);
					}
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
		reset();
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
		jpane.setLayout(new GridLayout(1, 1));
		startButton.setOpaque(true);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(this);
		jpane.add(startButton);
		frame.add(jpane);
		frame.requestFocus();
	}

	void drawGameOver() {
		gameState = 3;
		frame.revalidate();
		frame.repaint();
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
				player.setYSpeed(50	);
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
