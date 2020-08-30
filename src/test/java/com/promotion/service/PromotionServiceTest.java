package com.promotion.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.promotion.model.IncomingCheckoutProduct;
import com.promotion.model.ProductSKUPrice;
import com.promotion.model.PromotionRules;

class PromotionServiceTest {
	
	ProductSKUPrice productSKUPrice;
	
	PromotionRules promotionRules;
	
	List<IncomingCheckoutProduct> incomingCheckoutProducts = new ArrayList<>();
	
	PromotionService PromotionService ;

	@BeforeEach
	void setUp() throws Exception {
		 
		 PromotionService =  new PromotionService();
	}

	@Test
	void testShouldCalculatepromotionTotalForSkuA() {
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("A",4));
		int sum = PromotionService.processCheckout(incomingCheckoutProducts);
		Assertions.assertEquals(sum, 150);
	}
	
	@Test
	void testShouldCalculateTotalForSkuAwithoutAddingPromotion() {
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("A",1));
		int sum = PromotionService.processCheckout(incomingCheckoutProducts);
		Assertions.assertEquals(sum, 20);
	}
	
	@Test
	void testShouldCalculatepromotionTotalForSkuB() {
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("B",3));
		int sum = PromotionService.processCheckout(incomingCheckoutProducts);
		Assertions.assertEquals(sum, 55);
	}
	@Test
	void testShouldCalculatepromotionTotalForSkuBAndSkuA() {
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("B",3));
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("A",4));
		int sum = PromotionService.processCheckout(incomingCheckoutProducts);
		Assertions.assertEquals(sum, 205);
	}
	
	@Test
	void testShouldCalculatepromotionTotalForSkuC() {
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("C",3));
		incomingCheckoutProducts.add(new IncomingCheckoutProduct("D",4));
		int sum = PromotionService.processCheckout(incomingCheckoutProducts);
		Assertions.assertEquals(sum, 80);
	}
	
	@Test
	void testShouldNotCalculatepromotionTotalForSkuE() {
	}

}
