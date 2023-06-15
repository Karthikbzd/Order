package com.order.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.model.Product;

@Component
public class ProductDataImp {

	static List<Product> products = new ArrayList<>();

	static {
		int i = 1;
		products.add(new Product(i++, "Boost", 95));
		products.add(new Product(i++, "Soap", 46));
		products.add(new Product(i++, "Glass", 10));
		products.add(new Product(i++, "Brush", 20));
		products.add(new Product(i++, "Shampoo", 1.50));
	}

}
