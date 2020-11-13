package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order>
{
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException 
	{
		Long id = resultSet.getLong("order_id");
		Item item = new Item(resultSet.getLong("item_id"), resultSet.getString("item_name"), resultSet.getDouble("price"));
		Customer customer = new Customer(resultSet.getLong("cust_id"), resultSet.getString("cust_name"));
		int quantity = resultSet.getInt("quantity");
		return new Order(id, item, customer, quantity);
	}
	public Order modelFromResultSetId(ResultSet resultSet) throws SQLException 
	{
		Long id = resultSet.getLong("order_id");
//		Item item = new Item(resultSet.getLong("item_id"), resultSet.getString("item_name"), resultSet.getDouble("price"));
//		Customer customer = new Customer(resultSet.getLong("cust_id"), resultSet.getString("cust_name"));
//		int quantity = resultSet.getInt("quantity");
		return new Order(id);
	}
	
	@Override
	public List<Order> readAll() 
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select "
						+ "orders.order_id, customers.cust_id, customers.cust_name, "
						+ "items.item_id, items.item_name, items.price, "
						+ "orders_items.quantity from orders_items " + 
						"join orders on orders_items.fk_order_id = orders.order_id " + 
						"join customers on customers.cust_id = orders.fk_cust_id " + 
						"join items on items.item_id = orders_items.fk_item_id "
						+ "order by orders.order_id;");) 
		{
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Order readLatest() 
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT "
						+ "orders.order_id, customers.cust_id, customers.cust_name, "
						+ "items.item_id, items.item_name, items.price, "
						+ "orders_items.quantity from orders_items "
						+ "join orders on orders_items.fk_order_id = orders.order_id " + 
						"join customers on customers.cust_id = orders.fk_cust_id " + 
						"join items on items.item_id = orders_items.fk_item_id "
						+ "ORDER BY orders.order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order create(Order order) 
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders (fk_cust_id) values('" + order.getCustomer().getId()
					+  "')");// + "INSERT INTO orders_items (fk_order_id, fk_item_id, quantity) values('" + order.getId()
			//+ "','" + order.getItem().getId() + "','" + order.getQuantity()
			//		+  "');");
			
			//NOT A GOOD LINE, RETURNS ALL IDs FOR SELECTED CUSTOMER
			ResultSet resultSet = statement.executeQuery("SELECT order_id FROM orders WHERE fk_cust_id = " + order.getCustomer().getId());
			resultSet.next();
			order.setId(modelFromResultSetId(resultSet).getId());
			statement.executeUpdate("INSERT INTO orders_items (fk_order_id, fk_item_id, quantity) values('" + order.getId()
			+ "','" + order.getItem().getId() + "','" + order.getQuantity()
					+  "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT orders.order_id, customers.cust_id, customers.cust_name, "
						+ " items.item_id, items.item_name, items.price, "
						+ "orders_items.quantity from orders_items "
						+ "join orders on orders_items.fk_order_id = orders.order_id "
						+ "join customers on customers.cust_id = orders.fk_cust_id "
						+ "join items on items.item_id = orders_items.fk_item_id "
						+ "where orders.order_id = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	@Override
	public Order update(Order order) 
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set fk_cust_id ='" + order.getCustomer().getId()+ 
					 "' where order_id =" + order.getId());
			statement.executeUpdate("update orders_items set fk_order_id ='" + order.getId()+ 
					"', fk_item_id ='" + order.getItem().getId() + "', quantity ='" + order.getQuantity() +
					 "' where fk_item_id =" + order.getItem().getId()+ " and fk_order_id = " +order.getId());
			return readOrder(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Order deleteItem(long item_id, long order_id)
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete orders_items from orders_items join orders on orders_items.fk_order_id = orders.order_id"
					+ " where fk_item_id = " + item_id);
			//return statement.executeUpdate("delete from orders where order_id = " + id);
			return readOrder(order_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
		
	
	
	@Override
	public int delete(long id) 
	{
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete orders_items from orders_items join orders on orders_items.fk_order_id = orders.order_id"
					+ " where fk_order_id = " + id);
			return statement.executeUpdate("delete from orders where order_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public double calcCost(long id)
	{
		//Order order1 = readOrder(id);
		List<Order> orders = readAll();
		double cost = 0;
		for (Order order : orders) 
		{
			if(order.getId()==id)
			{
				cost = cost+order.getItem().getPrice()*order.getQuantity();
			}
		}
		
		return cost;
	}
}
