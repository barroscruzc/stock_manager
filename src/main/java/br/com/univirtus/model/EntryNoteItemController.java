package br.com.univirtus.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.univirtus.bo.EntryNoteBO;
import br.com.univirtus.bo.EntryNoteItemBO;
import br.com.univirtus.bo.ProductBO;
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
		
		//Verify product uniqueness
		if(bo.hasItem(item)) {
			result.rejectValue("product", "duplicate");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("products", productBO.findAll());
			return "/entry-note-item/form";
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
		return "redirect:/entry-note/edit/" + entryNoteId;
	}
}
