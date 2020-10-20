package vip.poryi.base.model.poi;

import lombok.Data;

@Data
public class ImportErrorResponse {
    //导入数据行数
    private Integer rowNumber;
    //错误消息
    private String message;
}
