package star.reusable.config;

import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.SAXException;

/**
 * @author Luke Xie
 */
@Configuration
@RequiredArgsConstructor
public class TikaConfig {

    private final ResourceLoader resourceLoader;

    @Bean
    public Tika tika() throws TikaException, IOException, SAXException {

        Resource resource = resourceLoader.getResource("classpath:tika-config.xml");
        InputStream inputStream = resource.getInputStream();

        org.apache.tika.config.TikaConfig config = new org.apache.tika.config.TikaConfig(inputStream);
        Detector detector = config.getDetector();
        Parser autoDetectParser = new AutoDetectParser(config);

        return new Tika(detector, autoDetectParser);
    }
}
