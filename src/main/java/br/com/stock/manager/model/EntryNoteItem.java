package br.com.stock.manager.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class EntryNoteItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="entry_note_id")
	@NotNull
	private EntryNote entryNote;
	
	@NotNull(message="Informe a quantidade")
	private Integer quantity;
	
	@NotNull(message="Informe o valor unitário")
	private Double unitaryValue;
	
	@NotNull(message="Subtotal não pode ser nulo")
	private Double subtotal;

	public EntryNoteItem() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public EntryNote getEntryNote() {
		return entryNote;
	}

	public void setEntryNote(EntryNote entryNote) {
		this.entryNote = entryNote;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		setSubtotal();
	}

	public Double getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(Double unitaryValue) {
		this.unitaryValue = unitaryValue;
		setSubtotal();
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal() {
		this.subtotal = quantity * unitaryValue;
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
		EntryNoteItem other = (EntryNoteItem) obj;
		return Objects.equals(id, other.id);
	}
	
}
