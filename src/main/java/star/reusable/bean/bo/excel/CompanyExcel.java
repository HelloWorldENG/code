package star.reusable.bean.bo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Luke Xie
 */
@Data
public class CompanyExcel {

    @ExcelProperty(value = "企业名称", index = 0)
    private String name;

    @ExcelProperty(value = "统一社会信用代码", index = 1)
    private String uscc;

}