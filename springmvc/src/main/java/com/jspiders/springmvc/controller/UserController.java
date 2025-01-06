package com.jspiders.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc.dto.UserDTO;
import com.jspiders.springmvc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/home")
	protected String getHomePage(HttpSession httpSession) {
		UserDTO authenticatedUser = (UserDTO) httpSession.getAttribute("authenticatedUser");
		if (authenticatedUser != null)
			return "home";
		else
			return "login";
	}

	@RequestMapping(path = "/sign-up-page")
	protected String getSignUpPage() {
		return "sign_up";
	}

	@RequestMapping(path = "/add-user", method = RequestMethod.POST)
	protected String addUser(@RequestParam(name = "username") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "mobile") long mobile, @RequestParam(name = "password") String password,
			ModelMap modelMap) {
		boolean userAdded = userService.addUser(name, email, mobile, password);
		if (userAdded) {
			modelMap.addAttribute("message", "User added");
			return "login";
		} else {
			modelMap.addAttribute("message", "Something went wrong");
			return "sign_up";
		}
	}

	@RequestMapping(path = "/login-page")
	protected String getLoginPage() {
		return "login";
	}

	@RequestMapping(path = "/auth-user")
	protected String authUser(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, ModelMap modelMap, HttpSession httpSession) {
		UserDTO authenticatedUser = userService.authUser(email, password);
		if (authenticatedUser != null) {
			httpSession.setAttribute("authenticatedUser", authenticatedUser);
			httpSession.setMaxInactiveInterval(30 * 24 * 60 * 60);
			return "home";
		} else {
			modelMap.addAttribute("message", "Invalid email or password");
			return "login";
		}
	}
	
	@RequestMapping(path = "/edit-user-page")
	protected String getEditUserPage(@RequestParam("id") int id, ModelMap modelMap) {
	    UserDTO user = userService.getUserById(id);
	    modelMap.addAttribute("user", user);
	    return "edit_user";
	}

	@RequestMapping(path = "/edit-user", method = RequestMethod.POST)
	protected String editUser(@RequestParam("id") int id,
	                          @RequestParam("username") String name,
	                          @RequestParam("email") String email,
	                          @RequestParam("mobile") long mobile,
	                          @RequestParam("password") String password,
	                          ModelMap modelMap) {
	    boolean isUpdated = userService.editUser(id, name, email, mobile, password);
	    if (isUpdated) {
	        modelMap.addAttribute("message", "User details updated successfully.");
	        return "home";
	    } else {
	        modelMap.addAttribute("message", "Failed to update user details.");
	        return "edit_user";
	    }
	}

	@RequestMapping(path = "/delete-user", method = RequestMethod.POST)
	protected String deleteUser(@RequestParam("id") int id, ModelMap modelMap) {
	    boolean isDeleted = userService.deleteUser(id);
	    if (isDeleted) {
	        modelMap.addAttribute("message", "User deleted successfully");
	    } else {
	        modelMap.addAttribute("message", "Failed to delete user");
	    }
	    return "redirect:/contacts"; // Redirect to the contacts page
	}


	@RequestMapping(path = "/logout")
	protected String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "login";
	}

}