package br.com.locadora;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.locadora.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
private ApiInfo apiInfo() {
		
		return new ApiInfo(
			      "Locadora API",
			      "API Locadora registros de locação de veículos, com CRUD de Automóveis, Usuarios e Locações. Favor entre com usuario e senha no Autenticacao Controller, "
			      + "\nclique em Authorize e use a palavra Bearer e o token JWT gerado!!! \n\nExemplo: (Bearer eyJ0eXAiOiJKV1QiLCJhbG...)",
			      "1.0.0",
			      "https://www.simuladoronline.com/termos-de-uso/",
			      new Contact("Marciel Bezerra", "https://www.gft.com/br/pt", "marciel.bezerra@gft.com"),
			      "Licença de API",
			      "https://www.simuladoronline.com/termos-de-uso/",
			      Collections.emptyList());
	}

}
