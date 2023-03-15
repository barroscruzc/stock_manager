package br.com.univirtus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.univirtus.bo.EntryNoteBO;
import br.com.univirtus.bo.SupplierBO;
import br.com.univirtus.model.EntryNote;

@Controller
@RequestMapping("/entry-notes")
public class EntryNoteController {

	@Autowired
	private EntryNoteBO bo;
	
	@Autowired SupplierBO supplierBO;
	
	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("entryNote", new EntryNote());
		model.addAttribute("suppliers", supplierBO.findAll());
		return new ModelAndView("/entry-notes/form", model);
	}
}
