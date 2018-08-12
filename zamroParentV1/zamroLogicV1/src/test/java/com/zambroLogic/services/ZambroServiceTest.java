package com.zambroLogic.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zamroLogic.services.ProductService;


/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ZambroServiceTest {

	@Autowired
	private ProductService pService;

	@Before
	public void init() {

		pService = new ProductService();

	}

	@Test
	public void productServiceFindAllTest() {

		assertTrue(pService.findAll(1).size() == 1);

	}
	
	@Test
	public void productServiceDeleteAllTest() {

		pService.deleteAll();
		
	

	}


}
