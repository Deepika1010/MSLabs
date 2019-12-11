package com.spring.microservices.ProductMS.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product", path = "restproduct",exported = true)
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
