package com.promotion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				sum = sum + calculatePromotionTotal(checkOutProduct,checkOutProducts, promotionForSKU, productSKUPrice, promotionRules);
				promotionRules.getEngineRules().put(promotionForSKU, true);
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
	 * @param product
	 * @param checkOutProducts 
	 * @param promotionForSKU
	 * @param productSKUPrice
	 * @param promotionRules 
	 * @return
	 */
	private int calculatePromotionTotal(IncomingCheckoutProduct product, List<IncomingCheckoutProduct> checkOutProducts,
			String promotionForSKU, ProductSKUPrice productSKUPrice, PromotionRules promotionRules) {
		Character promocode = promotionForSKU.charAt(1);
		int quantity = product.getQuantity();
		int promoQuantity;
		int sum = 0;
		if (!promotionRules.getEngineRules().get(promotionForSKU)) {
			switch (promocode) {
			case '*':
				promoQuantity = Character.getNumericValue(promotionForSKU.charAt(0));
				if (quantity > promoQuantity) {
					while (quantity % promoQuantity != 0) {
						sum += productSKUPrice.getProductSKUPrice().get(product.getProductSKU());
						quantity--;
					}
					sum += Integer.parseInt(promotionForSKU.substring(4)) * (quantity / promoQuantity);
				} else {
					sum += productSKUPrice.getProductSKUPrice().get(product.getProductSKU()) * product.getQuantity();
				}
				break;

			case '+':// A+B
				Character itemA = promotionForSKU.charAt(0);
				Character itemB = promotionForSKU.charAt(2);
				int quantityItemA = product.getQuantity();
				Optional<IncomingCheckoutProduct> nextProduct = checkOutProducts.stream()
						.filter(p -> p.getProductSKU().equals(itemB.toString())).findFirst();
				int quantityItemB = nextProduct.get().getQuantity();
				while (quantityItemA != 0 && quantityItemB != 0) {
					quantityItemA--;
					quantityItemB--;
					sum += Integer.parseInt(promotionForSKU.substring(4));
				}
				if (quantityItemA > 0) {
					sum += quantityItemA * productSKUPrice.getProductSKUPrice().get(itemA.toString());
				} else if (quantityItemB > 0) {
					sum += quantityItemB * productSKUPrice.getProductSKUPrice().get(itemB.toString());
				}

			}
		}
		return sum;
	}

}
