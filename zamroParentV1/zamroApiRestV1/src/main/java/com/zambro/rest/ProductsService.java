package com.zambro.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.zamroLogic.obj.ProductCategoryObject;
import com.zamroLogic.services.ProductService;

/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/

@Path("/products")
public class ProductsService {

	@GET
	@Path("/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts(@PathParam("max") Integer max) {
		ProductService productService = new ProductService();
		Gson gson = new Gson();
		return Response.status(201).entity(gson.toJson(productService.findAll(max)).toString()).build();

	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteProductById(@PathParam("id") String id) {
		ProductService productService = new ProductService();
		productService.deleteProduct(id);
		return Response.status(200).build();
	}

	@DELETE
	@Path("/delete")
	public Response deleteAll() {
		ProductService productService = new ProductService();
		productService.deleteAll();
		return Response.status(200).build();
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(String product) {
		ProductService productService = new ProductService();
		Gson gson = new Gson();
		ProductCategoryObject element = new ProductCategoryObject();
		element = gson.fromJson(product, ProductCategoryObject.class);
		List<ProductCategoryObject> productList = new ArrayList<ProductCategoryObject>();
		productList.add(element);
		productService.insertProduct(productList);
		return Response.status(200).build();
	}
}