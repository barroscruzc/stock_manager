package br.com.univirtus.dao;

import java.util.List;

import br.com.univirtus.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ProductDAO implements CRUD<Product, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Product searchById(Long id) {
		return entityManager.find(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Query query = entityManager.createQuery("Select p from Product p");
		return (List<Product>) query.getResultList();
	}

	@Override
	public void insert(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void remove(Product product) {
		entityManager.remove(product);
	}

}
