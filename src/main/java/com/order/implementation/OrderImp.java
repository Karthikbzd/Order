package com.order.implementation;

import java.util.List;

import com.order.model.Order;

public interface OrderImp {

	List<Order> getOrders();

	boolean placeOrders(Order order);

	Order getOrder(int orderId);

}
