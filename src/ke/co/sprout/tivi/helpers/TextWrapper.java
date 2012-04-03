package ke.co.sprout.tivi.helpers;

import java.util.Vector;
import javax.microedition.lcdui.Font;

/**
 * Utility for wrapping text to a certain width. 
 */
public class TextWrapper {

    /**
     * Wraps text
     * @param text Text to be wrapped
     * @param wrapWidth Max width of one line in pixels
     * @param font Font to be used in calculating 
     * @return Wrapped line strings in a vector
     */
    public static Vector wrapTextToWidth(String text, int wrapWidth, Font font) {
        Vector lines = new Vector();

        int start = 0;
        int position = 0;
        int length = text.length();
        while (position < length - 1) {
            start = position;
            int lastBreak = -1;
            int i = position;
            for (; i < length && font.stringWidth(text.substring(position, i)) <= wrapWidth; i++) {
                if (text.charAt(i) == ' ') {
                    lastBreak = i;
                } else if (text.charAt(i) == '\n') {
                    lastBreak = i;
                    break;
                }
            }
            if (i == length) {
                position = i;
            } else if (lastBreak <= position) {
                position = i;
            } else {
                position = lastBreak;
            }

            lines.addElement(text.substring(start, position));

            if (position == lastBreak) {
                position++;
            }
        }

        return lines;
    }
}
