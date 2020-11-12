package com.qa.ims.persistence.domain;

public class Order 
{
	private Long id;
	private Item item;
	private Customer customer;
	private int quantity;
	
	// constructors
	public Order(Long id, Item item, Customer customer, int quantity) 
	{
		this.id = id;
		this.item = item;
		this.customer = customer;
		this.quantity = quantity;
	}
	
	public Order(Long id, Item item, int quantity) 
	{
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}

	public Order(Long id, Item item, Customer customer) 
	{
		this.id = id;
		this.item = item;
		this.customer = customer;		
	}
	public Order(Long id, Item item) 
	{
		this.id = id;
		this.item = item;		
	}
	public Order(Long id) 
	{
		this.id = id;		
	}
	
	public Order(Item item, Customer customer, int quantity) 
	{
		this.item = item;
		this.customer = customer;
		this.quantity = quantity;
	}
	public Order(Item item, Customer customer) 
	{
		this.item = item;
		this.customer = customer;
		//this.quantity = quantity;
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
	public Item getItem() 
	{
		return item;
	}
	public void setItem(Item item) 
	{
		this.item = item;
	}
	public Customer getCustomer() 
	{
		return customer;
	}
	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	// overridden methods
	@Override
	public String toString() 
	{
		return "id: " + id + " item: " + item.getName() + " quantity: " + quantity + " customer: "+ customer.getName();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (quantity == 0) {
			if (other.quantity != 0)
				return false;
		} else if (quantity != other.quantity)
			return false;
		return true;
	}
	
}
