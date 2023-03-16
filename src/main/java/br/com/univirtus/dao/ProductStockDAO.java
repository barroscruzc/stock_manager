package br.com.univirtus.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.univirtus.model.ProductStock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductStockDAO implements CRUD<ProductStock, Long>{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public ProductStock searchById(Long id) {
		return entityManager.find(ProductStock.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductStock> findAll() {
		Query query = entityManager.createNativeQuery("Select ps from ProductStock ps");
		return query.getResultList();
	}

	@Override
	public void insert(ProductStock productStock) {
		entityManager.persist(productStock);
	}

	@Override
	public void update(ProductStock productStock) {
		entityManager.merge(productStock);
	}

	@Override
	public void remove(ProductStock productStock) {
		entityManager.remove(productStock);
	}

}
