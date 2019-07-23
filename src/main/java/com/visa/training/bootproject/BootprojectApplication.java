package com.visa.training.bootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.visa.training.bootproject.ui.ProductConsoleUI;

@SpringBootApplication
public class BootprojectApplication {

	public static void main(String[] args) {
		ApplicationContext springContainer=
		SpringApplication.run(BootprojectApplication.class, args);
		
		ProductConsoleUI ui=springContainer.getBean(ProductConsoleUI.class);
		ui.createProductWithUI();
	}

}
