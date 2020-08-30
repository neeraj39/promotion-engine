package com.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promotion.model.IncomingCheckoutProduct;
import com.promotion.service.PromotionService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
	
	@Autowired
	private PromotionService promotionService;
	
	@PostMapping(value = "/promotion")
	public int checkoutWithPromotionTotal(@RequestBody final List<IncomingCheckoutProduct> checkOutProducts) {
		return promotionService.processCheckout(checkOutProducts);
		
	}
}
