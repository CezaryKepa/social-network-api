package com.speakout.speakoutapi;

import com.speakout.speakoutapi.customer.CustomerMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpeakOutApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeakOutApiApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
