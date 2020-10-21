package vip.poryi.base.utils.time;

public enum TimeTypeEnum {
    YEAR(1,"本年"),
    MONTH(2,"本月"),
    WEEK(3,"本周"),
    DAY(4,"本日"),
    ;
    private Integer code;
    private String  desc;

    TimeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static TimeTypeEnum getTimeTypeEnum(Integer code) {
        for (TimeTypeEnum e : TimeTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
