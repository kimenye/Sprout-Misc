package ke.co.sprout.tivi.app;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import ke.co.sprout.tivi.resources.Settings;
import ke.co.sprout.tivi.views.ViewMaster;

public class Main extends MIDlet {
    
    private static Main self;
    private Display display;
    private ViewMaster viewMaster;
    
    public static Main getInstance() {
        return self;
    }
    
    public void startApp() {
        if (display == null) {
            self = this;
            display = Display.getDisplay(this);            
            // Load persistent settings
            Settings.load();
            
            // Initiate view master and let it handle the views
            viewMaster = ViewMaster.getInstance();
            viewMaster.setDisplay(display);
            viewMaster.openView(ViewMaster.VIEW_HOME);
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
