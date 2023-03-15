package br.com.univirtus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EntryNote implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDateTime dateTime;
	private float total;
	private Supplier supplier;
	
	public EntryNote() {
		
	}

	public EntryNote(Long id, LocalDateTime dateTime, float total, Supplier supplier) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.total = total;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
