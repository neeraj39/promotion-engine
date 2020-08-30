package com.promotion.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Admin
 *
 */

@Getter
@Setter
public class ProductSKUPrice {
	
	private Map<String,Integer> productSKUPrice;
	
	
	
	public ProductSKUPrice() {
		productSKUPrice = new HashMap<>();
		productSKUPrice.put("A", 20);
		productSKUPrice.put("B", 25);
		productSKUPrice.put("C", 20);
		productSKUPrice.put("D", 20);
		productSKUPrice.put("E", 20);
	}



	public Map<String, Integer> getProductSKUPrice() {
		return productSKUPrice;
	}



	public void setProductSKUPrice(Map<String, Integer> productSKUPrice) {
		this.productSKUPrice = productSKUPrice;
	}



	
	
}
