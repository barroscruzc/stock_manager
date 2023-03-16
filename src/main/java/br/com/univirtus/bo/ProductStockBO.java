package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.ProductStockDAO;
import br.com.univirtus.model.ProductStock;

@Repository
public class ProductStockBO implements CRUD<ProductStock, Long>{

	@Autowired
	private ProductStockDAO dao;
	
	@Override
	public ProductStock searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<ProductStock> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(ProductStock productStock) {
		dao.insert(productStock);
	}

	@Override
	public void update(ProductStock productStock) {
		dao.insert(productStock);
	}

	@Override
	public void remove(ProductStock productStock) {
		dao.insert(productStock);
	}

}
