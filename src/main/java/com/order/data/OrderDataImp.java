package com.order.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		orders.add(new Order(i++, pList1, "Karthi"));
		orders.add(new Order(i++, pList2, "Barnala"));
		orders.add(new Order(i++, pList3, "Aranganathan"));
		orders.add(new Order(i++, pList4, "Sairam"));
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

}
