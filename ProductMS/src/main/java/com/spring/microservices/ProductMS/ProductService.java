package com.spring.microservices.ProductMS;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.microservices.ProductMS.model.DiscountRequest;
import com.spring.microservices.ProductMS.model.Product;
import com.spring.microservices.ProductMS.model.ProductDTO;
import com.spring.microservices.ProductMS.model.ProductRepository;

@Service
public class ProductService {

	private static Logger log = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	Map<Integer, Product> productSeeds;

	@Autowired
	private ProductRepository repo;

	@LoadBalanced
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	@Lazy
	RestTemplate lbrestTemplate;

	// @Autowired

	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	public List<Product> allProductseeds() {

		return new ArrayList<Product>(productSeeds.values());

	}

	public List<ProductDTO> allProducts() {
		RestTemplate restTemplate = new RestTemplate();

		List<Product> products = repo.findAll();
		List<ProductDTO> enhancedProducts = new ArrayList<ProductDTO>();
		for (Product p : products) {
			DiscountRequest drequest = new DiscountRequest();
			drequest.setCategory(p.getCategory());
			drequest.setMrp(p.getMrp());

			InstanceInfo info = eurekaClient.getNextServerFromEureka("discountms", false);
			URI uri = URI.create("http://" + info.getHostName() + ":" + info.getPort() + "/caldisc");
			double drp = restTemplate.postForObject(uri, drequest, Double.class);
			// double drp = this.restTemplate.getForObject(uri, Double.class);
			ProductDTO pdto = new ProductDTO();
			pdto.setCategory(p.getCategory());
			pdto.setDrp(drp);
			pdto.setId(p.getId());
			pdto.setMrp(p.getMrp());
			pdto.setName(p.getName());
			pdto.setShortDescription(p.getShortDescription());
			pdto.setTags(p.getTags());
			enhancedProducts.add(pdto);
		}
		return enhancedProducts;

	}

	@RequestMapping(method = RequestMethod.GET, path = "/product/{id}")
	public ProductDTO getProduct(@PathVariable(name = "id") Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		ServiceInstance serviceInstance = loadBalancer.choose("discountms");
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/caldisc";
		Optional<Product> opProduct = repo.findById(id);
		Product p = opProduct.get();
		DiscountRequest drequest = new DiscountRequest();
		drequest.setCategory(p.getCategory());
		drequest.setMrp(p.getMrp());
		double drp = restTemplate.postForObject(baseUrl, drequest, Double.class);
		// double drp = this.restTemplate.getForObject(uri, Double.class);
		ProductDTO pdto = new ProductDTO();
		pdto.setCategory(p.getCategory());
		pdto.setDrp(drp);
		pdto.setId(p.getId());
		pdto.setMrp(p.getMrp());
		pdto.setName(p.getName());
		pdto.setShortDescription(p.getShortDescription());
		pdto.setTags(p.getTags());
		return pdto;

	}

	public ProductDTO getProductlb(@PathVariable(name = "id") Integer id) {

		Optional<Product> opProduct = repo.findById(id);
		Product p = opProduct.get();
		DiscountRequest drequest = new DiscountRequest();
		drequest.setCategory(p.getCategory());
		drequest.setMrp(p.getMrp());
		double drp = lbrestTemplate.postForObject("http://discountms/caldisc", drequest, Double.class);
		ProductDTO pdto = new ProductDTO();
		pdto.setCategory(p.getCategory());
		pdto.setDrp(drp);
		pdto.setId(p.getId());
		pdto.setMrp(p.getMrp());
		pdto.setName(p.getName());
		pdto.setShortDescription(p.getShortDescription());
		pdto.setTags(p.getTags());
		return pdto;

	}

	public ProductDTO getProductcb(@PathVariable(name = "id") Integer id) {

		Optional<Product> opProduct = repo.findById(id);
		Product p = opProduct.get();

		return applyDiscount(p);

	}

	@HystrixCommand(commandKey = "APPLY-DISCOUNT-COMMAND", fallbackMethod = "discountFallback", threadPoolKey = "discountFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") })
	public ProductDTO applyDiscount(Product p) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			// InstanceInfo info = eurekaClient.getNextServerFromEureka("discountms",
			// false);
			// URI uri = URI.create("http://" + info.getHostName() + ":" + info.getPort() +
			// "/caldisc");
			DiscountRequest drequest = new DiscountRequest();
			drequest.setCategory(p.getCategory());
			drequest.setMrp(p.getMrp());
			double drp = restTemplate.postForObject("http://localhost:8083/caldisc", drequest, Double.class);
			ProductDTO pdto = new ProductDTO();
			pdto.setCategory(p.getCategory());
			pdto.setDrp(drp);
			pdto.setId(p.getId());
			pdto.setMrp(p.getMrp());
			pdto.setName(p.getName());
			pdto.setShortDescription(p.getShortDescription());
			pdto.setTags(p.getTags());
			return pdto;
		} catch (Exception e) {
			throw e;
		}

	}

	public ProductDTO discountFallback(Product p) {
		ProductDTO pdto = new ProductDTO();
		pdto.setCategory(p.getCategory());
		pdto.setId(p.getId());
		pdto.setMrp(p.getMrp());
		pdto.setName(p.getName());
		pdto.setShortDescription(p.getShortDescription());
		pdto.setTags(p.getTags());
		return pdto;
	}

	@Transactional
	public ResponseEntity<String> allProducts(@RequestBody ProductDTO product) {
		log.debug(product.toString());
		Product p = new Product(product.getId(), product.getName(), product.getShortDescription(),
				product.getCategory(), product.getMrp());
		p.setTags(product.getTags());
		p.setRepo(repo);
		p.saveProduct();
		return ResponseEntity.accepted().build();

	}

}
