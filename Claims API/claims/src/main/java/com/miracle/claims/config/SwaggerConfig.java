package com.miracle.claims.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** The app name. */
	@Value("${spring.application.name:claims}")
	private String appName;

	/** The env host URL. */
	@Value("${env.host.url:#{null}}")
	private String envHostURL;

	/** The bean factory. */
	@Autowired
	BeanFactory beanFactory;

    /**
     * Published API.
     *
     * @return the docket
     */
    @Bean
    Docket publishedAPI() {
        if (envHostURL != null) {
            return (new Docket(DocumentationType.SWAGGER_2)).host(envHostURL.concat("/").concat(appName))
                    .groupName("Claims Service").apiInfo(this.apiInfo()).select().paths(this.servicePaths())
                    .build();
        } else {
            return (new Docket(DocumentationType.SWAGGER_2)).groupName("Claims Service").apiInfo(this.apiInfo())
                    .select().paths(this.servicePaths()).build();
        }
    }

	/**
	 * Service paths.
	 *
	 * @return the predicate
	 */
	private Predicate<String> servicePaths() {
		return PathSelectors.regex("/.*");
	}

	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		return (new ApiInfoBuilder()).title("Claims Service").description("REST API").contact("MIRACLE")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("1.0").build();
	}

}
