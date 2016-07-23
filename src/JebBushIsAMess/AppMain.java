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
	ImageIcon winScreen = new ImageIcon("winned.jpg");
	static finalBosos finalBoss;
	static List<Enemy> enemies;
	static Player player;
	static Hillary hillaryPlane; 

	TTimer timer;

	int gameState = 1; // Start screen
	int level;
	boolean win;

	final int BOSS_LEVEL = 10;

	void reset() {
		finalBoss = new finalBosos(300, 500);
		enemies = new CopyOnWriteArrayList<>();
		player = new Player(10, 450, width);
		hillaryPlane = new Hillary(0,70, 0);
		level = 1;
		win = false;

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

				hillaryPlane.drawHillary(g, this); 
				
				finalBoss.drawBoss(g, this);

				if (level == BOSS_LEVEL && finalBoss.isAlive()) {
					finalBoss.drawBoss(g, this);
				}
			} else if (gameState == 3) {

				if (win)
					g.drawImage(winScreen.getImage(), 0, 0, this);
				else
					g.drawImage(gameOver.getImage(), 0, 0, this);
			}
		}

	}

	public void nextLevel() {
		if (level < BOSS_LEVEL)
			makeEnemies(++level);
	}

	public void start() {
		timer = new TTimer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (timer) {
					if (level < BOSS_LEVEL && enemies.size() == 0) {
						nextLevel();
					} else if (level == BOSS_LEVEL && !finalBoss.isAlive()) {
						win = true;
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

					
					hillaryPlane.doSomething();
					if (hillaryPlane.hitGround){
						hillaryPlane = new Hillary(0, 70, 0); 
					}


					if (level == BOSS_LEVEL) {


						if (finalBoss.doSomething(player.x_pos, player.y_pos)) {
							player.decrementHealth();
						}
						for (Bullet b : Player.getBullets()) {
							if (b.checkHit(finalBoss.x, finalBoss.y, finalBoss.width, finalBoss.height)) {
								System.out.println("GIT");
								finalBoss.decrementHealth();
								Player.getBullets().remove(b);
							}
							if (b.fly(width))
								Player.getBullets().remove(b);
						}
					}
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

			while (!win && !player.getIsDead()) {
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
		frame.requestFocus();
	}

	void drawGameOver() {
		gameState = 3;
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
				player.setYSpeed(50);
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
