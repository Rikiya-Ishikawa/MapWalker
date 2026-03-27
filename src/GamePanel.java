import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private InputHandler inputHandler;

    public GamePanel() {
        inputHandler = new InputHandler();
        this.addKeyListener(inputHandler);
    }
}