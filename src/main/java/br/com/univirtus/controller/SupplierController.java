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

import br.com.univirtus.bo.SupplierBO;
import br.com.univirtus.model.Supplier;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	private SupplierBO bo;
	
	@RequestMapping(path = "/new", method=RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("supplier", new Supplier());
		return new ModelAndView("/suppliers/form", model);
	}
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute Supplier supplier, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "suppliers/form";
		} else if (supplier.getId() == null) {
			bo.insert(supplier);
			attr.addFlashAttribute("feedback", "Fornecedor cadastrado com sucesso!");
		} else {
			bo.update(supplier);
			attr.addFlashAttribute("feedback", "Fornecedor atualizado com sucesso!");
		}
		return "redirect:/suppliers";
	}
	
	@RequestMapping(path = "", method=RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("suppliers", bo.findAll());
		return new ModelAndView("/suppliers/list", model);
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("supplier", bo.searchById(id));
		return new ModelAndView("/suppliers;form", model);
	}
	
	@RequestMapping(value="/activate/{id}", method=RequestMethod.GET)
	public String activate(@PathVariable("id") Long id, ModelMap model) {
		Supplier supplier = bo.searchById(id);
		bo.activate(supplier);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/deactivate/{id}", method=RequestMethod.GET)
	public String deactivate(@PathVariable("id") Long id, ModelMap model) {
		Supplier supplier = bo.searchById(id);
		bo.deactivate(supplier);
		return "redirect:/clients";
	}
	
}
