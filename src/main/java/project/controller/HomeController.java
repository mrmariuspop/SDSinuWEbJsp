package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import project.dto.UserDto;
import project.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public String login(@ModelAttribute("user") UserDto userDto, HttpServletRequest request) {
		String checkLogin=userService.login(userDto);
		if(checkLogin.equals("ROLE_STUDENT")){
			int id=userService.findIdByUsername(userDto.getUsername());
			return "redirect:/student?id="+id;
		}
		if(checkLogin.equals("ROLE_PROFESSOR")){
			return "redirect:/professor";
		}
		if(checkLogin.equals("ROLE_ADMIN")){
			return "redirect:/admin";
		}
		request.setAttribute("mode", "MODE_ERROR_PASS");
		return "index";
	}
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
