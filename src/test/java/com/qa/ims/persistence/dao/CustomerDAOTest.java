package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest 
{

	private final CustomerDAO DAO = new CustomerDAO();

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
		final Customer created = new Customer(3L, "Data");
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testCreateFail() 
	{
		final Customer created = new Customer(2L, "Wo';rf");
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() 
	{
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "Jean-Luc Picard"));
		expected.add(new Customer(2L, "Worf"));
		assertEquals(expected, DAO.readAll());
	}
	
//	@Test
//	public void testReadAllFail() 
//	{
//		List<Customer> expected = new ArrayList<>();
//		expected.add(new Customer(2L, "jordan har';rison"));
//		assertEquals(new ArrayList<>(), DAO.readAll());
//	}

	@Test
	public void testReadLatest() 
	{
		assertEquals(new Customer(2L, "Worf"), DAO.readLatest());
	}
//	@Test
//	public void testReadLatestFail() 
//	{
//		assertEquals(new Customer(1L, "jordan harr';ison"), DAO.readLatest());
//	}

	@Test
	public void testRead() 
	{
		final long ID = 1L;
		assertEquals(new Customer(ID, "Jean-Luc Picard"), DAO.readCustomer(ID));
	}
	
	@Test
	public void testReadFail() 
	{
		final long ID = 0L;
		assertNull(DAO.readCustomer(ID));
	}

	@Test
	public void testUpdate() 
	{
		final Customer updated = new Customer(1L, "Worf");
		assertEquals(updated, DAO.update(updated));

	}
	
	@Test
	public void testUpdateFail() 
	{
		final Customer updated = new Customer(1L, "Wo';rf");
		assertNull(DAO.update(updated));

	}

	@Test
	public void testDelete() 
	{
		assertEquals(1, DAO.delete(2L));
	}
}
