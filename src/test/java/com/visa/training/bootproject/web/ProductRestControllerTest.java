package com.visa.training.bootproject.web;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.visa.training.bootproject.domain.Product;
import com.visa.training.bootproject.service.ProductService;

//the below line indicates that it should use spring container for testing
@RunWith(SpringRunner.class)
@WebMvcTest(value = (ProductRestController.class))
public class ProductRestControllerTest {

	@Autowired
	//autowire  mvc obj...note here since it uses springcontainer to test
	//we can use autowired.
	MockMvc mvc;
	
	@MockBean
	//mock a bean of productservice.
	ProductService service;
	
	@Test
	public void testGetById() throws Exception{
		
		Product data=new Product("test",1819,90);
		data.setId(1);
		
		Mockito.when(service.findById(1)).thenReturn(data);
		mvc.perform(get("/api/products/{id}",1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id",is(1)));
		
	}

}
