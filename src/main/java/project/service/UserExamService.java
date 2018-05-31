package project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.ExamDao;
import project.dao.UserDao;
import project.dao.UserExamDao;
import project.dto.UserExamDto;
import project.dto.mapper.UserExamMapper;
import project.model.Exam;
import project.model.User;
import project.model.UserExam;

@Service
public class UserExamService {

	@Autowired
	private UserExamDao userExamDao;
	private UserExamMapper userExamMapper = new UserExamMapper();
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ExamDao examDao;

	public List<UserExamDto> getStudentsForOneExam(int id) {
		List<UserExamDto> userExams = new ArrayList<>();
		for (UserExam userExam : userExamDao.findAll()) {
			if (userExam.getExam().getExamId() == id) {
				UserExamDto userExamDto = userExamMapper.mapToDTO(userExam);
				userExams.add(userExamDto);
			}
		}
		return userExams;
	}

	public List<UserExamDto> getStudentExams(int id) {
		List<UserExamDto> userExams = new ArrayList<>();
		for (UserExam userExam : userExamDao.findAll()) {
			if (userExam.getUser().getUserid() == id) {
				UserExamDto userExamDto = userExamMapper.mapToDTO(userExam);
				userExams.add(userExamDto);
			}
		}
		return userExams;
	}

	public UserExamDto findStudent(int id) {
		UserExam userExam = userExamDao.findOne(id);
		UserExamDto userExamDto = userExamMapper.mapToDTO(userExam);
		return userExamDto;
	}

	public void save(UserExamDto userExamDto) {
		UserExam userExam = userExamMapper.mapToModel(userExamDto);
		userExamDao.save(userExam);
	}

	public void deleteStudent(int id) {
		userExamDao.delete(id);
	}

	public void update(UserExamDto userExamDto) {
		UserExam userExamDb = userExamDao.findOne(userExamDto.getId());
		userExamDb.setGrade(userExamDto.getGrade());
		userExamDao.save(userExamDb);
	}
	public int findExamId(int userExamDtoId){
		UserExam userExamDb = userExamDao.findOne(userExamDtoId);
		return userExamDb.getExam().getExamId();
	}

	public void addStudentToExam(int userid, int examid) {
		User user=userDao.findOne(userid);
		Exam exam=examDao.findOne(examid);
		UserExam userExam=new UserExam();
		userExam.setUser(user);
		userExam.setExam(exam);
		userExamDao.save(userExam);
	}

}
