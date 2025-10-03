package io.github.andrelzinnn.services.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServicesCrudApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServicesCrudApplication.class, args);

  }
}
