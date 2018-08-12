package com.zambroLogic.repository;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zamroLogic.conf.ConfigApp;
import com.zamroLogic.obj.ProductCategoryObject;
import com.zamroLogic.obj.ProductObj;
import com.zamroLogic.repository.ProductRepository;
import com.zamroLogic.utils.ZambroUtils;


/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ZambroRepositoryTest {


    private final Logger logger = LoggerFactory.getLogger(ZambroRepositoryTest.class);
    
	@Autowired
	private ProductRepository productRepository;

	@Before

	public void init() {
		logger.info("Init Spring content");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigApp.class);
		productRepository = (ProductRepository) applicationContext.getBean("getProductRepository");

	}

	@Test
	public void categoryProductTest() {


		logger.info("Executing categoryProductTest");
		List<ProductObj> products = ZambroUtils.getProductFromFile("src/test/resources/ProductData.csv");
		HashMap<Long, String> categories = ZambroUtils.getCategoriesFromFile("src/test/resources/CategoryData.csv");

		List<ProductCategoryObject> productCatyegory = ZambroUtils.getProductCategory(products, categories);

		assertTrue(productCatyegory.get(1).getCategories().get(0).getCategoryId() == 48622l);

		assertTrue(productCatyegory.get(1).getCategories().get(0).getName()
				.equalsIgnoreCase("Decoupeerzagen en recipro zaagbladen"));

	}

	@Test
	public void categoryProductDeleteTest() {


		logger.info("Executing categoryProductDeleteTest");
		List<ProductObj> products = ZambroUtils.getProductFromFile("src/test/resources/ProductData.csv");
		HashMap<Long, String> categories = ZambroUtils.getCategoriesFromFile("src/test/resources/CategoryData.csv");

		List<ProductCategoryObject> productCatyegory = ZambroUtils.getProductCategory(products, categories);

		productRepository.deleteAllProduct();
		productRepository.insertProductList(productCatyegory);

		System.out.println(productRepository.countProduct());
		assertTrue(productRepository.countProduct() == 5l);

		productRepository.findAllProducts(5);

		assertTrue(productRepository.findAllProducts(5).size() == 5);

		assertTrue(productRepository.findBydId("1") != null);

		productRepository.deleteProductById("1");

		assertTrue(productRepository.findBydId("1") == null);
		productRepository.insertProductList(productCatyegory);
		productRepository.deleteAllProduct();
	}

	@Test
	public void categoryProductFindTest() {

		logger.info("Executing categoryProductFindTest");

		List<ProductObj> products = ZambroUtils.getProductFromFile("src/test/resources/ProductData.csv");
		HashMap<Long, String> categories = ZambroUtils.getCategoriesFromFile("src/test/resources/CategoryData.csv");

		List<ProductCategoryObject> productCatyegory = ZambroUtils.getProductCategory(products, categories);

		productRepository.deleteAllProduct();
		productRepository.insertProductList(productCatyegory);

		assertTrue(productRepository.countProduct() == 5l);

		productRepository.findAllProducts(5);

		assertTrue(productRepository.findAllProducts(5).size() == 5);

		assertTrue(productRepository.findBydId("1") != null);

		productRepository.deleteProductById("1");

		assertTrue(productRepository.findBydId("1") == null);
		productRepository.insertProductList(productCatyegory);
		productRepository.deleteAllProduct();

	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
