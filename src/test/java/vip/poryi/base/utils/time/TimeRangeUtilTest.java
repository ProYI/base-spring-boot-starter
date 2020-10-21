package vip.poryi.base.utils.time;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

@Slf4j
class TimeRangeUtilTest {
    @Test
    public void testTimeRangeTimestamp() {
        LocalDate localDate = LocalDate.now();
        log.info("start day type! {}", LocalDateUtil.formateDefault(localDate));
        Map<String, Object> dayMap = TimeRangeUtil.timeRangeTimestamp(localDate, TimeTypeEnum.DAY);
        try {
            log.info("day type result : {}", new ObjectMapper().writeValueAsString(dayMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, Object> weekMap = TimeRangeUtil.timeRangeTimestamp(localDate, TimeTypeEnum.WEEK);
        try {
            log.info("week type result : {}", new ObjectMapper().writeValueAsString(weekMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, Object> monthMap = TimeRangeUtil.timeRangeTimestamp(localDate, TimeTypeEnum.MONTH);
        try {
            log.info("month type result : {}", new ObjectMapper().writeValueAsString(monthMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, Object> yearMap = TimeRangeUtil.timeRangeTimestamp(localDate, TimeTypeEnum.YEAR);
        try {
            log.info("year type result : {}", new ObjectMapper().writeValueAsString(yearMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}