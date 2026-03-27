import javax.swing.JFrame;

public class Main {


    public Main() {

        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel());
        frame.setVisible(true);

    }

    
    public static void main(String[] args) {
        new Main();
    }
}