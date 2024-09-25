package star.reusable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import star.reusable.biz.ExcelBiz;

/**
 * @author Luke Xie
 */
@Tag(name = "API - Excel")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/excels")
public class ExcelController {

    private final ExcelBiz excelBiz;

    @Operation(
            summary = "导出",
            description = "查询数据后直接导出<br>"
                    + "流程：查询数据 - 构造Excel文件 - 导出Excel"
    )
    @GetMapping
    public void export(HttpServletResponse response) {
        excelBiz.export(response);
    }

}

