package com.wanghuan.service.impl.sys;


import com.wanghuan.dao.StudentDao;

import com.wanghuan.model.sys.StudentEntity;
import com.wanghuan.service.sys.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void insert(StudentEntity signEntity) {
		studentDao.insert(signEntity);
	}

	@Override
	public void del(StudentEntity signEntity) {
		studentDao.del(signEntity);
	}

	@Override
	public StudentEntity getStudentEntityByStudentName(String studentName) {
		return studentDao.getStudentEntityByStudentName(studentName);
	}

	@Override
	public List<StudentEntity> studentList(String studentName, int pageSize, int start) {
		return studentDao.studentList( studentName,  pageSize,  start);
	}

	@Override
	public Integer studentSize(String studentName, int pageSize, int start) {
		return studentDao.studentSize(studentName, pageSize, start);
	}

	@Override
	public void insertStudent(StudentEntity signEntity) {

		studentDao.insertStudent(signEntity);
	}

	@Override
	public void updateStudent(StudentEntity signEntity) {

		studentDao.updateStudent(signEntity);
	}

	@Override
	public void deleteStudent(List<String> groupId) {
		studentDao.deleteStudent(groupId);
	}

	@Override
	public List<StudentEntity> allStudent() {

		return studentDao.allStudent();
	}


}
