package ke.co.sprout.tivi.network;

import java.util.Vector;

/**
 * Operation for retrieving location data.
 */
public class GetChannelsOperation extends NetworkOperation {
//    TODO: url? 
    final static String SERVICE_URL = "some-url";
    final static String SERVICE_PARAMS = "some-params";
    private Listener listener;
    private String query = "";

    /**
     * Listener interface
     */
    public interface Listener {

        /**
         * Returns the retrieved channels or null in case of an error.
         * @param comments
         */
        public void channelsReceived(Vector channels);
    }

    /**
     * Cosntructor
     * @param listener
     * @param query Preferred form "<city>"
     */
    public GetChannelsOperation(Listener listener, String query) {
        this.listener = listener;
        this.query = query;
    }

    /**
     * Starts the operation.
     */
    public void start() {
        new Thread() {

            public void run() {
                startNetwork();
            }
        }.start();
    }

    /**
     * Initiates a new GET request
     */
    private void startNetwork() {
        // Search from static city list in TEST MODE
//        if (Main.getInstance().isInTestMode()) {
//            Vector locations = new Vector();
//            int length = Resources.CITIES.length;
//            for (int i = 0; i < length - 1; i += 2) {
//                // Returns always at least the last location in the list (Testham)
//                if (Resources.CITIES[i].startsWith(query) || i == length - 2) {
//                    Location location = new Location();
//                    location.city = Resources.CITIES[i];
//                    location.country = Resources.CITIES[i + 1];
//                    locations.addElement(location);
//                }
//            }
//            listener.locationsReceived(locations);
//            return;
//        }
//
//        // Start the http request
//        Network nw = new Network(this);
//        nw.startHttpGet(SERVICE_URL + "?q=" + query + "&" + SERVICE_PARAMS);
    }

    /**
     * Callback for GET request
     * @param response
     */
    public void networkHttpGetResponse(String response) {
        parseChannels(response);
    }

    /**
     * Parses the server response and calls the listener.
     * @param response
     */
    public void parseChannels(String response) {
        System.out.println("response: "+response);
        try {
            ChannelsParser parser = new ChannelsParser();
            parser.parse(response);

            Vector channels = parser.getChannels();
            listener.channelsReceived(channels);
        } catch (ParseError pe) {
            listener.channelsReceived(null);
        }
    }
}
