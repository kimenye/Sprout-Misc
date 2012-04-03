package ke.co.sprout.tivi.network;

/**
 * Abstract class for network operations. 
 */
public abstract class NetworkOperation implements NetworkListener {

    /**
     * Starts the operation in asynchronous manner.
     */
    public abstract void start();

    /**
     * Default implementation for interface callback.
     * @param response
     */
    public void networkHttpPostResponse(String response) {
        System.out.println("Post response IGNORED");
    }

    /**
     * Default implementation for interface callback.
     * @param response
     */
    public void networkHttpGetResponse(String response) {
        System.out.println("Get response IGNORED");
    }
}
