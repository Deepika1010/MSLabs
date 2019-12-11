package com.spring.microservices.ProductMS.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

	
	private int id;
	private String name;
	private String shortDescription;
	private ProductCategory category;
	private double mrp;
	private double drp;
	private List<ProductTag> tags = new ArrayList<ProductTag>();
	
	
	
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(int id, String name, String shortDescription, ProductCategory category, double mrp,
			List<ProductTag> tags) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.category = category;
		this.mrp = mrp;
		this.tags = tags;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public List<ProductTag> getTags() {
		return tags;
	}
	public void setTags(List<ProductTag> tags) {
		this.tags = tags;
	}
	public double getDrp() {
		return drp;
	}
	public void setDrp(double drp) {
		this.drp = drp;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", category="
				+ category + ", mrp=" + mrp + ", drp=" + drp + ", tags=" + tags + "]";
	}
	

}
