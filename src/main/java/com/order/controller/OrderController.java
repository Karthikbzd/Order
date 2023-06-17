package com.order.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.order.implementation.OrderImp;
import com.order.model.Order;
import com.order.response.Response;

@RestController
@RequestMapping(value = "/OrderCtl")
public class OrderController {

	private static final Logger log = LogManager.getLogger(OrderController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	OrderImp orderImp;

	@Autowired
	Response response;

	Map<String, Object> resMap;

	@GetMapping("/getOrders")
	public ResponseEntity<?> getOrders() {
		log.info("called...");
		boolean status = false;
		List<Order> list = orderImp.getOrders();
		if (!list.isEmpty())
			status = true;
		resMap = response.setResponse(status, list);
		return ResponseEntity.ok(resMap);
	}

	@PostMapping("/placeOrders")
	public ResponseEntity<?> placeOrders(@RequestBody Order order) {
		log.info("called...");
		boolean status = false;

		if (null != order && null != order.getProducts() && !order.getProducts().isEmpty()) {
			status = orderImp.placeOrders(order);
		}
		resMap = new LinkedHashMap<>();
		resMap.put("status", status);
		return ResponseEntity.ok(resMap);
	}

	@GetMapping("/getOrder/{id}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable(value = "id") int orderId) {
		boolean status = false;
		if (orderId == 0) {
			resMap = response.setResponse(status, Optional.empty());
		} else {
			Order order = orderImp.getOrder(orderId);
			resMap = response.setResponse((order != null), order);
		}
		return ResponseEntity.ok(resMap);
	}
	
	@GetMapping("/getOrdersByName/{productName}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable(value = "productName") String productName) {
		boolean status = false;
		if (productName.isEmpty()) {
			resMap = response.setResponse(status, Optional.empty());
		} else {
			List<Order> orders = orderImp.getOrdersByProductName(productName);
			resMap = response.setResponse((orders != null && !orders.isEmpty()), orders);
		}
		return ResponseEntity.ok(resMap);
	}
}
