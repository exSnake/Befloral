package it.befloral.model;
import it.befloral.beans.*;
import java.util.*;

public class Cart {
	List<CartProductBean> products;
	
	public Cart() {
		products = new ArrayList<CartProductBean>();
	}
	
	public void addProduct(ProductBean product) {
		if(products.contains(product)) {
			var prod = products.get(products.indexOf(product));
			prod.setQuantity(prod.getQuantity() + 1);
		} else {
			CartProductBean p = new CartProductBean();
			p.setQuantity(1);
			products.add(p);
		}
	}
	
	public void deleteProduct(ProductBean product) {
		for(CartProductBean prod : products) {
			if(prod.getId() == product.getId()) {
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
	
	
}
