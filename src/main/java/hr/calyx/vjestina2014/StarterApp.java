package hr.calyx.vjestina2014;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class StarterApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StarterApp.class, args);
    }
}
