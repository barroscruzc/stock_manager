package br.com.stock.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductStockController {

	@RequestMapping(value="/stock", method=RequestMethod.GET)
	public String stock() {
		return "product-stock/list";
	}
}
