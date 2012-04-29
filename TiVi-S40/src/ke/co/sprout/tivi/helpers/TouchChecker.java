
package ke.co.sprout.tivi.helpers;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class TouchChecker extends Canvas {

    private static TouchChecker self;
    private static boolean traitsChecked = false;
    private static boolean traitsSupported;

    protected void paint(Graphics g) {
    }

    private TouchChecker() {
    }

    /**
     * Checks wheter pointer events are supported
     */
    public static boolean touchEnabled() {
        if (self == null) {
            self = new TouchChecker();
        }
        return self.hasPointerEvents();
    }

    /**
     * Checks whether LCDUIUtil is available and thus UI traits also
     */
    public static boolean s40UITraitsSupported() {
        if (!traitsChecked) {
            try {
                Class.forName("com.nokia.mid.ui.LCDUIUtil");
                traitsSupported = true;
            } catch (NoClassDefFoundError ncdfe) {
                traitsSupported = false;
            } catch (Exception e) {
                traitsSupported = false;
            }
        }
        return traitsSupported;
    }
}
