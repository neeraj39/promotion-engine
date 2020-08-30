package com.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncomingCheckoutProduct {
	
	private String productSKU;
	private int quantity ;
}
