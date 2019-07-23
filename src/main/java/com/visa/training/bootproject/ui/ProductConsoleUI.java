package com.visa.training.bootproject.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.visa.training.bootproject.domain.Product;
import com.visa.training.bootproject.service.ProductService;

@Component
public class ProductConsoleUI {

	ProductService service;
	
	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}
	public void createProductWithUI()
	{
		Scanner kb=new Scanner(System.in);
		System.out.println("enter a name");
		String name=kb.nextLine();
		System.out.println("enter a price");
		float price=kb.nextFloat();
		System.out.println("enter the qoh");
		int qoh=kb.nextInt();
		
		Product p=new Product(name,price,qoh);
		
		try {
			int id=service.addNewProduct(p);
			System.out.println("created product with id"+id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}
}
