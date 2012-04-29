package ke.co.sprout.tivi.components;

import java.util.Stack;
import ke.co.sprout.tivi.views.ViewMaster;

public class ViewStack {

    private Stack stack = new Stack();

    /**
     * Add a view to the stack.
     * @param viewId
     */
    public void pushView(int id) {
        // Remove the view from viewstack to prevent stack size growing too big
        stack.removeElement(new Integer(id));
        // Push the view id into viewstack.
        stack.push(new Integer(id));
    }

    /**
     * Returns the top most view from the stack.
     */
    public int popView() {
        if (!stack.isEmpty()) {
            int viewId = ((Integer) stack.pop()).intValue();
            return viewId;
        }
        return ViewMaster.VIEW_NOVIEW;
    }
}
