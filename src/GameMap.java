import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameMap {
	private int[][] map = {
		    {1, 1, 1, 1, 1, 1, 1, 1},
		    {1, 0, 0, 0, 0, 0, 0, 1},
		    {1, 0, 1, 1, 1, 1, 0, 1},
		    {1, 0, 1, 0, 0, 0, 0, 1},
		    {1, 0, 1, 0, 1, 1, 1, 1},
		    {1, 0, 0, 0, 0, 0, 0, 1},
		    {1, 1, 1, 0, 1, 1, 2, 1},
		    {1, 1, 1, 1, 1, 1, 1, 1}
		};
	
	private final int TILE_SIZE = 50;
	private BufferedImage  wallImage;
	
	public GameMap() {
        try {
            // 画像の読み込みを試みる
            // URL先のグレーレンガ画像を保存し、resフォルダにwall.pngとして配置してください
            wallImage = ImageIO.read(new File("ref/trak2_wall1c.tga.preview.jpg"));
        } catch (IOException e) {
            // ファイルがない、パスが違う等のエラー時に動く
            System.err.println("壁画像の読み込みに失敗しました。");
            wallImage = null; 
        }
    }
	
	//マップを描画する専用メソッド
	public void draw(Graphics g) {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				if(map[row][col] == 1) {
					// 【変更箇所】画像があれば画像を描画、なければ従来の黒塗りにする
                    if (wallImage != null) {
                        // 第4, 5引数にTILE_SIZEを指定することで、大きな画像も50x50に収まります
                        g.drawImage(wallImage, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                    } else {
                        // フォールバック（画像がない場合の保険）
                        g.setColor(Color.BLACK);
                        g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
				}
				
				// ★★★ ゴール描画を追加 ★★★
	            if (map[row][col] == 2) {
	                g.setColor(Color.YELLOW);
	                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	            }
			}
		}
	}
	
	
	//境界や壁を判定するメソッド(アップデート2)
	public boolean isWall(int x, int y) {
		if(x < 0 || x >= 400 || y < 0 || y >= 400) {
			return true;
		}
		
		// 座標を配列のインデックス(0〜7)に変換する
	    // ここでJavaが自動的に計算してくれます
	    int col = x / TILE_SIZE; 
	    int row = y / TILE_SIZE;

	    // 配列のその場所が 1 なら true（壁だ！）と答える
	    return map[row][col] == 1;
	}
	
	public boolean isGoal(int x, int y) {
	    int col = x / TILE_SIZE;
	    int row = y / TILE_SIZE;

	    return map[row][col] == 2;
	}

}



//ImageIOって古いクラスでは。最新の上位互換があるのでは？
//ImageIOってBufferedImageクラスの実装クラスなんですか
//toFile()メソッドはどのクラスのメソッドですか？
//wallImage = ImageIO.read(new File("res/wall.png"));について調べる