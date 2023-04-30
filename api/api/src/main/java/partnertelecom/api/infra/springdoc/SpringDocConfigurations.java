package partnertelecom.api.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info()
                        .title("PartnerLab API")
                        .description("API Rest da aplicação PartnerLab, contendo as funcionalidades de CRUD de tecnicos, clientes e equipamentos, além de agendamento e cancelamento de ordens de servico")
                        .contact(new Contact()
                                .name("Tiago Cassio Carvalho da Rocha")
                                .email("tiago@partnertelecom.com.br"))
                        .license(new License()
                                .name("PartnerLab")
                                .url("http://partnertelecom.com.br/partnerlab/licenca")));
    }
}
