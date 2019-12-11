package com.ms.training.SimpleWeb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimplewebRestController {
	@Value("${greeting:Hello MS Default}")
	private String greeting;

	@GetMapping("/greet")
	public ResponseEntity<String> greeting() {
		ResponseEntity<String> response = ResponseEntity.ok().header("x-ms-header", "XULIVTT").body(greeting);

		return response;
	}

}
