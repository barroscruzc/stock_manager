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
import br.com.univirtus.bo.EntryNoteItemBO;
import br.com.univirtus.bo.ProductBO;
import br.com.univirtus.model.EntryNote;
import br.com.univirtus.model.EntryNoteItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path="/entry-note-item")
public class EntryNoteItemController {

	
	@Autowired
	EntryNoteItemBO bo;
	
	@Autowired
	ProductBO productBO;
	
	@Autowired
	EntryNoteBO entryNoteBO;
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute EntryNoteItem item, BindingResult result, RedirectAttributes attr, ModelMap model) {
		//Product validation
		Long productId = item.getProduct().getId();
		if(productId == null) {
			result.rejectValue("product", "field.required");
		}
		
		if (result.hasErrors()) {
			return "redirect:/entry-notes/" + item.getEntryNote().getId() + "/item";
		}
		
		EntryNote entryNote = entryNoteBO.searchById(item.getEntryNote().getId());
		item.setEntryNote(entryNote);
		
		if(item.getId() == null) {
			bo.insert(item);
			attr.addFlashAttribute("feedback", "Produto adicionado com sucesso!");
		} else {
			bo.update(item);
			attr.addFlashAttribute("feedback", "Produto atualizado com sucesso!");
		}
		
		Long entryNoteId = item.getEntryNote().getId();
		return "redirect:/entry-notes/edit/" + entryNoteId;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("item", bo.searchById(id));
		model.addAttribute("products", productBO.findAll());
		return new ModelAndView("/entry-note-item/form", model);
	}
	
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		Long entryNoteId = 0L;
		EntryNoteItem item = bo.searchById(id);
		entryNoteId = item.getEntryNote().getId();
		bo.remove(item);
		attr.addAttribute("feedback", "Item removido com sucesso");
		return "redirect:/entry-notes/edit/" + entryNoteId;
	}
}
