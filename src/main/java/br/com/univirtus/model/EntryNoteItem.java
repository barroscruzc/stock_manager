package br.com.univirtus.model;

import java.io.Serializable;
import java.util.Objects;

public class EntryNoteItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantity;
	private float unitaryValue;
	private float subTotal;
	private Product product;
	
	public EntryNoteItem() {
		
	}

	public EntryNoteItem(Long id, Integer quantity, float unitaryValue, float subTotal, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitaryValue = unitaryValue;
		this.subTotal = subTotal;
		this.product = product;
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

	public float getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(float unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
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
