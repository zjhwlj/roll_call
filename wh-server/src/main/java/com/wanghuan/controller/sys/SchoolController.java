package com.wanghuan.controller.sys;

import com.wanghuan.model.sys.PageResult;
import com.wanghuan.model.sys.SchoolsEntity;
import com.wanghuan.service.sys.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class SchoolController {

	private Logger log = LoggerFactory.getLogger(SchoolController.class);

	@Resource(name = "SchoolServiceImpl")
	private SchoolService schoolService;

	@GetMapping("/school/{schoolName}")
	public SchoolsEntity schoolGet(@PathVariable String schoolName) {
		SchoolsEntity schoolEntity = schoolService.getSchoolEntityBySchoolName(schoolName);
		log.debug("The method is ending");
		return schoolEntity;
	}

	@GetMapping("/schools/all")
	public List<SchoolsEntity> allSchools(){

		return schoolService.allSchools();

	}

	/**
	 * 获取user表数据
	 * 
	 * @param schoolName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/schools")
	public PageResult schoolsList(String schoolName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(schoolService.schoolList(schoolName, pageSize, page * pageSize));
		pageResult.setTotalCount(schoolService.schoolSize(schoolName, pageSize, page * pageSize));
		log.debug("111111111111111");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param schoolEntity
	 * @return
	 */
	@PostMapping("/schools/insert")
	public SchoolsEntity insertSchool(@RequestBody SchoolsEntity schoolEntity) {
		schoolService.insertSchool(schoolEntity);
		log.debug(schoolEntity.getAmAfterclasstime());
		return schoolEntity;
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
	@PutMapping("/schools/{schoolId}")
	public SchoolsEntity updateSchool(@RequestBody SchoolsEntity schoolEntity, @PathVariable int schoolId) {
		if (schoolEntity.getSchoolId() == schoolId) {
			schoolService.updateSchool(schoolEntity);
			log.debug(String.valueOf(schoolId));
			log.debug("ddddddddddddd");
		}
		//log.debug(String.valueOf(schoolId));
		log.debug("ddddddddddddddddddddddd");
		return schoolEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/schools/delete")
	public List<String> deleteSchools(@RequestBody List<String> groupId) {
		schoolService.deleteSchool(groupId);
		return groupId;
	}
}
