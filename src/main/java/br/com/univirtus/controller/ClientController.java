package br.com.univirtus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.univirtus.bo.ClientBO;
import br.com.univirtus.model.Client;

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
	public String save(@ModelAttribute Client client) {
		bo.insert(client);
		return "/clients/form";
	}
}
