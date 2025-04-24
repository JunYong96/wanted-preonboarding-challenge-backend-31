package wanted.challenge.backend.global.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Configuration
@Component
public class SwaggerConfig {
    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @return API information
     */
    @Bean
    public OpenAPI openAPI(SwaggerProperties swaggerProperties) {

        // spring session
        // Security 스키마 설정
        SecurityScheme springSessionAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        // Security 요청 설정
        SecurityRequirement addSecurityItem = new SecurityRequirement()
                .addList("Authorization");

        return new OpenAPI()
                .info(apiInfo(swaggerProperties))
                .components(new Components().addSecuritySchemes("Authorization", springSessionAuth))
                .addSecurityItem(addSecurityItem);
    }

    private Info apiInfo(SwaggerProperties swaggerProperties) {

        String licenseName = "Apache 2.0";
        String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

        return new Info()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getApiVersion())
                .description(swaggerProperties.getDescription());
        //.license(new License().name(licenseName).url(licenseUrl));
    }
}
