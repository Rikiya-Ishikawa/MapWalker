import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private final Map<Integer, Boolean> keys = new HashMap<>();



    @Override
    public void keyPressed(KeyEvent e) {
    	
    	//確認用
    	System.out.println("keyPressed: " + e.getKeyCode());

        keys.put(e.getKeyCode(), true);
    }


    @Override
    public void keyReleased(KeyEvent e) {
    	
    	//確認用
    	System.out.println("keyReleased: " + e.getKeyCode());

        keys.put(e.getKeyCode(), false);
    }

    public boolean isPressed(int keyCode) {
        return keys.getOrDefault(keyCode, false);
    }


    @Override public void keyTyped(KeyEvent e) {}

}