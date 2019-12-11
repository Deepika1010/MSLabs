package com.spring.microservices.ProductMS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservices.ProductMS.model.MinionsLibrary;
import com.spring.microservices.ProductMS.model.Product;
import com.spring.microservices.ProductMS.model.ProductDTO;

//@EnableDiscoveryClient
@RestController
@RefreshScope
//@RibbonClient(name = "SPRING-CLOUD-EUREKA-RIBBON")
@Service
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	@Autowired
	MinionsLibrary library;

	/*
	 * @Bean RestTemplate getRestTemplate() { return new RestTemplate(); }
	 */

	@Value("${greeting}")
	private String greeting;

	@RequestMapping(method = RequestMethod.GET, path = "/productseeds")
	public List<Product> allProductseeds(HttpServletRequest request) {

		return productService.allProductseeds();

	}

	@RequestMapping(method = RequestMethod.GET, path = "/product")
	public List<ProductDTO> allProducts(HttpServletRequest request) {
		return productService.allProducts();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/product/{id}")
	public ProductDTO getProduct(@PathVariable(name = "id") Integer id) {
		return productService.getProduct(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/product/lb/{id}")
	public ProductDTO getProductlb(@PathVariable(name = "id") Integer id) {

		return productService.getProductlb(id);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/product/cb/{id}")
	public ProductDTO getProductcb(@PathVariable(name = "id") Integer id) {

		return productService.getProductcb(id);

	}

	
	@RequestMapping(method = RequestMethod.PUT, path = "/product")
	@Transactional
	public ResponseEntity<String> allProducts(@RequestBody ProductDTO product) {
		return productService.allProducts(product);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public String ping(HttpServletRequest request) {
		log.info("\n" + request.getHeaderNames());
		return "|PONG| ::  " + greeting;

	}

	@RequestMapping("/minion/fragment/{name}")
	public String getMinion(@PathVariable String name) {
		log.debug("fragment-request");
		return library.getMinion(name);
	}

}
