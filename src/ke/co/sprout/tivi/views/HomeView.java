/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.sprout.tivi.views;

import javax.microedition.lcdui.Form;

/**
 *
 * @author small
 */
public class HomeView extends Form implements View {
    
    private boolean closing = false;
            
    public HomeView() {
        super("TiVi");
    }
    
    public void activate() {
        
    }
    
    public void deactivate() {
        
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
