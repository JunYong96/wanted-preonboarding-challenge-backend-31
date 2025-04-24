package wanted.challenge.backend.global.swagger;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
public class SwaggerProperties {
    static Logger logger = LoggerFactory.getLogger(SwaggerProperties.class);

    @Value("${springdoc.apiVersion}")
    private String apiVersion;

    @Value("${springdoc.title}")
    private String title;

    @Value("${springdoc.description}")
    private String description;

    @Value("${springdoc.api-docs.enabled}")
    private boolean enabled = true;

    @Value("#{'${springdoc.paths-to-exclude:{}}'.split(',')}")
    private List<String> exceptPath;
}
