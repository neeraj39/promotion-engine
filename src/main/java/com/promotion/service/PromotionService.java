package com.promotion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promotion.model.IncomingCheckoutProduct;
import com.promotion.model.ProductSKUPrice;
import com.promotion.model.PromotionRules;

@Service
public class PromotionService {
	

	/**
	 * @param checkOutProducts
	 * @return
	 */
	public int processCheckout(final List<IncomingCheckoutProduct> checkOutProducts) {
		ProductSKUPrice productSKUPrice = new ProductSKUPrice();
		PromotionRules promotionRules = new PromotionRules();
		
		return calculateTotal(checkOutProducts, promotionRules, productSKUPrice);
	}

	/**
	 * @param checkOutProducts
	 * @param promotionRules
	 * @param productSKUPrice
	 * @return
	 */
	private int calculateTotal(List<IncomingCheckoutProduct> checkOutProducts, PromotionRules promotionRules, ProductSKUPrice productSKUPrice) {
		int sum = 0;
		for(IncomingCheckoutProduct checkOutProduct : checkOutProducts) {
			String promotionForSKU = getPromotionForSKU(checkOutProduct, promotionRules);
			if(promotionForSKU != null) {
				sum = sum + calculatePromotionTotal(checkOutProduct,promotionForSKU,productSKUPrice);
			} else {
				sum += productSKUPrice.getProductSKUPrice().get(checkOutProduct.getProductSKU()) * checkOutProduct.getQuantity();
			}
			
			
		}
		return sum;
	}


	/**
	 * @param checkOutProduct
	 * @param promotionRules
	 * @return
	 */
	private String getPromotionForSKU(IncomingCheckoutProduct checkOutProduct, PromotionRules promotionRules) {
		List keys = new ArrayList(promotionRules.getEngineRules().keySet());
		for (int i = 0; i < keys.size(); i++) {
			String value = (String) keys.get(i);
			if(value.contains(checkOutProduct.getProductSKU()))
			return value;
		}
		return null;
	}

	/**
	 * @param checkOutProduct
	 * @param promotionForSKU
	 * @param productSKUPrice
	 * @return
	 */
	private int calculatePromotionTotal(IncomingCheckoutProduct checkOutProduct, String promotionForSKU, ProductSKUPrice productSKUPrice) {
		Character promocode = promotionForSKU.charAt(1);
		int quantity = checkOutProduct.getQuantity();
		int promoQuantity;
		int sum = 0;
		switch(promocode) {
		case '*': promoQuantity = Character.getNumericValue(promotionForSKU.charAt(0));
				if(quantity > promoQuantity) {
					while (quantity % promoQuantity != 0) {
						sum +=productSKUPrice.getProductSKUPrice().get(checkOutProduct.getProductSKU());
						quantity --;
					}
					sum += Integer.parseInt(promotionForSKU.substring(4)) * (quantity / promoQuantity) ;
				} else {
					sum += productSKUPrice.getProductSKUPrice().get(checkOutProduct.getProductSKU()) * checkOutProduct.getQuantity();
				}
			
		}
		return sum;
	}

}
