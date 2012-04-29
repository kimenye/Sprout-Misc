package ke.co.sprout.tivi.resources;

import javax.microedition.lcdui.Font;

/**
 * Singleton class containing visual theme of the application
 */
public class Visual {

    // Modes
    public static final int DAY_MODE = 0;
    public static final int NIGHT_MODE = 1;

    //Colors
    public final int COLOR_BACKGROUND;
    public final int COLOR_PRIMARY_TEXT;
    public final int COLOR_SECONDARY_TEXT;
    public final int COLOR_TERTIARY_TEXT;
    public final int COLOR_ERROR_TEXT;
    public final int COLOR_MESSAGE_TEXT;
    public final int COLOR_HEADER_TEXT;

    //Fonts
    public final Font FONT_SMALL = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    public final Font FONT_SMALL_BOLD = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    public final Font FONT_MEDIUM = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    public final Font FONT_MEDIUM_BOLD = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    public final Font FONT_LARGE = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    public final Font FONT_LARGE_BOLD = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    
    //Other    
    public final int HEIGHT_HEADER = (int) (FONT_MEDIUM_BOLD.getHeight() * 1.4);
    protected static Visual self = null;
    protected static int currentMode = DAY_MODE;

    private Visual() {
        // Common for both modes
        COLOR_ERROR_TEXT = 0xff3300;
        COLOR_MESSAGE_TEXT = 0x888888;

        if (currentMode == DAY_MODE) {
            COLOR_BACKGROUND = 0x45a4e7;
            COLOR_PRIMARY_TEXT = 0x333333;
            COLOR_SECONDARY_TEXT = 0x555555;
            COLOR_TERTIARY_TEXT = 0x888888;
            COLOR_HEADER_TEXT = 0x333333;
        } else {
            COLOR_BACKGROUND = 0x00002f;
            COLOR_PRIMARY_TEXT = 0xffffff;
            COLOR_SECONDARY_TEXT = 0x888888;
            COLOR_TERTIARY_TEXT = 0x444444;
            COLOR_HEADER_TEXT = 0xffffff;
        }
    }

    /**
     * Returns and/or instantiates a new Visual instance according to current mode
     * @return Visual theme
     */
    public static Visual getInstance() {
        if (self == null) {
            self = new Visual();
        }
        return self;
    }

    /**
     * Returns and/or instantiates a new Visual instance with specified mode
     * @return Visual theme
     */
    public static Visual getInstance(int mode) {
        if (mode != currentMode) {
            currentMode = mode;
            self = new Visual();
        }
        return self;
    }

    public int getMode() {
        return currentMode;
    }
}
