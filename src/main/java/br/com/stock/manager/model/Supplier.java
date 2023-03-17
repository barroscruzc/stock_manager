package br.com.stock.manager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Supplier implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(length = 20)
	@Size(min = 3, max = 20, message="Este campo deve conter entre 3 a 20 caracteres!")
	private String brand;
	
	@Column(nullable=false, length = 50)
	@NotBlank(message="Informe o nome da empresa!")
	@Size(min = 3, max = 50, message="Nome da empresa deve conter entre 3 e 50 caracteres!")
	private String companyName;
	
	@Column(nullable=false, length=18)
	@NotNull(message="Informe o CNPJ!")
	@CNPJ(message="CNPJ inválido!")
	private String cnpj;
	
	@Column(nullable=false, length=14)
	@Size(min=10, max=14)
	private String phone;
	
	@Column(length=15)
	@Size(min=10, max=15)
	private String mobile;
	
	@Column(length=50)
	@Email(message="Endereço de e-mail inválido!")
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "supplier")
	private Set<EntryNote> entryNotes = new HashSet<>();
	
	private boolean active;
	
	public Supplier() {
		this.active = true;
	}

	public Supplier(Long id, String brand, String companyName, String cnpj, String phone, String mobile, String email,
			boolean active) {
		super();
		this.id = id;
		this.brand = brand;
		this.companyName = companyName;
		this.cnpj = cnpj;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<EntryNote> getEntryNotes() {
		return entryNotes;
	}

	public void addEntryNotes(EntryNote entryNote) {
		this.entryNotes.add(entryNote);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		Supplier other = (Supplier) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
