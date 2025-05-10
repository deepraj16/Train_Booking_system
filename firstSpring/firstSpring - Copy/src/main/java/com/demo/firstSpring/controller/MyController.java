package com.demo.firstSpring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.demo.firstSpring.Entity.Course;
//import com.demo.firstSpring.Service.coursesService;
//import com.demo.firstSpring.dao.CourseDao;

import com.demo.firstSpring.Entity.Login;
import com.demo.firstSpring.Service.loginService;
import com.demo.firstSpring.dao.LoginDao;


import com.demo.firstSpring.Entity.Cab;
import com.demo.firstSpring.Service.cabService;
import com.demo.firstSpring.dao.CabDao;



@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin("http://localhost:4200/")
public class MyController {
	

	@Autowired
	private loginService ls;

	@Autowired
	private cabService cb;

	@GetMapping("/")
	public String home() {
		return "Welcome to course application";
	}
	@GetMapping("/login")
	public List<Login> getlogin(){
		return this.ls.getlogin();
		
	}
	

	@GetMapping("/login/{loginid}")
	public Login getlogin(@PathVariable String loginid) {
		return this.ls.getlogin(Long.parseLong(loginid));
	}
	
	@PostMapping("/login")
	public Login addlogin(@RequestBody Login course) {
		return this.ls.addlogin(course);
		
	}
	 private final List<Login> users = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Login user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required!");
        }
        users.add(user);
        return ResponseEntity.ok("User added successfully");
    }

	@PutMapping("/login")
	public Login updatelogin(@RequestBody Login course) {
		return this.ls.updatelogin(course);
	}
	
	@DeleteMapping("/login/{login}")
	public ResponseEntity<HttpStatus> deletelogin
	(@PathVariable String courseId) {
		try {
			this.ls.deletelogin(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>
			(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("/cab")
	public List<Cab> getcab(){
		return this.cb.getcab();
		
	}
	

	@GetMapping("/cab/{id}")
	public Cab getcCab(@PathVariable String id) {
		return this.cb.getcab(Long.parseLong(id));
	}
		


}


