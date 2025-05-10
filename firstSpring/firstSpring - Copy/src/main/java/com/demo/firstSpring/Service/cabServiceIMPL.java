package com.demo.firstSpring.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.firstSpring.Entity.Cab;
import com.demo.firstSpring.dao.CabDao;

@Service
public class cabServiceIMPL implements cabService {

	@Autowired
	private CabDao courseDao;
	
	
	@Override
	public List<Cab> getcab() {
				return courseDao.findAll();
	}
	@Override
	public Cab getcab(long courseId) {
		
		return courseDao.findById(courseId).get();
	}
	@Override
	public Cab addcab(Cab course) {
	      courseDao.save(course);
		return course;
	}
	@Override
	public Cab updatecab(Cab course) {
	courseDao.save(course);
		return course;
	}
	@Override
	public void deletecab(long parseLong) {
		Cab entity=courseDao.getOne(parseLong);
		courseDao.delete(entity);
	}

}

