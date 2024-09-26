package star.reusable.biz;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Luke Xie
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileBiz {
    private final Tika tika;

    public String parseFileContent(MultipartFile file) {

        try {
            return tika.parseToString(file.getInputStream());
        } catch (TikaException | IOException e) {
            log.error("解析文件内容出错: {}", e.getMessage());
        }

        return null;
    }

}
