package br.com.univirtus.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import br.com.univirtus.model.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private LocalDate birthDate;
	private String phone;
	private String mobile;
	private String email;
	private boolean active;
	private Gender gender;
	
	public Client() {
		
	}

	public Client(Long id, String name, LocalDate birthDate, String phone, String mobile, String email, boolean active,
			Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.active = active;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
