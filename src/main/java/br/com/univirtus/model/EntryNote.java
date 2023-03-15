package br.com.univirtus.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="entry_note")
public class EntryNote implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'" , timezone="GMT")
	private Instant date;
	
	@Transient
	private Double total;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable=false)
	private Supplier supplier;
	
	@OneToMany(mappedBy = "")
	private Set<EntryNoteItem> items = new HashSet<>();
	
	public EntryNote() {
		
	}
	
	public EntryNote(Long id, Supplier supplier) {
		super();
		this.id = id;
		this.date = Instant.now();
		this.supplier = supplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDateTime() {
		return date;
	}

	public void setDateTime() {
		this.date = Instant.now();
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
