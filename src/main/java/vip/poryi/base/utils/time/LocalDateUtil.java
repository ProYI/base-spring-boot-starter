package vip.poryi.base.utils.time;

import vip.poryi.base.common.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {


    public static String formateDefault(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(Constant.LOCAL_DATE_DEFAULT_FORMATE));
    }
}
