package io.septem150.xeric.server.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@Slf4j
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuerUri) {
    String authorizationUrl = UriComponentsBuilder.fromUriString(issuerUri).pathSegment("protocol", "openid-connect", "auth").build().toUriString();
    String tokenUrl = UriComponentsBuilder.fromUriString(issuerUri).pathSegment("protocol", "openid-connect", "token").build().toUriString();
    return new OpenAPI()
            .info(new Info()
                    .title("Project Xeric")
                    .version("1")
                    .description("Resource server for the Project Xeric RuneLite plugin")
                    .contact(new Contact()
                            .name("Carly Mullins")
                            .email("carson.mullins@proton.me")
                            .url("https://github.com/Septem151"))
                    .license(new License().name("BSD 2-Clause").url("https://choosealicense.com/licenses/bsd-2-clause/")))
            .components(new Components().addSecuritySchemes("keycloak",
                    new SecurityScheme()
                            .type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows()
                                    .authorizationCode(new OAuthFlow()
                                            .authorizationUrl(authorizationUrl)
                                            .tokenUrl(tokenUrl)))));
  }

}
