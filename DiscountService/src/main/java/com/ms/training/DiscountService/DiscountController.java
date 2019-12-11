package com.ms.training.DiscountService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

	Map<ProductCategory, Double> discountMap = new HashMap<ProductCategory, Double>();

	@PostConstruct
	public void initBean() {
		discountMap.put(ProductCategory.FURNISHING, 0.04);
		discountMap.put(ProductCategory.KITCHENELECTRONIC, 0.03);
		discountMap.put(ProductCategory.MEDICALEQUIPMENT, 0.07);
		discountMap.put(ProductCategory.MOBILEPHONE, 0.05);
		discountMap.put(ProductCategory.MUSICINSTRUMENT, 0.09);
		discountMap.put(ProductCategory.TOY, 0.02);

	}

	@RequestMapping(value = "/caldisc", method = RequestMethod.POST)
	public double calculateDiscount(@RequestBody DiscountRequest request) {
		System.out.println(request);
		double discountper = discountMap.get(request.getCategory()) + Math.ceil(Math.random() * 10);
		return Math.round(request.getMrp() - ((discountper / 100) * request.getMrp()));
	}

}
