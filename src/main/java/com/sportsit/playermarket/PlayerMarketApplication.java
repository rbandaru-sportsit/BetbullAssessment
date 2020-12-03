package com.sportsit.playermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class PlayerMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerMarketApplication.class, args);
	}

	@Configuration
	@EnableSwagger2
	public class SwaggerConfig extends WebMvcConfigurationSupport {
		@Bean
		public Docket productApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select().apis(RequestHandlerSelectors.basePackage("com.sportsit.playermarket"))
					.paths(regex("/football.*"))
					.build();
		}
		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html")
					.addResourceLocations("classpath:/META-INF/resources/");

			registry.addResourceHandler("/webjars/**")
					.addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}
}
