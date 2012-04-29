package ke.co.sprout.tivi.components;

import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Image;

public class Banner extends Button {

    private boolean appearing = false;
    private boolean disappearing = false;
    private int originY = 0;
    private int destY = 0;
    private Timer animator;
    private Timer timeout;
    

    public Banner(Image released, Image pressed, int originY, int destY) {
        super(released, pressed);
        this.originY = originY;
        this.destY = destY;
        setVisible(false);
        animator = new Timer();
        timeout = new Timer();
    }

    public void show(final int duration) {
        if (isVisible()) {
            return;
        }
        appearing = true;
        setVisible(true);
        animator.cancel();
        animator = new Timer();
        animator.schedule(new TimerTask() {

            public void run() {
                int dy = (int) (0.9 * (getY() - destY));
                setPosition(getX(), destY + dy);
                if (dy == 0) {
                    timeout.schedule(new TimerTask() {

                        public void run() {
                            hide();
                        }
                    }, duration);
                    this.cancel();
                    appearing = false;
                }
            }
        }, 0, 60);
    }

    // Similar to the show animation, but instead shortens the radius
    public void hide() {
        if (!isVisible() || disappearing) {
            return;
        }
        disappearing = true;
        animator.cancel();
        animator = new Timer();
        animator.schedule(new TimerTask() {

            public void run() {
                int dy = (int) ((getY() - destY + 1) * 1.1);
                int y = destY + dy;
                setPosition(getX(), y);
                if (y > originY) {
                    setPosition(getX(), originY);
                    this.cancel();
                    setVisible(false);
                    disappearing = false;
                    if (isSeleted()) {
                        notifyDeselected();
                    }
                }
            }
        }, 0, 60);
    }

    public int getOrigin() {
        return originY;
    }

    public void setOrigin(int y) {
        originY = y;
    }

    public int getDestination(int y) {
        return destY;
    }

    public void setDestination(int y) {
        destY = y;
    }

    public boolean isAppearing() {
        return appearing;
    }

    public boolean isDisappearing() {
        return disappearing;
    }
}
