package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;



public class OrderDAOTest 
{

	private final OrderDAO orderDAO = new OrderDAO();
	
	//private final OrderDAO DAO = new OrderDAO();
	
	
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
		Item item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer(1L, "Jean-Luc Picard");
		int quant = 3;
		final Order created = new Order(2L, item, cust, quant);
		assertEquals(created, orderDAO.create(created));
	}
	
	@Test
	public void testCreateFail() 
	{
		Customer cust = new Customer(3L, "Jean-Luc Pi';card");
		Item item = new Item(3L, "Tea, Earl Gr';ey, Hot", 3.50);
		int quant = 3;
		final Order created = new Order(1L, item, cust, quant);
		assertNull(orderDAO.create(created));
	}
	
	@Test
	public void testReadAll() 
	{
		List<Order> expected = new ArrayList<>();
		Item item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer(1L, "Jean-Luc Picard");
		int quant = 3;
		expected.add(new Order(1L, item, cust, quant));
		assertEquals(expected, orderDAO.readAll());
	}
	
	@Test
	public void testReadLatest() 
	{
		Item item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer(1L, "Jean-Luc Picard");
		assertEquals(new Order(1L, item, cust, 3), orderDAO.readLatest());
	}
	
	@Test
	public void testRead() 
	{
		final long id = 1L;
		Item item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer(1L, "Jean-Luc Picard");
		int quant = 3;
		assertEquals(new Order(id, item, cust, quant ), orderDAO.readOrder(id));
	}
	
	@Test
	public void testReadFail() 
	{
		final long id = 0L;
		assertNull(orderDAO.readOrder(id));
	}
	
	@Test
	public void testUpdate() 
	{
		Customer cust = new Customer(2L, "Worf");
		Item item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
		Order updated = new Order(1L, item, cust, 5);
		assertEquals(updated, orderDAO.update(updated));

	}
	
	@Test
	public void testDelete() 
	{
		assertEquals(1, orderDAO.delete(1));
	}
	
	@Test
	public void testDeleteItem()
	{
		long item_id = 1;
		long order_id = 1;
//		this.item = new Item(1L, "Tea, Earl Grey, Hot", 3.50);
//		this.cust = new Customer(1L, "Jean-Luc Picard");
		assertNull(orderDAO.deleteItem(item_id, order_id));
	}
	
	@Test
	public void testCalcCost()
	{
		long id = 1;
		assertEquals(10.5, orderDAO.calcCost(id), 0);
	}
}
