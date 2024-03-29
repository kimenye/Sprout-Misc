/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.sprout.tivi.views;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import ke.co.sprout.tivi.components.Banner;
import ke.co.sprout.tivi.helpers.TextWrapper;
import ke.co.sprout.tivi.resources.Resources;
import ke.co.sprout.tivi.resources.Visual;

/**
 *
 * @author small
 */
public class HomeView extends GameCanvas implements View {
    
    private boolean closing = false;
    private Graphics g;
    private Resources r;
    private Visual v;
    private Banner banner;
    private String message = "";
    private String caption = "";
    private Vector lines = new Vector();
    
    private Sprite background;
            
    public HomeView() {
        super(false);
        this.setFullScreenMode(false);
        setTitle("TiVi");
        
        g = getGraphics();
        r = Resources.getInstance(getWidth(), getHeight());
        v = Visual.getInstance();
        initialize();
        
        setMessage("Now Showing");
    }
    
    private void initialize() {
        // Initialize banner to prevent null pointer exceptions
        banner = new Banner(Image.createImage(1, 1), Image.createImage(1, 1), -1, -1);
    }
    
    public void activate() {
        
    }
    
    public void deactivate() {
        
    }
    
    private void setMessage(String msg) {
        if (!closing) {
            message = msg;
            lines = TextWrapper.wrapTextToWidth(msg, getWidth() / 10 * 9, v.FONT_MEDIUM);
        }
    }
    
    public void render() {
        g.setColor(v.COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        background.paint(g);
    }

    /**
     * @return the closing
     */
    public boolean isClosing() {
        return closing;
    }

    /**
     * @param closing the closing to set
     */
    public void setClosing(boolean closing) {
        this.closing = closing;
    }
}
