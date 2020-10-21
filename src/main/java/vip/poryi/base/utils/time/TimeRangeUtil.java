package vip.poryi.base.utils.time;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Map;

@Slf4j
public class TimeRangeUtil {

    /**
     * 计算 传入某一日的特定条件时间范围
     * @param localDate
     * @param typeEnum
     * @return
     */
    public static Map<String, Object> timeRangeTimestamp(LocalDate localDate, TimeTypeEnum typeEnum) {
        LocalDate startDate = null, endDate = null;
        switch (typeEnum) {
            case DAY:
                startDate = localDate;
                endDate = localDate;
                break;
            case WEEK:
                startDate = getFirstDayOfWeek(localDate);
                endDate = getLastDayOfWeek(localDate);
                break;
            case MONTH:
                startDate = getFirstDayOfMonth(localDate);
                endDate = getLastDayOfMonth(localDate);
                break;
            case YEAR:
                startDate = getFirstDayOfYear(localDate);
                endDate = getEndDayOfYear(localDate);
                break;
            default:
                log.error("time Range timestamp error! reason : [unknown time Type]");
        }
        log.info("startDate：{}, endDate:{}", LocalDateUtil.formateDefault(startDate), LocalDateUtil.formateDefault(endDate));
        return localdateToTimestamp(startDate, endDate);

    }

    private static Map<String, Object> localdateToTimestamp(LocalDate firstDate, LocalDate endDate) {
        long startTimestamp = LocalDateTime.of(firstDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endTimestamp = LocalDateTime.of(endDate, LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Map<String, Object> map = Maps.newHashMap();
        map.put("startTimestamp", startTimestamp);
        map.put("endTimestamp", endTimestamp);
        return map;
    }

    public static LocalDate getFirstDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        return localDate.with(fieldISO, 1);
    }

    public static LocalDate getLastDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(DayOfWeek.MONDAY,1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        return localDate.with(fieldISO, 7);
    }

    public static LocalDate getFirstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate getFirstDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfYear());
    }

    public static LocalDate getEndDayOfYear(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfYear());
    }
}
