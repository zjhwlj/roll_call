package com.wanghuan.controller.sys;

import com.wanghuan.model.sys.ClassEntity;
import com.wanghuan.model.sys.PageResult;

import com.wanghuan.service.sys.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class ClassController {

	private Logger log = LoggerFactory.getLogger(ClassController.class);

	@Resource(name = "ClassServiceImpl")
	private ClassService classService;

	@GetMapping("/course/{courseName}")
	public ClassEntity courseGet(@PathVariable String courseName) {
		ClassEntity classEntity = classService.getClassEntityByCourseName(courseName);
		log.debug("The method is ending");
		return classEntity;
	}

	@GetMapping("/course/all")
	public List<ClassEntity> allClasss(){

		return classService.allClasss();

	}

	/**
	 * 获取user表数据
	 * 
	 * @param courseName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/course")
	public PageResult coursesList(String courseName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(classService.classList(courseName, pageSize, page * pageSize));
		pageResult.setTotalCount(classService.classSize(courseName, pageSize, page * pageSize));
		log.debug("111111111111111");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param schoolEntity
	 * @return
	 */
	@PostMapping("/course/insert")
	public ClassEntity insertClass(@RequestBody ClassEntity classEntity) {
		classService.insertClass(classEntity);
		//log.debug(schoolEntity.getAmAfterclasstime());
		return classEntity;
	}

	/**
	 *
	 *
	 * 更新用户信息
	 * 
	 * @param schoolEntity
	 * @param schoolId
	 * @return
	 */
	@PutMapping("/course/{id}")
	public ClassEntity updateClass(@RequestBody ClassEntity classEntity, @PathVariable int id) {
		if (classEntity.getId() == id) {
			classService.updateClass(classEntity);
			//log.debug(String.valueOf(schoolId));
			log.debug("ddddddddddddd");
		}
		log.debug(String.valueOf(id));
		log.debug("ddddddddddddddddddddddd");
		return classEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/course/delete")
	public List<String> deleteClasss(@RequestBody List<String> groupId) {
		classService.deleteClass(groupId);
		return groupId;
	}
}
