package com.order.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {

	private int orderId;
	private String customerName;
	private List<Product> products;
	

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", products=" + products + ", customerName=" + customerName + "]";
	}

}
