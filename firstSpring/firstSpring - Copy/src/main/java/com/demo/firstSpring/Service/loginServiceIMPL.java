package com.demo.firstSpring.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.firstSpring.Entity.Login;
import com.demo.firstSpring.dao.LoginDao;

@Service
public class loginServiceIMPL implements loginService {

	@Autowired
	private LoginDao courseDao;
	
	
	@Override
	public List<Login> getlogin() {
				return courseDao.findAll();
	}
	@Override
	public Login getlogin(long courseId) {
		
		return courseDao.findById(courseId).get();
	}
	@Override
	public Login addlogin(Login course) {
	      courseDao.save(course);
		return course;
	}
	@Override
	public Login updatelogin(Login course) {
	courseDao.save(course);
		return course;
	}
	@Override
	public void deletelogin(long parseLong) {
		Login entity=courseDao.getOne(parseLong);
		courseDao.delete(entity);
	}

}

