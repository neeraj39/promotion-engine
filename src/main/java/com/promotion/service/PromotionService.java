package com.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promotion.model.IncomingCheckoutProduct;
import com.promotion.model.ProductSKUPrice;
import com.promotion.model.PromotionRules;

@Service
public class PromotionService {
	

	public int processCheckout(final List<IncomingCheckoutProduct> checkOutProducts) {
		ProductSKUPrice productSKUPrice = new ProductSKUPrice();
		PromotionRules promotionRules = new PromotionRules();
		return 0;
	}

}
