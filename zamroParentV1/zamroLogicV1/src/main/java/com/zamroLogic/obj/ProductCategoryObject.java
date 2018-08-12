package com.zamroLogic.obj;

import java.util.List;


/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ProductCategoryObject {
	
	private String zambroId;
	private String name;
	private String description;
	private Double minOrderQuantity;
	private String unitOfMeasure;

	private Double purchasePrice;

	private boolean available;
	
	private List<CategoryObj> categories;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((categories == null) ? 0 : categories.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((minOrderQuantity == null) ? 0 : minOrderQuantity.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((purchasePrice == null) ? 0 : purchasePrice.hashCode());
		result = prime * result + ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
		result = prime * result + ((zambroId == null) ? 0 : zambroId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategoryObject other = (ProductCategoryObject) obj;
		if (available != other.available)
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (minOrderQuantity == null) {
			if (other.minOrderQuantity != null)
				return false;
		} else if (!minOrderQuantity.equals(other.minOrderQuantity))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (purchasePrice == null) {
			if (other.purchasePrice != null)
				return false;
		} else if (!purchasePrice.equals(other.purchasePrice))
			return false;
		if (unitOfMeasure == null) {
			if (other.unitOfMeasure != null)
				return false;
		} else if (!unitOfMeasure.equals(other.unitOfMeasure))
			return false;
		if (zambroId == null) {
			if (other.zambroId != null)
				return false;
		} else if (!zambroId.equals(other.zambroId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductCategoryObject [zambroId=" + zambroId + ", name=" + name + ", description=" + description
				+ ", minOrderQuantity=" + minOrderQuantity + ", unitOfMeasure=" + unitOfMeasure + ", purchasePrice="
				+ purchasePrice + ", available=" + available + ", categories=" + categories + "]";
	}

	public String getZambroId() {
		return zambroId;
	}

	public void setZambroId(String zambroId) {
		this.zambroId = zambroId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMinOrderQuantity() {
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(Double minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<CategoryObj> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryObj> categories) {
		this.categories = categories;
	}

		
}
