package com.example.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {

	@Bean(value = "defaultApi2")
	public Docket defaultApi2() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("2.x版本").select()
			.paths(PathSelectors.any()).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("示例demo相关接口文档").description("示例demo相关接口文档").version("1.0.0")
			.termsOfServiceUrl("http://localhost:8088").build();
	}
}