package project.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.dto.ExamDto;
import project.service.ExamService;

@RequestMapping("/professor")
@Controller
public class ProfessorController {

	@Autowired
	private ExamService examService;
	
	@GetMapping
	public String allExams(HttpServletRequest request) {
		request.setAttribute("exams", examService.getExams());
		request.setAttribute("mode", "MODE_MAIN_PAGE");
		return "professor";
	}
	
	@GetMapping("/add_exam")
	public String addExam(HttpServletRequest request) {
		request.setAttribute("examDto", new ExamDto());
		request.setAttribute("mode", "MODE_ADD");
		return "professor";
	}

	@PostMapping("/add_exam")
	public String addExam(@ModelAttribute("examDto") ExamDto examDto, HttpServletRequest request) {
		DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date examTime=null;
		Date examDate=null;
		try {
			examTime = timeFormat.parse(request.getParameter("examTime") + ":00");
			examDate = dateFormat.parse(request.getParameter("examDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		examDto.setTime(examTime);
		examDto.setDate(examDate);
		examService.save(examDto);
		return "redirect:/professor";
	}
	
	@GetMapping("/update_exam")
	public String updateExam(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("examDto", examService.findExam(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "professor";
	}
	
	@PostMapping("/update_exam")
	public String updateExam(@ModelAttribute("examDto") ExamDto examDto, HttpServletRequest request) {
		DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date examTime=null;
		Date examDate=null;
		try {
			examTime = timeFormat.parse(request.getParameter("examTime") + ":00");
			examDate = dateFormat.parse(request.getParameter("examDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		examDto.setTime(examTime);
		examDto.setDate(examDate);
		examService.save(examDto);
		return "redirect:/professor";
	}
	
	@GetMapping("/delete_exam")
	public String deleteExam(@RequestParam int id, HttpServletRequest request) {
		examService.deleteExam(id);
		return "redirect:/professor";
	}
	
}
