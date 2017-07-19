import javax.swing.UIManager;


public class Motor {

    static public void main(String[] arg) {
    	try {
    	    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
        new RedBlackTreeFrame();
  }
}
