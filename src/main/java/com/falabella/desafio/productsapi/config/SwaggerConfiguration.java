package com.falabella.desafio.productsapi.config;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
    
    @NonNull private final TypeResolver typeResolver;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDate.class),
                                typeResolver.resolve(List.class, String.class)))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.falabella.desafio.productsapi.rest"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Desafio Falabella - Products Api")
                .description("")
                .version("1.0")
                .build();
    }
}
