package ca.sheridancollege.tanciong.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.tanciong.beans.Shopping;
import ca.sheridancollege.tanciong.database.DatabaseAccess;

@Controller
public class ShoppingController {
	
	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("shopping", new Shopping());
		model.addAttribute("shoppingList", da.getShoppingList()); 
		return "index";
	}
	
	@PostMapping("/insertShopping")
	public String insertShopping(Model model, @ModelAttribute Shopping shopping) {
		da.insertShopping(shopping);
		model.addAttribute("shopping", new Shopping());
		model.addAttribute("shoppingList", da.getShoppingList());
		return "index";
	}	
	
	@GetMapping("/editShoppingById/{id}")
	public String editShopping(Model model, @PathVariable Long id) {
		Shopping Shopping = da.getShoppingListById(id).get(0);
		model.addAttribute("shopping", Shopping);
		da.deleteShoppingById(id);
		model.addAttribute("shoppingList", da.getShoppingList());
		return "index";
		
	}	
	
	@GetMapping("/deleteShoppingById/{id}")
	public String deleteShoppingById(Model model, @PathVariable Long id) {
		da.deleteShoppingById(id);
		model.addAttribute("shopping", new Shopping());
		model.addAttribute("shoppingList", da.getShoppingList());
		return "index";
	}
	
	@GetMapping("/filterShoppingListByStoreName")
	public String filterShoppingListByStoreName(Model model, @RequestParam String storeName) {
		
		model.addAttribute("shopping", new Shopping());
		model.addAttribute("shoppingList", da.filterShoppingListByStoreName(storeName));
		return "index";
	}
	
	@GetMapping("/orderByStoreName")
	public String orderByStoreName(Model model) {
		
		model.addAttribute("shopping", new Shopping());
		
		model.addAttribute("shoppingList", da.orderByStoreName());
		
		return "index";
	}
}
