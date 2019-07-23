package com.visa.training.bootproject.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.visa.training.bootproject.domain.Product;
@Repository
@Transactional
public class ProductDAOJpaImpl implements ProductDAO{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Product save(Product p) {
		// TODO Auto-generated method stub
		em.persist(p);
		return p;
	
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Product.class, id);
		
				
				
				
				
				
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select p from Product as p").getResultList();
		
	}

	@Override
	public void update(Product p) {
		// TODO Auto-generated method stub
		em.merge(p);		
		
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		Product p=em.find(Product.class, id);
		em.remove(p);
		
	}

}
