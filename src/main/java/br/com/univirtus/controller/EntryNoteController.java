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

import br.com.univirtus.bo.EntryNoteBO;
import br.com.univirtus.bo.ProductBO;
import br.com.univirtus.bo.SupplierBO;
import br.com.univirtus.model.EntryNote;
import br.com.univirtus.model.EntryNoteItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/entry-notes")
public class EntryNoteController {

	@Autowired
	private EntryNoteBO bo;
	
	@Autowired 
	private SupplierBO supplierBO;
	
	@Autowired
	private ProductBO productBO;
	
	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("entryNote", new EntryNote());
		model.addAttribute("suppliers", supplierBO.findAll());
		return new ModelAndView("/entry-notes/form", model);
	}
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute EntryNote entryNote, BindingResult result, RedirectAttributes attr, ModelMap model) {
		
		if(result.hasErrors()) {
			return "/entry-notes/form";
		} else if (entryNote.getId() == null) {
			bo.insert(entryNote);
			attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrada com sucesso!");
		} else {
			bo.update(entryNote);
			attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso!");
		}
		return "redirect:/entry-notes";
	}
	
	@RequestMapping(path="", method=RequestMethod.GET)
	public ModelAndView list(ModelMap model) {
		model.addAttribute("entryNotes", bo.findAll());
		return new ModelAndView("/entry-notes/list", model);
	}
	
	@RequestMapping(value="/{id}/item", method=RequestMethod.GET)
	public ModelAndView addProduct(@PathVariable("id") Long id, ModelMap model) {
		EntryNoteItem item = new EntryNoteItem();
		EntryNote entryNote = bo.searchById(id);
		item.setEntryNote(entryNote);
		model.addAttribute("item", item);
		model.addAttribute("products", productBO.findAll());
		return new ModelAndView("/entry-note-item/form", model);
	}
}
