package com.company.base.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.base.model.Url;
import com.company.base.model.UrlItem;
import com.company.base.service.UrlRetrieverService;

@RestController
public class UrlRetrieverController {

	private UrlRetrieverService service;
	
	@Autowired
	public UrlRetrieverController(UrlRetrieverService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("urls", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/showItems/{id}")
	public ModelAndView findItemsForAll(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("/url-items");
		List<UrlItem> items = service.findItemsByUrlId(id);
		mv.addObject("urlItems",items);
		
		return mv;
	}
	
	@GetMapping("/showform")
    public ModelAndView showSignUpForm(Url url) {
		ModelAndView mv = new ModelAndView("/add-url");
		mv.addObject("url", url);
		
		return mv;
	}
	
	@PostMapping("/urlsave")
	public ModelAndView saveUrl(@Valid Url url, BindingResult result, Model model) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/url-retriever-error");
            return mv;
        }

		service.save(url);
			
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("urls", service.findAll());
				
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) throws IllegalArgumentException {
	    Url url = service.findById(id)
	    		.orElseThrow(() -> new IllegalArgumentException("Invalid url Id:" + id));
	     
	    model.addAttribute("url", url);
	    
	    return "update-url";
	}
	
	@PostMapping("/update/{id}")
	public String updateUrl(@PathVariable("id") long id, @Valid Url url, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        url.setId(id);
	        return "update-url";
	    }
	         
	    service.save(url);
	    
	    model.addAttribute("urls", service.findAll());
	    
	    return "index";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUrl(@PathVariable("id") long id, Model model) {
	    Url url = service.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid url Id:" + id));
	    
	    service.delete(url);
	    
	    model.addAttribute("urls", service.findAll());
	    
	    return "index";
	}
}
