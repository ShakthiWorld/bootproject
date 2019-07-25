package com.visa.training.bootproject;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.visa.training.bootproject.domain.Product;

@SpringBootApplication
public class BootprojectApplication {

	public static void main(String[] args) {
//		ApplicationContext springContainer=
		SpringApplication.run(BootprojectApplication.class, args);
		
		
}
}
