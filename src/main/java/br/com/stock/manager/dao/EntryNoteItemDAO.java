package br.com.stock.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.stock.manager.model.EntryNoteItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EntryNoteItemDAO implements CRUD<EntryNoteItem, Long>{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public EntryNoteItem searchById(Long id) {
		return entityManager.find(EntryNoteItem.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntryNoteItem> findAll() {
		Query query = entityManager.createQuery("Select eni from EntryNoteItem eni");
		return (List<EntryNoteItem>) query.getResultList();
	}

	@Override
	public void insert(EntryNoteItem entryNoteItem) {
		entityManager.persist(entryNoteItem);
	}

	@Override
	public void update(EntryNoteItem entryNoteItem) {
		entityManager.merge(entryNoteItem);
	}

	@Override
	public void remove(EntryNoteItem entryNoteItem) {
		entityManager.remove(entryNoteItem);
	}

	@SuppressWarnings("unchecked")
	public List<EntryNoteItem> listByEntryNoteId(Long entryNoteId){
		Query query = entityManager.createQuery("from EntryNoteItem e where e.entryNote.id = :entryNoteId")
				.setParameter("entryNoteId", entryNoteId);
		return query.getResultList();
	}
}
