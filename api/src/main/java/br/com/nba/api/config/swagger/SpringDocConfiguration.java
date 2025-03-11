package br.com.nba.api.config.swagger;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

public class SpringDocConfiguration {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NBA API")
                        .description("API para gerenciamento de jogos da NBA")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Código no GitHub")
                                .url("https://github.com/Pedro-H-Gregorio/NBA_api"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));
    }
}
