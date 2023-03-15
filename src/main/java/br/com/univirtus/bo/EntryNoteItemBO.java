package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.EntryNoteItemDAO;
import br.com.univirtus.model.EntryNoteItem;

@Service
public class EntryNoteItemBO implements CRUD<EntryNoteItem, Long>{

	@Autowired
	EntryNoteItemDAO dao;
	
	@Override
	public EntryNoteItem searchById(Long id) {
		return dao.searchById(id);
	}

	@Override
	public List<EntryNoteItem> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(EntryNoteItem entryNoteItem) {
		dao.insert(entryNoteItem);
	}

	@Override
	public void update(EntryNoteItem entryNoteItem) {
		dao.update(entryNoteItem);
	}

	@Override
	public void remove(EntryNoteItem entryNoteItem) {
		dao.remove(entryNoteItem);
	}

}
