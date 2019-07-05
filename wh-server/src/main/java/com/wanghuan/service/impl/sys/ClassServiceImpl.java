package com.wanghuan.service.impl.sys;

import com.wanghuan.dao.ClassDao;
import com.wanghuan.model.sys.ClassEntity;
import com.wanghuan.service.sys.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ClassServiceImpl")
public class ClassServiceImpl implements ClassService{

	private Logger log = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassDao classDao;

	@Override
	public void insert(ClassEntity classEntity) {
		classDao.insert(classEntity);
	}

	@Override
	public void del(ClassEntity classEntity) {
		classDao.del(classEntity);
	}

	@Override
	public ClassEntity getClassEntityByCourseName(String schoolName) {
		return classDao.getClassEntityByCourseName(schoolName);
	}

	@Override
	public List<ClassEntity> classList(String schoolName, int pageSize, int start) {
		return classDao.classList( schoolName,  pageSize,  start);
	}

    @Override
    public Integer classSize(String schoolName, int pageSize, int start){
	return classDao.classSize(schoolName, pageSize, start);
    }
	//@Override
	//public Integer  schoolsSize(String schoolName, int pageSize, int start) {
		//return schoolDao.schoolsSize(schoolName, pageSize, start);
	//}

	@Override
	public void insertClass(ClassEntity schoolEntity){
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		//schoolDao.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(schoolDao.getPassword()));
		classDao.insertClass(schoolEntity);
	}

	@Override
	public void updateClass(ClassEntity classEntity) {
		classDao.updateClass(classEntity);
	}

	@Override
	public void deleteClass(List<String> groupId){
		classDao.deleteClass(groupId);
	}

	@Override
	public List<ClassEntity> allClasss() {
		//log.debug(classDao.allClass().toString());
		//log.debug(schoolDao.allSchools());
		return classDao.allClass();
	}

}
