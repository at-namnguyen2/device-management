package device.management.demo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public final class StringUtils {

    /**
     * alphabeUpCaseNumber.
     */
    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String mask = "0123456789_aAÃ¡Ã?Ã Ã€áº£áº¢áº¡áº Ã£ÃƒÃ¢Ã‚áº¥áº¤áº§áº¦áº©áº¨áº­áº¬áº«áºªÄƒÄ‚áº¯áº®áº±áº°áº³áº²áº·áº¶áºµáº´bBcCdDÄ‘Ä?eEÃ©Ã‰Ã¨Ãˆáº»áººáº¹áº¸áº½áº¼ÃªÃŠáº¿áº¾á»?á»€á»ƒá»‚á»‡á»†á»…á»„fFgGhHiIÃ­Ã?Ã¬ÃŒá»‰á»ˆá»‹IHÄ©Ä¨jJkKlLmMnNoOÃ³Ã“Ã²Ã’á»?á»Žá»?á»ŒÃµÃ•Ã´Ã”á»‘á»?á»“á»’á»•á»”á»™á»˜á»—á»–Æ¡Æ á»›á»šá»?á»œá»Ÿá»žá»£á»¢á»¡á» pPqQrRsStTuUÃºÃšÃ¹Ã™á»§á»¦á»¥á»¤Å©Å¨Æ°Æ¯á»©á»¨á»«á»ªá»­á»¬á»±á»°á»¯á»®vVwWxXyYÃ½Ã?á»³á»²á»·á»¶á»µá»´á»¹á»¸zZ";
    private static String maskEN = "0123456789_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private static final String c[] = { "<", ">" };
    private static final String expansion[] = { "&lt;", "&gt;" };

    public static String HTMLEncode(String s) {
        String sTemp = s;
        for (int j = 0; j < c.length; j++) {
            sTemp = sTemp.replace(c[j], expansion[j]);
        }
        return sTemp;
    }

    public static String HTMLDecode(String s) {
        String mine = s;
        for (int i = 0; i < c.length; i++) {
            mine.replaceAll(expansion[i], (c[i] + ""));
        }
        return mine;
    }

    /**
     * method compare two string
     *
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean compareString(String str1, String str2) {
        String str1Temp = str1;
        String str2Temp = str2;
        if (str1Temp == null) {
            str1Temp = "";
        }
        if (str2Temp == null) {
            str2Temp = "";
        }

        if (str1Temp.equals(str2Temp)) {
            return true;
        }
        return false;
    }

    /**
     * method convert long to string
     *
     * @param lng Long
     * @return String
     */
    public static String convertFromLongToString(Long lng) {
        return String.valueOf(lng);
    }

    /*
     * @todo: convert from Long array to String array
     */
    public static String[] convertFromLongToString(Long[] arrLong) throws Exception {
        String[] arrResult = new String[arrLong.length];
        for (int i = 0; i < arrLong.length; i++) {
            arrResult[i] = convertFromLongToString(arrLong[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String array to Long array
     */
    public static long[] convertFromStringToLong(String[] arrStr) throws Exception {
        long[] arrResult = new long[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arrResult[i] = Long.parseLong(arrStr[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String value to Long value
     */
    public static long convertFromStringToLong(String value) throws Exception {
        return Long.parseLong(value);
    }

    /*
     * Check String that containt only AlphabeUpCase and Number Return True if
     * String was valid, false if String was not valid
     */
    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public static boolean isValidString(Object temp) {
        if (temp == null || temp.toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isNotValidString(Object temp) {
        return !isValidString(temp);
    }

    public static boolean isValidLength(String str, int min, int max) {
        if (isNotValidString(str)) {
            return false;
        }
        int len = str.length();
        return len >= min && len <= max;
    }

    public static boolean isNotValidLength(String str, int min, int max) {
        return !isValidLength(str, min, max);
    }

    public static boolean maskVN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (mask.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean maskEN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (maskEN.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        if (str.toLowerCase().charAt(0) < 'a' || str.toLowerCase().charAt(0) > 'z') {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String str) {
        if (str == null || !str.matches("[0-9]+$")) {
            return false;
        }
        return true;
    }

    public static String formatString(String str) {
        return " '" + str.trim().toLowerCase() + "'";
    }

    public static String formatLike(String str) {
        return "%" + str.trim().toLowerCase().replaceAll("_", "\\\\_") + "%";
    }

    public static String formatOrder(String str, String direction) {
        return " NLSSORT(" + str + ",'NLS_SORT=vietnamese') " + direction;
    }

    public static String formatDate(Date date) {
        // return " to_date('" + DateTimeUtils.convertDateToString(date,
        // ParamUtils.ddMMyyyy) + "', '" + ParamUtils.ddMMyyyy + "')";
        return DateTimeUtils.convertDateToString(date, ParamUtils.ddMMyyyy);
    }

    public static String formatFunction(String function, String str) {
        return " " + function + "(" + str + ") ";
    }

    public static String formatConstant(String str) {
        String str1 = "";
        int index = 0;
        String alphabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i < str.length(); i++) {
            if (alphabe.indexOf(str.charAt(i)) > 0) {
                str1 = str1 + str.substring(index, i).toUpperCase() + "_";
                index = i;
            }
        }
        str1 = str1 + str.substring(index, str.length()).toUpperCase() + "_";
        return str1;
    }

    public static boolean isLong(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isBoolean(String str) {
        try {
            Boolean.valueOf(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean containSpecialCharacteristic(String str) {
        if (str == null) {
            return false;
        }
        List<String> lstSpecialCharacteristic = new ArrayList<String>();
        lstSpecialCharacteristic.add("!");
        lstSpecialCharacteristic.add("@");
        lstSpecialCharacteristic.add("#");
        lstSpecialCharacteristic.add("%");
        lstSpecialCharacteristic.add("^");
        lstSpecialCharacteristic.add("&");
        lstSpecialCharacteristic.add("*");
        lstSpecialCharacteristic.add("(");
        lstSpecialCharacteristic.add(")");
        lstSpecialCharacteristic.add(" ");
        for (int i = 0; i < lstSpecialCharacteristic.size(); i++) {
            if (str.contains(lstSpecialCharacteristic.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public static String upperFirstChar(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1.trim()));
    }

    public static boolean isLongNullOrEmpty(String obj1) {
        return (obj1 == null || "0L".equals(obj1));
    }

    public static boolean isDoubleNullOrEmpty(String obj1) {
        return (obj1 == null || "0D".equals(obj1));
    }

    //

    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || obj1.toString().trim().equals("");
    }

    public static Boolean convertStringToBooleanOrNull(String input) {
        try {
            return Boolean.valueOf(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static Long convertStringToLongOrNull(String input) {
        try {
            return Long.valueOf(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer convertStringToIntegerOrNull(String input) {
        try {
            return Integer.valueOf(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static Float convertStringToFloatOrNull(String input) {
        try {
            return Float.valueOf(input);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertObjectToString(Object input) {
        return input == null ? null : input.toString();

    }

    public static String convertDateToString(Date input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = dateFormat.format(input).toString();
        return date;

    }

    public static String buildSelectQuery(String[] attribute, String[] table) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append("select ");
        selectQuery.append(String.join(", ", attribute));
        selectQuery.append(" from ");
        selectQuery.append(String.join(", ", table));
        return selectQuery.toString();
    }

    public static String createFullName(String lastName, String midName, String firstName) {

        StringBuilder fullName = new StringBuilder();

        fullName.append(lastName == null ? "" : lastName + " ");
        fullName.append(midName == null ? "" : midName + " ");
        fullName.append(firstName == null ? "" : firstName);

        return fullName.toString().trim();
    }

    public static String createAdress(String adress1, String adress2) {

        StringBuilder adress = new StringBuilder();

        adress.append(adress1 == null ? "" : adress1 + " ");
        adress.append(adress2 == null ? "" : adress2);

        return adress.toString().trim();
    }

    public static String createFullAddress(String street, String wards, String district, String city, String province) {
        StringBuilder fullAddress = new StringBuilder("");
        fullAddress.append(street == null || "".equals(street) ? "" : street + ", ");
        fullAddress.append(wards == null || "".equals(wards) ? "" : wards + ", ");
        fullAddress.append(district == null || "".equals(district) ? "" : district + ", ");
        fullAddress.append(city == null || "".equals(city) ? "" : city + ", ");
        fullAddress.append(province == null || "".equals(province) ? "" : province + ", ");
        if (fullAddress.length() > 2) {
            fullAddress.replace(fullAddress.length() - 2, fullAddress.length() - 1, "");
        }
        return fullAddress.toString();
    }

    public static String createFullAddress(String number, String street, String wards, String district, String city,
            String province) {
        StringBuilder fullAddress = new StringBuilder("");
        fullAddress.append(number == null || "".equals(number) ? "" : number + " ");
        fullAddress.append(street == null || "".equals(street) ? "" : street + ", ");
        fullAddress.append(wards == null || "".equals(wards) ? "" : wards + ", ");
        fullAddress.append(district == null || "".equals(district) ? "" : district + ", ");
        fullAddress.append(province == null || "".equals(province) ? "" : province + ", ");
        if (fullAddress.length() > 2) {
            fullAddress.replace(fullAddress.length() - 2, fullAddress.length() - 1, "");
        }
        return fullAddress.toString();
    }

    public static Boolean convertStringToBoolean(String input) throws Exception {
        if (input != null) {
            if (input.equals(Boolean.TRUE.toString()))
                return true;
            if (input.equals(Boolean.FALSE.toString()))
                return false;
        }
        throw new Exception();
    }

    public static String buildSelectQuery(String[] attributes, String[] tables, String[] conditions) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attributes));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", tables));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", conditions));
        return selectQuery.toString();
    }

    public static String buildSelectQuery(List<String> attribute, List<String> table, List<String> condition) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attribute));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", table));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", condition));
        return selectQuery.toString();
    }

    public static String buildSelectQuery(List<String> attributes, List<String> tables, List<String> conditions,
            List<String> orderBys) {
        return buildSelectQuery(attributes, tables, conditions) + " ORDER BY " + String.join(" AND ", orderBys);
    }

    public static String buildSelectQuery(String[] attribute, String[] table, String[] condition, String orderByValue) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attribute));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", table));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", condition));
        selectQuery.append(" ORDER BY " + orderByValue);
        return selectQuery.toString();
    }

    public static String buildSelectQuery(String[] attributes, String[] tables, String[] conditions, String[] groupBy,
            String orderByValue) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attributes));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", tables));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", conditions));
        selectQuery.append(" GROUP BY ");
        selectQuery.append(String.join(", ", groupBy));
        selectQuery.append(" ORDER BY " + orderByValue);
        return selectQuery.toString();
    }

    public static String buildSelectQuery(String[] attributes, String[] tables, String[] conditions, String[] groupBy) {
        StringBuilder selectQuery = new StringBuilder();
        selectQuery.append(" SELECT ");
        selectQuery.append(String.join(", ", attributes));
        selectQuery.append(" FROM ");
        selectQuery.append(String.join(" ", tables));
        selectQuery.append(" WHERE ");
        selectQuery.append(String.join(" AND ", conditions));
        selectQuery.append(" GROUP BY ");
        selectQuery.append(String.join(", ", groupBy));

        return selectQuery.toString();
    }

    public static String buildGroupByQuery(String[] groups) {
        StringBuilder groupByQuery = new StringBuilder();
        groupByQuery.append(" GROUP BY ");
        groupByQuery.append(String.join(", ", groups));
        return groupByQuery.toString();
    }

    public static boolean isStringAscii(String str) {
        for (char ch : str.toCharArray()) {
            if (!isAscii(ch)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAscii(char ch) {
        return ch < 128;
    }

    /**
     * @param lastName  (example: evaluator.lastName)
     * @param midName   (example: evaluator.midName)
     * @param firstName (example: evaluator.firstName)
     * @return string example: ((case when evaluator.lastName is null then '' else
     * evaluator.lastName end) || ' ' || (case when evaluator.midName is
     * null then '' else evaluator.midName end) || ' ' || (case when
     * evaluator.firstName is null then '' else evaluator.firstName end))
     */
    public static String buildFullNameSQL(String lastName, String midName, String firstName) {
        StringBuilder fullName = new StringBuilder();
        fullName.append("((case when ");
        fullName.append(lastName);
        fullName.append(" is null then '' else ");
        fullName.append(lastName);
        fullName.append(" end) || (case when ");
        fullName.append(midName);
        fullName.append(" is null then '' else ");
        fullName.append("CONCAT(' ', " + midName + ")");
        fullName.append(" end) || (case when ");
        fullName.append(firstName);
        fullName.append(" is null then '' else ");
        fullName.append("CONCAT(' ', " + firstName + ")");
        fullName.append(" end))");
        return fullName.toString();
    }

    public static String buildAdressSQL(String adress1, String adress2) {
        StringBuilder adress = new StringBuilder();
        adress.append("((case when ");
        adress.append(adress1);
        adress.append(" is null then '' else ");
        adress.append(adress1);
        adress.append(" end) || (case when ");
        adress.append(adress2);
        adress.append(" is null then '' else ");
        adress.append("CONCAT(' ', " + adress2 + ")");
        adress.append(" end))");
        return adress.toString();
    }

    public static String buildFullNameSQLCastToVarchar(String lastName, String midName, String firstName) {
        return rawCastToVarchar(buildFullNameSQL(lastName, midName, firstName));
    }

    public static String buildFullNameSQLWithLower(String lastName, String midName, String firstName) {
        return toSQLLower(buildFullNameSQL(lastName, midName, firstName));
    }

    /**
     * @param street   (example: ct.streetNameOfResidence)
     * @param wards    (example: ct.wardsOfResidence)
     * @param district (example: ct.districtOfResidence)
     * @param city     (example: ct.cityOfResidence)
     * @param province (example: ct.provinceOfResidence)
     * @return string example: ((case when ct.streetNameOfResidence is null then ''
     * else ct.streetNameOfResidence end) || ', ' || (case when
     * ct.wardsOfResidence is null then '' else ct.wardsOfResidence end) ||
     * ', ' || (case when ct.districtOfResidence is null then '' else
     * ct.districtOfResidence end) || ', ' || (case when ct.cityOfResidence
     * is null then '' else ct.cityOfResidence end) || ', ' || (case when
     * ct.provinceOfResidence is null then '' else ct.provinceOfResidence
     * end))
     */
    public static String buildFullAddressSQL(String street, String wards, String district, String city,
            String province) {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append("((case when ");
        fullAddress.append(street);
        fullAddress.append(" is null then '' else ");
        fullAddress.append(street);
        fullAddress.append(" end) || (case when ");
        fullAddress.append(wards);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(wards);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(district);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(district);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(city);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(city);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(province);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(province);
        fullAddress.append(") end))");
        return fullAddress.toString();
    }

    public static String buildFullAddressSQL(String number, String street, String wards, String district, String city,
            String province) {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append("((case when ");
        fullAddress.append(number);
        fullAddress.append(" is null then '' else ");
        fullAddress.append(number + " ");
        fullAddress.append(" end) || (case when ");
        fullAddress.append(street);
        fullAddress.append(" is null then '' else ");
        fullAddress.append(street);
        fullAddress.append(" end) || (case when ");
        fullAddress.append(wards);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(wards);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(district);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(district);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(city);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(city);
        fullAddress.append(") end) || (case when ");
        fullAddress.append(province);
        fullAddress.append(" is null then '' else concat(', ', ");
        fullAddress.append(province);
        fullAddress.append(") end))");
        return fullAddress.toString();
    }

    public static String buildFullAddressSQLCastToVarchar(String street, String wards, String district, String city,
            String province) {
        return rawCastToVarchar(buildFullAddressSQL(street, wards, district, city, province));
    }

    public static String buildFullAddressSQLCastToVarchar(String number, String street, String wards, String district,
            String city,
            String province) {
        return rawCastToVarchar(buildFullAddressSQL(number, street, wards, district, city, province));
    }

    public static String buildFullAddressSQLLower(String street, String wards, String district, String city,
            String province) {
        return toSQLLower(buildFullAddressSQL(street, wards, district, city, province));
    }

    public static String buildFullAddressSQLLower(String number, String street, String wards, String district,
            String city,
            String province) {
        return toSQLLower(buildFullAddressSQL(number, street, wards, district, city, province));
    }

    public static String rawCastToVarchar(String key) {
        return "utl_raw.cast_to_varchar2((nlssort(lower(" + key + "),'nls_sort=binary_ai')))";
    }

    public static String toSQLLower(String field) {
        return new StringBuilder("lower(").append(field).append(")").toString();
    }

    public static String likeString(String key, String param) {
        return "(" + key + " LIKE " + param + ")";
    }

    public static String makeSortVietnameseSQL(String firstField, String secondField, String firstFieldDirection) {
        StringBuilder sortString = new StringBuilder();
        sortString.append("NLSSORT(");
        sortString.append(firstField);
        sortString.append(", 'nls_sort = Vietnamese') ");
        sortString.append(firstFieldDirection);
        sortString.append(", ");
        sortString.append(secondField);
        return sortString.toString();
    }

    public static String makeSortTwoLevel(String firstField, String secondField, String firstFieldDirection) {
        StringBuilder sortString = new StringBuilder();
        sortString.append(firstField);
        sortString.append(" ");
        sortString.append(firstFieldDirection);
        sortString.append(", ");
        sortString.append(secondField);
        return sortString.toString();
    }

    public static String makeSortThreeLevelVietnameseSQL(String firstField, String secondField, String thirdField,
            String firstFieldDirection) {
        StringBuilder sortString = new StringBuilder();
        sortString.append("NLSSORT(");
        sortString.append(firstField);
        sortString.append(", 'nls_sort = Vietnamese') ");
        sortString.append(firstFieldDirection);
        sortString.append(", ");
        sortString.append(secondField);
        sortString.append(", ");
        sortString.append(thirdField);
        return sortString.toString();
    }

    public static String makeSortThreeLevel(String firstField, String secondField, String thirdField,
            String firstFieldDirection) {
        StringBuilder sortString = new StringBuilder();
        sortString.append(firstField);
        sortString.append(" ");
        sortString.append(firstFieldDirection);
        sortString.append(", ");
        sortString.append(secondField);
        sortString.append(", ");
        sortString.append(thirdField);
        return sortString.toString();
    }

    public static String[] splitFullName(String fullName) {
        String[] result = new String[3];
        if (fullName != null) {
            String[] splittedFullname = fullName.split(" ");
            StringBuilder midNameBuilder = new StringBuilder();
            for (int i = 0; i < splittedFullname.length; i++) {
                if (i == 0) {
                    result[0] = splittedFullname[i];
                } else if (i == splittedFullname.length - 1) {
                    result[2] = splittedFullname[i];
                } else {
                    midNameBuilder.append(splittedFullname[i]).append(" ");
                }
            }
            result[1] = midNameBuilder.toString().trim();
        }
        return result;
    }

    public static Double convertStringToDoubleOrNull(String amountNumber) {
        try {
            return Double.valueOf(amountNumber);
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal convertStringToBigDecimalOrNull(String number) {
        try {
            return new BigDecimal(number);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
                "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean containsOnlyNumbers(String str) {
        String regex = "[0-9]+";
        boolean b = str.matches(regex);
        return b;
    }

    public static String convertDoubleToStringOrNull(Double value) {
        return value == null ? null : new DecimalFormat("#").format(new Double(value.toString()));
    }

    public static String generateFileName() {
        Random rand = new Random();
        int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
        String dateString = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return "image-upload-" + dateString + randomNum + ".jpg";
    }

    public static String buildListIdSQL(List<String> ids) {
        StringBuilder eCondition = new StringBuilder();

        List<String> idsNotNull = new ArrayList<String>();
        if (ids != null && ids.size() > 0) {
            StringBuilder idConditions = new StringBuilder("");
            Long staffId;
            for (String id : ids) {
                staffId = StringUtils.convertStringToLongOrNull(id);
                if (null != staffId)
                    idsNotNull.add(id);
            }

            idConditions.append(String.join(",", idsNotNull));
            if (idConditions.length() > 0) {
                idConditions.insert(0, " s.id in (");
                idConditions.append(")");

                eCondition.append(idConditions);
            }
        }
        return eCondition.toString();
    }

    public static String removeInvalidAddressChars(String inputAddress) {
        if (!isValidString(inputAddress)) {
            return inputAddress;
        }

        inputAddress = inputAddress.replaceAll("(^[,\\s]+)|([,\\s]+$)", "");
        inputAddress = inputAddress.replaceAll("[,\\s]+[,\\s]+", ",").replaceAll(",", ", ");

        return inputAddress;
    }

    public static Boolean checkTime(String value) {
    	Boolean check= value.matches("^(([0-1]\\d)|(2[0-3])):[0-5]\\d$");
		return check;
	}
    /**
     * method convert int to string
     *
     * @param no Int
     * @return String
     */
    public static String convertFromIntToString(Integer no) {
    	return String.valueOf(no);
    }
}
