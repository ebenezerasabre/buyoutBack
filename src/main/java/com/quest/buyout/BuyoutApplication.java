package com.quest.buyout;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class BuyoutApplication  {
//
//    public static void main(String[] args) {
//        SpringApplication.run(BuyoutApplication.class, args);
//    }
//}


@SpringBootApplication
public class BuyoutApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BuyoutApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BuyoutApplication.class);
    }

}

