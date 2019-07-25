package com.visa.training.bootproject.testclient;

import java.net.URI;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.visa.training.bootproject.domain.Product;

public class ProductRemoteClientApp {

	public static void main(String[] args) {
		
		createProduct();
		listProducts();
		updateProduct();
		listProductById();
		
	}
	
	
	
	private static void listProductById() {
		
		Scanner kb=new Scanner(System.in);
		System.out.println("enter the id");
		int id=kb.nextInt();
		RestTemplate temp=new RestTemplate();
		ResponseEntity<Product> pro=temp.getForEntity("http://localhost:8080/api/products/"+id, Product.class);
		System.out.println(pro.toString());
		
	}



	private static void updateProduct() {
		
		Scanner kb=new Scanner(System.in);
		System.out.println("enter the id");
		int id=kb.nextInt();
		System.out.println("enter the product name");
		String name=kb.nextLine();
		System.out.println("enter the price");
		float price=kb.nextFloat();
		System.out.println("enter the qoh");
		int qoh=kb.nextInt();
		
		RestTemplate temp=new RestTemplate();
		Product p=new Product(name, price, qoh);
		temp.put("http://localhost:8080/api/products/"+id,p);
		listProductById();
	}



	private static void createProduct() {
		Scanner kb=new Scanner(System.in);
		System.out.println("enter the product name");
		String name=kb.nextLine();
		System.out.println("enter the price");
		float price=kb.nextFloat();
		System.out.println("enter the qoh");
		int qoh=kb.nextInt();
		
		Product p=new Product(name,price,qoh);
		RestTemplate temp=new RestTemplate();
		URI uri=temp.postForLocation("http://localhost:8080/api/products",p);
		System.out.println("uri of the product created is "+uri.toString());
		
	}

	//we can see that this class uses the restful service in boot application.
	//so it a must that we first have to run the service application(bootapp)
	//and then this class;
	public static void listProducts()
	{
		RestTemplate temp=new RestTemplate();
		String url="http://localhost:8080/api/products";
		ResponseEntity<List<Product>> response;
		try {
			System.out.println("inside tyr");
			response = temp.exchange(url, HttpMethod.GET, null,new ParameterizedTypeReference<List<Product>>() {});
			List<Product> products=response.getBody();
			products.stream().forEach(System.out::println);
			System.out.println("outside tyr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}

	}


