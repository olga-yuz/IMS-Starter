package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) {
		IMS ims = new IMS();
//		ItemDAO itemDAO = new ItemDAO();
//		Long id = (long) 1;
		
		ims.imsSystem();
		//Item item = itemDAO.readItem(id);
		//System.out.println(item);
		LOGGER.info("SO LONG!");
	}

}
