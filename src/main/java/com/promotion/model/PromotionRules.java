package com.promotion.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionRules {
	
	private Map<String,Boolean> engineRules;

	public Map<String, Boolean> getEngineRules() {
		return engineRules;
	}

	public void setEngineRules(Map<String, Boolean> engineRules) {
		this.engineRules = engineRules;
	}

	public PromotionRules() {
		engineRules = new HashMap();
		engineRules.put(EngineRule.PromotionA, false);
		engineRules.put(EngineRule.PromotionB, false);
		engineRules.put(EngineRule.PromotionC, false);
		
	}
	 
	 

}
