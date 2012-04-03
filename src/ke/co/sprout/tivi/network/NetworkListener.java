package ke.co.sprout.tivi.network;

/**
 * Interface for reporting network operations.
 */
public interface NetworkListener {

    /**
     * Called when a HTTP POST request is done.
     * @param response Server reply
     */
    void networkHttpPostResponse(String response);

    /**
     * Called when a HTTP GET request is done.
     * @param response Server reply
     */
    void networkHttpGetResponse(String response);
}
