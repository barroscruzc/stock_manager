package br.com.stock.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.stock.manager.model.EntryNote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteDAO implements CRUD<EntryNote, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public EntryNote searchById(Long id) {
		return entityManager.find(EntryNote.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntryNote> findAll() {
		Query query = entityManager.createQuery("Select en from EntryNote en");
		return (List<EntryNote>) query.getResultList();
	}

	@Override
	public void insert(EntryNote entryNote) {
		entryNote.setTotal();
		entityManager.persist(entryNote);
	}

	@Override
	public void update(EntryNote entryNote) {
		entryNote.setTotal();
		entityManager.merge(entryNote);
		
	}

	@Override
	public void remove(EntryNote entryNote) {
		entityManager.remove(entryNote);
		
	}

}
