//package com.codewithapp.blog.config;
//
//import java.util.Arrays;
//import  java.util.List;
//
//import springfox.documentation.spring.web.plugins.Docket;
//
//import springfox.documentation.service.AuthorizationScope;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//
//
//import org.springframework.security.core.context.SecurityContext;
//
//import io.jsonwebtoken.lang.Collections;
//import
//
//@Configuration
//public class SwaggerConfig {
//	
//	
//	public static  final String  AUTHRORIZATION_HEADER="Authorization";
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(getInfo())
//				.select()
//				.securityContexts()
//				.securityScehmes()
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build();
//	}
//	
//	
//	  private List<SecurityContext> securityContext(){
//		  return  Arrays.asList(SecurityContext.builder().securityReferences(sf).build());
//	  }
//	  private List<SecurityReference> sf(){
//		    AuthorizationScope  scope=new  AuthorizationScope("global","accessEverything");
//		  return  Arrays.asList( new SecurityContext("scope",new AuthorizationScope[] {scope}));
//		  
//	  }
//	private ApiInfo getInfo() {
//		return new ApiInfo("Blogging Application: Backend Course" , "This project is developed to learn to create real life Apis", "1.0", "Terms of service", null, "License of Apis", "Api license URL", Collections.emptyList());
//
//		
//	}
//	 private Apikey apiKeys() {
//		 return  new  ApiKey("JWT",AUTHRORIZATION_HEADER,"header");
//	 }
//
//}
