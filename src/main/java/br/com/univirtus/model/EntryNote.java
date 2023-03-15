package br.com.univirtus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class EntryNote implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(nullable=false, name="date_time", columnDefinition = "DATETIME")
	private LocalDateTime dateTime;
	
	@Transient
	private Double total;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable=false)
	private Supplier supplier;
	
	public EntryNote() {
		
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


	public void setTotal(Double total) {
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
