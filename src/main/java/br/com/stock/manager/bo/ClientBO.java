package br.com.stock.manager.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stock.manager.dao.CRUD;
import br.com.stock.manager.dao.ClientDAO;
import br.com.stock.manager.model.Client;

@Service
public class ClientBO implements CRUD<Client, Long> {

	@Autowired
	private ClientDAO dao;

	@Override
	public Client searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<Client> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Client client) {
		dao.insert(client);
	}

	@Override
	public void update(Client client) {
		dao.update(client);
	}

	@Override
	public void remove(Client client) {
		dao.remove(client);
	}
	
	public void deactivate(Client client) {
		client.setActive(false);
		dao.update(client);
	}
	
	public void activate(Client client) {
		client.setActive(true);
		dao.update(client);
	}
}
