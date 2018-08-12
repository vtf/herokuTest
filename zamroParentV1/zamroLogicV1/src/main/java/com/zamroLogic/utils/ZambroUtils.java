package com.zamroLogic.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zamroLogic.obj.CategoryObj;
import com.zamroLogic.obj.ProductCategoryObject;
import com.zamroLogic.obj.ProductObj;


/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ZambroUtils {

    private final static Logger logger = LoggerFactory.getLogger(ZambroUtils.class);
	
	public static List<ProductCategoryObject> getProductCategory(List<ProductObj> products,
			HashMap<Long, String> categories) {

		List<ProductCategoryObject> toret = new ArrayList<ProductCategoryObject>();

		for (ProductObj productObj : products) {
			ProductCategoryObject pcobj = new ProductCategoryObject();
			List<CategoryObj> categoryList = new ArrayList<CategoryObj>();
			if (categories.get(productObj.getCategoryId()) != null) {
				CategoryObj category = new CategoryObj();
				category.setCategoryId(productObj.getCategoryId());
				category.setName(categories.get(productObj.getCategoryId()));
				categoryList.add(category);

			}

			pcobj.setDescription(productObj.getDescription());
			pcobj.setMinOrderQuantity(productObj.getMinOrderQuantity());
			pcobj.setName(productObj.getName());
			pcobj.setPurchasePrice(productObj.getPurchasePrice());
			pcobj.setUnitOfMeasure(productObj.getUnitOfMeasure());
			pcobj.setZambroId(productObj.getZambroId());
			pcobj.setAvailable(productObj.isAvailable());
			pcobj.setCategories(categoryList);

			toret.add(pcobj);
		}

		return toret;
	}
	
	public static List<ProductObj> getProductFromFile(String csvFile) {

		List<List<String>> list = parseCsvFile(csvFile);
		List<ProductObj> productList = new ArrayList<ProductObj>();

		for (List<String> l : list) {
			
			ProductObj product = new ProductObj();
			product.setCategoryId(Long.valueOf(l.get(5)));
			product.setDescription(l.get(2));
			if(!l.get(3).isEmpty()){
			product.setMinOrderQuantity(Double.valueOf(l.get(3)));
			}
			product.setName(l.get(1));
			product.setPurchasePrice(Double.valueOf(l.get(6)));
			product.setUnitOfMeasure(l.get(4));
			product.setZambroId((l.get(0)));
			product.setAvailable(Boolean.valueOf(l.get(7)));

			productList.add(product);
		}

		return productList;
	}

	public static HashMap<Long, String> getCategoriesFromFile(String csvFile) {

		List<List<String>> list = parseCsvFile(csvFile);
		HashMap<Long, String> categoryMap = new HashMap<Long, String>();

		for (List<String> l : list) {

			if (l != null && l.size() == 2) {
				CategoryObj categoryObj = new CategoryObj();
				categoryObj.setCategoryId(Long.parseLong(l.get(0)));
				categoryObj.setName(l.get(1));
				categoryMap.put(categoryObj.getCategoryId(), categoryObj.getName());
			}
		}

		return categoryMap;
	}

	private static List<List<String>> parseCsvFile(String csvFile) {
		List<List<String>> toret = new ArrayList<List<String>>();
		try {
			String line = "";

			BufferedReader br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				List<String> list = customSplit(line);
				toret.add(list);
			}
			br.close();
		} catch (IOException e) {
			logger.error("Error executuing parseCsvFile", e);
		}

		// Deleted first header element
		toret.remove(0);
		return toret;
	}

	private static List<String> customSplit(String line) {

		List<String> dividedLine = new ArrayList<String>();

		while (line.length() > 0) {
			if (line.charAt(0) != '"') {

				// System.out.println(line.indexOf(","));

				if (line.contains(",")) {
					dividedLine.add(line.substring(0, line.indexOf(",")));
					line = line.substring(line.indexOf(",") + 1, line.length());
				} else {
					dividedLine.add(line);
					line = "";
				}

			} else if (line.charAt(0) == '"') {
				dividedLine.add(line.substring(line.indexOf("\""), line.indexOf("\",")) + "\"");
				line = line.substring(line.indexOf("\",") + 2, line.length());
			}

			else {
				dividedLine.add(line);
				line = "";
			}
		}
		return dividedLine;
	}

	public void exportToFile(List<ProductObj> poducts, String fileExtension){
		
	}
	
}
