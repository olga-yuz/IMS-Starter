package com.qa.ims.persistence.domain;

public class Customer {

	private Long id;
	private String name;
	//private String surname;

	public Customer(String name) {
		this.name = name;
		//this.surname = surname;
	}

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
		//this.surname = surname;
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

	
	

	@Override
	public String toString() {
		return "id:" + id + " name:" + name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
//		if (surname == null) {
//			if (other.surname != null)
//				return false;
//		} else if (!surname.equals(other.surname))
//			return false;
		return true;
	}

}
