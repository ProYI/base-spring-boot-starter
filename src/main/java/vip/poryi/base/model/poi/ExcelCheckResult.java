package vip.poryi.base.model.poi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("导入文件验证结果对象")
public class ExcelCheckResult<T> {
    @ApiModelProperty("文件导入数量")
    private Integer importNum;

    @ApiModelProperty("验证成功数量")
    private Integer successNum;

    @ApiModelProperty("验证失败数量")
    private Integer errorNum;

    @ApiModelProperty("验证失败原因")
    private List<ImportErrorResponse> errorReason;

    @ApiModelProperty("成功数据")
    private List<T> successInfo;

    public static <T> ExcelCheckResult<T> createExcelCheckResult(List<T> successList, List<ImportErrorResponse> errorList) {
        Integer importNum = successList.size() + errorList.size();

        ExcelCheckResult<T> excelCheckResult = new ExcelCheckResult<T>();
        excelCheckResult.setImportNum(importNum);
        excelCheckResult.setSuccessNum(successList.size());
        excelCheckResult.setErrorNum(errorList.size());
        excelCheckResult.setErrorReason(errorList);
        excelCheckResult.setSuccessInfo(successList);
        return excelCheckResult;
    }

}
