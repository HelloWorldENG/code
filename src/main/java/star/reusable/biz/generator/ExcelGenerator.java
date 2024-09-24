package star.reusable.biz.generator;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Luke Xie
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelGenerator {

    @Value("${application.web.fileTemPath}")
    private String fileTemPath;

    /**
     * @param data 导出数据
     * @param fileName 文件名
     * @param clazz 导出类
     * @return 下载url
     */
    public String writeData2Excel(List data, String filePath, String fileName, Class clazz) {
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        if (filePath == null) {
            filePath = fileTemPath + fileName;
            File fileDefaultPath = new File(fileTemPath);
            if (!fileDefaultPath.exists()) {
                fileDefaultPath.mkdirs();
            }
        } else {
            filePath = filePath + fileName;
        }
        File file = new File(filePath);
        log.info("Excel导出文件路径: {}", filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            EasyExcel.write(filePath, clazz)
                    .excelType(ExcelTypeEnum.XLSX)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .sheet("sheet1")
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出文件失败: ", e);
            throw new RuntimeException("导出失败");
        }
        return filePath;
    }

    public void exportExcel(HttpServletResponse response, String filePath, String excelName) {

        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream()) {

            long fileSize = file.length();
            // 设置响应头
            response.setHeader("Content-Disposition", "attachment; filename=" + excelName);
            response.setContentType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 设置内容类型为Excel类型
            response.setContentLength((int) fileSize);
            // 使用流式复制
            IOUtils.copy(fis, outputStream);
            response.setHeader("Content-Length", String.valueOf(fileSize));
            response.getOutputStream().write(fis.readAllBytes());
        } catch (IOException e) {
            log.error("excel导出异常", e);
            throw new RuntimeException("excel导出服务繁忙");
        }
    }

}

