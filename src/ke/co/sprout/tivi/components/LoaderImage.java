/*
 * Copyright © 2011 Nokia Corporation. All rights reserved.
 * Nokia and Nokia Connecting People are registered trademarks of Nokia Corporation.
 * Oracle and Java are trademarks or registered trademarks of Oracle and/or its
 * affiliates. Other product and company names mentioned herein may be trademarks
 * or trade names of their respective owners.
 * See LICENSE.TXT for license information.
 */

package ke.co.sprout.tivi.components;

import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class LoaderImage extends Sprite {

    private Timer animationTimer;

    public LoaderImage(Image image, int x, int y, int frameWidth, int frameHeight) {
        super(image, frameWidth, frameHeight);
        defineReferencePixel(frameWidth / 2, frameHeight / 2);
        setRefPixelPosition(x, y);
        show();
    }

    public final synchronized void show() {
        // Cancel old timer to prevent speeding up
        if (animationTimer != null) {
            animationTimer.cancel();
        }
        animationTimer = new Timer();
        animationTimer.schedule(new TimerTask() {

            public void run() {
                nextFrame();
            }
        }, 50, 100);
        setVisible(true);
    }

    public final synchronized void hide() {
        setVisible(false);
        if (animationTimer != null) {
            animationTimer.cancel();
            animationTimer = null;
        }
    }
}
