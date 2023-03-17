package br.com.stock.manager.model.enums;

public enum Gender {

	MALE("Male"),
	FEMALE("Female");
	
	private String description;
	
	Gender(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
