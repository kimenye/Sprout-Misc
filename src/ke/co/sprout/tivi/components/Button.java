/*
 * Copyright © 2011 Nokia Corporation. All rights reserved.
 * Nokia and Nokia Connecting People are registered trademarks of Nokia Corporation.
 * Oracle and Java are trademarks or registered trademarks of Oracle and/or its
 * affiliates. Other product and company names mentioned herein may be trademarks
 * or trade names of their respective owners.
 * See LICENSE.TXT for license information.
 */
package ke.co.sprout.tivi.components;

import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Button extends Sprite {

    private boolean enabled = true;
    private boolean pressed = false;
    private Vector listeners = new Vector();
    private Image releasedImg;
    private Image pressedImg;

    public interface Listener {

        void clicked(Button button);
        void selected(Button button);
        void deselected(Button button);
    }

    public Button(Image released, Image pressed) {
        super(released);
        releasedImg = released;
        pressedImg = pressed;
    }

    /**
     * Handles the pressed event
     */
    public boolean touchDown(int x, int y) {
        if (isVisible() && enabled && contains(x, y)) {
            pressed = true;
            updateImage();
            return true;
        }
        return false;
    }

    /**
     * Handles the released event
     */
    public boolean touchUp(int x, int y) {
        if (pressed) {
            pressed = false;
            updateImage();
            if (isVisible() && enabled && contains(x, y)) {
                notifyClicked();
                return true;
            }
        }
        return false;
    }

    public boolean contains(int x, int y) {
        return x >= getX() && x <= getX() + getWidth()
                && y >= getY() && y <= getY() + getHeight();
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }

    public void addListener(Listener listener) {
        if (!listeners.contains(this)) {
            listeners.addElement(listener);
        }
    }

    public void setPressedImg(Image pressedImg) {
        this.pressedImg = pressedImg;
        updateImage();
    }

    public void setReleasedImg(Image releasedImg) {
        this.releasedImg = releasedImg;
        updateImage();
    }

    public boolean isSeleted() {
        return pressed;
    }

    public void select() {
        if (!pressed) {
            toggle();
        }
    }

    public void deselect() {
        if (pressed) {
            toggle();
        }
    }

    public void toggle() {
        pressed = !pressed;
        updateImage();
        if(pressed) {
            notifySelected();
        } else {
            notifyDeselected();
        }
    }

    public void notifyClicked() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            ((Listener) listeners.elementAt(i)).clicked(this);
        }
    }

    public void notifySelected() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            ((Listener) listeners.elementAt(i)).selected(this);
        }
    }

    public void notifyDeselected() {
        int length = listeners.size();
        for (int i = 0; i < length; i++) {
            ((Listener) listeners.elementAt(i)).deselected(this);
        }
    }

    private void updateImage() {
        if (pressed) {
            setImage(pressedImg, pressedImg.getWidth(), pressedImg.getHeight());
        } else {
            setImage(releasedImg, releasedImg.getWidth(), releasedImg.getHeight());
        }
    }
}
