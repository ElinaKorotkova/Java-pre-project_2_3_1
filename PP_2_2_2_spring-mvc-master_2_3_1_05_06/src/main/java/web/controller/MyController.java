package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

@Controller
public class MyController {

	private final UserService userService;


	public MyController(UserService userService){
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String print(ModelMap model){
		model.addAttribute("users",userService.getAllUsers());
		return "users";
	}
	@GetMapping(value = "/users")
	public String showAllUsers(ModelMap model){
		model.addAttribute("users",userService.getAllUsers());
		return "users";
	}

	@GetMapping(value ="/new")
	public String addNewUser(ModelMap model){
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping(value = "/users")
	public String saveUser(@ModelAttribute("user")User user){
		userService.saveUser(user);
		return "redirect:/users";
	}

	@PostMapping(value = "/users{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
		userService.updateUser(user);
		return "redirect:/users";
	}

	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable("id") long id){
		userService.deleteUser(id);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id")int id, ModelMap model){
		model.addAttribute("user", userService.getUser(id));
		return "edit";
	}
	
}