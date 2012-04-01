/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.sprout.tivi.resources;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author small
 */
public class Settings {
   
    public static Vector favouriteChannels = new Vector();
    public static Vector favouriteShows = new Vector();
    
    public static void load() {
        
    }
    
    public static void save()
    {
        
    }    
//    private static void loadSettings() {
//        try {
//            RecordStore rs = RecordStore.openRecordStore("settings", true);
//            RecordEnumeration re = rs.enumerateRecords(null, null, true);
//
//            int id = re.nextRecordId();
//            ByteArrayInputStream bais = new ByteArrayInputStream(rs.getRecord(id));
//            DataInputStream dis = new DataInputStream(bais);
//            try {
//                windSpeedUnit = dis.readInt();
//                temperatureUnit = dis.readInt();
//            } catch (EOFException eofe) {                
//                eofe.printStackTrace();
//            }
//            rs.closeRecordStore();
//        } catch (RecordStoreException rse) {
//            rse.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
}
