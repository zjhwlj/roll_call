package com.wanghuan.service.impl.sys;


import com.wanghuan.dao.TeacherDao;
import com.wanghuan.model.sys.TeacherEntity;
import com.wanghuan.service.sys.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {

	private Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

	@Autowired
	private TeacherDao teacherDao;

	@Override
	public void insert(TeacherEntity teacherEntity) {
		teacherDao.insert(teacherEntity);
	}

	@Override
	public void del(TeacherEntity teacherEntity) {
		teacherDao.del(teacherEntity);
	}

	@Override
	public TeacherEntity getTeacherEntityByTeacherName(String teacherName) {
		return teacherDao.getTeacherEntityByTeacherName(teacherName);
	}

	@Override
	public List<TeacherEntity> teacherList(String teacherName, int pageSize, int start) {
		return teacherDao.teacherList( teacherName,  pageSize,  start);
	}
    @Override
    public Integer teacherSize(String teacherName, int pageSize, int start){
	return teacherDao.teacherSize(teacherName, pageSize, start);
    }
	//@Override
	//public Integer  schoolsSize(String schoolName, int pageSize, int start) {
		//return schoolDao.schoolsSize(schoolName, pageSize, start);
	//}

	@Override
	public void insertTeacher(TeacherEntity teacherEntity){
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		//schoolDao.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(schoolDao.getPassword()));
		teacherDao.insertTeacher(teacherEntity);
	}

	@Override
	public void updateTeacher(TeacherEntity teacherEntity) {
		teacherDao.updateTeacher(teacherEntity);
	}

	@Override
	public void deleteTeacher(List<String> groupId){
		teacherDao.deleteTeacher(groupId);
	}

	@Override
	public List<TeacherEntity> allTeacher() {
		log.debug(teacherDao.allTeacher().toString());
		//log.debug(schoolDao.allSchools());
		return teacherDao.allTeacher();
	}

}
