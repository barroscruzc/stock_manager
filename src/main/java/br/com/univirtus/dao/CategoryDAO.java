package br.com.univirtus.dao;

import java.util.List;

import br.com.univirtus.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class CategoryDAO implements CRUD<Category, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Category searchById(Long id) {
		return entityManager.find(Category.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		Query query = entityManager.createQuery("Select c from Category c");
		return (List<Category>) query.getResultList();
	}

	@Override
	public void insert(Category category) {
		entityManager.persist(category);
	}

	@Override
	public void update(Category category) {
		entityManager.merge(category);
	}

	@Override
	public void remove(Category category) {
		entityManager.remove(category);
	}

}
