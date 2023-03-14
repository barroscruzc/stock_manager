package br.com.univirtus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.univirtus.bo.CategoryBO;
import br.com.univirtus.bo.ProductBO;
import br.com.univirtus.model.Product;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductBO bo;
	
	@Autowired
	private CategoryBO categoryBO;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryBO.findAll());
		return new ModelAndView("/products/form", model);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute Product product, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryBO.findAll());
			return "products/form";
		} else if (product.getId() == null) {
			bo.insert(product);
			attr.addFlashAttribute("feedback", "Produto cadastrado com sucesso!");
		} else {
			bo.update(product);
			attr.addFlashAttribute("feedback", "Produto atualizado com sucesso!");
		}
		return "redirect:/products";
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("products", bo.findAll());
		return new ModelAndView("/products/list", model);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("product", bo.searchById(id));
		model.addAttribute("categories", categoryBO.findAll());
		return new ModelAndView("/products/form", model);
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.GET)
	public String activate(@PathVariable("id") Long id, ModelMap model) {
		Product product = bo.searchById(id);
		bo.activate(product);
		return "redirect:/products";
	}

	@RequestMapping(value = "/deactivate/{id}", method = RequestMethod.GET)
	public String deactivate(@PathVariable("id") Long id, ModelMap model) {
		Product product = bo.searchById(id);
		bo.deactivate(product);
		return "redirect:/products";
	}

}
