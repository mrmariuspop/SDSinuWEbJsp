package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.dto.UserDto;
import project.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String allStudents(HttpServletRequest request) {
		request.setAttribute("students", userService.getAllDtoStudents());
		request.setAttribute("mode", "MODE_MAIN_PAGE");
		return "admin";
	}

	@GetMapping("/create_student")
	public String createUser(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_CREATE");
		return "admin";
	}

	@PostMapping("/create_student")
	public String saveUser(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) {
		boolean userExists = false;
		for (UserDto user : userService.getAllUsers()) {
			if (userDto.getUsername().compareTo(user.getUsername()) == 0) {
				userExists = true;
			}
		}
		if (userExists != true) {
			userDto.setRole(userService.findRole("ROLE_STUDENT"));
			userService.save(userDto);			
			return "redirect:/admin";
		} else {

			request.setAttribute("mode", "MODE_CREATE");
			request.setAttribute("errorMessage", "Username already exists");
			return "admin";
		}
	}

	@GetMapping("/update_student")
	public String updateStudent(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("userDto", userService.findUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "admin";
	}

	@PostMapping("/update_student")
	public String updateStudent(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) {
		boolean userExists = false;
		for (UserDto user : userService.getAllUsers()) {
			if (userDto.getUsername().compareTo(user.getUsername()) == 0) {
				userExists = true;
			}
		}
		for (UserDto user : userService.getAllUsers()) {
			if (user.getUserid() == userDto.getUserid() && user.getUsername().equals(userDto.getUsername())) {
				userDto.setRole(userService.findRole("ROLE_STUDENT"));
				userService.save(userDto);
				return "redirect:/admin";
			}
			if (user.getUserid() == userDto.getUserid() && userExists == false) {
				userDto.setRole(userService.findRole("ROLE_STUDENT"));
				userService.save(userDto);
				return "redirect:/admin";
			}
		}
		request.setAttribute("mode", "MODE_UPDATE");
		request.setAttribute("errorMessage", "Username already exists");

		return "admin";
	}

	@GetMapping("/delete_student")
	public String deleteStudent(@RequestParam int id, HttpServletRequest request) {
		userService.delete(id);
		return "redirect:/admin";
	}

	@GetMapping("/professors")
	public String allProfessors(HttpServletRequest request) {
		request.setAttribute("professors", userService.getAllDtoProfessors());
		request.setAttribute("mode", "MODE_MAIN_PAGE_PROFESSOR");
		return "admin";
	}

	@GetMapping("/professors/create_professor")
	public String createProfessor(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_CREATE_PROFESSOR");
		return "admin";
	}

	@PostMapping("/professors/create_professor")
	public String saveProfessor(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) {
		boolean userExists = false;
		for (UserDto user : userService.getAllUsers()) {
			if (userDto.getUsername().compareTo(user.getUsername()) == 0) {
				userExists = true;
			}
		}
		if (userExists != true) {
			userDto.setRole(userService.findRole("ROLE_PROFESSOR"));
			userService.save(userDto);			
			return "redirect:/admin/professors";
		} else {

			request.setAttribute("mode", "MODE_CREATE_PROFESSOR");
			request.setAttribute("errorMessage", "Username already exists");
			return "admin";
		}
	}

	@GetMapping("/professors/update_professor")
	public String updateProfessor(@RequestParam int id, HttpServletRequest request) {

		request.setAttribute("userDto", userService.findUser(id));
		request.setAttribute("mode", "MODE_UPDATE_PROFESSOR");
		return "admin";
	}

	@PostMapping("/professors/update_professor")
	public String updateProfessor(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) {
		boolean userExists = false;
		for (UserDto user : userService.getAllUsers()) {
			if (userDto.getUsername().compareTo(user.getUsername()) == 0) {
				userExists = true;
			}
		}
		for (UserDto user : userService.getAllUsers()) {
			if (user.getUserid() == userDto.getUserid() && user.getUsername().equals(userDto.getUsername())) {
				userDto.setRole(userService.findRole("ROLE_PROFESSOR"));
				userService.save(userDto);
				return "redirect:/admin/professors";
			}
			if (user.getUserid() == userDto.getUserid() && userExists == false) {
				userDto.setRole(userService.findRole("ROLE_PROFESSOR"));
				userService.save(userDto);
				return "redirect:/admin/professors";
			}
		}
		request.setAttribute("mode", "MODE_UPDATE_PROFESSOR");
		request.setAttribute("errorMessage", "Username already exists");

		return "admin";
	}

	@GetMapping("/professors/delete_professor")
	public String deleteProfessor(@RequestParam int id, HttpServletRequest request) {
		userService.delete(id);
		return "redirect:/admin/professors";
	}

}