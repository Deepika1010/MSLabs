package com.spring.microservices.ProductMS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.spring.microservices.ProductMS.model.Product;
import com.spring.microservices.ProductMS.model.ProductCategory;
import com.spring.microservices.ProductMS.model.ProductRepository;
import com.spring.microservices.ProductMS.model.ProductTag;
import com.spring.microservices.ProductMS.model.ProductTagRepository;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ProductMsApplication {

	@Autowired
	private ProductRepository prepo;
	@Autowired
	private ProductTagRepository ptrepo;
	public Map<Integer, Product> productSeeds = new HashMap<Integer, Product>();

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

	@Bean
	Map<Integer, Product> productSeeds() {
		return productSeeds;
	}

	@Value("${greeting}")
	private String greeting;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("\t\n\n" + greeting + "\n\n");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				// System.out.println(beanName);
			}

			seedItUp();

		};
	}


	

	@Transactional
	public void seedItUp() {
		Product p = new Product(1, "Kitchen Chimney", "6x4. Non-exhaust", ProductCategory.KITCHENELECTRONIC, 200.87);
		productSeeds.put(1, p);
		p = new Product(2, "Persian Carpet", "9x9. Handwoven", ProductCategory.FURNISHING, 1000.45);
		productSeeds.put(2, p);
		p = new Product(3, "Space Craft Lego", "580 pieces", ProductCategory.TOY, 776.00);
		productSeeds.put(3, p);
		productSeeds.values().forEach(pr -> {
			pr.setRepo(prepo);
			pr.saveProduct();
		});
		ptrepo.save(new ProductTag(3, "plastic"));
		ptrepo.save(new ProductTag(3, "blocks"));
		ptrepo.save(new ProductTag(3, "shapes"));
		ptrepo.save(new ProductTag(2, "wool"));
		ptrepo.save(new ProductTag(2, "royal"));
		ptrepo.save(new ProductTag(2, "living"));
		ptrepo.save(new ProductTag(1, "kitchen"));
		ptrepo.save(new ProductTag(1, "fresh"));
		ptrepo.save(new ProductTag(1, "carbon"));
	}

}
