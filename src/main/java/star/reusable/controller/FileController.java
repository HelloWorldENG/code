package star.reusable.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import star.reusable.biz.FileBiz;

/**
 * @author Luke Xie
 */
@Tag(name = "API - 文件处理")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileBiz fileBiz;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "解析文件内容",
            description = "上传文件并使用Apache Tika解析文件内容<br>"
                    + "支持解析：doc、docx、ppt、pptx、xls、xlsx、pdf、txt、md、xml、json、html、css、js、java、sql、properties、ini、yaml、yml等文件"
    )
    public String parseFileContent(@RequestParam("file") MultipartFile file) {
        return fileBiz.parseFileContent(file);
    }
}
