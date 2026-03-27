import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private InputHandler inputHandler;
    private GameMap gameMap;
    private Player player;


    public GamePanel() {
        inputHandler = new InputHandler();
        this.addKeyListener(inputHandler);
        gameMap = new GameMap();
        player = new Player(50, 50);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameMap.draw(g);
        player.draw(g);
    }
}