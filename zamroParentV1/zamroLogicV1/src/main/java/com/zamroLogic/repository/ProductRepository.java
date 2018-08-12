package com.zamroLogic.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.zamroLogic.obj.ProductCategoryObject;

/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/

@Repository
public class ProductRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	private final static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

	public void deleteAllProduct() {
		mongoTemplate.getCollection("zambroProduct").drop();
	}

	public void insertProduct(ProductCategoryObject product) {

		Gson gson = new GsonBuilder().create();

		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(product).toString());

		mongoTemplate.getCollection("zambroProduct").insertOne(new Document(dbObject.toMap()));

	}

	public void insertProductList(List<ProductCategoryObject> products) {
		List<Document> documentList = new ArrayList<Document>();

		logger.info("List of product size: " + products.size());
		int count = 0;
		for (ProductCategoryObject product : products) {
			// convert JSON to DBObject directly

			Gson gson = new GsonBuilder().create();

			DBObject dbObject = (DBObject) JSON.parse(gson.toJson(product).toString());

			documentList.add(new Document(dbObject.toMap()));

		}

		logger.info("Inserting in mongo");
		mongoTemplate.getCollection("zambroProduct").insertMany(documentList);

		logger.info("Inserted in mongo");
	}

	public void deleteProductById(String id) {

		BasicDBObject query = new BasicDBObject();
		query.put("zambroId", id);

		mongoTemplate.getCollection("zambroProduct").deleteOne(new Document(query.toMap()));

	}

	public ProductCategoryObject findBydId(String id) {

		BasicDBObject query = new BasicDBObject();
		query.put("zambroId", id);

		Iterator<Document> cursor = mongoTemplate.getCollection("zambroProduct").find(query).iterator();

		while (cursor.hasNext()) {
			Gson gson = new GsonBuilder().create();

			ProductCategoryObject element = gson.fromJson(cursor.next().toJson(), ProductCategoryObject.class);

			return element;
		}
		return null;
	}

	public List<ProductCategoryObject> findAllProducts(int max) {

		Iterator<Document> cursor = mongoTemplate.getCollection("zambroProduct").find().maxScan(Long.valueOf(max))
				.iterator();
		List<ProductCategoryObject> toret = new ArrayList<ProductCategoryObject>();
		while (cursor.hasNext()) {
			Gson gson = new GsonBuilder().create();

			ProductCategoryObject element = gson.fromJson(cursor.next().toJson(), ProductCategoryObject.class);
			toret.add(element);
		}

		toret = toret.subList(0, max);
		return toret;
	}

	public Long countProduct() {

		return mongoTemplate.getCollection("zambroProduct").count();

	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	};

}