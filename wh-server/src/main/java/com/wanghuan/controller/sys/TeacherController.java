package com.wanghuan.controller.sys;

import com.wanghuan.model.sys.PageResult;
import com.wanghuan.model.sys.TeacherEntity;
import com.wanghuan.service.sys.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class TeacherController {

	private Logger log = LoggerFactory.getLogger(TeacherController.class);

	@Resource(name = "TeacherServiceImpl")
	private TeacherService teacherService;

	@GetMapping("/teacher/{teacherName}")
	public TeacherEntity teacherGet(@PathVariable String teacherName) {
		TeacherEntity teacherEntity = teacherService.getTeacherEntityByTeacherName(teacherName);
		log.debug("The method is ending");
		return teacherEntity;
	}

	@GetMapping("/teacher/all")
	public List<TeacherEntity> allTeacher(){

		return teacherService.allTeacher();

	}

	/**
	 * 获取user表数据
	 * 
	 * @param teacherName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/teacher")
	public PageResult teacherList(String teacherName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(teacherService.teacherList(teacherName, pageSize, page * pageSize));
		pageResult.setTotalCount(teacherService.teacherSize(teacherName, pageSize, page * pageSize));
		log.debug("111111111111111");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param teacherEntity
	 * @return
	 */
	@PostMapping("/teacher/insert")
	public TeacherEntity insertTeacher(@RequestBody TeacherEntity teacherEntity) {
		teacherService.insertTeacher(teacherEntity);
		return teacherEntity;
	}

	/**
	 *
	 *
	 * 更新用户信息
	 * 
	 * @param teacherEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/teacher/{id}")
	public TeacherEntity updateTeacher(@RequestBody TeacherEntity teacherEntity, @PathVariable int id) {
		if (teacherEntity.getId() == id) {
			teacherService.updateTeacher(teacherEntity);
			//log.debug(String.valueOf(schoolId));
			log.debug("ddddddddddddd");
		}
		//log.debug(String.valueOf(schoolId));
		log.debug("ddddddddddddddddddddddd");
		return teacherEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/teacher/delete")
	public List<String> deleteTeacher(@RequestBody List<String> groupId) {
		teacherService.deleteTeacher(groupId);
		return groupId;
	}
}
