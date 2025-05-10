
package com.demo.firstSpring.Service;

import java.util.List;


import com.demo.firstSpring.Entity.Cab;

public interface cabService {

	public List<Cab> getcab();

	public Cab getcab(long Id);

	public Cab addcab(Cab course);
	
	public Cab updatecab(Cab course);
	
	public void deletecab(long parseLong);

}

