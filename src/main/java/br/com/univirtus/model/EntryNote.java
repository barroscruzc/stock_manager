package br.com.univirtus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EntryNote implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDateTime dateTime;
	private Double total;
	private Supplier supplier;
	private Set<EntryNoteItem> items = new HashSet<>();
	
	public EntryNote() {
		
	}
	
	public EntryNote(Long id, Supplier supplier) {
		super();
		this.id = id;
		this.dateTime = LocalDateTime.now();
		this.supplier = supplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal() {
		Double total = 0.0;
		for(EntryNoteItem item : items) {
			total += item.getSubTotal();
		}
		this.total = total;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Set<EntryNoteItem> getItems() {
		return items;
	}

	public void addItem(EntryNoteItem item) {
		this.items.add(item);
		setTotal();
	}
	
	public void removeItem(EntryNoteItem item) {
		this.items.add(item);
		setTotal();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntryNote other = (EntryNote) obj;
		return Objects.equals(id, other.id);
	}
	
}
