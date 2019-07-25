package com.visa.training.bootproject.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visa.training.bootproject.domain.Product;
import com.visa.training.bootproject.service.ProductService;

@RestController
//to mention that it is a rest controller.
//consume and produce data in the form of json
//the returned data is not of view but to be converted into json,etc.
public class ProductRestController {

	ProductService service;

	@Autowired
	// not necesary to have setter method for autowired.
	// doing this on the obj itself will do.
	public void setService(ProductService service) {
		this.service = service;
	}

	@RequestMapping(value = "/api/products", method = RequestMethod.GET)
	public List<Product> getAll() {
		
		try {
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			
		}
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/products/{id}")
	// the id above is called path variable and it should be the same as the one
	// given besides the path variable given below.

	// everytime we cant be sure that the id given doesnt correspond to a prod.
	// in such cases we need to handle the cases and so we uses responseentity
	// instead of product.
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Product p = service.findById(id);
		if (p != null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/products", method = RequestMethod.POST)
	//you give @requestbody since you want to get the object from the postman
	//service where you have specified the content
	public ResponseEntity createProduct(@RequestBody Product p) {

		try {
			int id = service.addNewProduct(p);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/api/products/" + id));
			return new ResponseEntity<>(headers, HttpStatus.CREATED);

		} catch (IllegalArgumentException e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@RequestBody Product p,@PathVariable("id") int id) {

		
		Product pro = service.findById(id);
		if (pro!=null)
		{
			service.update(p);
		
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
		
			Product p = service.findById(id);
			if(p!=null)
			{
			try {
				service.deleteProduct(id);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Product>(HttpStatus.ACCEPTED);
			}
			else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		

	}
}
