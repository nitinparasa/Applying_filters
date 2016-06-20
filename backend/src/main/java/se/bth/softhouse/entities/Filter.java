package se.bth.softhouse.entities;

import com.sun.istack.internal.NotNull;

public class Filter {

	private int id;
	@NotNull
	private String name;

	public Filter() {

	}

	public Filter(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
