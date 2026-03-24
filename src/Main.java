import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
*RPGゲームである、十字キーでキャラクターを動かす仕組みを作成してください
*/

public class Main extends JPanel{
	
	private GameMap gameMap = new GameMap(); //GameMap.javaを追加(アップデート1)
	private boolean isGameCleared = false;
	private InputHandler inputHandler;
	
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
	  inputHandler = new InputHandler();
	  frame.addKeyListener(inputHandler);
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

	    playerMover.move(this, gameMap, inputHandler);
		
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