package org.khusanjon.springreactivewithmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class SpringReactiveWithMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveWithMongoApplication.class, args);
    }

}
