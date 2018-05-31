package project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.dto.UserExamDto;
import project.service.UserExamService;
import project.service.UserService;

@RequestMapping("/professor/studentsExam")
@Controller
public class StudentsExamController {
	@Autowired
	private UserExamService userExamService;
	
	@Autowired
	private UserService userService;	
	
	@GetMapping
	public String allExams(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("studentsExam", userExamService.getStudentsForOneExam(id));
		request.setAttribute("examid", id);
		request.setAttribute("mode", "MODE_MAIN_PAGE");
		return "studentsExam";
	}
	@GetMapping("/add_student")
	public String allStudents(@RequestParam int examid,HttpServletRequest request) {
		request.setAttribute("students", userService.getStudentsWhichNotAttendTheExam(examid));
		request.setAttribute("examid", examid);
		request.setAttribute("mode", "MODE_ADD");
		return "studentsExam";
	}
	@GetMapping("/add_student_to_exam")
	public String addStudent(@RequestParam int examid,@RequestParam int userid,HttpServletRequest request) {
		userExamService.addStudentToExam(userid,examid);
		return "redirect:/professor/studentsExam/add_student?examid="+examid;
	}
	
	@GetMapping("/update_student")
	public String updateAnnouncement(@RequestParam int examid,@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("userExamDto", userExamService.findStudent(id));
		request.setAttribute("examid", examid);
		request.setAttribute("mode", "MODE_UPDATE");
		return "studentsExam";
	}
	
	@PostMapping("/update_student")
	public String updateAnnouncement(@ModelAttribute("userExamDto") UserExamDto userExamDto, HttpServletRequest request) {
		userExamService.update(userExamDto);
		return "redirect:/professor/studentsExam?id="+userExamService.findExamId(userExamDto.getId());
	}
	
	@GetMapping("/delete_student")
	public String deleteAnnouncement(@RequestParam int id, HttpServletRequest request) {
		int examid=userExamService.findExamId(id);
		userExamService.deleteStudent(id);
		
		return "redirect:/professor/studentsExam?id="+examid;
	}
	
}
