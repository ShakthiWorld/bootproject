package com.visa.training.bootproject.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visa.training.bootproject.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

	public Product findById(int id);
	public List<Product> findAll();
	public Product save(Product p);

	//method naming convention is followed in java in springboot. whenever 
	//a own method is written, find means select clause,findBy means where 
	//is used too...and findByName means select by name,etc..
	
	//findByPriceLessThan for select by price where it is less than...
	//findByNameLike for some name like..!
	
	@Query("select p from Product as p where p.qoh*p.price>1000")
	//for more complex queries where you cant think of a name and want to
	//create the query by yourself
	public List<Product> myComplexQuery();
	
	/*
	 * @Query("update Product p set p.name=:pro.getName(),p.price=:pro.getPrice(),p.qoh=:pro.getQoh() where p.id=:pro.getId()"
	 * ) public void update(@Param("product") Product pro);
	 */
}
