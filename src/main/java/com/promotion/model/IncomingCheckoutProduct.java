package com.promotion.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomingCheckoutProduct {
	
	public IncomingCheckoutProduct(String productSKU, int quantity) {
		super();
		this.productSKU = productSKU;
		this.quantity = quantity;
	}
	public String getProductSKU() {
		return productSKU;
	}
	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private String productSKU;
	private int quantity ;

}
