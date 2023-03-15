package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.EntryNoteDAO;
import br.com.univirtus.model.EntryNote;

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
		dao.insert(entryNote);
		
	}

	@Override
	public void update(EntryNote entryNote) {
		dao.update(entryNote);
		
	}

	@Override
	public void remove(EntryNote entryNote) {
		dao.remove(entryNote);
		
	}
	
}
