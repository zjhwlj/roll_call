package com.wanghuan.controller.sys;

import com.wanghuan.model.sys.PageResult;
import com.wanghuan.model.sys.SignEntity;
import com.wanghuan.model.sys.UserEntity;
import com.wanghuan.service.sys.SignService;
import com.wanghuan.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class SignController {

	private Logger log = LoggerFactory.getLogger(SignController.class);

	@Resource(name = "signServiceImpl")
	private SignService signService;

	@GetMapping("/sign/{name}")
	public SignEntity signGet(@PathVariable String name) {
		SignEntity signEntity = signService.getSignEntityByName(name);
		log.debug("The method is ending");
		return signEntity;
	}

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/signs")
	public PageResult signsList(String name, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(signService.signsList(name, pageSize, page * pageSize));
		pageResult.setTotalCount(signService.signsSize(name, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param signEntity
	 * @return
	 */
	@PostMapping("/signs/insert")
	public SignEntity insertSign(@RequestBody SignEntity signEntity) {
		signService.insertSign(signEntity);
		log.debug("The method is ending");
		return signEntity;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param signEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/signs/{id}")
	public SignEntity updateSign(@RequestBody SignEntity signEntity, @PathVariable int id) {
		if (signEntity.getId() == id) {
			signService.updateSign(signEntity);
		}
		log.debug("The method is ending");
		return signEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/signs/delete")
	public List<String> deleteSigns(@RequestBody List<String> groupId) {
		signService.deleteSigns(groupId);
		return groupId;
	}

	@GetMapping("/signs/all")
	public List<SignEntity> allSigns(){

		return signService.allSigns();

	}
}
