/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.sprout.tivi.views;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import ke.co.sprout.tivi.helpers.TextWrapper;
import ke.co.sprout.tivi.resources.Visual;

/**
 *
 * @author small
 */
public class HomeView extends GameCanvas implements View {
    
    private boolean closing = false;
    private Graphics g;
    private String message = "";
    private String caption = "";
    private Vector lines = new Vector();
    private Visual v;
            
    public HomeView() {
        super(false);
        this.setFullScreenMode(false);
        setTitle("TiVi");

        g = getGraphics();
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
