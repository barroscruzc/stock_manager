package br.com.univirtus.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.univirtus.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ClientDAO implements CRUD<Client, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Client searchById(Long id) {
		return entityManager.find(Client.class, id);
	}

	@Override
	public List<Client> findAll() {
		Query query = entityManager.createQuery("Select c from Client c");
		return (List<Client>) query.getResultList();
	}

	@Override
	public void insert(Client client) {
		entityManager.persist(client);
	}

	@Override
	public void update(Client client) {
		entityManager.merge(client);
	}

	@Override
	public void remove(Client client) {
		entityManager.remove(client);
	}

	
}
