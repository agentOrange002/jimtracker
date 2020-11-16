/*
 * package com.app.sys.jimtracker;
 * 
 * import java.util.ArrayList; import java.util.Arrays; import
 * java.util.HashSet; import java.util.List;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import springfox.documentation.builders.PathSelectors; import
 * springfox.documentation.builders.RequestHandlerSelectors; import
 * springfox.documentation.service.ApiInfo; import
 * springfox.documentation.service.Contact; import
 * springfox.documentation.service.VendorExtension; import
 * springfox.documentation.spi.DocumentationType; import
 * springfox.documentation.spring.web.plugins.Docket; import
 * springfox.documentation.swagger2.annotations.EnableSwagger2;
 * 
 * @Configuration
 * 
 * @EnableSwagger2 public class SwaggerConfig {
 * 
 * @SuppressWarnings("rawtypes") List<VendorExtension> vendorExtensions = new
 * ArrayList<>();
 * 
 * Contact contact = new Contact( "Nehemias C. Belong Jr.",
 * "http://sitenotavailable", "nehemiasbelong@gmail.com" );
 * 
 * ApiInfo apiInfo = new ApiInfo(
 * "Issue Tracking System RESTful Web Service Documentation",
 * "This Pages Documents Issue Tracking System  RESTful Web Service Endpoints",
 * "1.0", "http://localhost:8080/itsystem/service.html", contact, "Apache 2.0",
 * "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);
 * 
 * @Bean public Docket apiDocket() {
 * 
 * Docket docket = new Docket(DocumentationType.SWAGGER_2) .protocols(new
 * HashSet<>(Arrays.asList("HTTP","HTTPs"))) .apiInfo(apiInfo) .select()
 * .apis(RequestHandlerSelectors.basePackage("com.app.sys.jimtracker")).paths(
 * PathSelectors.any()) .build();
 * 
 * return docket;
 * 
 * } }
 */
