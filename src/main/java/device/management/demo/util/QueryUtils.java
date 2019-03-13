package device.management.demo.util;

import java.util.Date;
import java.util.List;

public class QueryUtils {

	 public static String like(String key, String value) {
	        return "(" + key + " LIKE '%" + value + "%')";
	    }

	    public static String likeWithAsciiAndLower(String key, String value) {
	        if (StringUtils.isStringAscii(value)) {
	            return like(StringUtils.rawCastToVarchar("lower(" + key + ")"), value.trim().toLowerCase());
	        } else {
	            return like("lower(" + key + ")", value.trim().toLowerCase());
	        }
	    }

	    public static String likeArgument(String key, String argument) {
	        return "(" + key + " LIKE '%:" + argument + "%')";
	    }

	    public static String equalValue(String key, String value) {
	        return "(" + key + " = " + value + ")";
	    }

	    public static String equal(String key, String argument) {
	        return "(" + key + " = :" + argument + ")";
	    }

	    public static String toChar(String col) {
	        return "TO_CHAR(" + col + ", 'DD-MM-YYYY')";
	    }

	    public static String formatDate(Date date) {
	        return DateTimeUtils.convertDateToString(date, "dd-MM-yyyy");
	    }
	    
	    public static String notLike(String key, String value) {
	        return "(" + key + " NOT LIKE '%" + value + "%')";
	    }
}
