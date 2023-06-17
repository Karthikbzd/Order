package com.order.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.order.implementation.OrderImp;
import com.order.model.Order;
import com.order.model.Product;

@Service("OrderImp")
public class OrderDataImp implements OrderImp {

	private static final Logger log = LogManager.getLogger(OrderDataImp.class);

	static List<Order> orders = new ArrayList<>();

	static {
		int i = 1;
		List<Product> pList1 = ProductDataImp.products.subList(0, 2);
		List<Product> pList2 = ProductDataImp.products.subList(2, 3);
		List<Product> pList3 = ProductDataImp.products.subList(3, 5);
		List<Product> pList4 = ProductDataImp.products.subList(0, 5);

		orders.add(new Order(i++, "Karthi", pList1));
		orders.add(new Order(i++, "Barnala", pList2));
		orders.add(new Order(i++, "Aranganathan", pList3));
		orders.add(new Order(i++, "Sairam", pList4));
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public boolean placeOrders(Order order) {
		boolean status = false;
		int actual = orders.size();
		order.setOrderId(actual + 1);
		orders.add(order);
		if (orders.size() > actual)
			status = true;
		return status;
	}

	@Override
	public Order getOrder(int orderId) {
		log.info(orderId);
		Optional<Order> order = orders.stream().filter(x -> x.getOrderId() == orderId).findFirst();
		return (order.isPresent()) ? order.get() : null;
	}
	@Override
	public List<Order> getOrdersByProductName(String productName) {
		log.info(productName);
		List<Order> ordersList = orders.stream().filter(order->order.getProducts().stream()
				.anyMatch(product->product.getName().equalsIgnoreCase(productName)))
				.collect(Collectors.toList());
		return (!ordersList.isEmpty()) ? ordersList : null;
	}

}
