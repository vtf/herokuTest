package com.zambroLogic.utils;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.zamroLogic.obj.ProductCategoryObject;
import com.zamroLogic.obj.ProductObj;
import com.zamroLogic.utils.ZambroUtils;

public class ZambroUtilsTest {


	@Test
	public void csvProductParserTest() {

		List<ProductObj> prdoucts = ZambroUtils.getProductFromFile("src/test/resources/ProductData.csv");

		assertTrue(prdoucts.size() > 0);

		assertTrue(prdoucts.get(0).getZambroId().equalsIgnoreCase("1"));
		assertTrue(prdoucts.get(0).getDescription().equals("\"Description, complex\""));
		
		assertTrue(prdoucts.get(1).getZambroId().equalsIgnoreCase("2"));
		assertTrue(prdoucts.get(1).getDescription().equals("Description complex"));
	
	}

	@Test
	public void csvCategoryParserTest() {

		HashMap<Long, String> categories = ZambroUtils.getCategoriesFromFile("src/test/resources/CategoryData.csv");

		assertTrue(categories.size() > 0);
		assertTrue(categories.containsKey(48622l));
		assertTrue(categories.get(48622l).equals("Decoupeerzagen en recipro zaagbladen"));
	}

	
	@Test
	public void categoryProductTest() {

		List<ProductObj> products = ZambroUtils.getProductFromFile("src/test/resources/ProductData.csv");
		HashMap<Long, String> categories = ZambroUtils.getCategoriesFromFile("src/test/resources/CategoryData.csv");
		
		List<ProductCategoryObject>  productCatyegory= ZambroUtils.getProductCategory(products, categories);

		assertTrue(productCatyegory.get(1).getCategories().get(0).getCategoryId()==48622l);

		assertTrue(productCatyegory.get(1).getCategories().get(0).getName().equalsIgnoreCase("Decoupeerzagen en recipro zaagbladen"));
		
		 assertTrue(productCatyegory.size() > 0);
	}
	
	
	
}
