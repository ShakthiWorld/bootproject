package com.visa.training.bootproject.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.visa.training.bootproject.dal.ProductRepository;
import com.visa.training.bootproject.domain.Product;

public class ProductServiceImplTest {

	@Test
	public void addNewProduct_Returns_Valid_Id_When_Value_Gt_MinValue() {
		//arrange
		//act
		//assert ------ the general rhythm of unit testing
		
//		arrange
		ProductServiceImpl impl=new ProductServiceImpl();
		Product toBeAdded=new Product("test",10000,2);
		Product added=new Product("test",10000,2);
		added.setId(1);
				
		//create a mock obj of productrepository class
		ProductRepository mockDAO=Mockito.mock(ProductRepository.class);
		
		//train the mock obj on the required method
		Mockito.when(mockDAO.save(toBeAdded)).thenReturn(added);
		
		//set the mockdao to impl - manually inject the dependency
		impl.setDao(mockDAO);
		
		//act
		int id=impl.addNewProduct(toBeAdded);
		
		//assert
		assertTrue(id>0);
	}

}
