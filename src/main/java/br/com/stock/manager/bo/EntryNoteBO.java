package br.com.stock.manager.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stock.manager.dao.CRUD;
import br.com.stock.manager.dao.EntryNoteDAO;
import br.com.stock.manager.model.EntryNote;

@Service
public class EntryNoteBO implements CRUD<EntryNote, Long>{

	@Autowired
	private EntryNoteDAO dao;

	@Override
	public EntryNote searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<EntryNote> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(EntryNote entryNote) {
		entryNote.setTotal();
		dao.insert(entryNote);
	}

	@Override
	public void update(EntryNote entryNote) {
		entryNote.setTotal();
		dao.update(entryNote);
	}

	@Override
	public void remove(EntryNote entryNote) {
		dao.remove(entryNote);
		
	}
	
}
