package com.visa.training.bootproject.testclient;

import java.net.URI;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.visa.training.bootproject.domain.Product;

import reactor.core.publisher.Flux;

public class ProductRemoteClientWithWebFlux {

	public static void main(String[] args) {
		
		
		listProducts();
		//updateProduct();
		//listProductById();
		doOtherWork();
		
	}
	
	
	
	private static void doOtherWork() {
		System.out.println("doing other work meanwhile fetching data for the other process");
		
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



	

	//we can see that this class uses the restful service in boot application.
	//so it a must that we first have to run the service application(bootapp)
	//and then this class;
	
	//using webclient instead of the rest template which is used in the class 
	//in the same package. rest template uses synchronous aka blocking calls
	//while webclient does the next work meanwhile fetching data for the waited
	//process.  more efficient...!!!
	public static void listProducts()
	{
		WebClient client=WebClient.create("http://localhost:8080");
		Flux<Product> all=client.get().uri("/api/products").retrieve().bodyToFlux(Product.class);
		all.subscribe(System.out::println);
		
	
	}

	}


