package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.CategoryDAO;
import br.com.univirtus.model.Category;

@Service
public class CategoryBO implements CRUD<Category, Long> {

	@Autowired
	private CategoryDAO dao;

	@Override
	public Category searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Category category) {
		dao.insert(category);
	}

	@Override
	public void update(Category category) {
		dao.update(category);
	}

	@Override
	public void remove(Category category) {
		dao.remove(category);
	}
	
	public void deactivate(Category category) {
		category.setActive(false);
		dao.update(category);
	}
	
	public void activate(Category category) {
		category.setActive(true);
		dao.update(category);
	}
}
