package com.javachen.cshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * @author june
 * @createTime 2019-06-20 17:10
 * @see
 * @since
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket globalApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(new BasicAuth("basicAuth")))
                .securityContexts(Arrays.asList(securityContext()))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(basicAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> basicAuth() {
        return Arrays.asList(SecurityReference.builder()
                .reference("basicAuth")
                .scopes(new AuthorizationScope[0])
                .build());
    }


    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Cshop web API")
                .description("The default Cshop web APIs")
                .termsOfServiceUrl("http://www.cshop.com")
                .version("v1")
                .build();
    }

//    @Bean
//    public TranslationOperationBuilderPlugin translationPlugin() {
//        return new TranslationOperationBuilderPlugin();
//    }
//
//    @Order(Ordered.LOWEST_PRECEDENCE)
//    public static class TranslationOperationBuilderPlugin implements OperationBuilderPlugin {
//
//        @Autowired
//        protected MessageSource translator;
//
//        @Override
//        public boolean supports(DocumentationType delimiter) {
//            return true;
//        }
//
//        @Override
//        public void apply(OperationContext context) {
//            Set<ResponseMessage> messages = context.operationBuilder().build().getResponseMessages();
//            Set<ResponseMessage> translated = new HashSet<>();
//            for (ResponseMessage untranslated : messages) {
//                String translation = translator.getMessage(untranslated.getMessage(), null, untranslated.getMessage(), null);
//                translated.add(new ResponseMessage(untranslated.getCode(),
//                        translation,
//                        untranslated.getResponseModel(),
//                        untranslated.getHeaders(),
//                        untranslated.getVendorExtensions()));
//            }
//            context.operationBuilder().responseMessages(translated);
//        }
//
//    }
}