package com.zamroLogic.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.zamroLogic.conf.ConfigApp;
import com.zamroLogic.obj.ProductCategoryObject;
import com.zamroLogic.obj.ProductObj;
import com.zamroLogic.repository.ProductRepository;
import com.zamroLogic.utils.ZambroUtils;

/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ProductService {

	private final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	public ProductService() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigApp.class);
		productRepository = (ProductRepository) applicationContext.getBean("getProductRepository");
	}

	public void insertProduct(List<ProductCategoryObject> products) {

		// si no existe el id insertamos

		for (ProductCategoryObject product : products) {

			ProductCategoryObject element = productRepository.findBydId(product.getZambroId());

			if (element == null) {
				productRepository.insertProduct(product);
			}
		}
	}

	// TODO
	public void UpdateProduct(ProductObj product) {
	}

	public void deleteAll() {

		logger.info("Deleting mongoDb eleements and creating .txt file with its content");
		exportToFile(productRepository.findAllProducts(productRepository.countProduct().intValue()), ".txt");

		productRepository.deleteAllProduct();
	}

	public void deleteProduct(String idProduct) {

		productRepository.deleteProductById(idProduct);
	}

	public List<ProductCategoryObject> findAll(int max) {
		logger.info("Mongo collection is empty, we procedure to load data from file!");

		ClassPathResource resource1 = new ClassPathResource("ProductData.csv");

		ClassPathResource resource2 = new ClassPathResource("CategoryData.csv");
		File file1;
		File file2;
		try {
			file1 = resource1.getFile();
			file2 = resource2.getFile();
			String absolutePath1 = file1.getAbsolutePath();
			String absolutePath2 = file2.getAbsolutePath();

			List<ProductCategoryObject> productCategoryObjectList = ZambroUtils.getProductCategory(
					ZambroUtils.getProductFromFile(absolutePath1), ZambroUtils.getCategoriesFromFile(absolutePath2));

			productRepository.insertProductList(productCategoryObjectList);

			return productCategoryObjectList.subList(0, max);

		} catch (IOException e) {
			logger.error("Delete", e);
		}
		return productRepository.findAllProducts(max);

	}

	private void exportToFile(List<ProductCategoryObject> products, String extensionFile) {

		try {
			File statText = new File("zamro" + extensionFile);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);

			for (ProductCategoryObject product : products) {
				w.write(product.toString() + "\n");

			}
			w.close();

			logger.info("File exported to: " + statText.getAbsolutePath());
		} catch (IOException e) {
			logger.error("Error exportToFile ", e);
		}
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
