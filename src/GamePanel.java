import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private InputHandler inputHandler;
    private GameMap gameMap;


    public GamePanel() {
        inputHandler = new InputHandler();
        this.addKeyListener(inputHandler);
        gameMap = new GameMap();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameMap.draw(g);
    }
}