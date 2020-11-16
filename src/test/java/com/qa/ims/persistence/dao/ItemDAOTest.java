package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest 
{
	private final ItemDAO DAO = new ItemDAO();
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
		final Item created = new Item(2L, "coffee", 4.99);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testCreateFail() 
	{
		final Item created = new Item(2L, "coff';ee");
		assertNull(DAO.create(created));
	}
	
	@Test
	public void testReadAll() 
	{
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "tea", 3.50));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() 
	{
		assertEquals(new Item(1L, "tea", 3.50), DAO.readLatest());
	}
	
	@Test
	public void testRead() 
	{
		final long ID = 1L;
		assertEquals(new Item(ID, "tea", 3.50), DAO.readItem(ID));
	}
	
	@Test
	public void testReadFail() 
	{
		final long ID = 0L;
		assertNull(DAO.readItem(ID));
	}
	
	@Test
	public void testUpdate() 
	{
		final Item updated = new Item(1L, "coffee", 4.99);
		assertEquals(updated, DAO.update(updated));

	}
	
	@Test
	public void testUpdateFail() 
	{
		final Item updated = new Item(1L, "coff';ee");
		assertNull(DAO.update(updated));

	}
	
	@Test
	public void testDelete() 
	{
		assertEquals(1, DAO.delete(1));
	}
}
