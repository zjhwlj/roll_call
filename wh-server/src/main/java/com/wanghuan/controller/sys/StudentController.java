package com.wanghuan.controller.sys;

import com.wanghuan.model.sys.PageResult;

import com.wanghuan.model.sys.StudentEntity;
import com.wanghuan.service.sys.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class StudentController {

	private Logger log = LoggerFactory.getLogger(StudentController.class);

	@Resource(name = "StudentServiceImpl")
	private StudentService studentService;

	@GetMapping("/student/{studentName}")
	public StudentEntity studentGet(@PathVariable String studentName) {
		StudentEntity studentEntity = studentService.getStudentEntityByStudentName(studentName);
		log.debug("The method is ending");
		return studentEntity;
	}

	@GetMapping("/student/all")
	public List<StudentEntity> allStudent(){

		return studentService.allStudent();

	}

	/**
	 * 获取user表数据
	 * 
	 * @param studentName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/student")
	public PageResult studentList(String studentName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(studentService.studentList(studentName, pageSize, page * pageSize));
		pageResult.setTotalCount(studentService.studentSize(studentName, pageSize, page * pageSize));
		log.debug("111111111111111");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param studentEntity
	 * @return
	 */
	@PostMapping("/student/insert")
	public StudentEntity insertStudent(@RequestBody StudentEntity studentEntity) {
		studentService.insertStudent(studentEntity);
		//log.debug(studentEntity.getAmAfterclasstime());
		return studentEntity;
	}

	/**
	 *
	 *
	 * 更新用户信息
	 * 
	 * @param studentEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/student/{id}")
	public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity, @PathVariable int id) {
		if (studentEntity.getId() == id) {
			studentService.updateStudent(studentEntity);
			log.debug(String.valueOf(id));
			log.debug("ddddddddddddd");
		}
		log.debug(String.valueOf(id));

		return studentEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/student/delete")
	public List<String> deleteStudent(@RequestBody List<String> groupId) {
		studentService.deleteStudent(groupId);
		return groupId;
	}
}
