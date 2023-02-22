package uy.com.sofka.retodevjunior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@RestController
public class SofkaRetoDevJuniorWebBackendApplication {
  
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }
  
  public static void main(String[] args) {
    SpringApplication.run(SofkaRetoDevJuniorWebBackendApplication.class, args);
  }
  
  // greeting endpoint
   @GetMapping("/greeting")
   public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
     return String.format("Hello %s!", name);

   }
  
}
