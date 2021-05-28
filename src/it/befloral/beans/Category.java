package it.befloral.beans;

import java.util.ArrayList;

public class Category {

	private int id;
	private String name,description,metaKeywords;
	
	private ArrayList<Integer> productsId;
	
	public Category() {
		productsId= new ArrayList<Integer>();
	}
	
	public void addProductId(int code) {
		if(productsId.contains(code))
			productsId.add(code) ;
	}
	
		
	public boolean isProductBelongs(int idProduct) {
		return productsId.contains(idProduct) ? true : false;
	}
	
	
	public ArrayList<Integer> getProductsId() {
		return productsId;
	}

	public void setProductsId(ArrayList<Integer> productsId) {
		this.productsId = productsId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	
	
	
	
	
	public Category(int id, String name, String description, String metaKeywords, String metaTitle,
			String metaDescription) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.metaKeywords = metaKeywords;

		productsId= new ArrayList<Integer>();
	}
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;

		result = prime * result + ((metaKeywords == null) ? 0 : metaKeywords.hashCode());

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (metaKeywords == null) {
			if (other.metaKeywords != null)
				return false;
		} else if (!metaKeywords.equals(other.metaKeywords))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", metaKeywords="
				+ metaKeywords + "]";
	}

	
	
	
	
	
}
