package com.example.my_springboot_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.my_springboot_demo"})
public class MySpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringbootDemoApplication.class, args);
	}

}
