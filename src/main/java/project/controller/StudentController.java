package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.service.AnnouncementService;
import project.service.UserExamService;

@RequestMapping("/student")
@Controller
public class StudentController {

	@Autowired
	private UserExamService userExamService;
	
	@Autowired
	private AnnouncementService announcementService;		
	
	@GetMapping
	public String allExams(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("exams", userExamService.getStudentExams(id));
		request.setAttribute("id", id);
		request.setAttribute("mode", "MODE_MAIN_PAGE");
		return "student";
	}
	
	@GetMapping("/announcements")
	public String allAnnouncements(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("announcements", announcementService.getAnnouncements());
		request.setAttribute("id", id);
		request.setAttribute("mode", "MODE_ANNOUNCEMENT");
		return "student";
	}
}
