package com.zamroLogic.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.zamroLogic.repository.ProductRepository;

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan
public class ConfigApp {

	@Value("${db2.name}")
	private String name;

	@Value("${mongo.user}")
	private String mongoUser;

	@Value("${mongo.pass}")
	private String mongoPass;

	@Value("${mongo.machine}")
	private String mongoMachine;

	@Autowired
	@Bean(name = "config")
	public ConfigApp getConfigApp() {
		ConfigApp configApp = new ConfigApp();
		configApp.setName(this.name);

		return configApp;
	}

	// To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	@Bean(name = "mongoDbFactory")
	public MongoDbFactory mongoDbFactory() {

		String connectionString = "mongodb://vtf180:9Eldv8BxOf9rNMZF@cluster0-shard-00-00-vaohf.mongodb.net:27017,cluster0-shard-00-01-vaohf.mongodb.net:27017,cluster0-shard-00-02-vaohf.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true";

		MongoClient mongoClient=null;

			mongoClient = new MongoClient(new MongoClientURI(connectionString));
	

		// mongoClient.getDatabase("admin")
		// .runCommand(new Document("ping", 1));
		//
		return new SimpleMongoDbFactory(mongoClient, "Orders");

	}

	@Autowired
	@Bean(name = "mongoTemplate")
	public MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}

	@Autowired
	@Bean(name = "getProductRepository")
	public ProductRepository productRepository() throws Exception {

		ProductRepository productRepository = new ProductRepository();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		productRepository.setMongoTemplate(mongoTemplate);
		return productRepository;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}