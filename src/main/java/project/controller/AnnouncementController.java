package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.dto.AnnouncementDto;
import project.service.AnnouncementService;

@RequestMapping("professor/announcement")
@Controller
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;
	
	@GetMapping
	public String allAnnouncements(HttpServletRequest request) {
		request.setAttribute("announcements", announcementService.getAnnouncements());
		request.setAttribute("mode", "MODE_MAIN_PAGE");
		return "announcement";
	}
	
	@GetMapping("/add_announcement")
	public String addAnnouncement(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_ADD");
		return "announcement";
	}

	@PostMapping("/add_announcement")
	public String addAnnouncement(@ModelAttribute("announcementDto") AnnouncementDto announcementDto, HttpServletRequest request) {
		
		announcementService.save(announcementDto);
		return "redirect:/professor/announcement";
	}
	
	@GetMapping("/update_announcement")
	public String updateAnnouncement(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("announcementDto", announcementService.findAnnouncement(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "announcement";
	}
	
	@PostMapping("/update_announcement")
	public String updateAnnouncement(@ModelAttribute("announcementDto") AnnouncementDto announcementDto, HttpServletRequest request) {
		announcementService.save(announcementDto);
		return "redirect:/professor/announcement";
	}
	
	@GetMapping("/delete_announcement")
	public String deleteAnnouncement(@RequestParam int id, HttpServletRequest request) {
		announcementService.deleteAnnouncement(id);
		return "redirect:/professor/announcement";
	}
}
