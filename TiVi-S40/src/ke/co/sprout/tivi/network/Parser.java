
package ke.co.sprout.tivi.network;

import java.util.Calendar;

/**
 * Parser abstract class to be extended by specific parsers.
 */
public abstract class Parser {

    public abstract void parse(String response) throws ParseError;

    /**
     * Convert the ISO8601 to a more readable date representation.
     * @param text Date string in ISO8601
     * @return
     */
    protected String convertDate(String text) {
        //01234567890123456
        //20100520T11:53:54
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(text.substring(0, 4)).intValue());
        cal.set(Calendar.MONTH, Integer.valueOf(text.substring(4, 6)).intValue() - 1); // note -1
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(text.substring(6, 8)).intValue());
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(text.substring(9, 11)).intValue());
        cal.set(Calendar.MINUTE, Integer.valueOf(text.substring(12, 14)).intValue());
        cal.set(Calendar.SECOND, Integer.valueOf(text.substring(15, 17)).intValue());

        // "dow mon dd hh:mm:ss zzz yyy"
        String s = cal.getTime().toString();

        //     "dow "             "hh:mm"                      "dd"
        return s.substring(0, 4) + s.substring(11, 16) + ", " + s.substring(8, 10) + ". " + s.substring(4, 7);
    }
}
