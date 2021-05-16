package it.befloral.beans;

public class CartProduct {
	private static final long serialVersionUID = 1L;
	int quantity;
	private Product product;

	public CartProduct(Product product) {
		super();
		this.setQuantity(0);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addProd() {
		this.setQuantity(this.getQuantity() + 1);
	}

	public void delProd() {
		this.setQuantity(this.getQuantity() - 1);
	}

	@Override
	public boolean equals(Object obj) {
		// Se e' lo stesso oggetto ritorna true
		if (obj == this) {
			return true;
		}
		/* Se non e' un istanza di className o obj e' null, ritorna false */
		if (!(obj instanceof CartProduct)) {
			return false;
		}
		// typecast a className in modo da poterli comparare
		CartProduct prod = (CartProduct) obj;
		return prod.getId() == this.getId();
	}

	public int getId() {
		return this.product.getId();
	}

	public String getTotalPriceToString() {
		return String.format("%.2f", getTotalPrice());
	}

	public double getTotalPrice() {
		return this.product.getPrice() * this.getQuantity();
	}

}
