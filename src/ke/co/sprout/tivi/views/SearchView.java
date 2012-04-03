package ke.co.sprout.tivi.views;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.location.Location;
import ke.co.sprout.tivi.components.ListItem;
import ke.co.sprout.tivi.network.GetChannelsOperation;

/**
 * Search view with autocomplete functionality 
 */
public class SearchView extends Form implements View, ItemStateListener, ItemCommandListener, ListItem.Listener,
        GetChannelsOperation.Listener {

    private final Command selectCmd = new Command("Select", Command.ITEM, 1);
    private Display display;
    private TextField searchField;
    private String lastSearch = "";
    private Timer throttle;
    private Vector locations;

    public SearchView() {
        super("Search show");
        throttle = new Timer();
        searchField = new TextField(null, "", 50, TextField.NON_PREDICTIVE & TextField.INITIAL_CAPS_WORD);
        append(searchField);
        setItemStateListener(this);
    }

    public void setDisplay(Display d) {
        this.display = d;
    }

    /**
     * Performs a location search, if text input changes.
     * If list item state changes, current location gets set accordingly.
     * @param item
     */
    public void itemStateChanged(Item item) {
        if (item instanceof TextField && !searchField.getString().equals(lastSearch)) {
            throttleSearch();
        }
    }

    /**
     * Delays location search and cancels a possible pending search
     */
    public void throttleSearch() {
        if (throttle != null) {
            throttle.cancel();
        }

        throttle = new Timer();
        throttle.schedule(new TimerTask() {

            public void run() {
                searchLocations();
                cancel();
            }
        }, 1000, 2000);
    }

    /**
     * Performs a location search with the current input string
     */
    public void searchLocations() {
//        String searchText = searchField.getString();
//        if (!searchText.equals("") && searchText.length() > 2) {
//            clearList();
//            append(new LoaderItem());
//            // Create new GET request
//            new GetLocationsOperation(this, searchText).start();
//        }
    }

    public void clearList() {
        while (size() > 1) {
            delete(1);
        }
    }

    public void activate() {
        searchField.setString("");
        clearList();
        if (display != null) {
            display.setCurrentItem(searchField);
        }
//        if (!Network.isAllowed()) {
//            setTicker(new Ticker("No network access. Please restart and allow network access."));
//        }
    }

    public void deactivate() {
    }

    /**
     * Handles the select command related to a list item
     * @param c
     * @param item
     */
    public void commandAction(Command c, Item item) {
        if (item instanceof ListItem) {
            Location location = (Location) locations.elementAt(((ListItem) item).getIndex());
            if (location != null) {
//                Settings.setLocation(location);
                ViewMaster.getInstance().backView();
            }
        }
    }

    public void clicked(ListItem listItem) {
        commandAction(selectCmd, listItem);
    }

    public synchronized void channelsReceived(Vector channelsData) {
//        clearList();
//        if (locationsData == null) {
//            ViewMaster.getInstance().showAlert("Network error", "Sorry, cannot connect to internet.", AlertType.INFO);
//        } else if (!locationsData.isEmpty()) {
//            if (TouchChecker.touchEnabled()) {
//                append(new DividerItem(getWidth()));
//            }
//            locations = locationsData;
//            int length = locationsData.size();
//            for (int i = 0; i < length; i++) {
//                Location location = (Location) locationsData.elementAt(i);
//
//                // Create new result item
//                ListItem listItem = new ListItem(location.city + ", " + location.country, getWidth(), 10, i, true);
//                listItem.addListener(this);
//                listItem.setDefaultCommand(selectCmd);
//                listItem.setItemCommandListener(this);
//                append(listItem);
//            }
//        }
    }
}
