package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest 
{
	private final OrderDAO DAO = new OrderDAO();
	@BeforeClass
	public static void init() 
	{
		DBUtils.connect("root", "R1k3rsB34rd");
		
	}
	
	@Before
	public void setup() 
	{
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() 
	{
		final Item item = new Item(2L, "coffee", 4.99);
		final Customer cust = new Customer(2L, "Worf");
		final Order created = new Order(2L, item, cust, 5);
		assertEquals(created, DAO.create(created));
	}
}
