package it.befloral.model;
import it.befloral.beans.*;
import java.util.*;

public class Cart {
	List<CartProductBean> products;
	
	public Cart() {
		products = new ArrayList<CartProductBean>();
	}
	
	public void addProduct(ProductBean product) {
		CartProductBean cartProd= new CartProductBean(product);
		if(products.contains(cartProd)) {
			var prod = products.get(products.indexOf(cartProd));
			prod.setQuantity(prod.getQuantity() + 1);
		} else {
			
			cartProd.setQuantity(1);
			products.add(cartProd);
		}
	}
	
	public void deleteProduct(ProductBean product) {
		CartProductBean cartProd= new CartProductBean(product);
		for(CartProductBean prod : products) {	
			if(prod.equals(cartProd)) {
				products.remove(prod);
				break;
			}
		}
	}
	
	public List<CartProductBean> getProducts() {
		return products;
	}
	
	public int getTotalProductsQuantity() {
		var sum = 0;
		for(CartProductBean prod : products) {
			sum += prod.getQuantity();
		}
		return sum;
	}
	
	public double getTotalPrice() {
		var sum = 0;
		for(CartProductBean prod : products) {
			sum += prod.getTotalPrice();
		}
		return sum;
	}

	public void deleteAll() {
		products=new ArrayList<CartProductBean>();
		
	}

	public void updateProduct(ProductBean product, int quantity) {
		CartProductBean cartProd= new CartProductBean(product);
		if(products.contains(cartProd)) {
			var prod = products.get(products.indexOf(cartProd));
			prod.setQuantity(quantity);
		}
	}
	
		
	
	
	
}
