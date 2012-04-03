/*
 * Copyright © 2011 Nokia Corporation. All rights reserved.
 * Nokia and Nokia Connecting People are registered trademarks of Nokia Corporation. 
 * Oracle and Java are trademarks or registered trademarks of Oracle and/or its
 * affiliates. Other product and company names mentioned herein may be trademarks
 * or trade names of their respective owners. 
 * See LICENSE.TXT for license information.
 */

package ke.co.sprout.tivi.network;

import java.util.Vector;
import javax.microedition.location.Location;
import ke.co.sprout.tivi.models.Channel;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 * Parses the server locations response.
 */
public class ChannelsParser extends Parser {

    /**
     * Vector of result forecasts.
     */
    private Vector channels = new Vector();

    public Vector getChannels() {
        return channels;
    }

    /**
     * Parses JSON response
     * @param response JSON response as string
     * @throws ParseError
     */
    public void parse(String response) throws ParseError {
//        try {            
//            JSONObject obj = new JSONObject(response);
//            if (obj.isNull("search_api")) {
//                return;
//            }
//            JSONObject data = obj.getJSONObject("search_api");            
//            JSONArray results = data.getJSONArray("result");
//            int length = results.length();
//            for (int i = 0; i < length; ++i) {
//                channels.addElement(parseChannel(results.getJSONObject(i)));
//            }
//        } catch (Exception e) {
//            throw new ParseError(e.getMessage());
//        }
    }


    private Channel parseChannel(JSONObject channel) throws JSONException {
//        Location l = new Location();
//        JSONArray city = location.getJSONArray("areaName");
//        l.city = city.getJSONObject(0).getString("value");
//
//        JSONArray country = location.getJSONArray("country");
//        l.country = country.getJSONObject(0).getString("value");
//
//        l.latitude = location.getString("latitude");
//        l.latitude = location.getString("longitude");
//
//        return l;
        Channel c = null;
        
        return c;
    }
}
