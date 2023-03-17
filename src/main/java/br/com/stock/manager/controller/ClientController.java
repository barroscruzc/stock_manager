package br.com.stock.manager.controller;

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

import br.com.stock.manager.bo.ClientBO;
import br.com.stock.manager.model.Client;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientBO bo;
	
	@RequestMapping(path = "/new", method=RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("client", new Client());
		return new ModelAndView("/clients/form", model);
	}
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute Client client, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "clients/form";
		}
		else if (client.getId() == null) {
			bo.insert(client);
			attr.addFlashAttribute("feedback", "Cliente cadastrado com sucesso!");
		} else {
			bo.update(client);
			attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso!");
		}
		return "redirect:/clients";
	}
	
	@RequestMapping(path = "", method=RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("clients", bo.findAll());
		return new ModelAndView("/clients/list", model);
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("client", bo.searchById(id));
		return new ModelAndView("/clients/form", model);
	}
	
	@RequestMapping(value="/activate/{id}", method=RequestMethod.GET)
	public String activate(@PathVariable("id") Long id, ModelMap model) {
		Client client = bo.searchById(id);
		bo.activate(client);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/deactivate/{id}", method=RequestMethod.GET)
	public String deactivate(@PathVariable("id") Long id, ModelMap model) {
		Client client = bo.searchById(id);
		bo.deactivate(client);
		return "redirect:/clients";
	}
}
