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

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest 
{
	@Mock
	private Utils utils;

	@Mock
	private ItemDAO itemDao;

	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void testCreate() 
	{
		final String name = "tea";
		final double price = 2.99;
		final Item created = new Item(name, price);

		Mockito.when(utils.getString()).thenReturn(name);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(itemDao.create(created)).thenReturn(created);

		assertEquals(created, itemController.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemDao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() 
	{
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "coffee", 3.99));

		Mockito.when(itemDao.readAll()).thenReturn(items);

		assertEquals(items, itemController.readAll());

		Mockito.verify(itemDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() 
	{
		Item updated = new Item(1L, "matcha", 2.50);
		String name = "matcha";
		Long id = 1L;
		Double price = 2.50;
		Mockito.when(this.utils.getLong()).thenReturn(id);
		Mockito.when(this.utils.getString()).thenReturn(name);
		Mockito.when(this.utils.getDouble()).thenReturn(price);
		Mockito.when(this.itemDao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.itemController.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() 
	{
		final long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(itemDao.delete(id)).thenReturn(1);

		assertEquals(1L, this.itemController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemDao, Mockito.times(1)).delete(id);
	}
}
