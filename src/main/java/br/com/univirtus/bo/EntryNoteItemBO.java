package br.com.univirtus.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.univirtus.dao.CRUD;
import br.com.univirtus.dao.EntryNoteItemDAO;
import br.com.univirtus.model.EntryNoteItem;

@Service
public class EntryNoteItemBO implements CRUD<EntryNoteItem, Long> {

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

	// Returns true if the entryNote already has the same product
	public boolean hasItem(EntryNoteItem entryNoteItem) {
		Long entryNoteId = entryNoteItem.getEntryNote().getId();
		List<EntryNoteItem> items = dao.listByEntryNoteId(entryNoteId);

		Long productId = entryNoteItem.getProduct().getId();

		if (entryNoteItem.getId() == null) {
			for (EntryNoteItem item : items) {
				if (item.getProduct().getId() == productId) {
					return true;
				}
			}

		} else {
			Long entryNoteItemId = entryNoteItem.getId();
			for (EntryNoteItem item : items) {
				if (item.getProduct().getId() == productId && entryNoteItemId == item.getId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public List<EntryNoteItem> listByEntryNoteId(Long entryNoteId){
		return dao.listByEntryNoteId(entryNoteId);
	}
}
