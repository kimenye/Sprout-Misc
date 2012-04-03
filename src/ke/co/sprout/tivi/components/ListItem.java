package ke.co.sprout.tivi.components;

import com.nokia.mid.ui.LCDUIUtil;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import ke.co.sprout.tivi.app.Main;
import ke.co.sprout.tivi.helpers.TextWrapper;
import ke.co.sprout.tivi.helpers.TouchChecker;

/**
 * CustomItem trying to look and feel like a regular list item in List screen.
 */
public class ListItem extends CustomItem {

    private int padding;
    private int prefWidth;
    private int prefHeight;
    private int index = 0;
    private boolean pressed = false;
    private final Font font = Font.getFont(Font.FONT_STATIC_TEXT);
    private Vector lines;
    private String label;
    private Vector listeners = new Vector();

    public interface Listener {

        void clicked(ListItem listItem);
    }

    public ListItem(String label, int width, int padding, int index, boolean directSelect) {
        super("");
        this.label = label;
        this.padding = padding;
        this.index = index;

        // Receive touch events without having to be set as current (selected) first.
        if (directSelect && TouchChecker.touchEnabled() && TouchChecker.s40UITraitsSupported()) {
            LCDUIUtil.setObjectTrait(this, "nokia.ui.s40.item.direct_touch", new Boolean(true));
        }
        prefWidth = width;
        wrapLabel(width);
    }

    public void paint(Graphics g, int width, int height) {
        // Get platform foreground font color
        g.setColor(Display.getDisplay(Main.getInstance()).getColor(Display.COLOR_FOREGROUND));

        // Draw wrapped strings
        int length = lines.size();
        for (int i = 0; i < length; i++) {
            String line = (String) lines.elementAt(i);
            g.drawString(line, padding, padding + i * font.getHeight(), g.TOP | g.LEFT);
        }
    }

    public int getIndex() {
        return index;
    }

    public void addListener(Listener listener) {
        listeners.addElement(listener);
    }

    public void sizeChanged(int w, int h) {
        prefWidth = w;
        wrapLabel(w);
        invalidate();
        repaint();
    }

    /**
     * Wraps label to the given width column. Affects the preferred height
     */
    protected void wrapLabel(int width) {
        lines = TextWrapper.wrapTextToWidth(label, width - 2 * padding, font);
        prefHeight = 2 * padding + lines.size() * font.getHeight();
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
    protected int getPrefContentWidth(int height) {
        return prefWidth;
    }

    /**     
     * Called by the system to retrieve preferred height for this control.
     */
    protected int getPrefContentHeight(int width) {
        return prefHeight;
    }

    protected void pointerPressed(int x, int y) {
        super.pointerPressed(x, y);
        pressed = true;
        System.out.println("pointerPressed x:" + x + ", y:" + y);
    }

    protected void pointerReleased(int x, int y) {
        super.pointerReleased(x, y);
        System.out.println("pointerReleased x:" + x + ", y:" + y);
        if (pressed && y >= 0 && y <= prefHeight) { // For Symbian, the y value needs to be checked also
            int length = listeners.size();
            for (int i = 0; i < length; i++) {
                ((Listener) listeners.elementAt(i)).clicked(this);
            }
        }
        pressed = false;
    }

    protected int getPadding() {
        return padding;
    }

    protected boolean isPressed() {
        return pressed;
    }
}
