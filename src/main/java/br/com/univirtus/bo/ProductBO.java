package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.ProductDAO;
import br.com.univirtus.model.Product;

@Service
public class ProductBO implements CRUD<Product, Long> {

	@Autowired
	private ProductDAO dao;

	@Override
	public Product searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Product product) {
		dao.insert(product);
	}

	@Override
	public void update(Product product) {
		dao.update(product);
	}

	@Override
	public void remove(Product product) {
		dao.remove(product);
	}
	
	public void deactivate(Product product) {
		product.setActive(false);
		dao.update(product);
	}
	
	public void activate(Product product) {
		product.setActive(true);
		dao.update(product);
	}
}
