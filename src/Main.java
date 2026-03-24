import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
*RPGゲームである、十字キーでキャラクターを動かす仕組みを作成してください
*/

public class Main extends JPanel implements KeyListener{
	
	private GameMap gameMap = new GameMap(); //GameMap.javaを追加(アップデート1)
	private boolean isGameCleared = false;
	
	//キャラの座標
	private int x = 50;
	private int y = 50;
	private final int CHARSIZE = 32;
	private BufferedImage playerImage;
	
	public Main() {
	  //ウィンドウの設定
	  JFrame frame = new JFrame();
	  frame.setSize(400, 400);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.add(this);
	  frame.addKeyListener(this);
	  frame.setVisible(true);
	  try {
		  Path imagePath = Path.of("ref/pipo-charachip029a.png");
		  BufferedImage fullSheet = ImageIO.read(imagePath.toFile());
		  this.playerImage = fullSheet.getSubimage(0, 0, 32, 32); 
	  } catch (IOException e) {
		  System.err.println("画像が見つかりません。四角で代用します: " + e.getMessage());
	  }
	}
	
	// 画面を描くメソッド
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// マップにお願いして描いてもらう（中身のループ処理は見えなくてOK！）
	    gameMap.draw(g); //GameMap.javaを追加(アップデート1)
	    
		//キャラクターを描く
	    if (playerImage != null) {
	    	g.drawImage(playerImage, x, y, CHARSIZE, CHARSIZE, null);
	    } else {
	    	g.setColor(Color.BLUE);
			g.fillRect(x, y, CHARSIZE, CHARSIZE);
	    }
		
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		int speed = 10;
		
		// ★ ゴール後は操作禁止 ★
	    if (isGameCleared) {
	        return;  // ← ここでメソッド終了
	    }

		if(key == KeyEvent.VK_LEFT) {
			int nextX = x - speed;
			boolean topLeft = (gameMap.isWall(nextX, y) == false);
	        boolean bottomLeft = (gameMap.isWall(nextX, y + CHARSIZE - 1) == false);
			if (topLeft && bottomLeft) {
				x = nextX;
			}
		}
			
		if(key == KeyEvent.VK_RIGHT) {
			int nextX = x + speed;
			boolean topRight = (gameMap.isWall(nextX + CHARSIZE - 1, y) == false);
	        boolean bottomRight = (gameMap.isWall(nextX + CHARSIZE - 1, y + CHARSIZE - 1) == false);
			if (topRight && bottomRight) {
				x = nextX;
			}
		}
		
		if(key == KeyEvent.VK_UP) {
			int nextY = y - speed;
			boolean topLeft = (gameMap.isWall(x, nextY) == false);
	        boolean topRight = (gameMap.isWall(x + CHARSIZE - 1, nextY) == false);
			if (topLeft && topRight) {
				y = nextY;
			}
		}
		
		if(key == KeyEvent.VK_DOWN) {
			int nextY = y + speed;
			boolean bottomLeft = (gameMap.isWall(x, nextY + CHARSIZE - 1) == false);
	        boolean bottomRight = (gameMap.isWall(x + CHARSIZE - 1, nextY + CHARSIZE - 1) == false);
			if (bottomLeft && bottomRight) {
				y = nextY;
			}
		}
		
		// ★ ゴール判定 ★
		if (gameMap.isGoal(x, y)) {
			isGameCleared = true;
		    javax.swing.JOptionPane.showMessageDialog(this, "ゴールしました！");
		}

		repaint();
	}
	
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	
	public static void main(String[] args) {
		new Main();
	}
}