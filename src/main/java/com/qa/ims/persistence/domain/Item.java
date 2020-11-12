package com.qa.ims.persistence.domain;

public class Item 
{
	private Long id;
	private String name;
	private Double price;
	
	
	// constructors
	
	public Item(String name) 
	{
		this.name = name;
	}
	

	public Item(Long id, String name) 
	{
		this.id = id;
		this.name = name;
	}
	
	public Item(Long id, String name, Double price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, Double price)
	{
		this.name = name;
		this.price = price;
	}

	// getters & setters
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Double getPrice() 
	{
		return price;
	}

	public void setPrice(Double price) 
	{
		this.price = price;
	}
	
	//overridden methods
	@Override
	public String toString() 
	{
		return "id: " + id + " name: " + name + " price: "+ price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
