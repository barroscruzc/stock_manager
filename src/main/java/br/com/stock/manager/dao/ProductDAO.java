package br.com.stock.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.stock.manager.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
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
