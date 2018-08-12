package com.zamroLogic.obj;


/*@author  Victor Trapiello
* @version 1.0
* @since   2018-07-30 
*/
public class ProductObj {

	private String zambroId;
	private String name;
	private String description;
	private Double minOrderQuantity;
	private String unitOfMeasure;

	private long categoryId;

	private Double purchasePrice;

	private boolean available;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
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
		ProductObj other = (ProductObj) obj;
		if (available != other.available)
			return false;
		if (categoryId != other.categoryId)
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
		return "ProductObj [zambroId=" + zambroId + ", name=" + name + ", description=" + description
				+ ", minOrderQuantity=" + minOrderQuantity + ", unitOfMeasure=" + unitOfMeasure + ", categoryId="
				+ categoryId + ", purchasePrice=" + purchasePrice + ", available=" + available + "]";
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

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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

	
}
