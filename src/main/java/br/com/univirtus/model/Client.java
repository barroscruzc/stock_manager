package br.com.univirtus.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.univirtus.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Informe o nome completo!")
	@Size(min = 3, max = 50, message="Nome deve conter entre 3 a 50 caracteres")
	private String name;
	
	@Column(length = 14)
	@CPF(message="CPF inválido!")
	private String cpf;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(nullable=false, name = "birth_date", columnDefinition = "DATE")
	@NotNull(message = "Informe a data de nascimento!")
	private LocalDate birthDate;
	
	@Column(length = 14)
	@Size(min = 10, max = 14)
	private String phone;
	
	@Column(length = 15)
	@Size(min = 10, max = 15)
	private String mobile;
	
	@Column(length = 50)
	@Email(message="Endereço de e-mail inválido")
	private String email;
	
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Informe o gênero!")
	private Gender gender;
	
	public Client() {
		this.active = true;
	}

	public Client(Long id, String name, String cpf, LocalDate birthDate, String phone, String mobile, String email, boolean active,
			Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birthDate=" + birthDate + ", phone=" + phone
				+ ", mobile=" + mobile + ", email=" + email + ", active=" + active + ", gender=" + gender + "]";
	}
	
}
