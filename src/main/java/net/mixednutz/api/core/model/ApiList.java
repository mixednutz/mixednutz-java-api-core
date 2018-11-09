package net.mixednutz.api.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Adapter so you can return a named list in a JSON model.
 * 
 * @author apfesta
 *
 * @param <T>
 */
public class ApiList<T> extends HashMap<String, List<T>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String listName;
	
	public ApiList(String name) {
		this(name, new ArrayList<T>());
	}
	
	public ApiList(String name, List<T> list) {
		this.listName = name;
		this.put(listName, list);
	}
	
	public void add(T e) {
		this.getList().add(e);
	}
	
	@JsonIgnore
	public List<T> getList() {
		return this.get(listName);
	}
}