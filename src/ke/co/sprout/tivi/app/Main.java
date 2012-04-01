/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.sprout.tivi.app;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import ke.co.sprout.tivi.resources.Settings;

/**
 *
 * @author small
 */
public class Main extends MIDlet {
    
    private static Main self;
    private Display display;
    
    public static Main getInstance() {
        return self;
    }
    
    public void startApp() {
        if (display == null) {
            self = this;
            display = Display.getDisplay(this);            
            // Load persistent settings
            Settings.load();
        }
     }
     
    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        // Save persistent settings
        Settings.save();
    }
    
    public void exit() {
        destroyApp(true);
        notifyDestroyed();
    }
}
