package it.befloral.model;

public class Product {


	public Product(int idProduct,String name, String description, String shortDescription,String metaDescription, String metaKeyword,
			double price, String weight, boolean available, double discount, int onSale, int quantity) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.metaDescription = metaDescription;
		this.metaKeyword = metaKeyword;
		this.description = description;
		this.price = price;
		this.weight = weight;
		this.available = available;
		this.discount = discount;
		this.onSale = onSale;
		this.quantity = quantity;
		this.idProduct = idProduct;
	}
	
	public int getId() {
		return idProduct;
	}
	public String getName() {
		return name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	
	public String getMetaKeyword() {
		return metaKeyword;
	}
	public String getDescription() {
		return description;
	}
	public double getPrice() {
		return price;
	}
	public String getWeight() {
		return weight;
	}
	public boolean isAvailable() {
		return available;
	}
	public double getDiscount() {
		return discount;
	}
	public int getOnSale() {
		return onSale;
	}
	public int getQuantity() {
		return quantity;
	}
	private String name;
	private String shortDescription;
	private String metaDescription;
	private String metaKeyword;

	private String description;
	private double price;
	private String weight;
	private boolean available;
	private double discount;
	private int onSale;
	private int quantity;
	private int idProduct;

	
}
