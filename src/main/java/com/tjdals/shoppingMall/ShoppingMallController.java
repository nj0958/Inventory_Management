package com.tjdals.shoppingMall;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShoppingMallController {

	
	@Autowired
	ProductRepository ProductRepository;

	


	@GetMapping("/product_save")
	public String product_save() {

		return "product_save";
	}

	@PostMapping("/product_save")
	public String product_save(@RequestParam("ItemNumber") String ItemNumber,
			@RequestParam("productname") String productname, 
			@RequestParam("productprice") String productprice,
			@RequestParam("producPicture") String producPicture) {
		Product_list Product_list = new Product_list();

		Product_list.setItemNumber(ItemNumber);
		Product_list.setProductname(productname);
		Product_list.setProducPicture(producPicture);
		Product_list.setProductprice(productprice);

		ProductRepository.save(Product_list);

		return "redirect:/product_list";
	}

	@GetMapping("/product_list")
	public String product_list(Model model) {
		List<Product_list> product_list = ProductRepository.findAll();

		model.addAttribute("product_list", product_list);
		return "product_list";
	}

	@GetMapping("/product_del/{product_list_id}")
	public String product_del(@PathVariable int product_list_id) {
		ProductRepository.deleteById(product_list_id);

		return "redirect:/product_list";
	}

	@GetMapping("/product_edit/{product_id}")
	public String product_edit(Model model, 
			@PathVariable int product_id
			) {

		Optional<Product_list> Optionalproduct = ProductRepository.findById(product_id);
		Product_list Product_list = new Product_list();
		if (Optionalproduct.isPresent()) {
			Product_list = Optionalproduct.orElse(null);
		}
		model.addAttribute("Product_list", Product_list);
		
		
		return "product_edit";
	}
	@PostMapping("/product_edit")
	public String product_edit_process(
			
			@RequestParam String ItemNumber,
			@RequestParam String productname,
			@RequestParam String productprice,
			@RequestParam String producPicture,
			RedirectAttributes redirectAttributes
			) {
		Product_list Product_list = new Product_list();
	
		Product_list.setItemNumber(ItemNumber);
		Product_list.setProductname(productname);
		Product_list.setProductprice(productprice);
		Product_list.setProducPicture(producPicture);
		
		
		ProductRepository.save(Product_list);
		
		redirectAttributes.addFlashAttribute("db_message", "자료가 수정되었습니다.");
		
		return "redirect:/product_list";
	}

}
