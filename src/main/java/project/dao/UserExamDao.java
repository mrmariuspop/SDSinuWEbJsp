package project.dao;

import org.springframework.data.repository.CrudRepository;

import project.model.UserExam;

public interface UserExamDao extends CrudRepository<UserExam, Integer> {

}
