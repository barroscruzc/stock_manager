package br.com.univirtus.model;

import java.io.Serializable;
import java.util.Objects;

public class EntryNoteItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantity;
	private Double unitaryValue;
	private Double subTotal;
	private Product product;
	
	public EntryNoteItem() {
		
	}

	public EntryNoteItem(Long id, Integer quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		setUnitaryValue();
		setSubTotal();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue() {
		this.unitaryValue = this.product.getValue();
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal() {
		this.subTotal = unitaryValue * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
