package it.befloral.beans;

public class CartProductBean extends ProductBean {
	private static final long serialVersionUID = 1L;
	int quantity;
	
	public CartProductBean() {
		this.setQuantity(0);
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
		if (!(obj instanceof CartProductBean)) {
			return false;
		}
		// typecast a className in modo da poterli comparare 
		CartProductBean prod = (CartProductBean) obj;
		return prod.getId() == this.getId();
	}
	
	
}	
