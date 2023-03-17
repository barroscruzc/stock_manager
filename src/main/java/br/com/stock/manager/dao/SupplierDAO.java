package br.com.stock.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.stock.manager.model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SupplierDAO implements CRUD<Supplier, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Supplier searchById(Long id) {
		return entityManager.find(Supplier.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findAll() {
		Query query = entityManager.createQuery("Select s from Supplier s");
		return (List<Supplier>) query.getResultList();
	}

	@Override
	public void insert(Supplier supplier) {
		entityManager.persist(supplier);
	}

	@Override
	public void update(Supplier supplier) {
		entityManager.merge(supplier);
	}

	@Override
	public void remove(Supplier supplier) {
		entityManager.remove(supplier);
	}



	
}
