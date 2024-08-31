package kz.tenizcap.emailservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EmailServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EmailServiceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
