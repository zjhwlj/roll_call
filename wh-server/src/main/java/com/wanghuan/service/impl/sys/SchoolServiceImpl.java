package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.SchoolDao;
import com.wanghuan.model.sys.SchoolsEntity;
import com.wanghuan.service.sys.SchoolService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "SchoolServiceImpl")
public class SchoolServiceImpl implements SchoolService {

	private Logger log = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Autowired
	private SchoolDao schoolDao;

	@Override
	public void insert(SchoolsEntity schoolEntity) {
		schoolDao.insert(schoolEntity);
	}

	@Override
	public void del(SchoolsEntity schoolEntity) {
		schoolDao.del(schoolEntity);
	}

	@Override
	public SchoolsEntity getSchoolEntityBySchoolName(String schoolName) {
		return schoolDao.getSchoolEntityBySchoolName(schoolName);
	}

	@Override
	public List<SchoolsEntity> schoolList(String schoolName, int pageSize, int start) {
		return schoolDao.schoolsList( schoolName,  pageSize,  start);
	}
    @Override
    public Integer schoolSize(String schoolName, int pageSize, int start){
	return schoolDao.schoolsSize(schoolName, pageSize, start);
    }
	//@Override
	//public Integer  schoolsSize(String schoolName, int pageSize, int start) {
		//return schoolDao.schoolsSize(schoolName, pageSize, start);
	//}

	@Override
	public void insertSchool(SchoolsEntity schoolEntity){
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		//schoolDao.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(schoolDao.getPassword()));
		schoolDao.insertSchool(schoolEntity);
	}

	@Override
	public void updateSchool(SchoolsEntity schoolEntity) {
		schoolDao.updateSchool(schoolEntity);
	}

	@Override
	public void deleteSchool(List<String> groupId){
		schoolDao.deleteSchools(groupId);
	}

	@Override
	public List<SchoolsEntity> allSchools() {
		log.debug(schoolDao.allSchools().toString());
		//log.debug(schoolDao.allSchools());
		return schoolDao.allSchools();
	}

}
