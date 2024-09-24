package star.reusable.biz;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import star.reusable.bean.bo.excel.CompanyExcel;
import star.reusable.biz.generator.ExcelGenerator;
import star.reusable.dao.domain.Company;
import star.reusable.manager.CompanyManager;

/**
 * @author Luke Xie
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelBiz {

    private final ExcelGenerator excelGenerator;
    private final CompanyManager companyManager;

    public void export(HttpServletResponse response) {

        List<Company> list = companyManager.list();

        List<CompanyExcel> excelList = list.stream().map(r -> {
            var excelEntity = new CompanyExcel();
            excelEntity.setName(r.getName());
            excelEntity.setUscc(r.getUscc());

            return excelEntity;
        }).collect(Collectors.toList());

        String excelName = URLEncoder.encode("导出文件名称.xlsx", StandardCharsets.UTF_8);
        String filePath = excelGenerator.writeData2Excel(excelList, null, "导出文件名称.xlsx",
                CompanyExcel.class);
        excelGenerator.exportExcel(response, filePath, excelName);
    }

}
