package com.promotion.model;

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
		productSKUPrice.put("A", 20);
	}



	
	
}
