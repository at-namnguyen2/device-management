package device.management.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public final class DateTimeUtils {

    /**
     * .
     */
    public static final int CONST3 = 3;
    /**
     * .
     */
    public static final int CONST4 = 4;
    /**
     * .
     */
    public static final int CONST5 = 5;
    /**
     * .
     */
    public static final int CONST6 = 6;
    /**
     * .
     */
    public static final int CONST7 = 7;
    /**
     * .
     */
    public static final int CONST8 = 8;
    /**
     * .
     */
    public static final int CONST9 = 9;
    /**
     * .
     */
    public static final int CONST10 = 10;
    /**
     * .
     */
    public static final int CONST11 = 11;
    /**
     * .
     */
    public static final int CONST12 = 12;

    /**
     * private constructor
     */
    private DateTimeUtils() {
    }

    /**
     * @param date    to convert
     * @param pattern in converting
     * @return date
     */
    public static Date convertStringToTime(String date, String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);

        } catch (ParseException e) {
            System.out.println("Date ParseException, string value:" + date);
            throw e;
        }
    }

    /**
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDate(String date) throws Exception {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        String dateTemp = "";
        dateTemp = date;
        if (dateTemp.length() <= 10) {
            dateTemp = dateTemp + " 00:00:00";
        }
        return convertStringToTime(dateTemp, pattern);
    }

    public static String String2DateFormSQL(String date) throws ParseException {

        Date initDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }

    /**
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static String convertDateToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    public static String convertDateToAPIString(Date date) {
        return convertDateToString(date, ParamUtils.yyyyMMddTHHmmssZ);
    }

    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @return String
     * @throws Exception if error
     */
    public static String getSysdate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        return convertDateToString(calendar.getTime());
    }

    /**
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime() throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * @param pattern to convert
     * @return String
     * @throws Exception if error
     */
    public static String getSysDateTime(String pattern) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static Date convertStringToDateTime(String date) throws Exception {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        return convertStringToTime(date, pattern);
    }

    /**
     * @param date to convert
     * @return String
     * @throws Exception if error
     */
    public static String convertDateTimeToString(Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param utilDate to convert
     * @return date
     */
    public static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * @param monthInput to parse
     * @return String
     */
    public static String parseDate(int monthInput) {
        String dateReturn = "01/01/";
        Calendar cal = Calendar.getInstance();
        switch (monthInput) {
        case 1:
            dateReturn = "01/01/";
            break;
        case 2:
            dateReturn = "01/02/";
            break;
        case CONST3:
            dateReturn = "01/03/";
            break;
        case CONST4:
            dateReturn = "01/04/";
            break;
        case CONST5:
            dateReturn = "01/05/";
            break;
        case CONST6:
            dateReturn = "01/06/";
            break;
        case CONST7:
            dateReturn = "01/07/";
            break;
        case CONST8:
            dateReturn = "01/08/";
            break;
        case CONST9:
            dateReturn = "01/09/";
            break;
        case CONST10:
            dateReturn = "01/10/";
            break;
        case CONST11:
            dateReturn = "01/11/";
            break;
        case CONST12:
            dateReturn = "01/12/";
            break;
        }
        return dateReturn + cal.get(Calendar.YEAR);
    }

    public static int compareDateTime(Date d1, Date d2) {
        int result = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        if (cal1.after(cal2)) {
            result = 1;
        } else if (cal1.before(cal2)) {
            result = -1;
        }
        return result;
    }

    public static String date2ddMMyyyyString(Date value) {
        if (value != null) {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
            return ddMMyyyy.format(value);
        }
        return "";
    }

    public static Date string2Date(String value) throws ParseException {
        if (!DateTimeUtils.isNullOrEmpty(value)) {
            SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy");
            return dateTime.parse(value);
        }
        return null;
    }

    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1.trim()));
    }

    public static String getSysdate(String format) {
        long time = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(time);
        return convertDateToString(date, format);
    }

    public static Date convertStringToDateOrNull(String value) {
        try {
            return convertStringToDate(value);
        } catch (Exception e) {
            System.out.println("Date ParseException " + e.getMessage());
            return null;
        }
    }

    public static Date convertStringToDateOrNull(String date, String pattern) {
        try {
            return convertStringToTime(date, pattern);
        } catch (Exception e) {
            System.out.println("Date ParseException " + e.getMessage());
            return null;
        }
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static String convertSqlTimeToIso(String yyyyMMddHHmmssSSS) throws Exception {
        Date date = yyyyMMddHHmmssSSS == null ?
                null :
                DateTimeUtils.convertStringToTime(yyyyMMddHHmmssSSS, ParamUtils.yyyyMMddHHmmssSSS);
        String dateIso = date == null ? null : DateTimeUtils.convertDateToString(date, ParamUtils.yyyyMMddTHHmmssZ);
        return dateIso;
    }

    public static String convertSqlDateToIso(String yyyyMMddHHmmssSSS) throws Exception {
        Date date = yyyyMMddHHmmssSSS == null ?
                null :
                DateTimeUtils.convertStringToTime(yyyyMMddHHmmssSSS, ParamUtils.yyyyMMddHHmmssSSS);
        String dateIso = date == null ? null : DateTimeUtils.convertDateToString(date, ParamUtils.yyyyMMdd);
        return dateIso;
    }

    public static Integer getAge(final Date birthdate) {
        return getAge(Calendar.getInstance().getTime(), birthdate);
    }

    public static int getAge(final Date current, final Date birthdate) {

        if (birthdate == null) {
            return 0;
        }
        if (current == null) {
            return getAge(birthdate);
        } else {
            final Calendar calend = new GregorianCalendar();
            calend.set(Calendar.HOUR_OF_DAY, 0);
            calend.set(Calendar.MINUTE, 0);
            calend.set(Calendar.SECOND, 0);
            calend.set(Calendar.MILLISECOND, 0);

            calend.setTimeInMillis(current.getTime() - birthdate.getTime());

            int result = 0;
            result = calend.get(Calendar.YEAR) - 1970;
            result += (int) calend.get(Calendar.MONTH) / (int) 12;
            return result;
        }

    }

    public static Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    public static Date getDateBeforeAfter(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    public static String convertDateSQLOracleToyyyyMMddTHH(String dateSql) {
        Date data = convertStringToDateOrNull(dateSql, ParamUtils.yyyyMMddHHmmss);
        return DateTimeUtils.convertDateToString(data, ParamUtils.yyyyMMddTHHmmssZ);
    }

    public static List<Date> getDatesBetween(
            Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }
}
