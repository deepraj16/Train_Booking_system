
package com.demo.firstSpring.Service;

import java.util.List;


import com.demo.firstSpring.Entity.Login;

public interface loginService {

	public List<Login> getlogin();

	public Login getlogin(long courseId);

	public Login addlogin(Login course);
	
	public Login updatelogin(Login course);
	
	public void deletelogin(long parseLong);

}
