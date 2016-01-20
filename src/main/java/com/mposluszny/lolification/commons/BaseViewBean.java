package com.mposluszny.lolification.commons;

import java.io.Serializable;
import java.util.List;

public abstract class BaseViewBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract List<T> getAll();
	public abstract T getById(long id);
	public abstract void add(T object);
	public abstract void update(T object);
	public abstract void remove(T object);
}
