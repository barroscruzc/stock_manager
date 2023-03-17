package br.com.stock.manager.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stock.manager.bo.ProductBO;
import br.com.stock.manager.bo.ProductStockBO;
import br.com.stock.manager.model.Product;
import br.com.stock.manager.model.ProductStock;

@RestController
public class ProductStockRestController {

	@Autowired
	private ProductStockBO bo;
	
	@Autowired
	private ProductBO productBO;
	
	
	@RequestMapping(value="/api/stock", method=RequestMethod.GET)
	public List<ProductStock> findAll(){
		return bo.findAll();
	}
	
	@RequestMapping(value="/api/stock/{id}", method=RequestMethod.GET)
	public ProductStock searchById(@PathVariable Long id) {
		return bo.searchById(id);
	}
	
	@RequestMapping(value="/api/stock", method=RequestMethod.POST)
	public ProductStock save(@RequestBody ProductStock productStock) {
		Product product = productBO.searchById(productStock.getProduct().getId());
		productStock.setProduct(product);
		bo.insert(productStock);
		return productStock;
	}
	
	@RequestMapping(value="/api/stock/{id}", method=RequestMethod.PUT)
	public ProductStock update(@PathVariable Long id, @RequestBody ProductStock productStock) {
		productStock.setId(id);
		productStock.setProduct(productBO.searchById(productStock.getProduct().getId()));
		bo.update(productStock);
		return productStock;
	}
	
	@RequestMapping(value="/api/stock/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		ProductStock productStock = bo.searchById(id);
		bo.remove(productStock);
	}
}
