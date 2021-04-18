package br.com.igti.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ParticipanteConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.igti.api"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Partcipantes/Leiloeiros REST API")
				.description("API de Gerenciamento de Participantes e Leiloeiros.")
				.version("1.0.0")
		        .license("Apache License Version 2.0")
		        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(new AbstractConverter<String, Date>() {
			@Override
			protected Date convert(String source) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					return df.parse(source);
				} catch (ParseException e) {
					return null;
				}
			}
		});
		mapper.addConverter(new AbstractConverter<Date, String>() {
			@Override
			protected String convert(Date source) {
				return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(source);
			}
		});
		return mapper;
	}
}
