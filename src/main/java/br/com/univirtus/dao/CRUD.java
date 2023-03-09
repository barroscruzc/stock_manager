package br.com.univirtus.dao;

import java.util.List;

public interface CRUD <T, ID>{

	T searchById(ID id);
	List<T> findAll();
	void insert(T t);
	void update(T t);
	void remove(T t);
}
