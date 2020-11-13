package com.qa.ims.controller;


import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
//import com.qa.ims.persistence.domain.Customer;
//import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderController implements CrudController<Order>
{
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	private ItemDAO itemDAO;
	private CustomerDAO customerDAO;
	public OrderController(OrderDAO orderDAO, ItemDAO itemDAO, CustomerDAO customerDAO, Utils utils) 
	{
		super();
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.customerDAO = customerDAO;
		this.utils = utils;
	}
	
	@Override
	public List<Order> readAll() 
	{
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	
	@Override
	public Order create() 
	{
//		ItemDAO itemDAO = new ItemDAO();
//		CustomerDAO customerDAO = new CustomerDAO();
		LOGGER.info("Please enter a customer ID");
		Long cust_id = utils.getLong();
		LOGGER.info("Please enter an item ID");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();
		int quant = Math.toIntExact(quantity);
		//LOGGER.info(quant);
		Item item = itemDAO.readItem(item_id);
		//LOGGER.info("item found");
		Customer customer = customerDAO.readCustomer(cust_id);
		Order order = orderDAO.create(new Order(item, customer, quant));
		LOGGER.info("Order created");
		return order;
	}
	
	@Override
	public Order update() 
	{
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Would you like to add an item? (Y/N)");
		String ans = utils.getString();
		if(ans.toLowerCase().equals("y"))
		{
			LOGGER.info("Please enter an item ID");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter a customer ID");
			Long cust_id = utils.getLong();
			LOGGER.info("Please enter a quantity");
			Long quantity = utils.getLong();
			int quant = Math.toIntExact(quantity);
			Order order = orderDAO.create(new Order(id, itemDAO.readItem(item_id), customerDAO.readCustomer(cust_id), quant));
			LOGGER.info("Order Updated");
			return order;
		}
		else
		{			
			LOGGER.info("Would you like to remove an item? (Y/N)");
			String ans2 = utils.getString();
			if(ans2.toLowerCase().equals("y"))
			{			
				LOGGER.info("Please enter an item ID");
				Long item_id = utils.getLong();
				Order order = orderDAO.deleteItem(item_id,id);
				return order;
			}
			else
			{
				LOGGER.info("Please enter a customer ID");
				Long cust_id = utils.getLong();
				LOGGER.info("Please enter an item ID");
				Long item_id = utils.getLong();
				LOGGER.info("Please enter a quantity");
				Long quantity = utils.getLong();
				int quant = Math.toIntExact(quantity);
				Order order = orderDAO.update(new Order(id, itemDAO.readItem(item_id), customerDAO.readCustomer(cust_id), quant));
				LOGGER.info("Order Updated");
				return order;
			}
		}
		
		
		//LOGGER.info("Order Updated");
		//return order;
	}
	@Override
	public int delete() 
	{
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}
}
