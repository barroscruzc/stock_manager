package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.SupplierDAO;
import br.com.univirtus.model.Supplier;

public class SupplierBO implements CRUD<Supplier, Long>{

	@Autowired
	private SupplierDAO dao;
	
	@Override
	public Supplier searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<Supplier> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Supplier client) {
		dao.insert(client);
	}

	@Override
	public void update(Supplier client) {
		dao.update(client);
	}

	@Override
	public void remove(Supplier client) {
		dao.remove(client);
	}
	
	public void deactivate(Supplier supplier) {
		supplier.setActive(false);
		dao.update(supplier);
	}
	
	public void activate(Supplier supplier) {
		supplier.setActive(true);
		dao.update(supplier);
	}

}
