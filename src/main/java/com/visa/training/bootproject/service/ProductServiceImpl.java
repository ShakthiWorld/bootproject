package com.visa.training.bootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.training.bootproject.dal.ProductDAO;
import com.visa.training.bootproject.domain.Product;

@Service
public class ProductServiceImpl implements ProductService{

	ProductDAO dao;
	
	@Autowired
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}

	@Override
	public int addNewProduct(Product p) {
		// TODO Auto-generated method stub
		
		int id=0;
		if(p.getPrice()*p.getQoh()>=10000)
		{
			Product created=dao.save(p);
			id=created.getId();
			
		}
		else
		{
			throw new IllegalArgumentException("min value of stock should be atleast 10k");
		}
		return id;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		
		return dao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
		}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
	
		/*
		 * if(p.getPrice()*p.getQoh()>1000) { throw new
		 * IllegalArgumentException("cant remove when value>1000"); } else {
		 * dao.remove(p); }
		 */
		dao.remove(id);
	}

	
}
