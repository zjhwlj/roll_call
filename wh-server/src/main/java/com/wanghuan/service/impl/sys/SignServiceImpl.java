package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.SignDao;
import com.wanghuan.dao.UserDao;
import com.wanghuan.model.sys.SignEntity;
import com.wanghuan.model.sys.UserEntity;
import com.wanghuan.service.sys.SignService;
import com.wanghuan.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "signServiceImpl")
public class SignServiceImpl implements SignService {

	@Autowired
	private SignDao signDao;
	
	@Override
	public void insert(SignEntity signEntity) {
		signDao.insert(signEntity);
	}

	@Override
	public void del(SignEntity signEntity) {
		signDao.del(signEntity);
	}

	@Override
	public SignEntity getSignEntityByName(String loginName) {
		return signDao.getSignEntityByName(loginName);
	}

	@Override
	public List<SignEntity> signsList(String loginName, int pageSize, int start) {
		return signDao.signsList( loginName,  pageSize,  start);
	}

	@Override
	public Integer signsSize(String loginName, int pageSize, int start) {
		return signDao.signsSize(loginName, pageSize, start);
	}

	@Override
	public void insertSign(SignEntity signEntity) {

		signDao.insertSign(signEntity);
	}

	@Override
	public void updateSign(SignEntity signEntity) {
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		//signEntity.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(signEntity.getPassword()));
		signDao.updateSign(signEntity);
	}

	@Override
	public void deleteSigns(List<String> groupId) {
		signDao.deleteSigns(groupId);
	}

	@Override
	public List<SignEntity> allSigns() {
		//log.debug(signDao.allSchools().toString());
		//log.debug(schoolDao.allSchools());
		return signDao.allSigns();
	}

}
