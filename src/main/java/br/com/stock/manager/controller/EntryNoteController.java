package br.com.stock.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.stock.manager.bo.EntryNoteBO;
import br.com.stock.manager.bo.EntryNoteItemBO;
import br.com.stock.manager.bo.ProductBO;
import br.com.stock.manager.bo.SupplierBO;
import br.com.stock.manager.model.EntryNote;
import br.com.stock.manager.model.EntryNoteItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/entry-notes")
@Validated
public class EntryNoteController {

	@Autowired
	private EntryNoteBO bo;
	
	@Autowired 
	private SupplierBO supplierBO;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private EntryNoteItemBO entryNoteItemBO;
	
	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
		model.addAttribute("entryNote", new EntryNote());
		model.addAttribute("suppliers", supplierBO.findAll());
		return new ModelAndView("/entry-notes/form", model);
	}
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute EntryNote entryNote, BindingResult result, RedirectAttributes attr, ModelMap model) {
		
		if(result.hasErrors()) {
			if(entryNote.getId() == null) {
				return "redirect:/entry-notes/new";
			}
			return "redirect:/entry-notes/edit/" + entryNote.getId();
		} else if (entryNote.getId() == null) {
			bo.insert(entryNote);
			attr.addFlashAttribute("feedback", "A nota de entrada gerada com sucesso!");
			return "/entry-notes/form";
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
	
	@RequestMapping(path="/{id}/item", method=RequestMethod.GET)
	public ModelAndView addProduct(@PathVariable("id") Long id, ModelMap model) {
		EntryNoteItem item = new EntryNoteItem();
		EntryNote entryNote = bo.searchById(id);
		item.setEntryNote(entryNote);
		model.addAttribute("item", item);
		model.addAttribute("products", productBO.findAll());
		return new ModelAndView("/entry-note-item/form", model);
	}
	
	@RequestMapping(path="edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		EntryNote entryNote = bo.searchById(id);
		model.addAttribute("entryNoteItem", new EntryNoteItem());
		model.addAttribute("suppliers", supplierBO.findAll());
		model.addAttribute("entryNote", entryNote);
		model.addAttribute("items", entryNoteItemBO.listByEntryNoteId(id));
		return new ModelAndView("/entry-notes/form", model);
	}
	
	@RequestMapping(path="remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		EntryNote entryNote = bo.searchById(id);
		bo.remove(entryNote);
		attr.addFlashAttribute("feedback", "Nota de entrada removida com sucesso!");
		return "redirect:/entry-notes";
	}
}
