package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest 
{
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private CustomerDAO customerDAO;

	@InjectMocks
	private OrderController orderController;
	
	@Test
	public void testCreate() 
	{
		final Long cust_id = 1L;
		final Long item_id = 1L;
		final Long quant = 2L;
		int quant_int = Math.toIntExact(quant);
		Item item = new Item( "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer( "Jean-Luc Picard");
		Order created = new Order( item, cust, quant_int);
		
		Mockito.when(utils.getLong()).thenReturn(cust_id, item_id, quant);
		Mockito.when(itemDAO.readItem(item_id)).thenReturn(item);		
		Mockito.when(customerDAO.readCustomer(cust_id)).thenReturn(cust);
		Mockito.when(orderDAO.create(created)).thenReturn(created);
		
		assertEquals(created, orderController.create());
		Mockito.verify(itemDAO, Mockito.times(1)).readItem(item_id);
		Mockito.verify(customerDAO, Mockito.times(1)).readCustomer(cust_id);
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() 
	{
		List<Order> orders = new ArrayList<>();
		Item item = new Item( "Tea, Earl Grey, Hot", 3.50);
		Customer cust = new Customer( "Jean-Luc Picard");
		orders.add(new Order(1L, item, cust, 7));
		String ans1 = "y";
		String ans2 = "n";
		Mockito.when(utils.getString()).thenReturn(ans1, ans2);
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderDAO.calcCost(1L)).thenReturn(3.5*7);
		Mockito.when(orderDAO.readAll()).thenReturn(orders);

		assertEquals(orders, orderController.readAll());

		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() 
	{
		
		Long item_id = 1L;
		
		Long order_id = 1L;		
		String ans1 = "y";
		
		Long quant = 4L;
		int quant_int = Math.toIntExact(quant);
		Item item = new Item("Tea, Earl Grey, Hot", 2.50);
		Customer cust = new Customer("Jean-Luc Picard");
		Order updated = new Order(1L, item, cust, quant_int);
		
		
		Mockito.when(this.utils.getLong()).thenReturn(order_id, item_id, quant);
		Mockito.when(this.utils.getString()).thenReturn(ans1);

		Mockito.when(itemDAO.readItem(item_id)).thenReturn(item);
		Mockito.when(orderDAO.readOrder(order_id)).thenReturn(updated);
		
		
		Mockito.when(this.orderDAO.create(new Order(order_id, itemDAO.readItem(item_id), orderDAO.readOrder(order_id).getCustomer(), quant_int))).thenReturn(updated);
		//Mockito.when(this.orderDAO.create(Mockito.any(Order.class))).thenReturn(updated);
		

		assertEquals(updated, this.orderController.update());

		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.orderDAO, Mockito.times(1)).create(updated);
	}
	
	@Test
	public void testUpdate2() 
	{
		
		Long item_id = 1L;
		
		Long order_id = 1L;		
		String ans1 = "y";
		String ans2 = "n";
		Long quant = 4L;
		int quant_int = Math.toIntExact(quant);
		Item item = new Item("Tea, Earl Grey, Hot", 2.50);
		Customer cust = new Customer("Jean-Luc Picard");
		Order updated = new Order(1L, item, cust, quant_int);
		
		
		Mockito.when(this.utils.getLong()).thenReturn(order_id, item_id);
		Mockito.when(this.utils.getString()).thenReturn(ans2, ans1);
		Mockito.when(orderDAO.deleteItem(item_id,order_id)).thenReturn(updated);


		assertEquals(updated, this.orderController.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.orderDAO, Mockito.times(1)).deleteItem(item_id, order_id);
	}
	
	@Test
	public void testUpdate3() 
	{
		
		Long item_id = 1L;
		Long cust_id = 1L;
		Long order_id = 1L;		
		
		String ans2 = "n";
		Long quant = 4L;
		int quant_int = Math.toIntExact(quant);
		Item item = new Item("Tea, Earl Grey, Hot", 2.50);
		Customer cust = new Customer("Jean-Luc Picard");
		Order updated = new Order(1L, item, cust, quant_int);
		
		
		Mockito.when(this.utils.getLong()).thenReturn(order_id, cust_id,item_id, quant);
		Mockito.when(this.utils.getString()).thenReturn(ans2, ans2);

		Mockito.when(customerDAO.readCustomer(cust_id)).thenReturn(cust);
		Mockito.when(itemDAO.readItem(item_id)).thenReturn(item);
	
		Mockito.when(this.orderDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.orderController.update());

		Mockito.verify(this.utils, Mockito.times(4)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.orderDAO, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() 
	{
		final long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(orderDAO.delete(id)).thenReturn(1);

		assertEquals(1L, this.orderController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(id);
	}
}
