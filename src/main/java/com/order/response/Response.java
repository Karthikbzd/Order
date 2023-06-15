package com.order.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.order.OrderConstants;
import com.order.model.Order;

@Component
public class Response {

	public Map<String, Object> setResponse(boolean status, Optional<Integer> id) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(OrderConstants.STATUS, status);
		if (id.isPresent()) {
			response.put(OrderConstants.MSG, "ID Sholud Not be 0.");
		} else {
			if (!status)
				response.put(OrderConstants.MSG, OrderConstants.NOT_FOUND);
		}
		return response;
	}

	public Map<String, Object> setResponse(boolean status, Order order) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(OrderConstants.STATUS, status);
		if (order!=null) {
			response.put(OrderConstants.OBJECT, order);
		} else {
			if (!status)
				response.put(OrderConstants.MSG, OrderConstants.NOT_FOUND);
		}
		return response;
	}

	public Map<String, Object> setResponse(boolean status, List<Order> list) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(OrderConstants.STATUS, status);
		if (list!=null && !list.isEmpty()) {
			response.put(OrderConstants.OBJECT, list);
		} else {
			if (!status)
				response.put(OrderConstants.MSG, OrderConstants.NOT_FOUND);
		}
		return response;
	}

}
