package ke.co.sprout.tivi.components;


import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import ke.co.sprout.tivi.app.Main;

/**
 * CustomItem trying to look and feel like a regular list item in List screen.
 */
public class DividerItem extends CustomItem {

    private int prefWidth;
    private int prefHeight;

    public DividerItem(int width) {
        super(null);
        prefWidth = width;
        prefHeight = 1;
    }

    public void paint(Graphics g, int width, int height) {
        // Get platform foreground font color
        g.setColor(Display.getDisplay(Main.getInstance()).getColor(Display.COLOR_BORDER));
        g.drawLine(0, 0, width, 0);
    }

    /**
     * Called by the system to retrieve minimum width required for this control.
     */
    protected int getMinContentWidth() {
        return prefWidth;
    }

    /**
     * Called by the system to retrieve minimum height required for this control.
     */
    protected int getMinContentHeight() {
        return prefHeight;
    }

    /**
     * Called by the system to retrieve preferred width for this control.
     */
    protected int getPrefContentWidth(int arg0) {
        return prefWidth;
    }

    /**
     * Called by the system to retrieve preferred height for this control.
     */
    protected int getPrefContentHeight(int arg0) {
        return prefHeight;
    }

    protected boolean traverse(int dir, int viewportWidth, int viewportHeight, int[] visRect_inout) {
        return false;
    }

    protected void sizeChanged(int w, int h) {
        prefWidth = w;
        super.sizeChanged(w, h);
    }
}
