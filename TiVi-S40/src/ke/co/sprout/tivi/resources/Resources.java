package ke.co.sprout.tivi.resources;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 * Singleton class responsible of loading resources.
 */
public class Resources {


    private static Resources self = null;
    private String resourcePath;
    private Image weatherIcon;
    private Image humidity;
    private Image termometer;
    private Image windDirection;
    private Image background;
    private Image loader;
    private Image nextUp;
    private Image nextDown;
    private Image prevUp;
    private Image prevDown;
    private Image checkedIcon;
    private Image cloudsBack;
    private Image cloudsFront;

    /**
     * Loads resources and returns singleton instance of the Resources class
     * @param width Screen width
     * @param height Screen height
     * @return Resources
     */
    public static Resources getInstance(int width, int height) {
        if (self == null) {
            self = new Resources(width, height);
        }
        return self;
    }

    /**
     * Loads resources according to the screen resolution
     * @param width
     * @param height
     */
    private Resources(int width, int height) {
        boolean high = Math.max(width, height) > 160;

        if (high) {
            resourcePath = "/high/";
        } else {
            resourcePath = "/low/";
        }

//        updateMode(Visual.DAY_MODE);
//        humidity = loadImage("humidity.png");
//        termometer = loadImage("termometer.png");
//        loader = loadImage("loader.png");
//        checkedIcon = loadImage("current_location.png");
//        nextDown = loadImage("next_pressed.png");
//        prevDown = loadImage("previous_pressed.png");
//        cloudsBack = loadImage("clouds_back.png");
//        cloudsFront = loadImage("clouds_front.png");
    }

    /**
     * Updates images according to mode
     * @param mode Visual.DAY_MODE or Visual.NIGHT_MODE
     */
    final public void updateMode(int mode) {
        String modeSuffix = mode == Visual.DAY_MODE ? "day" : "night";        
        nextUp = loadImage("next_" + modeSuffix + ".png");        
        prevUp = loadImage("previous_" + modeSuffix + ".png");        
        windDirection = loadImage("wind_direction_" + modeSuffix + ".png");
        background = loadImage("bg_" + modeSuffix + ".gif");        
    }

    public Image getHumidity() {
        return humidity;
    }

    public Image getTermometer() {
        return termometer;
    }

    public Image getWindDirection() {
        return windDirection;
    }

    public Image getBackground() {
        return background;
    }

    public Image getLoader() {
        return loader;
    }

    public Image getNextUp() {
        return nextUp;
    }

    public Image getPrevUp() {
        return prevUp;
    }

    public Image getNextDown() {
        return nextDown;
    }

    public Image getPrevDown() {
        return prevDown;
    }

    public Image getCloudsBack() {
        return cloudsBack;
    }

    public Image getCloudsFront() {
        return cloudsFront;
    }

    public Image getWeatherIcon() {
        return weatherIcon;
    }

    /**
     * Loads and returns weather icon with specified filename
     * @param filename Filename of the icon
     * @return Loads icon or null if file does not exists
     */
    public Image getWeatherIcon(String filename) {
        weatherIcon = loadImage(filename);
        return weatherIcon;
    }

    public Image getCheckedIcon() {
        return checkedIcon;
    }

    public void clearWeatherIcon() {
        weatherIcon = null;
    }

    /**
     * Loads an image specified by file name
     * @param fileName
     * @return Loaded image or null if file does not exist
     */
    private Image loadImage(String fileName) {
        try {
            return Image.createImage(resourcePath + fileName);
        } catch (IOException e) {
            return null;
        }
    }
}
