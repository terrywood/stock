package com.gt.bmf.pojo;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
